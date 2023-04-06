package com.demo.domain.entry.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "parent_id")
    private Integer parentId;

    @TableField(value = "menu_path")
    private String path;

    @TableField(value = "menu_name")
    private String name;

    @TableField(value = "menu_icon")
    private String icon;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "create_by")
    private Integer createBy;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "update_by")
    private Date updateBy;

    @TableField(value = "dele_flag")
    private Boolean deleFlag;

    @TableField(exist = false)
    private List<SysMenuPo> chrldren;
}
