package com.bjtu.warehouse.service;

import com.bjtu.warehouse.model.Warehouse;
import com.bjtu.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


public interface WarehouseService {

    Mono<Warehouse> addWarehouse(Warehouse warehouse);

}
