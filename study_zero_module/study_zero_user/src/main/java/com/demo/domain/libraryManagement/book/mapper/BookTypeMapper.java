package com.demo.domain.libraryManagement.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.domain.libraryManagement.book.entry.po.BookTypePo;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wangmengwei
* @description 针对表【book_type(图书类别)】的数据库操作Mapper
* @createDate 2024-06-11 13:41:19
* @Entity generator.domain.BookType
*/
@Mapper
public interface BookTypeMapper extends BaseMapper<BookTypePo> {

}




