package com.bjtu.warehousemanagebackend.controller;


import com.bjtu.warehousemanagebackend.entity.Storage;
import com.bjtu.warehousemanagebackend.service.impl.StorageServiceImpl;
import com.bjtu.warehousemanagebackend.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 仓库存放货物 前端控制器
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@RestController
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private StorageServiceImpl storageService;

    //供货商供应商品，增一条
    @PostMapping
    public ResponseEntity<Result> addStorage(@RequestBody @Valid Storage storage){
        storageService.save(storage);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }


    // Search provides by wId and gid
    @GetMapping
    public ResponseEntity<Result> searchStorageByWidAndGid(@RequestParam String wid,@RequestParam String gid) {
        Storage storage = storageService.getBywIdAndGid(wid, gid);
        return new ResponseEntity<>(Result.success(storage), HttpStatus.OK);
    }

}
