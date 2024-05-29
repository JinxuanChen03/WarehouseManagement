package com.bjtu.warehouse.controller;

import com.bjtu.warehouse.model.Warehouse;
import com.bjtu.warehouse.service.WarehouseService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    @Resource
    private WarehouseService warehouseService;

    /**
     * 新增一个仓库
     * @param warehouse
     * @return
     */
    @PostMapping
    public Mono<ResponseEntity<Object>> addWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.addWarehouse(warehouse)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * 更新仓库信息
     *
     * @param id
     * @param warehouse
     * @return
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Warehouse>> updateWarehouse(@PathVariable("id") String id, @RequestBody Warehouse warehouse){
        System.out.println(warehouse);
        return warehouseService.updateWarehouse(warehouse)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * 获取一条仓库信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Warehouse>> getWarehouseById(@PathVariable("id") String id) {
        return warehouseService.getWarehouseById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * 获取全部仓库信息
     * @return
     */
    @GetMapping
    public Flux<Warehouse> getAll() {
        return warehouseService.getAll();
    }

    /**
     * 删除仓库
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Mono<Void> deleteWarehouse(@PathVariable("id") String id) {
        return warehouseService.deleteWarehouse(id);
    }
}
