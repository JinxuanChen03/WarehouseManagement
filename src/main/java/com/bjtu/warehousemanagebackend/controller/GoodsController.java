package com.bjtu.warehousemanagebackend.controller;


import com.bjtu.warehousemanagebackend.entity.Goods;
import com.bjtu.warehousemanagebackend.service.impl.GoodsServiceImpl;
import com.bjtu.warehousemanagebackend.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 货物 前端控制器
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsServiceImpl goodsService;

    //新增一个仓库
    @PostMapping
    public ResponseEntity<Result> addGoods(@RequestBody @Valid Goods good){
        goodsService.save(good);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    // PUT请求：更新指定id的仓库信息
    @PutMapping("/{id}")
    public ResponseEntity<Result> updateGoods(@PathVariable("id") String id, @RequestBody @Valid Goods good) {
        good.setId(id); // 设置要更新的仓库的id
        goodsService.updateById(good);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    // DELETE请求：逻辑删除指定id的仓库
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteGoods(@PathVariable("id") String id) {
        goodsService.deleteGoods(id);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    // GET请求：获取指定id的仓库信息
    @GetMapping("/{id}")
    public ResponseEntity<Result> getGoodsById(@PathVariable("id") String id) {
        Goods good = goodsService.getGoodsById(id);
        return new ResponseEntity<>(Result.success(good), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Result>  findByLikeName(@PathVariable String name) {
        return new ResponseEntity<>(Result.success(goodsService.findByLikeName(name)), HttpStatus.OK);
    }

    // GET请求：获取所有仓库信息
    @GetMapping
    public ResponseEntity<Result> getAllGoods() {
        List<Goods> goods = goodsService.getAllGoods();
        return new ResponseEntity<>(Result.success(goods), HttpStatus.OK);
    }

}
