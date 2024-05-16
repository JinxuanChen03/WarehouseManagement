package com.bjtu.warehousemanagebackend.service;

import com.bjtu.warehousemanagebackend.controller.dto.LoginDto;
import com.bjtu.warehousemanagebackend.domain.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-05-16
 */
public interface IAdminService extends IService<Admin> {

    void initSuperAdmin();

    boolean hasSuperAdmin();

    List<Map<String, Object>> getAllAdmin();

    Map<String, Object> getOneAdmin(String id);

    void deleteOneAdmin(String id);

    void resetPassword(String id, String password);

    void resetInfo(String id, Admin info);

    Admin getByName(String name);

    void adminRegister(Admin newAdmin);

    HashMap<String,String> loginAdmin(LoginDto dto);

    void logout();
}
