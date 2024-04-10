package com.bjtu.warehousemanagebackend.service.Impl;

import com.bjtu.warehousemanagebackend.entity.Warehouse;
import com.bjtu.warehousemanagebackend.mapper.WarehouseMapper;
import com.bjtu.warehousemanagebackend.service.IWarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 仓库 服务实现类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements IWarehouseService {
    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public void deleteWarehouse(String id) {
        //逻辑删除
        Warehouse warehouse = getById(id);
        warehouse.setDeleted(true);
        updateById(warehouse);
    }
}
