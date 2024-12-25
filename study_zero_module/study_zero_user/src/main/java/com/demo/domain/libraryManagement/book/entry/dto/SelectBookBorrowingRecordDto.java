package com.demo.domain.libraryManagement.book.entry.dto;

import lombok.Data;

/**
 * 图书借阅记录 查询DTO
 *
 * @TableName
 */
@Data
public class SelectBookBorrowingRecordDto {

    private int pageNum = 1;

    private int pageSize = 10;
}