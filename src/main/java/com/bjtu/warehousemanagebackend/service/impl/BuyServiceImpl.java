package com.bjtu.warehousemanagebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bjtu.warehousemanagebackend.entity.Buy;
import com.bjtu.warehousemanagebackend.mapper.BuyMapper;
import com.bjtu.warehousemanagebackend.service.IBuyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Buy> getOrderByUid(String uid) {
        LambdaQueryWrapper<Buy> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Buy::getUId,uid);

        return listObjs(wrapper);
    }

    @Override
    public List<Buy> getOrderByGid(String gid) {
        LambdaQueryWrapper<Buy> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Buy::getGId,gid);

        return listObjs(wrapper);
    }

    public List<Buy> queryPurchaseByWiAndGid(String uid, String gid) {
        LambdaQueryWrapper<Buy> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Buy::getGId,gid)
                .eq(Buy::getUId,uid);

        return listObjs(wrapper);
    }
}
