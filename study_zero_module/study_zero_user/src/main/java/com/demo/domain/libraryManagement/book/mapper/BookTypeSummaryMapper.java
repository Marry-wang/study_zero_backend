package com.demo.domain.libraryManagement.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.domain.libraryManagement.book.entry.po.BookTypeSummaryPo;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wangmengwei
* @description 针对表【book_type_summary(图书类别汇总)】的数据库操作Mapper
* @createDate 2024-06-11 13:41:23
* @Entity generator.domain.BookTypeSummary
*/
@Mapper
public interface BookTypeSummaryMapper extends BaseMapper<BookTypeSummaryPo> {

}




