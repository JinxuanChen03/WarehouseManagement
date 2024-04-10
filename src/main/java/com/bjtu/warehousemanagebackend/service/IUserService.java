package com.bjtu.warehousemanagebackend.service;

import com.bjtu.warehousemanagebackend.annotation.CurrentUser;
import com.bjtu.warehousemanagebackend.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
public interface IUserService extends IService<User> {

    String getPermission(String id);

    void register(User newUser);

    void logout();

    HashMap<String,String> login(User user);

}
