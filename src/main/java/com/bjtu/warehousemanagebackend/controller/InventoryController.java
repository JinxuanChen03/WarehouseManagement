package com.bjtu.warehousemanagebackend.controller;


import com.bjtu.warehousemanagebackend.service.impl.InventoryServiceImpl;
import com.bjtu.warehousemanagebackend.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryServiceImpl inventoryService;

    /**
     * 查询所有库存
     * @return
     */
    @GetMapping
    public ResponseEntity<Result> findAll() {
        return new ResponseEntity<>(Result.success(inventoryService.findAll()), HttpStatus.OK);
    }

    /**
     * 指定仓库id，查询某个仓库的库存情况
     * @param id
     * @return
     */
    @GetMapping("/warehouse/{id}")
    public ResponseEntity<Result> findByWarehouse(@PathVariable String id) {
        return new ResponseEntity<>(Result.success(inventoryService.findByWarehouse(id)), HttpStatus.OK);
    }

    /**
     * 指定商品id，查询某个商品在所有仓库的库存
     * @param id
     * @return
     */
    @GetMapping("/goods/{id}")
    public ResponseEntity<Result> findByGoods(@PathVariable String id) {
        return new ResponseEntity<>(Result.success((inventoryService.findByGoodsId(id))), HttpStatus.OK);

    }
}
