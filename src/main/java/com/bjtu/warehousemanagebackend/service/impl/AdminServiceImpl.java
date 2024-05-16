package com.bjtu.warehousemanagebackend.service.impl;

import com.bjtu.warehousemanagebackend.domain.Admin;
import com.bjtu.warehousemanagebackend.mapper.AdminMapper;
import com.bjtu.warehousemanagebackend.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-05-16
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
