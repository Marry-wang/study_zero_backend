package com.demo.domain.libraryManagement.book.entry.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 图书类别
 * @TableName book_type
 */
@TableName(value ="book_type")
@Data
public class BookTypePo implements Serializable {
    /**
     * 
     */
    @TableField(value = "book_id")
    private Integer bookId;

    /**
     * 
     */
    @TableField(value = "book_type_id")
    private Integer bookTypeId;

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