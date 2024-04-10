package com.bjtu.warehousemanagebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bjtu.warehousemanagebackend.mapper")
public class WarehouseManageBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseManageBackendApplication.class, args);
	}

}
