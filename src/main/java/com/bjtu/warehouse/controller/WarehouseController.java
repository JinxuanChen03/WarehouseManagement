package com.bjtu.warehouse.controller;

import com.bjtu.warehouse.model.Warehouse;
import com.bjtu.warehouse.service.WarehouseService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    @Resource
    private WarehouseService warehouseService;

    @PostMapping
    public Mono<Warehouse> addWarehouse(@RequestBody Warehouse warehouse){
        System.out.println(warehouse);
        return warehouseService.addWarehouse(warehouse);
    }

//    @GetMapping("/test")
//    public String testEndpoint() {
//        System.out.println("1");
//        return "1";
//    }
}
