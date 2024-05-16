package com.bjtu.warehousemanagebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bjtu.warehousemanagebackend.domain.Admin;
import com.bjtu.warehousemanagebackend.domain.LoginAdmin;
import com.bjtu.warehousemanagebackend.domain.LoginUser;
import com.bjtu.warehousemanagebackend.domain.User;
import com.bjtu.warehousemanagebackend.exception.ServiceException;
import com.bjtu.warehousemanagebackend.mapper.AdminMapper;
import com.bjtu.warehousemanagebackend.mapper.UserMapper;
import com.bjtu.warehousemanagebackend.utils.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

//,UserDetailsPasswordService
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private  AdminMapper adminMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userNameRole) {
        String name = RegexUtil.parseUserName(userNameRole);
        if(RegexUtil.matchUser(userNameRole)) {
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getName,name);
            User user = userMapper.selectOne(wrapper);
            //如果查询不到数据就通过抛出异常来给出提示
            if (user == null) {
//            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"用户不存在");
                throw new UsernameNotFoundException("用户不存在");
            }
            //根据用户查询权限信息 添加到LoginUser中
            List<String> permissionKeyList =
                    Collections.singletonList(user.getPermission());
            //封装成UserDetails对象返回
            return new LoginUser(user, permissionKeyList);
        }else if(RegexUtil.matchAdmin(userNameRole)){
            LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Admin::getName,name);
            Admin admin = adminMapper.selectOne(wrapper);
            //如果查询不到数据就通过抛出异常来给出提示
            if (admin == null) {
                throw new UsernameNotFoundException("用户不存在");
            }
            //根据用户查询权限信息 添加到LoginUser中
            List<String> permissionKeyList =
                    Collections.singletonList(admin.getPermission());

            //封装成UserDetails对象返回
            return new LoginAdmin(admin, permissionKeyList);
        }else{
            throw new ServiceException(HttpStatus.FORBIDDEN.value(), "后缀错误");
        }
    }
}

