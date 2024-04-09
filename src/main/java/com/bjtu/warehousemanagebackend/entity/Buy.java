package com.bjtu.warehousemanagebackend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户购买货物
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("buy")
@ApiModel(value="Buy对象", description="用户购买货物")
public class Buy implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户编号")
    private String uId;

    @ApiModelProperty(value = "货号")
    private String gId;

    @ApiModelProperty(value = "购买量")
    private Integer purchase;


}
