package com.demo.backend.demo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("zero_schedule_setting")
public class ScheduleSetting {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("job_name")
    private String jobName;
    @TableField("class_name")
    private String className;
    @TableField("method")
    private String method;
    @TableField("cron")
    private String cron;
    @TableField("status")
    private Integer status;
}
