package com.bjtu.warehousemanagebackend.service;

import com.bjtu.warehousemanagebackend.entity.InventoryRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-30
 */
public interface IInventoryRecordService extends IService<InventoryRecord> {

    List<InventoryRecord> findAllByWarehouseId(String id);

    List<InventoryRecord> findAllByCommodityId(String id);

    void in(InventoryRecord record);

    void out(InventoryRecord record);
}
