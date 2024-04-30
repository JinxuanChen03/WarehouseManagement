package com.bjtu.warehousemanagebackend.service;

import com.bjtu.warehousemanagebackend.entity.Driver;
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
public interface IDriverService extends IService<Driver> {

    List<Driver> findAll();
}
