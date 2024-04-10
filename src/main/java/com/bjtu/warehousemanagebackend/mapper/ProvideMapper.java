package com.bjtu.warehousemanagebackend.mapper;

import com.bjtu.warehousemanagebackend.entity.Provide;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 供应商供货 Mapper 接口
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */

public interface ProvideMapper extends BaseMapper<Provide> {

    List<Provide> searchProvidersByGid();

    List<Provide> searchProvidersByUid();

    Provide searchProvidersByUidAndGid();

    void updateProvide(String uId, String gId);
}
