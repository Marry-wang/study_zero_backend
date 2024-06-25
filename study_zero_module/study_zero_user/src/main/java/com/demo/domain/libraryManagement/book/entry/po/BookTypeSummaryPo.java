package com.demo.domain.libraryManagement.book.entry.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    @TableField(value = "create_by")
    private Integer createBy;

    /**
     * 
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 
     */
    @TableField(value = "update_by")
    private Integer updateBy;

    /**
     * 
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}