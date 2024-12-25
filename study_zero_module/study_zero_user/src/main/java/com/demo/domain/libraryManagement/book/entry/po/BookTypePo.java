package com.demo.domain.libraryManagement.book.entry.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 图书类别
 *
 * @TableName book_type
 */
@TableName(value = "book_type")
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
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Integer createBy;

    /**
     *
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     *
     */
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private Integer updateBy;

    /**
     *
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}