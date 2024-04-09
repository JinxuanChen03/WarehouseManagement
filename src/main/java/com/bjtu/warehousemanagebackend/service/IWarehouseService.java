package com.bjtu.warehousemanagebackend.service;

import com.bjtu.warehousemanagebackend.entity.Warehouse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 仓库 服务类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
public interface IWarehouseService extends IService<Warehouse> {

    static void addWarehouse(Warehouse warehouse) {

    }

    void addWareHouse(Warehouse warehouse);

    void updateWarehouse(Warehouse warehouse);

    void deleteWarehouse(Long id);

    Warehouse getWarehouseById(Long id);

    List<Warehouse> getAllWarehouses();
}
