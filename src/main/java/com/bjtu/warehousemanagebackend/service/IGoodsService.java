package com.bjtu.warehousemanagebackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjtu.warehousemanagebackend.domain.Goods;

import java.util.List;

/**
 * <p>
 * 货物 服务类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
public interface IGoodsService extends IService<Goods> {

    void deleteGoods(String id);

    Goods getGoodsById(String id);

    List<Goods> getAllGoods();

    List<Goods> findByLikeName(String name);

    void addGoods(Goods good);

    Goods getGoodsByName(String name);

    void updateGoods(Goods good);
}
