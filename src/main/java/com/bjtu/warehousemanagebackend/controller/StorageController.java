package com.bjtu.warehousemanagebackend.controller;


import com.bjtu.warehousemanagebackend.entity.Provide;
import com.bjtu.warehousemanagebackend.entity.Storage;
import com.bjtu.warehousemanagebackend.service.IProvideService;
import com.bjtu.warehousemanagebackend.service.IStorageService;
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
    private IStorageService storageService;

    //供货商供应商品，增一条
    @PostMapping
    public ResponseEntity<Result> addStorage(@RequestBody @Valid Storage storage){
        storageService.save(storage);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    // Search provides by wId
    @GetMapping("/wId/{wId}")
    public ResponseEntity<Result> searchStorageBywId(@PathVariable String wId) {
        List<Storage> storage = storageService.getBywId(wId);
        return new ResponseEntity<>(Result.success(storage), HttpStatus.OK);
    }

    // Search provides by gid
    @GetMapping("/gId/{gId}")
    public ResponseEntity<Result> searchStorageByGid(@PathVariable String gId) {
        List<Storage> storage = storageService.getByGid(gId);
        return new ResponseEntity<>(Result.success(storage), HttpStatus.OK);
    }

    // Search provides by wId and gid
    @GetMapping("/wId/{wId}/gId/{gId}")
    public ResponseEntity<Result> searchStorageBywIdAndGid(@PathVariable String wId, @PathVariable String gId) {
        Storage storage = storageService.getBywIdAndGid(wId, gId);
        return new ResponseEntity<>(Result.success(storage), HttpStatus.OK);
    }

}
