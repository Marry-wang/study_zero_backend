package com.demo.domain.system.entry.po;

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
@TableName(value = "sys_menu")
public class SysMenuPo {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "parent_id")
    private Integer parentId;

    @TableField(value = "menu_path")
    private String path;

    @TableField(value = "menu_name")
    private String menuName;

    @TableField(value = "menu_icon")
    private String icon;

    @TableField(value = "menu_redirect")
    private String redirect;

    @TableField(value = "menu_component")
    private String component;

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

    @TableField(exist = false)
    private List<SysMenuPo> children;
}
