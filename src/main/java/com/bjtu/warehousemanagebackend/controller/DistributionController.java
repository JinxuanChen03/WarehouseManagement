package com.bjtu.warehousemanagebackend.controller;


import com.bjtu.warehousemanagebackend.entity.Distribution;
import com.bjtu.warehousemanagebackend.service.impl.DistributionServiceImpl;
import com.bjtu.warehousemanagebackend.service.impl.DriverServiceImpl;
import com.bjtu.warehousemanagebackend.service.impl.VehicleServiceImpl;
import com.bjtu.warehousemanagebackend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/distribution")
public class DistributionController {

    @Autowired
    private DistributionServiceImpl distributionService;

    @Autowired
    private DriverServiceImpl driverService;

    @Autowired
    private VehicleServiceImpl vehicleService;

    @PostMapping("")
    public ResponseEntity<Result> save(@RequestBody Distribution distribution) throws Exception {
        distributionService.save(distribution);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Result> findAll() {
        return new ResponseEntity<>(Result.success(distributionService.findAll()), HttpStatus.OK);
    }

    @GetMapping("can")
    public ResponseEntity<Result> can() {
        Map<String, Object> map = new HashMap<>();
        map.put("drivers", driverService.findAll());
        map.put("vehicles", vehicleService.findAll());
        return new ResponseEntity<>(Result.success(map), HttpStatus.OK);
    }

}
