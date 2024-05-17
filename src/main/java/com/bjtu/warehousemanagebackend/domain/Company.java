package com.bjtu.warehousemanagebackend.domain;

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
 * 公司表
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("company")
@ApiModel(value="Company对象", description="公司表")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公司id")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "公司名")
    private String name;

    @ApiModelProperty(value = "公司地址")
    private String addr;

    @ApiModelProperty(value = "创建时间")
    private String createAt;
}
