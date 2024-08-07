package com.demo.domain.libraryManagement.book.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlHelper;
import com.demo.domain.libraryManagement.book.entry.dto.BookBorrowingRecordDto;
import com.demo.domain.libraryManagement.book.entry.dto.BookDto;
import com.demo.domain.libraryManagement.book.entry.dto.BookTypeSummaryDto;
import com.demo.domain.libraryManagement.book.entry.po.BookBorrowingRecordPo;
import com.demo.domain.libraryManagement.book.entry.po.BookPo;
import com.demo.domain.libraryManagement.book.entry.po.BookTypePo;
import com.demo.domain.libraryManagement.book.entry.po.BookTypeSummaryPo;
import com.demo.domain.libraryManagement.book.entry.vo.BookBorrowingRecordVo;
import com.demo.domain.libraryManagement.book.entry.vo.BookTypeSummaryVo;
import com.demo.domain.libraryManagement.book.entry.vo.BookVo;
import com.demo.domain.libraryManagement.book.mapper.BookBorrowingRecordMapper;
import com.demo.domain.libraryManagement.book.mapper.BookMapper;
import com.demo.domain.libraryManagement.book.mapper.BookTypeMapper;
import com.demo.domain.libraryManagement.book.mapper.BookTypeSummaryMapper;
import com.demo.enums.BaseResultEnum;
import com.demo.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;
    private final BookTypeMapper bookTypeMapper;
    private final BookTypeSummaryMapper bookTypeSummaryMapper;
    private final BookBorrowingRecordMapper recordMapper;

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
        if (CollectionUtil.isNotEmpty(bookTypeSummaryPos)) {
            for (BookTypeSummaryPo bookTypeSummaryPo : bookTypeSummaryPos) {
                BookTypeSummaryVo bookTypeSummaryVo = new BookTypeSummaryVo();
                BeanUtil.copyProperties(bookTypeSummaryPo, bookTypeSummaryVo);
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

    /**
     * 新增/修改图书借阅记录
     *
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateBookBorrowingRecord(BookBorrowingRecordDto dto) {
        if(ObjectUtil.isEmpty(dto.getBorrowingRecordId())){
            List<BookBorrowingRecordPo> bookBorrowingRecordPos = recordMapper.selectList(new QueryWrapper<BookBorrowingRecordPo>().lambda()
                    .eq(BookBorrowingRecordPo::getBookId, dto.getBookId())
                    .eq(BookBorrowingRecordPo::getBorrowingBy, dto.getBorrowingBy())
                    .eq(BookBorrowingRecordPo::getStatus, "0")
            );
            if (CollectionUtil.isNotEmpty(bookBorrowingRecordPos)) {
                throw new BaseException(BaseResultEnum.BOOK_RECORD_IS_EXIT);
            } else {
                BookBorrowingRecordPo recordPo = new BookBorrowingRecordPo();
                recordPo.setBookId(dto.getBookId());
                recordPo.setBorrowingBy(dto.getBorrowingBy());
                recordPo.setBorrowingTime(new Date());
                recordPo.setStatus("0");
                recordMapper.insert(recordPo);
            }
        }else{
            BookBorrowingRecordPo recordPo = new BookBorrowingRecordPo();
            recordPo.setBorrowingRecordId(dto.getBorrowingRecordId());
            recordPo.setReturnTime(new Date());
            recordPo.setStatus("1");
            recordMapper.updateById(recordPo);
        }
        return true;
    }

    /**
     * 图书借阅记录
     *
     * @return
     */
    public List<BookBorrowingRecordVo> selectBorrowingRecords() {
        List<BookBorrowingRecordPo> bookBorrowingRecordPos = recordMapper.selectList(new QueryWrapper<BookBorrowingRecordPo>().lambda());

        List<Integer> collect = bookBorrowingRecordPos.stream().map(BookBorrowingRecordPo::getBookId).collect(Collectors.toList());
        List<BookPo> bookPos = bookMapper.selectList(new QueryWrapper<BookPo>().lambda().in(BookPo::getId, collect));

        List<BookBorrowingRecordVo> bookBorrowingRecordVos = new ArrayList<>();
        if(bookBorrowingRecordPos.size()>0){
            BeanUtil.copyProperties(bookBorrowingRecordVos,bookBorrowingRecordPos);
            for (BookBorrowingRecordPo bookBorrowingRecordPo : bookBorrowingRecordPos) {
                BookBorrowingRecordVo bookBorrowingRecordVo = new BookBorrowingRecordVo();
                BeanUtil.copyProperties(bookBorrowingRecordPo,bookBorrowingRecordVo);
                for (BookPo bookPo : bookPos) {
                    if(bookBorrowingRecordVo.getBookId().equals(bookPo.getId())){
                        bookBorrowingRecordVo.setBookName(bookPo.getBookName());
                        break;
                    }
                }
                bookBorrowingRecordVos.add(bookBorrowingRecordVo);
            }
        }
        return bookBorrowingRecordVos;
    }

}
