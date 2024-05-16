package com.bjtu.warehousemanagebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjtu.warehousemanagebackend.constants.Role;
import com.bjtu.warehousemanagebackend.controller.dto.LoginDto;
import com.bjtu.warehousemanagebackend.domain.LoginUser;
import com.bjtu.warehousemanagebackend.domain.User;
import com.bjtu.warehousemanagebackend.exception.ServiceException;
import com.bjtu.warehousemanagebackend.mapper.UserMapper;
import com.bjtu.warehousemanagebackend.service.IUserService;
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
 *  服务实现类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-05-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String getPermission(String id){
        return getById(id).getPermission();
    }

    @Override
    public void register(User newUser) {
        if(getByName(newUser.getName()) != null) {
            throw new ServiceException(HttpStatus.FORBIDDEN.value(), "用户名已存在");
        }
        newUser.setPassword(encodePassword(newUser.getPassword()));
        newUser.setCreateAt(DateTimeUtil.getNowTimeString());
        newUser.setPermission(Role.ROLE_USER.getValue());
        save(newUser);
    }

    @Override
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userName = loginUser.getUser().getName();
        redisCache.deleteObject("user login:" + userName);
    }

    @Override
    public HashMap<String,String> login(LoginDto dto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getName()+Role.ROLE_USER.getValue(), dto.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if(Objects.isNull(authenticate)) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED.value(), "用户名或密码错误");
        }

        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userName = loginUser.getUser().getName();
        String jwt = JwtUtil.createJWT(userName,Role.ROLE_USER.getValue());

        //authenticate存入redis
        redisCache.setCacheObject("user login:"+userName,loginUser);

        //把token响应给前端
        HashMap<String,String> map = new HashMap<>();
        User nowUser = loginUser.getUser();
        map.put("token",jwt);
        map.put("permission",nowUser.getPermission());
        map.put("name", nowUser.getName());
        map.put("id", nowUser.getId().toString());

        return map;
    }

    @Override
    public User getByName(String name) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getName,name);
        return getOne(wrapper);
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
        updateById(user);
    }

    @Override
    public void resetInfo(String id, User info) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId,id);
        if(getOne(wrapper) == null){
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"用户不存在");
        }
        info.setId(id);
        updateById(info);
    }

    @Override
    public List<Map<String, Object>> getAllUser() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(User::getId,User::getName);
        return userMapper.selectMaps(wrapper);
    }

    @Override
    public Map<String, Object> getOneUser(String id) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId,id)
                .select(User::getId,User::getName);
        return userMapper.selectMaps(wrapper).get(0);
    }

    @Override
    public void deleteOneUser(String id) {
        if(getOneUser(id) == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
        }
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId,id);
        update(wrapper);
    }
}
