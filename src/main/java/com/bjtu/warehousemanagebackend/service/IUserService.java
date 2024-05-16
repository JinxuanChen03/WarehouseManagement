package com.bjtu.warehousemanagebackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjtu.warehousemanagebackend.controller.dto.LoginDto;
import com.bjtu.warehousemanagebackend.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    HashMap<String,String> login(LoginDto dto);

    User getByName(String name);

    void resetPassword(String id, String password);

    void resetInfo(String id, User info);

    List<Map<String, Object>> getAllUser();

    Map<String, Object> getOneUser(String id);

    void deleteOneUser(String id);
}
