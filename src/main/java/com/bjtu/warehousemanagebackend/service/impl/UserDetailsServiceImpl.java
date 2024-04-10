package com.bjtu.warehousemanagebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjtu.warehousemanagebackend.entity.User;
import com.bjtu.warehousemanagebackend.exception.ServiceException;
import com.bjtu.warehousemanagebackend.mapper.UserMapper;
import com.bjtu.warehousemanagebackend.utils.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bjtu.warehousemanagebackend.service.impl.UserServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl extends ServiceImpl<UserMapper, User> implements UserDetailsService,UserDetailsPasswordService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserServiceImpl userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String id) {
        User user = getById(id);
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new ServiceException(HttpStatus.FORBIDDEN.value(),"用户名或密码错误");
        }
        //根据用户查询权限信息 添加到LoginUser中
        List<String> permissionKeyList =
                Collections.singletonList(
                        String.valueOf(userService.getPermission(user.getId())));

        //封装成UserDetails对象返回
        return new LoginUser(user,permissionKeyList);
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //对用户密码进行加密
        String encodePassword = passwordEncoder.encode(newPassword);

        //更新用户密码
        User nowUser = userService.getById(user.getUsername());
        nowUser.setPassword(encodePassword);
        userService.updateById(nowUser);

        List<String> permissionKeyList =
                Collections.singletonList(
                        String.valueOf(userService.getPermission(nowUser.getId())));

        return new LoginUser(nowUser,permissionKeyList);
    }
}

