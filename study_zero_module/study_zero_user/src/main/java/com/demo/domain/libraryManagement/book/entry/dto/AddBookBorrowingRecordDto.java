package com.demo.domain.libraryManagement.book.entry.dto;

import lombok.Data;

/**
 * 图书借阅记录 添加DTO
 *
 * @TableName
 */
@Data
public class AddBookBorrowingRecordDto {
    /**
     * 图书id
     */
    private Integer bookId;

    /**
     * 借阅记录id
     */
    private Integer borrowingRecordId;

    /**
     * 借书人
     */
    private Integer borrowingBy;

    /**
     * 记录人员
     */
    private Integer managerBy;
}