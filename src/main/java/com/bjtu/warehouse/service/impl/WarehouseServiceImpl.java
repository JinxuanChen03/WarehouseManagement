package com.bjtu.warehouse.service.impl;

import com.bjtu.warehouse.model.Warehouse;
import com.bjtu.warehouse.repository.WarehouseRepository;
import com.bjtu.warehouse.service.WarehouseService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Resource
    private WarehouseRepository warehouseRepository;

    @Override
    public Mono<Object> addWarehouse(Warehouse warehouse){
        return warehouseRepository.findWarehouseByName(warehouse.getName())
                .flatMap(existingWarehouse -> Mono.error(new RuntimeException("仓库已存在")))
                .switchIfEmpty(warehouseRepository.save(warehouse));
    }

    @Override
    public Mono<Warehouse> updateWarehouse(Warehouse warehouse) {
        return warehouseRepository.findById(warehouse.getId())
                .flatMap(existingWarehouse -> {
                    // 保存更新后的仓库信息
                    return warehouseRepository.save(existingWarehouse);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("仓库不存在")));
    }

    @Override
    public Mono<Warehouse> getWarehouseById(String id) {
        return warehouseRepository.findById(id);
    }

    @Override
    public Flux<Warehouse> getAll() {
        return warehouseRepository.findAll();
    }

    @Override
    public Mono<Void> deleteWarehouse(String id) {
        return warehouseRepository.deleteById(id);
    }

}
