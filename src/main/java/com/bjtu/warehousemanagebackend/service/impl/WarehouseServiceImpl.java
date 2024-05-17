package com.bjtu.warehousemanagebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bjtu.warehousemanagebackend.domain.Warehouse;
import com.bjtu.warehousemanagebackend.exception.ServiceException;
import com.bjtu.warehousemanagebackend.mapper.WarehouseMapper;
import com.bjtu.warehousemanagebackend.service.IWarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjtu.warehousemanagebackend.utils.DateTimeUtil;
import org.springframework.http.HttpStatus;
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

    @Override
    public void deleteWarehouse(String id) {
        //逻辑删除
        Warehouse warehouse = getById(id);
        updateById(warehouse);
    }

    @Override
    public Warehouse getWarehouseById(String id) {
        return getById(id);
    }

    @Override
    public Warehouse getWarehouseByName(String name) {
        LambdaQueryWrapper<Warehouse> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Warehouse::getName,name);
        return getOne(wrapper);
    }

    @Override
    public void addWareHouse(Warehouse warehouse) {
        if(getWarehouseByName(warehouse.getName()) != null)
            throw new ServiceException(HttpStatus.FORBIDDEN.value(), "仓库已存在");
        warehouse.setCreateAt(DateTimeUtil.getNowTimeString());
        save(warehouse);
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) {
        if(getWarehouseById(warehouse.getId()) == null)
            throw new ServiceException(HttpStatus.FORBIDDEN.value(), "仓库不存在");
        updateById(warehouse);
    }

    @Override
    public List<Warehouse> searchByName(String name) {
        LambdaQueryWrapper<Warehouse> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Warehouse::getName,name);
        return listObjs(wrapper);
    }

    @Override
    public List<Warehouse> getAll() {
        return list();
    }
}
