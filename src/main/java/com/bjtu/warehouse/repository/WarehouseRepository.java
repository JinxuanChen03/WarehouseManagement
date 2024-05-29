package com.bjtu.warehouse.repository;

import com.bjtu.warehouse.model.Warehouse;
import org.reactivestreams.Publisher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WarehouseRepository extends ReactiveCrudRepository<Warehouse,String> {
    //spring-data封装好的简单CRUD接口
}
