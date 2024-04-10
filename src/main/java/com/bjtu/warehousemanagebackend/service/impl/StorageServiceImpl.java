package com.bjtu.warehousemanagebackend.service.impl;


import com.bjtu.warehousemanagebackend.entity.Storage;
import com.bjtu.warehousemanagebackend.mapper.StorageMapper;
import com.bjtu.warehousemanagebackend.mapper.UserMapper;
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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StorageMapper storageMapper;

    @Override
    public List<Storage> getBywId(String wId) {
        return storageMapper.searchStoragersBywId(wId);
    }

    @Override
    public List<Storage> getByGid(String gId) {
        return storageMapper.searchStoragersByGid(gId);
    }

    @Override
    public Storage getBywIdAndGid(String wId, String gId) {
        return storageMapper.searchStoragersBywIdAndGid(wId,gId);
    }
}
