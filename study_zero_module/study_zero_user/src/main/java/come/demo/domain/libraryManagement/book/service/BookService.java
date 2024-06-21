package come.demo.domain.libraryManagement.book.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlHelper;
import come.demo.domain.libraryManagement.book.entry.dto.BookDto;
import come.demo.domain.libraryManagement.book.entry.dto.BookTypeSummaryDto;
import come.demo.domain.libraryManagement.book.entry.po.BookPo;
import come.demo.domain.libraryManagement.book.entry.po.BookTypePo;
import come.demo.domain.libraryManagement.book.entry.po.BookTypeSummaryPo;
import come.demo.domain.libraryManagement.book.entry.vo.BookTypeSummaryVo;
import come.demo.domain.libraryManagement.book.entry.vo.BookVo;
import come.demo.domain.libraryManagement.book.mapper.BookMapper;
import come.demo.domain.libraryManagement.book.mapper.BookTypeMapper;
import come.demo.domain.libraryManagement.book.mapper.BookTypeSummaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;
    private final BookTypeMapper bookTypeMapper;
    private final BookTypeSummaryMapper bookTypeSummaryMapper;

    /**
     * 新增图书类别
     *
     * @param dto
     * @return
     */
    public boolean addBookTypeSummary(BookTypeSummaryDto dto) {
        BookTypeSummaryPo bookTypeSummaryPo = new BookTypeSummaryPo();
        bookTypeSummaryPo.setBookTypeName(dto.getBookTypeName());
        return SqlHelper.retBool(bookTypeSummaryMapper.insert(bookTypeSummaryPo));
    }

    /**
     * 删除图书类别
     *
     * @param dto
     * @return
     */
    public boolean delBookTypeSummary(BookTypeSummaryDto dto) {
        return SqlHelper.delBool(bookTypeSummaryMapper.deleteById(dto.getId()));
    }

    /**
     * 修改图书类别
     *
     * @param dto
     * @return
     */
    public boolean editBookTypeSummary(BookTypeSummaryDto dto) {
        BookTypeSummaryPo bookTypeSummaryPo = new BookTypeSummaryPo();
        bookTypeSummaryPo.setId(dto.getId());
        bookTypeSummaryPo.setBookTypeName(dto.getBookTypeName());
        return SqlHelper.retBool(bookTypeSummaryMapper.updateById(bookTypeSummaryPo));
    }

    /**
     * 查询图书类别
     *
     * @param dto
     * @return
     */
    public List<BookTypeSummaryVo> selectBookTypeSummary(BookTypeSummaryDto dto) {
        LambdaQueryWrapper<BookTypeSummaryPo> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty(dto.getBookTypeName())) {
            wrapper.like(BookTypeSummaryPo::getBookTypeName, dto.getBookTypeName());
        }
        List<BookTypeSummaryPo> bookTypeSummaryPos = bookTypeSummaryMapper.selectList(wrapper);
        List<BookTypeSummaryVo> bookTypeSummaryVos = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(bookTypeSummaryPos)){
            for (BookTypeSummaryPo bookTypeSummaryPo : bookTypeSummaryPos) {
                BookTypeSummaryVo bookTypeSummaryVo =new BookTypeSummaryVo();
                BeanUtil.copyProperties(bookTypeSummaryPo,bookTypeSummaryVo);
                bookTypeSummaryVos.add(bookTypeSummaryVo);
            }
        }
        return bookTypeSummaryVos;
    }

    /**
     * 添加图书
     *
     * @param dto
     * @return
     */
    public boolean addBook(BookDto dto) {
        BookPo bookPo = new BookPo();
        BeanUtil.copyProperties(dto, bookPo);
        bookMapper.insert(bookPo);

        BookTypePo bookTypePo = new BookTypePo();
        bookTypePo.setBookId(bookPo.getId());
        bookTypePo.setBookTypeId(dto.getBookTypeId());
        bookTypeMapper.insert(bookTypePo);

        return true;
    }

    /**
     * 删除图书信息
     *
     * @param bookId
     * @return
     */
    public boolean delBook(Integer bookId) {
        bookMapper.deleteById(bookId);

        bookTypeMapper.delete(new QueryWrapper<BookTypePo>().lambda().eq(BookTypePo::getBookId, bookId));

        return true;
    }

    /**
     * 查询图书信息
     *
     * @param dto
     * @return
     */
    public List<BookVo> selectBook(BookDto dto) {
        LambdaQueryWrapper<BookPo> wrapper = new LambdaQueryWrapper<>();
        List<BookPo> bookPos = bookMapper.selectList(wrapper);

        List<BookVo> bookVoList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(bookPos)) {
            for (BookPo bookPo : bookPos) {
                BookVo bookVo = new BookVo();
                BeanUtil.copyProperties(bookPo, bookVo);

                List<BookTypePo> bookTypePos = bookTypeMapper.selectList(new QueryWrapper<BookTypePo>().lambda().eq(BookTypePo::getBookId, bookPo.getId()));
                bookVo.setBookTypeId(bookTypePos.get(0).getBookTypeId());
                BookTypeSummaryPo bookTypeSummaryPo = bookTypeSummaryMapper.selectById(bookTypePos.get(0).getBookTypeId());
                bookVo.setBookTypeName(bookTypeSummaryPo.getBookTypeName());
                bookVo.setBookId(bookPo.getId());
                bookVoList.add(bookVo);
            }
        }

        return bookVoList;
    }

    /**
     * 修改图书信息
     *
     * @param dto
     * @return
     */
    public boolean editBook(BookDto dto) {
        BookPo bookPo = new BookPo();
        BeanUtil.copyProperties(dto, bookPo);
        bookPo.setId(dto.getBookId());
        bookMapper.updateById(bookPo);

        bookTypeMapper.delete(new QueryWrapper<BookTypePo>().lambda().eq(BookTypePo::getBookId, dto.getBookId()));
        BookTypePo bookTypePo = new BookTypePo();
        bookTypePo.setBookId(dto.getBookId());
        bookTypePo.setBookTypeId(dto.getBookTypeId());
        bookTypeMapper.insert(bookTypePo);

        return true;
    }

}
