package com.bjtu.warehousemanagebackend.mapper;

import com.bjtu.warehousemanagebackend.entity.Warehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 仓库 Mapper 接口
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */

public interface WarehouseMapper extends BaseMapper<Warehouse> {
    void deleteWarehouse(String id);
}
