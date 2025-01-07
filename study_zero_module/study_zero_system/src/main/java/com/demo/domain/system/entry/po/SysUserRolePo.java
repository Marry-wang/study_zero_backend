package com.demo.domain.system.entry.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author: 王孟伟
 * @Date: 2023/3/3 16:42
 * @Description:
 */
@Data
@TableName(value = "sys_user_role")
public class SysUserRolePo {

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "role_id")
    private Integer roleId;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "create_by",fill = FieldFill.INSERT)
    private Integer createBy;

    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableField(value = "update_by",fill = FieldFill.UPDATE)
    private Integer updateBy;

    @TableLogic
    @TableField(value = "del_flag")
    private Boolean delFlag;

}
