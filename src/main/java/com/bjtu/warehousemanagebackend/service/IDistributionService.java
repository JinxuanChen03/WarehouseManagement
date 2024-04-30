package com.bjtu.warehousemanagebackend.service;

import com.bjtu.warehousemanagebackend.entity.Distribution;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-30
 */
public interface IDistributionService extends IService<Distribution> {

    List<Distribution> findAll();
}
