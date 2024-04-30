package com.bjtu.warehousemanagebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bjtu.warehousemanagebackend.entity.User;
import com.bjtu.warehousemanagebackend.enums.Role;
import com.bjtu.warehousemanagebackend.exception.ServiceException;
import com.bjtu.warehousemanagebackend.mapper.UserMapper;
import com.bjtu.warehousemanagebackend.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjtu.warehousemanagebackend.utils.DateTimeUtil;
import com.bjtu.warehousemanagebackend.utils.JwtUtil;
import com.bjtu.warehousemanagebackend.utils.LoginUser;
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
import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String getPermission(String id){
        return getById(id).getPermission();
    }

    @Override
    public void register(User newUser) {
        if(getByName(newUser.getName()) != null)
            throw new ServiceException(HttpStatus.FORBIDDEN.value(), "用户名已存在");
        newUser.setPassword(encodePassword(newUser.getPassword()));
        newUser.setCreateAt(DateTimeUtil.getNowTimeString());
        newUser.setPermission(Role.ROLE_USER.getValue());
        save(newUser);
    }

    @Override
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userid = String.valueOf(loginUser.getUser().getId());
        redisCache.deleteObject("login:" + userid);
    }

    @Override
    public HashMap<String,String> login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if(Objects.isNull(authenticate)) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED.value(), "用户名或密码错误");
        }

        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId();
        String jwt = JwtUtil.createJWT(userId);

        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);

        //把token响应给前端
        HashMap<String,String> map = new HashMap<>();
        map.put("token",jwt);
        map.put("permission",loginUser.getUser().getPermission());

        return map;
    }

    @Override
    public User getByName(String name) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getName,name);
        return getOne(wrapper);
    }

    @Override
    public List<User> getAllAdmin() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPermission, Role.ROLE_ADMIN.getValue());
        return listObjs(wrapper);
    }

    @Override
    public User getOneAdmin(String id) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPermission, Role.ROLE_ADMIN.getValue())
                .eq(User::getId,id);
        return getOne(wrapper);
    }

    @Override
    public boolean hasSuperAdmin(){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPermission, Role.ROLE_SUPER_ADMIN.getValue());
        User superAdmin = getOne(wrapper);
        if(superAdmin == null)
            return false;
        else return true;
    }

    @Override
    public void initSuperAdmin() {
        //如果存在超级管理员，禁止操作
        if(hasSuperAdmin())
            throw new ServiceException(HttpStatus.FORBIDDEN.value(), "超级管理员已存在");
        User superAdmin = new User();
        superAdmin.setName("超级管理员");
        superAdmin.setPassword(encodePassword("123"));
        superAdmin.setPermission(Role.ROLE_SUPER_ADMIN.getValue());
        superAdmin.setCreateAt(DateTimeUtil.getNowTimeString());
        save(superAdmin);
    }

    private String encodePassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public void resetPassword(String id, String password) {
        //todo：验证
        User user = new User();
        user.setId(id);
        user.setPassword(encodePassword(password));
        user.setUpdateAt(DateTimeUtil.getNowTimeString());
        updateById(user);
    }

    @Override
    public void deleteOneAdmin(String id) {
        if(getOneAdmin(id) == null)
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "该管理员不存在");
        removeById(id);
    }
}
