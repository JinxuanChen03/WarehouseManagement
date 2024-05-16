package com.bjtu.warehousemanagebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bjtu.warehousemanagebackend.domain.Goods;
import com.bjtu.warehousemanagebackend.exception.ServiceException;
import com.bjtu.warehousemanagebackend.mapper.GoodsMapper;
import com.bjtu.warehousemanagebackend.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjtu.warehousemanagebackend.utils.DateTimeUtil;
import org.springframework.http.HttpStatus;
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
        removeById(id);
    }

    @Override
    public Goods getGoodsById(String id) {
        return getById(id);
    }

    @Override
    public List<Goods> getAllGoods() {
        return list();
    }

    @Override
    public List<Goods> findByLikeName(String name) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Goods::getName,name);
        return listObjs(wrapper);
    }

    @Override
    public void addGoods(Goods good) {
        if(getGoodsByName(good.getName()) != null)
            throw new ServiceException(HttpStatus.FORBIDDEN.value(), "商品已存在");
        good.setCreateAt(DateTimeUtil.getNowTimeString());
        save(good);
    }

    @Override
    public Goods getGoodsByName(String name) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getName,name);
        return getOne(wrapper);
    }

    @Override
    public void updateGoods(Goods good) {
        if(getGoodsById(good.getId()) == null)
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "该货物不存在");
        updateById(good);
    }
}
