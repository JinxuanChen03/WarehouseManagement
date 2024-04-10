package com.bjtu.warehousemanagebackend.mapper;

import com.bjtu.warehousemanagebackend.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
