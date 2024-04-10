package com.bjtu.warehousemanagebackend.controller;


import com.bjtu.warehousemanagebackend.entity.Provide;
import com.bjtu.warehousemanagebackend.entity.Warehouse;
import com.bjtu.warehousemanagebackend.service.IProvideService;
import com.bjtu.warehousemanagebackend.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 供应商供货 前端控制器
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@RestController
@RequestMapping("/provide")
public class ProvideController {
    @Autowired
    private IProvideService iProvideService;

    //供货商供应商品，增一条
    @PostMapping
    public ResponseEntity<Result>  addProvide(@RequestBody @Valid Provide provide){
        iProvideService.save(provide);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    // Search provides by uId
    @GetMapping("/uId/{uId}")
    public ResponseEntity<Result> searchProvideByuId(@PathVariable String uId) {
        List<Provide> provides = iProvideService.getByuId(uId);
        return new ResponseEntity<>(Result.success(provides), HttpStatus.OK);
    }

    // Search provides by gid
    @GetMapping("/gId/{gId}")
    public ResponseEntity<Result> searchProvideByGid(@PathVariable String gId) {
        List<Provide> provides = iProvideService.getByGid(gId);
        return new ResponseEntity<>(Result.success(provides), HttpStatus.OK);
    }

    // Search provides by uId and gid
    @GetMapping("/uId/{uId}/gId/{gId}")
    public ResponseEntity<Result> searchProvideByuIdAndGid(@PathVariable String uId, @PathVariable String gId) {
        Provide provide = iProvideService.getByuIdAndGid(uId, gId);
        return new ResponseEntity<>(Result.success(provide), HttpStatus.OK);
    }
    //改，增，减,增删数量
    @PutMapping("/uId/{uId}/gId/{gId}")
    public ResponseEntity<Result> updateProvide(@PathVariable String uId, @PathVariable String gId) {
        iProvideService.updateProvide(uId, gId);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

}
