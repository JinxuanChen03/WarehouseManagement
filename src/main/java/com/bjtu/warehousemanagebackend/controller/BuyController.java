package com.bjtu.warehousemanagebackend.controller;


import com.bjtu.warehousemanagebackend.entity.Buy;
import com.bjtu.warehousemanagebackend.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.bjtu.warehousemanagebackend.service.impl.BuyServiceImpl;

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
    private BuyServiceImpl buyService;

    //用户买商品
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Result> buyGoods(@RequestBody @Valid Buy buy){
        buyService.buyGoods(buy);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    //查买了什么商品




}
