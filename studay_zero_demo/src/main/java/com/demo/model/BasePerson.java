package com.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName BasePerson
 * @Author 王孟伟
 * @Date 2021/10/13 16:53
 * @Version 1.0
 */
@Data
@TableName("zero_base_person")
public class BasePerson {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("phone")
    private String phone;

    @TableField("salt")
    private String salt;

    @TableField("password")
    private String password;
}
