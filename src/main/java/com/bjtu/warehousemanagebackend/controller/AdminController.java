package com.bjtu.warehousemanagebackend.controller;


import com.bjtu.warehousemanagebackend.domain.User;
import com.bjtu.warehousemanagebackend.constants.Role;
import com.bjtu.warehousemanagebackend.service.impl.UserServiceImpl;
import com.bjtu.warehousemanagebackend.utils.DateTimeUtil;
import com.bjtu.warehousemanagebackend.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 初始化添加超级管理员
     * @return
     */
    @PostMapping("/init")
    public ResponseEntity<Result> initSuperAdmin() {
        userService.initSuperAdmin();
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    /**
     * 增加一个管理员
     * @param admin
     * @return
     * @throws Exception
     */
    @PostMapping
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<Result> addOneAdmin(@RequestBody User admin) {
        admin.setPermission(Role.ROLE_ADMIN.getValue());
        admin.setCreateAt(DateTimeUtil.getNowTimeString());
        return new ResponseEntity<>(Result.success(userService.save(admin)), HttpStatus.OK);
    }

    /**
     * 获取全部管理员
     * @return
     */
    @GetMapping
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_ADMIN')")
    public ResponseEntity<Result> getAllAdmin() {
        return new ResponseEntity<>(Result.success(userService.getAllAdmin()), HttpStatus.OK);
    }

    /**
     * 获取一个管理员
     * @param id
     * @return
     */
    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_ADMIN')")
    public ResponseEntity<Result> getOneAdmin(@PathVariable String id){
        return new ResponseEntity<>(Result.success(userService.getOneAdmin(id)), HttpStatus.OK);
    }

    /**
     * 重置管理员密码
     * @param id
     * @param password
     * @return
     */
    @PutMapping
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<Result> resetPassword(String id, String password) {
        userService.resetPassword(id,password);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    /**
     * 删除一个管理员
     * @param id
     * @return
     */
    @DeleteMapping
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<Result> deleteOneAdmin(String id) {
        userService.deleteOneAdmin(id);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

}

