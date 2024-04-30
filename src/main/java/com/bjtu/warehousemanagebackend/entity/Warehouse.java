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
 * 仓库
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("warehouse")
@ApiModel(value="Warehouse对象", description="仓库")
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "仓库号")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    private String name;

    private String createAt;

    private String updateAt;

    private Integer principle;


}
