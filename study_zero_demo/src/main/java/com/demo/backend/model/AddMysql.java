package com.demo.backend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("zero_add_mysql")
@ApiModel(value = "AddMysql", description = "")
public class AddMysql {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    public Integer id;

    @ApiModelProperty(value = "name")
    @TableField("name")
    public String name;

    @ApiModelProperty(value = "email")
    @TableField("email")
    public String email;

    @ApiModelProperty(value = "home")
    @TableField("home")
    public String home;

    @ApiModelProperty(value = "phone")
    @TableField("phone")
    public String phone;

    @ApiModelProperty(value = "idcard")
    @TableField("idcard")
    public String idcard;

    @ApiModelProperty(value = "school")
    @TableField("school")
    public String school;

    @ApiModelProperty(value = "createBy")
    @TableField("create_by")
    public String createBy;

    @ApiModelProperty(value = "createTime")
    @TableField("create_time")
    public Date createTime;
}
