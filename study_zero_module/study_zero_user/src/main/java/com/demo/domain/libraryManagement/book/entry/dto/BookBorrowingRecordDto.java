package com.demo.domain.libraryManagement.book.entry.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 图书借阅记录
 * @TableName book_borrowing_record
 */
@Data
public class BookBorrowingRecordDto {
    /**
     * 图书id
     */
    private Integer bookId;

    /**
     * 借书人
     */
    private Integer borrowingBy;

    /**
     * 记录人员
     */
    private Integer managerBy;
}