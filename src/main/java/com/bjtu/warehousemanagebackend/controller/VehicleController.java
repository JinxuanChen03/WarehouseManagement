package com.bjtu.warehousemanagebackend.controller;


import com.bjtu.warehousemanagebackend.entity.Vehicle;
import com.bjtu.warehousemanagebackend.service.impl.VehicleServiceImpl;
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
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleServiceImpl vehicleService;

    @PostMapping("")
    public ResponseEntity<Result> save(@RequestBody Vehicle vehicle) {
        vehicleService.save(vehicle);
        return new ResponseEntity<>((Result.success()), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Result> findAll() {
        return new ResponseEntity<>((Result.success(vehicleService.findAll())),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable String id) {
        return new ResponseEntity<>((Result.success(vehicleService.getById(id))),HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Result> delete(String id) {
        vehicleService.removeById(id);
        return new ResponseEntity<>((Result.success()),HttpStatus.OK);
    }
}
