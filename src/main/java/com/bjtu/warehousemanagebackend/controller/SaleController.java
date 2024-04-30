package com.bjtu.warehousemanagebackend.controller;


import com.bjtu.warehousemanagebackend.entity.Sale;
import com.bjtu.warehousemanagebackend.service.impl.SaleServiceImpl;
import com.bjtu.warehousemanagebackend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-30
 */
@RestController
@RequestMapping("/sale")
//@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_SALE')")
public class SaleController {
    @Autowired
    private SaleServiceImpl saleService;

    @PostMapping("")
    public ResponseEntity<Result> save(@RequestBody Sale sale) {
        saleService.save(sale);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Result> getAll() {
        return new ResponseEntity<>(Result.success(saleService.list()), HttpStatus.OK);
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<Result> search(@PathVariable String name) {
        return new ResponseEntity<>(Result.success(saleService.searchByCompany(name)), HttpStatus.OK);

    }

}
