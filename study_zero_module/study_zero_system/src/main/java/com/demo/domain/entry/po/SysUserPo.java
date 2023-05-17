package com.demo.domain.entry.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: 王孟伟
 * @Date: 2023/1/16 14:09
 * @Description:
 */
@Data
@TableName(value = "sys_user")
public class SysUserPo {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "create_by")
    private Integer createBy;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "update_by")
    private Date updateBy;

    @TableLogic
    @TableField(value = "dele_flag")
    private Boolean deleFlag;

}
