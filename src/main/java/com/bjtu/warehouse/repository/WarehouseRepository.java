package com.bjtu.warehouse.repository;

import com.bjtu.warehouse.model.Warehouse;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface WarehouseRepository extends ReactiveCrudRepository<Warehouse,String> {
    //spring-data封装好的简单CRUD接口
//    @Query("SELECT * FROM warehouse WHERE name = :name")
//    Mono<Warehouse> findByName(@Param("name") String name);
    Mono<Warehouse> findWarehouseByName(@Param("name") String name);
}
