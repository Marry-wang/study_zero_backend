package com.demo.domain.entry.po;

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

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "create_by")
    private Integer createBy;

    @TableField(value = "create_time")
    private Date updateTime;

    @TableField(value = "update_by")
    private Integer updateBy;

    @TableLogic
    @TableField(value = "del_flag")
    private Boolean delFlag;

}
