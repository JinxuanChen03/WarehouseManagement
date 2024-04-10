package com.bjtu.warehousemanagebackend.mapper;

import com.bjtu.warehousemanagebackend.entity.Buy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScans;

import java.util.List;

/**
 * <p>
 * 用户购买货物 Mapper 接口
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */

public interface BuyMapper extends BaseMapper<Buy> {

    //新增购买记录
    void insertBuy();
    //获取用户订单
    List<Buy> getOrderById(String uId);
    //获取全部订单

}
