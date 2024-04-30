package com.bjtu.warehousemanagebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bjtu.warehousemanagebackend.entity.Inventory;
import com.bjtu.warehousemanagebackend.mapper.InventoryMapper;
import com.bjtu.warehousemanagebackend.service.IInventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-30
 */
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements IInventoryService {

    @Override
    public List<Inventory> findAll() {
        return list();
    }

    @Override
    public List<Inventory> findByWarehouse(String id) {
        LambdaQueryWrapper<Inventory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Inventory::getWid,id);
        return listObjs(wrapper);
    }

    @Override
    public List<Inventory> findByCommodityId(String id) {
        LambdaQueryWrapper<Inventory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Inventory::getCid,id);
        return listObjs(wrapper);
    }

    public Inventory findByWidAndCid(String wid, String cid) {
        LambdaQueryWrapper<Inventory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Inventory::getWid,wid)
                .eq(Inventory::getCid,cid);
        return getOne(wrapper);
    }
}
