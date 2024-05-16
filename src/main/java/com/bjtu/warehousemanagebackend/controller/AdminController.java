package com.bjtu.warehousemanagebackend.controller;


import com.bjtu.warehousemanagebackend.annotation.CurrentUser;
import com.bjtu.warehousemanagebackend.domain.Admin;
import com.bjtu.warehousemanagebackend.domain.Result;
import com.bjtu.warehousemanagebackend.domain.User;
import com.bjtu.warehousemanagebackend.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    /**
     * 注册管理员
     * @param admin
     * @return
     * @throws Exception
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<Result> adminRegister(@RequestBody Admin admin) {
        adminService.adminRegister(admin);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    /**
     * 获取全部管理员
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<Result> getAllAdmin() {
        return new ResponseEntity<>(Result.success(adminService.getAllAdmin()), HttpStatus.OK);
    }

    /**
     * 获取一个管理员
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<Result> getOneAdmin(@PathVariable String id){
        return new ResponseEntity<>(Result.success(adminService.getOneAdmin(id)), HttpStatus.OK);
    }

    /**
     * 重置管理员密码
     * @param id
     * @param password
     * @return
     */
    @PutMapping("/security/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    public ResponseEntity<Result> resetPassword(@PathVariable String id, @RequestParam String password) {
        adminService.resetPassword(id,password);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    /**
     * 重置管理员信息（不包括密码）
     * @param user
     * @param info
     * @return
     */
    @PutMapping
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Result> updateUserInfo(@CurrentUser User user, @RequestBody Admin info){
        adminService.resetInfo(user.getId(),info);
        return new ResponseEntity<>((Result.success()),HttpStatus.OK);
    }

    /**
     * 删除一个管理员
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<Result> deleteOneAdmin(@PathVariable String id) {
        adminService.deleteOneAdmin(id);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

}

