package com.bjtu.warehousemanagebackend.service;

import com.bjtu.warehousemanagebackend.entity.Buy;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户购买货物 服务类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
public interface IBuyService extends IService<Buy> {

    void buyGoods(Buy buy);

    List<Buy> getOrderById(String uId);
}
