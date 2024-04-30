package com.bjtu.warehousemanagebackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjtu.warehousemanagebackend.entity.Warehouse;

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

    void deleteWarehouse(String id);

    Warehouse getWarehouseById(String id);

    Warehouse getWarehouseByName(String name);

    void addWareHouse(Warehouse warehouse);

    void updateWarehouse(Warehouse warehouse);

    List<Warehouse> searchByName(String name);

    List<Warehouse> getAll();
}
