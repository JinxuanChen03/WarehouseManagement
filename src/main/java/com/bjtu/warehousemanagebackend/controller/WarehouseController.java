package com.bjtu.warehousemanagebackend.controller;



import com.bjtu.warehousemanagebackend.entity.Storage;
import com.bjtu.warehousemanagebackend.entity.Warehouse;
import com.bjtu.warehousemanagebackend.service.impl.StorageServiceImpl;
import com.bjtu.warehousemanagebackend.service.impl.WarehouseServiceImpl;
import com.bjtu.warehousemanagebackend.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 仓库 前端控制器
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@RestController
@RequestMapping("/warehouses")
public class WarehouseController {
    @Autowired
    private WarehouseServiceImpl warehouseService;

    @Autowired
    private StorageServiceImpl storageService;

    //新增一个仓库
    @PostMapping
    public ResponseEntity<Result> addWareHouse(@RequestBody @Valid Warehouse warehouse){
        warehouseService.save(warehouse);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    // PUT请求：更新指定id的仓库信息
    @PutMapping("/{id}")
    public ResponseEntity<Result> updateWarehouse(@PathVariable("id") String id, @RequestBody @Valid Warehouse warehouse) {
        warehouse.setId(id); // 设置要更新的仓库的id
        warehouseService.updateById(warehouse);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    // DELETE请求：逻辑删除指定id的仓库
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteWarehouse(@PathVariable("id") String id) {
        warehouseService.deleteWarehouse(id);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    // GET请求：获取指定id的仓库信息
    @GetMapping("/{id}")
    public ResponseEntity<Result> getWarehouseById(@PathVariable("id") String id) {
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        return new ResponseEntity<>(Result.success(warehouse), HttpStatus.OK);
    }

    // GET请求：获取所有仓库信息
    @GetMapping
    public ResponseEntity<Result> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.list();
        return new ResponseEntity<>(Result.success(warehouses), HttpStatus.OK);
    }

    // Search provides by wId
    @GetMapping("/{wid}/storage")
    public ResponseEntity<Result> searchStorageBywId(@PathVariable String wid) {
        List<Storage> storage = storageService.getBywId(wid);
        return new ResponseEntity<>(Result.success(storage), HttpStatus.OK);
    }
}
