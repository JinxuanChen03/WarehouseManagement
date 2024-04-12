package com.bjtu.warehousemanagebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bjtu.warehousemanagebackend.entity.Goods;
import com.bjtu.warehousemanagebackend.mapper.GoodsMapper;
import com.bjtu.warehousemanagebackend.mapper.UserMapper;
import com.bjtu.warehousemanagebackend.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 货物 服务实现类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Override
    public void deleteGoods(String id) {
        Goods good = getById(id);
        good.setDeleted(true);
        updateById(good);
    }

    @Override
    public Goods getGoodsById(String id) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getId,id)
                .eq(Goods::getDeleted,false);
        return getOne(wrapper);
    }

    @Override
    public List<Goods> getAllGoods() {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getDeleted,false);
        return listObjs(wrapper);
    }
}
