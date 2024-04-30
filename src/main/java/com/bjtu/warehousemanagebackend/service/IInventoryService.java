package com.bjtu.warehousemanagebackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjtu.warehousemanagebackend.entity.Inventory;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-30
 */
public interface IInventoryService extends IService<Inventory> {

    List<Inventory> findAll();

    List<Inventory> findByWarehouse(String id);

    List<Inventory> findByGoodsId(String id);
}
