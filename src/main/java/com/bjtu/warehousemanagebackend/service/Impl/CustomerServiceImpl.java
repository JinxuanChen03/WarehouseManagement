package com.bjtu.warehousemanagebackend.service.impl;

import com.bjtu.warehousemanagebackend.entity.Customer;
import com.bjtu.warehousemanagebackend.mapper.CustomerMapper;
import com.bjtu.warehousemanagebackend.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
