package com.bjtu.warehousemanagebackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjtu.warehousemanagebackend.entity.User;

import java.util.HashMap;
import java.util.List;

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

    User getByName(String name);

    List<User> getAllAdmin();

    User getOneAdmin(String id);

    boolean hasSuperAdmin();

    void initSuperAdmin();

    void resetPassword(String id, String password);

    void deleteOneAdmin(String id);
}
