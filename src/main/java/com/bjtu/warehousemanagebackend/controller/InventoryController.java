package com.bjtu.warehousemanagebackend.controller;


import com.bjtu.warehousemanagebackend.entity.Inventory;
import com.bjtu.warehousemanagebackend.entity.InventoryRecord;
import com.bjtu.warehousemanagebackend.service.impl.InventoryRecordServiceImpl;
import com.bjtu.warehousemanagebackend.service.impl.InventoryServiceImpl;
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
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryServiceImpl inventoryService;

    @Autowired
    private InventoryRecordServiceImpl recordService;

    @GetMapping("")
    public ResponseEntity<Result> findAll() {
        return new ResponseEntity<>(Result.success(inventoryService.findAll()), HttpStatus.OK);
    }

//    @GetMapping("analyze")
//    public ResponseEntity<Result> analyze(Integer type) {
//        return new ResponseEntity<>(Result.success(recordService.analyzeCommodity(type)), HttpStatus.OK);
//    }

    //指定仓库id
    //查询某个仓库的库存情况
    @GetMapping("/warehouse/{id}")
    public ResponseEntity<Result> findByWarehouse(@PathVariable String id) {
        return new ResponseEntity<>(Result.success(inventoryService.findByWarehouse(id)), HttpStatus.OK);
    }

    //指定商品id
    //查询某个商品在所有仓库的库存
    @GetMapping("/commodity/{id}")
    public ResponseEntity<Result> findByCommodity(@PathVariable String id) {
        return new ResponseEntity<>(Result.success((inventoryService.findByCommodityId(id))), HttpStatus.OK);

    }

    //指定仓库id
    //查询某个仓库库内商品的出库入库记录
    @GetMapping("/record/warehouse/{id}")
    public ResponseEntity<Result> findRecordByWarehouse(@PathVariable String id) {
        return new ResponseEntity<>(Result.success(recordService.findAllByWarehouseId(id)), HttpStatus.OK);
    }

    //指定商品id
    //查询某个商品在所有仓库出库入库记录
    @GetMapping("/record/commodity/{id}")
    public ResponseEntity<Result> findRecordByCommodity(@PathVariable String id) {
        return new ResponseEntity<>(Result.success(recordService.findAllByCommodityId(id)), HttpStatus.OK);
    }

    @PostMapping("/in")
    public ResponseEntity<Result> in(@RequestBody InventoryRecord record) {
        recordService.in(record);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    @PostMapping("/out")
    public ResponseEntity<Result> out(@RequestBody InventoryRecord record) {
        recordService.out(record);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }
}
