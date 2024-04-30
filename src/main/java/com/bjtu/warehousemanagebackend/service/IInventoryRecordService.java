package com.bjtu.warehousemanagebackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjtu.warehousemanagebackend.entity.InventoryRecord;

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

    List<InventoryRecord> findAllByGoodsId(String id);

    void in(InventoryRecord record);

    void out(InventoryRecord record);
}
