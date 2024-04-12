package com.bjtu.warehousemanagebackend.controller;

import com.bjtu.warehousemanagebackend.entity.Buy;
import com.bjtu.warehousemanagebackend.entity.Storage;
import com.bjtu.warehousemanagebackend.service.impl.BuyServiceImpl;
import com.bjtu.warehousemanagebackend.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户购买货物 前端控制器
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@RestController
public class BuyController {

    @Autowired
    private BuyServiceImpl buyService;

    //用户买商品
    @PostMapping("/purchase")
    public ResponseEntity<Result> buyGoods(@RequestParam String uid, @RequestParam String gid, @RequestBody @Valid Buy info){
        info.setUId(uid);
        info.setGId(gid);
        buyService.updateById(info);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    //全部购买记录
    //todo:分页查询
    @GetMapping("/purchase")
    public ResponseEntity<Result> getAllOrder() {
        return new ResponseEntity<>(Result.success(buyService.list()), HttpStatus.OK);
    }

    //uid,gid查购买记录
    @GetMapping
    public ResponseEntity<Result> queryPurchaseByWiAndGid(@RequestParam String uid, @RequestParam String gid) {
        return new ResponseEntity<>(Result.success(buyService.queryPurchaseByWiAndGid(uid, gid)), HttpStatus.OK);
    }





}
