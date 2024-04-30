package com.bjtu.warehousemanagebackend.controller;


import com.bjtu.warehousemanagebackend.entity.Employee;
import com.bjtu.warehousemanagebackend.service.impl.EmployeeServiceImpl;
import com.bjtu.warehousemanagebackend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-30
 */
@RestController
@RequestMapping("/employee")
//@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_EMPLOYEE')")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping("")
    public ResponseEntity<Result> findAll() {
        return new ResponseEntity<>(Result.success(employeeService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable String id) {
        return new ResponseEntity<>(Result.success(employeeService.getById(id)), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Result> save(@RequestBody Employee employee) {
        employeeService.save(employee);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Result> update(@RequestBody Employee employee) {
        employeeService.updateById(employee);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Result> delete(String id) {
        employeeService.removeById(id);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

}
