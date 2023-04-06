package com.demo.backend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UserRole
 * @Author 王孟伟
 * @Date 2021/11/17 11:05
 * @Version 1.0
 */
@Data
@TableName("zero_user_role")
@ApiModel(value = "UserRole", description = "")
public class UserRole {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "userId")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "roleId")
    @TableField("role_id")
    private Integer roleId;
}
