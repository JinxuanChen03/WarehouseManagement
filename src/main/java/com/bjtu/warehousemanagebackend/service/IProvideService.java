package com.bjtu.warehousemanagebackend.service;

import com.bjtu.warehousemanagebackend.entity.Provide;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 供应商供货 服务类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
public interface IProvideService extends IService<Provide> {

    List<Provide> getByGid(String gid);


    Provide getByuIdAndGid(String uId, String gId);

    List<Provide> getByuId(String uId);


    void updateProvide(Provide provide);

    Provide getByUidAndGid(String uid, String gid);
}
