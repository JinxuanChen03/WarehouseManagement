package com.bjtu.warehousemanagebackend.service.impl;

import com.bjtu.warehousemanagebackend.entity.Buy;
import com.bjtu.warehousemanagebackend.mapper.BuyMapper;
import com.bjtu.warehousemanagebackend.service.IBuyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 用户购买货物 服务实现类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@Service
public class BuyServiceImpl extends ServiceImpl<BuyMapper, Buy> implements IBuyService {

    @Autowired
    private BuyMapper buyMapper;

    @Override
    public void buyGoods(Buy buy) {
        updateById(buy);
//        buyMapper.buyGoods(buy);
    }
}
