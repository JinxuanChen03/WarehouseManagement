package com.bjtu.warehousemanagebackend.service.impl;

import com.bjtu.warehousemanagebackend.entity.Distribution;
import com.bjtu.warehousemanagebackend.mapper.DistributionMapper;
import com.bjtu.warehousemanagebackend.service.IDistributionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-30
 */
@Service
public class DistributionServiceImpl extends ServiceImpl<DistributionMapper, Distribution> implements IDistributionService {

    @Override
    public List<Distribution> findAll() {
        return list();
    }
}
