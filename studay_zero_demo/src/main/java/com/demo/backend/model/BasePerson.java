package com.demo.backend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName BasePerson
 * @Author 王孟伟
 * @Date 2021/10/13 16:53
 * @Version 1.0
 */
@Data
@TableName("zero_base_person")
@ApiModel(value = "BasePerson", description = "")
public class BasePerson {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "name")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "phone")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "salt")
    @TableField("salt")
    private String salt;

    @ApiModelProperty(value = "password")
    @TableField("password")
    private String password;
}
