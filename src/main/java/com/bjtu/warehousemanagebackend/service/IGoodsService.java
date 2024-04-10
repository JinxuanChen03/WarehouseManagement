package com.bjtu.warehousemanagebackend.service;

import com.bjtu.warehousemanagebackend.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
