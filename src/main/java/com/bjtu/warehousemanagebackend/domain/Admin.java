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
 * 管理员表
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("admin")
@ApiModel(value="Admin对象", description="管理员表")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "管理员id")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "管理员名称")
    private String name;

    @ApiModelProperty(value = "管理员密码")
    private String password;

    @ApiModelProperty(value = "管理员权限")
    private String permission;

    @ApiModelProperty(value = "创建时间")
    private String createdAt;

    @ApiModelProperty(value = "是否删除")
    private Boolean deleted;


}
