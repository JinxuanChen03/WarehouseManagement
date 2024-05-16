package com.bjtu.warehousemanagebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.bjtu.warehousemanagebackend.controller.dto.LoginDto;
import com.bjtu.warehousemanagebackend.domain.Admin;
import com.bjtu.warehousemanagebackend.constants.Role;
import com.bjtu.warehousemanagebackend.domain.LoginAdmin;
import com.bjtu.warehousemanagebackend.exception.ServiceException;
import com.bjtu.warehousemanagebackend.mapper.AdminMapper;
import com.bjtu.warehousemanagebackend.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjtu.warehousemanagebackend.utils.DateTimeUtil;
import com.bjtu.warehousemanagebackend.utils.JwtUtil;
import com.bjtu.warehousemanagebackend.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-05-11
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void initSuperAdmin() {
        //如果不存在超级管理员，创建一个
        if(!hasSuperAdmin()) {
            Admin superAdmin = new Admin();
            superAdmin.setName("超级管理员");
            superAdmin.setPassword(encodePassword("123"));
            superAdmin.setPermission(Role.ROLE_SUPER_ADMIN.getValue());
            superAdmin.setCreateAt(DateTimeUtil.getNowTimeString());
            save(superAdmin);
        }
    }

    private String encodePassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean hasSuperAdmin(){
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getPermission, Role.ROLE_SUPER_ADMIN.getValue());
        Admin superAdmin = getOne(wrapper);
        return superAdmin != null;
    }

    @Override
    public List<Map<String, Object>> getAllAdmin() {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getPermission, Role.ROLE_ADMIN.getValue())
                .select(Admin::getId,Admin::getName);
        return adminMapper.selectMaps(wrapper);
    }

    @Override
    public Map<String, Object> getOneAdmin(String id) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getPermission, Role.ROLE_ADMIN.getValue())
                .eq(Admin::getId,id)
                .select(Admin::getId,Admin::getName);
        return adminMapper.selectMaps(wrapper).get(0);
    }

    @Override
    public void deleteOneAdmin(String id) {
        if(getOneAdmin(id) == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "管理员不存在");
        }
        LambdaUpdateWrapper<Admin> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Admin::getId,id);
        update(wrapper);
    }

    @Override
    public void resetPassword(String id, String password) {
        //todo：验证
        if(getOneAdmin(id) == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "管理员不存在");
        }
        Admin admin = new Admin();
        admin.setId(id);
        admin.setPassword(encodePassword(password));
        updateById(admin);
    }

    @Override
    public void resetInfo(String id, Admin info) {
        if(getOneAdmin(id) == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "管理员不存在");
        }
        info.setId(id);
        updateById(info);
    }

    @Override
    public Admin getByName(String name){
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getName,name);
        return getOne(wrapper);
    }

    @Override
    public void adminRegister(Admin newAdmin) {
        if(getByName(newAdmin.getName()) != null) {
            throw new ServiceException(HttpStatus.FORBIDDEN.value(), "用户名已存在");
        }
        newAdmin.setPassword(encodePassword(newAdmin.getPassword()));
        newAdmin.setCreateAt(DateTimeUtil.getNowTimeString());
        newAdmin.setPermission(Role.ROLE_ADMIN.getValue());
        save(newAdmin);
    }

    @Override
    public HashMap<String,String> loginAdmin(LoginDto dto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getName()+Role.ROLE_ADMIN.getValue(), dto.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if(Objects.isNull(authenticate)) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED.value(), "用户名或密码错误");
        }

        //使用userid生成token
        LoginAdmin loginAdmin = (LoginAdmin) authenticate.getPrincipal();
        String adminName = loginAdmin.getAdmin().getName();
        String jwt = JwtUtil.createJWT(adminName,Role.ROLE_ADMIN.getValue());

        //authenticate存入redis
        redisCache.setCacheObject("admin login:"+adminName,loginAdmin);

        //把token响应给前端
        HashMap<String,String> map = new HashMap<>();
        Admin nowAdmin = loginAdmin.getAdmin();
        map.put("token",jwt);
        map.put("permission",nowAdmin.getPermission());
        map.put("name", nowAdmin.getName());
        map.put("id", nowAdmin.getId().toString());

        return map;
    }

    @Override
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginAdmin loginAdmin = (LoginAdmin) authentication.getPrincipal();
        String adminName = loginAdmin.getAdmin().getName();
        redisCache.deleteObject("admin login:" + adminName);
    }
}
