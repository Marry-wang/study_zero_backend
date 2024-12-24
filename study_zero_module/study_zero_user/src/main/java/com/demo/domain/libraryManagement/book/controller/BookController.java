package com.demo.domain.libraryManagement.book.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.api.ZeroResult;
import com.demo.domain.libraryManagement.book.entry.dto.BookDto;
import com.demo.domain.libraryManagement.book.entry.dto.SelectBookBorrowingRecordDto;
import com.demo.domain.libraryManagement.book.entry.vo.BookTypeSummaryVo;
import com.demo.domain.libraryManagement.book.entry.dto.AddBookBorrowingRecordDto;
import com.demo.domain.libraryManagement.book.entry.dto.BookTypeSummaryDto;
import com.demo.domain.libraryManagement.book.entry.vo.BookBorrowingRecordVo;
import com.demo.domain.libraryManagement.book.entry.vo.BookVo;
import com.demo.domain.libraryManagement.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    /**
     * 添加图书类别
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/addBookTypeSummary")
    public ZeroResult<Boolean> addBookTypeSummary(@RequestBody BookTypeSummaryDto dto) {
        return ZeroResult.success(bookService.addBookTypeSummary(dto));
    }

    /**
     * 删除图书类别
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/delBookTypeSummary")
    public ZeroResult<Boolean> delBookTypeSummary(@RequestBody BookTypeSummaryDto dto) {
        return ZeroResult.success(bookService.delBookTypeSummary(dto));
    }

    /**
     * 修改图书类别
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/editBookTypeSummary")
    public ZeroResult<Boolean> editBookTypeSummary(@RequestBody BookTypeSummaryDto dto) {
        return ZeroResult.success(bookService.editBookTypeSummary(dto));
    }

    /**
     * 查询图书类别
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/selectBookTypeSummary")
    public ZeroResult<Page<BookTypeSummaryVo>> selectBookTypeSummary(@RequestBody BookTypeSummaryDto dto) {
        return ZeroResult.success(bookService.selectBookTypeSummary(dto));
    }

    /**
     * 添加图书
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/addBook")
    public ZeroResult<Boolean> addBook(@RequestBody BookDto dto) throws Exception {
        return ZeroResult.success(bookService.addBook(dto));
    }

    /**
     * 删除图书
     *
     * @param bookId
     * @return
     */
    @GetMapping(value = "/delBook")
    public ZeroResult<Boolean> delBook(@RequestParam("bookId") Integer bookId) {
        return ZeroResult.success(bookService.delBook(bookId));
    }

    /**
     * 查询图书
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/selectBook")
    public ZeroResult<Page<BookVo>> selectBook(@RequestBody BookDto dto) {
        return ZeroResult.success(bookService.selectBook(dto));
    }

    /**
     * 修改图书
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/editBook")
    public ZeroResult<Boolean> editBook(@RequestBody BookDto dto) {
        return ZeroResult.success(bookService.editBook(dto));
    }

    /**
     * 图书借阅记录
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/addOrUpdateBookBorrowingRecord")
    public ZeroResult<Boolean> addOrUpdateBookBorrowingRecord(@RequestBody AddBookBorrowingRecordDto dto) {
        return ZeroResult.success(bookService.addOrUpdateBookBorrowingRecord(dto));
    }

    /**
     * 图书借阅记录
     *
     * @return
     */
    @PostMapping(value = "/selectBorrowingRecords")
    public ZeroResult<Page<BookBorrowingRecordVo>> selectBorrowingRecords(@RequestBody SelectBookBorrowingRecordDto dto) {
        return ZeroResult.success(bookService.selectBorrowingRecords(dto));
    }
}
