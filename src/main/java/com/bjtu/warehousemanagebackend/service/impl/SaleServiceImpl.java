package com.bjtu.warehousemanagebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bjtu.warehousemanagebackend.entity.Sale;
import com.bjtu.warehousemanagebackend.mapper.SaleMapper;
import com.bjtu.warehousemanagebackend.service.ISaleService;
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
public class SaleServiceImpl extends ServiceImpl<SaleMapper, Sale> implements ISaleService {

    public List<Sale> searchByCompany(String name) {
        LambdaQueryWrapper<Sale> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Sale::getCompany,name);
        return listObjs(wrapper);
    }
}
