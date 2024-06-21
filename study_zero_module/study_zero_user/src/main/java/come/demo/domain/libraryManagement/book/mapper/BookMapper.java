package come.demo.domain.libraryManagement.book.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import come.demo.domain.libraryManagement.book.entry.po.BookPo;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wangmengwei
* @description 针对表【book(图书)】的数据库操作Mapper
* @createDate 2024-06-07 15:45:52
* @Entity generator.domain.Book
*/
@Mapper
public interface BookMapper extends BaseMapper<BookPo> {

}




