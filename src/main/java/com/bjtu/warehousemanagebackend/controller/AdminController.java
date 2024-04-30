package com.bjtu.warehousemanagebackend.controller;


import com.bjtu.warehousemanagebackend.entity.Admin;
import com.bjtu.warehousemanagebackend.enums.Role;
import com.bjtu.warehousemanagebackend.service.impl.AdminServiceImpl;
import com.bjtu.warehousemanagebackend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-30
 */
@RestController
@RequestMapping("/admin")
//@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;

//    @GetMapping("hasInit")
//    public boolean hasInit() {
//        return adminService.existsAdminByRoles(Role.ROLE_SUPER_ADMIN.getValue());
//    }

    @PostMapping("/init")
    public ResponseEntity<Result> init(@RequestBody Admin admin) throws Exception {
//        if (adminService.existsAdminByRoles(Role.ROLE_SUPER_ADMIN.getValue())) {
//            throw new Exception("初始化请求错误");
//        }
        admin.setRoles(Role.ROLE_SUPER_ADMIN.getValue());
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
//        return adminService.save(admin);
    }


    @GetMapping("")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_ADMIN')")
    public ResponseEntity<Result> getAll() {
        return new ResponseEntity<>(Result.success(adminService.getAll()), HttpStatus.OK);
    }

    @DeleteMapping("")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_ADMIN')")
    public ResponseEntity<Result> delete(String id) {
        return new ResponseEntity<>(Result.success(adminService.removeById(id)), HttpStatus.OK);
    }

    @PostMapping("")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_ADMIN')")
    public ResponseEntity<Result> save(@RequestBody Admin admin) throws Exception {
        return new ResponseEntity<>(Result.success(adminService.save(admin)), HttpStatus.OK);
    }

//    @PostMapping("/login")
//    public ResponseEntity<Result> loginByEmail(String type, @RequestBody LoginDto dto) throws Exception {
//        Map<String, Object> map = new HashMap<>();
//        Admin admin = type.equals("email") ? adminService.loginByEmail(dto) : adminService.loginByPassword(dto);
//        String token = adminService.createToken(admin,
//                dto.isRemember() ? JwtTokenUtil.REMEMBER_EXPIRATION_TIME : JwtTokenUtil.EXPIRATION_TIME);
//        map.put("admin", admin);
//        map.put("token", token);
//        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
//        return map;
//    }

//    @GetMapping("/sendEmail")
//    public ResponseEntity<Result> sendEmail(String email) throws Exception {
//        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
////        adminService.sendEmail(email);
//    }

}

