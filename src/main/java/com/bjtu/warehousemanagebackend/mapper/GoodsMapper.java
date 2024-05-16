package com.bjtu.warehousemanagebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjtu.warehousemanagebackend.domain.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 货物 Mapper 接口
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    void insertGood(Goods goods);
    void updateGood(Goods goods);
    void deleteGood(String id);
    List<Goods> getGoodsById(String id);
    List<Goods> getAllGoods();
}
