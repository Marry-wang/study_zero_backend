package com.demo.domain.libraryManagement.book.entry.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 图书
 *
 * @TableName book
 */
@TableName(value = "book")
@Data
public class BookPo implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 图书名称
     */
    @TableField(value = "book_name")
    private String bookName;

    /**
     * 图书封面路径
     */
    @TableField(value = "book_image_name")
    private String bookImageName;

    /**
     * 图书封面路径
     */
    @TableField(value = "book_image_path")
    private String bookImagePath;

    /**
     * 出版社
     */
    @TableField(value = "press")
    private String press;

    /**
     * 价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 图书编码
     */
    @TableField(value = "book_code")
    private String bookCode;

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