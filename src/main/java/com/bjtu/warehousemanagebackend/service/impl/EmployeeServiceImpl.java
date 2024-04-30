package com.bjtu.warehousemanagebackend.service.impl;

import com.bjtu.warehousemanagebackend.entity.Employee;
import com.bjtu.warehousemanagebackend.mapper.EmployeeMapper;
import com.bjtu.warehousemanagebackend.service.IEmployeeService;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    public List<Employee> findAll() {
        return list();
    }
}
