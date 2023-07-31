package com.demo.domain.entry.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: 王孟伟
 * @Date: 2023/2/28 16:04
 * @Description:
 */
@Data
@TableName(value = "sys_role")
public class SysRolePo {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "role_name")
    private String roleName;

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

    @TableField(exist = false)
    private List<Integer> roleMenuIds;
}
