package com.bjtu.warehousemanagebackend.service.impl;

import com.bjtu.warehousemanagebackend.entity.Admin;
import com.bjtu.warehousemanagebackend.mapper.AdminMapper;
import com.bjtu.warehousemanagebackend.service.IAdminService;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Override
    public List<Admin> getAll() {
        return list();
    }
}
