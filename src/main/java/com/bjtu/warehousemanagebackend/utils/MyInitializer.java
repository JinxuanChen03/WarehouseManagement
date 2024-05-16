package com.bjtu.warehousemanagebackend.utils;

import com.bjtu.warehousemanagebackend.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyInitializer implements CommandLineRunner {
    @Autowired
    private AdminServiceImpl adminService;

    @Override
    public void run(String... args) throws Exception {
        // 执行初始化逻辑
        adminService.initSuperAdmin();
    }
}
