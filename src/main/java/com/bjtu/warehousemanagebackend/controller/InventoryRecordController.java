package com.bjtu.warehousemanagebackend.controller;


import com.bjtu.warehousemanagebackend.entity.InventoryRecord;
import com.bjtu.warehousemanagebackend.service.impl.InventoryRecordServiceImpl;
import com.bjtu.warehousemanagebackend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
public class InventoryRecordController {

    @Autowired
    private InventoryRecordServiceImpl recordService;

    /**
     * 入库
     * @param record
     * @return
     */
    @PostMapping("/in")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_ADMIN')")
    public ResponseEntity<Result> in(@RequestBody InventoryRecord record) {
        recordService.in(record);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    /**
     * 出库
     * @param record
     * @return
     */
    @PostMapping("/out")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_ADMIN')")
    public ResponseEntity<Result> out(@RequestBody InventoryRecord record) {
        recordService.out(record);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    /**
     * 指定仓库id，查询某个仓库库内商品的出库入库记录
     * @param id
     * @return
     */
    @GetMapping("/warehouse/{id}")
    public ResponseEntity<Result> findRecordByWarehouse(@PathVariable String id) {
        return new ResponseEntity<>(Result.success(recordService.findAllByWarehouseId(id)), HttpStatus.OK);
    }

    /**
     * 指定商品id，查询某个商品在所有仓库出库入库记录
     * @param id
     * @return
     */
    @GetMapping("/goods/{id}")
    public ResponseEntity<Result> findRecordByGoods(@PathVariable String id) {
        return new ResponseEntity<>(Result.success(recordService.findAllByGoodsId(id)), HttpStatus.OK);
    }

}
