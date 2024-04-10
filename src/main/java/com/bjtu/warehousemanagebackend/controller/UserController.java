package com.bjtu.warehousemanagebackend.controller;

import com.bjtu.warehousemanagebackend.entity.User;
import com.bjtu.warehousemanagebackend.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bjtu.warehousemanagebackend.service.impl.UserServiceImpl;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@RestController
//@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    //注册用户
    @PostMapping
    public ResponseEntity<Result> register(@RequestBody @Valid User newUser){
        userService.register(newUser);
        return new ResponseEntity<>((Result.success()),HttpStatus.OK);
    }

    //更新用户数据
    @PutMapping("/{id}")
    public ResponseEntity<Result> updateUserInfo(@PathVariable String id,@RequestBody @Valid User info){
        info.setId(id);
        userService.updateById(info);
        return new ResponseEntity<>((Result.success()),HttpStatus.OK);
    }

    //todo:修改密码怎么办

    //获取用户列表
    @GetMapping
    public ResponseEntity<Result> getAll(){
        return new ResponseEntity<>(Result.success(userService.list()), HttpStatus.OK);
    }

    //获取用户信息
    //todo:分页
    @GetMapping("/{id}")
    public ResponseEntity<Result> getOne(@PathVariable String id){
        return new ResponseEntity<>(Result.success(userService.getById(id)), HttpStatus.OK);
    }

}
