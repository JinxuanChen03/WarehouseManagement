package com.bjtu.warehouse.service.impl;

import com.bjtu.warehouse.model.Warehouse;
import com.bjtu.warehouse.repository.WarehouseRepository;
import com.bjtu.warehouse.service.WarehouseService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Resource
    private WarehouseRepository warehouseRepository;

    @Override
    public Mono<Warehouse> addWarehouse(Warehouse warehouse){
        return warehouseRepository.save(warehouse);
    }

}
