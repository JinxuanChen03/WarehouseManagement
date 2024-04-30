package com.bjtu.warehousemanagebackend.controller;


import com.bjtu.warehousemanagebackend.entity.Driver;
import com.bjtu.warehousemanagebackend.service.impl.DriverServiceImpl;
import com.bjtu.warehousemanagebackend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverServiceImpl driverService;

    @PostMapping("")
    public ResponseEntity<Result> save(@RequestBody Driver driver) {
        driverService.save(driver);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Result> findAll() {
        return new ResponseEntity<>(Result.success(driverService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable String id) {
        return new ResponseEntity<>(Result.success(driverService.getById(id)), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Result> delete(String id) {
        driverService.removeById(id);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

}
