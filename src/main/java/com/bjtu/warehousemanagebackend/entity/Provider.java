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
 * 供应商
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("provider")
@ApiModel(value="Provider对象", description="供应商")
public class Provider implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "供应商编号")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String name;

    private Integer phone;

    private String addr;

    private Boolean deleted;


}
