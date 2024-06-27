package com.demo.domain.libraryManagement.book.entry.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 图书类别汇总
 * @TableName book_type_summary
 */
@TableName(value ="book_type_summary")
@Data
public class BookTypeSummaryPo implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 图书类别
     */
    @TableField(value = "book_type_name")
    private String bookTypeName;

    /**
     * 
     */
    @TableField(value = "create_by",fill = FieldFill.INSERT)
    private Integer createBy;

    /**
     * 
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 
     */
    @TableField(value = "update_by",fill = FieldFill.UPDATE)
    private Integer updateBy;

    /**
     * 
     */
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}