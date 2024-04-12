package com.bjtu.warehousemanagebackend.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bjtu.warehousemanagebackend.entity.Storage;
import com.bjtu.warehousemanagebackend.mapper.StorageMapper;
import com.bjtu.warehousemanagebackend.service.IStorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 仓库存放货物 服务实现类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements IStorageService {

    @Override
    public List<Storage> getByWid(String wid) {
        LambdaQueryWrapper<Storage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Storage::getWId,wid);
        return listObjs(wrapper);
    }

    @Override
    public List<Storage> getByGid(String gid) {
        LambdaQueryWrapper<Storage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Storage::getGId,gid);
        return listObjs(wrapper);
    }

    @Override
    public Storage getByWidAndGid(String wid, String gid) {
        LambdaQueryWrapper<Storage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Storage::getWId,wid)
                .eq(Storage::getGId,gid);
        return getOne(wrapper);
    }
}
