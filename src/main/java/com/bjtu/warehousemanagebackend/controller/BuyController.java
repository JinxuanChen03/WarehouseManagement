package com.bjtu.warehousemanagebackend.controller;

import com.bjtu.warehousemanagebackend.entity.Buy;
import com.bjtu.warehousemanagebackend.service.IBuyService;
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
@RequestMapping("/buy")
public class BuyController {

    @Autowired
    private IBuyService buyService;

    //用户买商品
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Result> buyGoods(@RequestBody @Valid Buy buy){
        buyService.buyGoods(buy);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    //全部购买记录
    @GetMapping
    public ResponseEntity<Result> getAllOrder() {
        List<Buy> buy = buyService.list();
        return new ResponseEntity<>(Result.success(buy), HttpStatus.OK);
    }

    // 具体一个人的购买记录
    @GetMapping("/{uId}")
    public ResponseEntity<Result> getOrderById(@PathVariable("uId") String uId) {
        List<Buy> buy = buyService.getOrderById(uId);
        return new ResponseEntity<>(Result.success(buy), HttpStatus.OK);
    }



}
