package com.demo.domain.libraryManagement.book.entry.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 图书借阅记录
 * @TableName book_borrowing_record
 */
@Data
public class BookBorrowingRecordVo{
    /**
     * 图书id
     */
    private Integer bookId;

    /**
     * 图书名称
     */
    private String bookName;

    /**
     * 借出时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date borrowingTime;

    /**
     * 归还时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returnTime;

    /**
     * 借书人
     */
    private Integer borrowingBy;

    /**
     * 状态(0未还，1已还)
     */
    private String status;

    /**
     * 记录人员
     */
    private Integer managerBy;

    /**
     * 
     */
    private Integer createBy;

    /**
     * 
     */
    private Date createTime;

    /**
     *
     */
    private Integer updateBy;

    /**
     *
     */
    private Date updateTime;
}