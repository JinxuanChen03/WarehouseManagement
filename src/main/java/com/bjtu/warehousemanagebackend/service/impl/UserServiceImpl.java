package com.bjtu.warehousemanagebackend.service.impl;

import com.bjtu.warehousemanagebackend.entity.User;
import com.bjtu.warehousemanagebackend.exception.ServiceException;
import com.bjtu.warehousemanagebackend.mapper.UserMapper;
import com.bjtu.warehousemanagebackend.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodePassword);
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
                new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword());

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


//    public List<User> getAll() {
//    }
}
