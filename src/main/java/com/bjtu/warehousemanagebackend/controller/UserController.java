package com.bjtu.warehousemanagebackend.controller;


import com.bjtu.warehousemanagebackend.domain.Result;
import com.bjtu.warehousemanagebackend.domain.User;
import com.bjtu.warehousemanagebackend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-05-10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 重置用户密码
     * @param id
     * @param password
     * @return
     */
    @PutMapping("/security/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Result> updateUserPassword(@PathVariable String id,@RequestParam String password){
        userService.resetPassword(id,password);
        return new ResponseEntity<>((Result.success()),HttpStatus.OK);
    }

    /**
     * 重置用户信息（不包括密码）
     * @param id
     * @param info
     * @return
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Result> updateUserInfo(@PathVariable String id, @RequestBody User info){
        userService.resetInfo(id,info);
        return new ResponseEntity<>((Result.success()),HttpStatus.OK);
    }

    /**
     * 获取全部用户
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    public ResponseEntity<Result> getAllUser(){
        return new ResponseEntity<>(Result.success(userService.getAllUser()), HttpStatus.OK);
    }

    /**
     * 获取一个用户
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    public ResponseEntity<Result> getOneUser(@PathVariable String id){
        return new ResponseEntity<>(Result.success(userService.getOneUser(id)), HttpStatus.OK);
    }

    /**
     * 删除一个用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    public ResponseEntity<Result> deleteOneUser(@PathVariable String id) {
        userService.deleteOneUser(id);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

}
