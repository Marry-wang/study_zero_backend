package com.demo.backend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName BaseRole
 * @Author 王孟伟
 * @Date 2021/11/17 11:05
 * @Version 1.0
 */
@Data
@TableName("zero_role")
@ApiModel(value = "BaseRole", description = "")
public class BaseRole {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    public Integer id;

    @ApiModelProperty(value = "role")
    @TableField("role")
    public String role;
}
