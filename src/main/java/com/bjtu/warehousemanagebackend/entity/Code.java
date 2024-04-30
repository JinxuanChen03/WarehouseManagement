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
 * 
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("code")
@ApiModel(value="Code对象", description="")
public class Code implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "email", type = IdType.AUTO)
    private String email;

    private Long exp;

    private String value;


}
