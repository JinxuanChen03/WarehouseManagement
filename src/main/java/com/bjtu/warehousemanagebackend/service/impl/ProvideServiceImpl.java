package com.bjtu.warehousemanagebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.bjtu.warehousemanagebackend.entity.Provide;
import com.bjtu.warehousemanagebackend.mapper.ProvideMapper;
import com.bjtu.warehousemanagebackend.service.IProvideService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 供应商供货 服务实现类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@Service
public class ProvideServiceImpl extends ServiceImpl<ProvideMapper, Provide> implements IProvideService {

    @Autowired
    private ProvideMapper provideMapper;

    @Override
    public List<Provide> getByGid(String gid) {
        return provideMapper.searchProvidersByGid();
    }

    @Override
    public Provide getByuIdAndGid(String uId, String gId) {
        return provideMapper.searchProvidersByUidAndGid();
    }

    @Override
    public List<Provide> getByuId(String uId) {
        return provideMapper.searchProvidersByUid();
    }


    @Override
    public void updateProvide(Provide info) {
        LambdaUpdateWrapper<Provide> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Provide::getUId,info.getUId())
                .eq(Provide::getGId,info.getGId())
                .set(Provide::getSupply,info.getSupply());
        update(wrapper);
    }

    @Override
    public Provide getByUidAndGid(String uid, String gid) {
        LambdaQueryWrapper<Provide> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Provide::getUId,uid)
                .eq(Provide::getGId,gid);
        return getOne(wrapper);
    }
}
