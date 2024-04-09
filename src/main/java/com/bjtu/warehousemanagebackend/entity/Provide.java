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
 * 供应商供货
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("provide")
@ApiModel(value="Provide对象", description="供应商供货")
public class Provide implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "货号")
    private String gId;

    @ApiModelProperty(value = "供应商编号")
    private String uId;

    @ApiModelProperty(value = "供货量")
    private Integer supply;


}
