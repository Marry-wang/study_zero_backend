package com.demo.domain.libraryManagement.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.domain.libraryManagement.book.entry.po.BookBorrowingRecordPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangmengwei
 * @description 针对表【book_borrowing_record(图书借阅记录)】的数据库操作Mapper
 * @createDate 2024-06-11 13:41:15
 * @Entity generator.domain.BookBorrowingRecord
 */
@Mapper
public interface BookBorrowingRecordMapper extends BaseMapper<BookBorrowingRecordPo> {

}




