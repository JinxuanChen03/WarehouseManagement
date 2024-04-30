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
 * 出入库记录表
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("inventory_record")
@ApiModel(value="InventoryRecord对象", description="出入库记录表")
public class InventoryRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "记录id")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "商品id")
    private String cid;

    @ApiModelProperty(value = "仓库id")
    private String wid;

    @ApiModelProperty(value = "出入库数量")
    private Integer count;

    @ApiModelProperty(value = "备注")
    private String description;

    @ApiModelProperty(value = "出库/入库")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    private String createAt;


}
