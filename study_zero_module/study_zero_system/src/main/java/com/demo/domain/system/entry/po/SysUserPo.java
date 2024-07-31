package com.demo.domain.system.entry.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Author: 王孟伟
 * @Date: 2023/1/16 14:09
 * @Description:
 */
@Data
@TableName(value = "sys_user")
public class SysUserPo {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "create_by",fill = FieldFill.INSERT)
    private Integer createBy;

    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableField(value = "update_by",fill = FieldFill.UPDATE)
    private Date updateBy;

    @TableLogic
    @TableField(value = "dele_flag")
    private Boolean deleFlag;

}
