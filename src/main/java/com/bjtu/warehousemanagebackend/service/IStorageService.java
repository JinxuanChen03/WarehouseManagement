package com.bjtu.warehousemanagebackend.service;

import com.bjtu.warehousemanagebackend.entity.Storage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 仓库存放货物 服务类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
public interface IStorageService extends IService<Storage> {

    List<Storage> getByWid(String wid);

    List<Storage> getByGid(String gid);

    Storage getByWidAndGid(String wid, String gid);
}
