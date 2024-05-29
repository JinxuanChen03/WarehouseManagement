package com.bjtu.warehouse.service;

import com.bjtu.warehouse.model.Warehouse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface WarehouseService {

    Mono<Object> addWarehouse(Warehouse warehouse);

    Mono<Warehouse> updateWarehouse(Warehouse warehouse);

    Mono<Warehouse> getWarehouseById(String id);

    Flux<Warehouse> getAll();

    Mono<Void> deleteWarehouse(String id);
}
