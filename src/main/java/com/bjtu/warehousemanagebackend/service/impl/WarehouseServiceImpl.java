package com.bjtu.warehousemanagebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bjtu.warehousemanagebackend.entity.Warehouse;
import com.bjtu.warehousemanagebackend.mapper.WarehouseMapper;
import com.bjtu.warehousemanagebackend.service.IWarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Warehouse getWarehouseById(String id) {
        LambdaQueryWrapper<Warehouse> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Warehouse::getId,id)
                .eq(Warehouse::getDeleted,false);
        return getOne(wrapper);
    }
}
