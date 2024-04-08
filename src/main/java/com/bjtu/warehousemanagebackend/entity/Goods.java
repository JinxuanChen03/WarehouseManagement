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
 * 货物
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("goods")
@ApiModel(value="Goods对象", description="货物")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "货号")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String name;

    private String type;

    private Boolean deleted;


}
