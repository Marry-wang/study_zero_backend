package come.demo.domain.libraryManagement.book.controller;

import com.demo.api.ZeroResult;
import come.demo.domain.libraryManagement.book.entry.dto.BookDto;
import come.demo.domain.libraryManagement.book.entry.dto.BookTypeSummaryDto;
import come.demo.domain.libraryManagement.book.entry.vo.BookTypeSummaryVo;
import come.demo.domain.libraryManagement.book.entry.vo.BookVo;
import come.demo.domain.libraryManagement.book.service.BookService;
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
    public ZeroResult<List<BookTypeSummaryVo>> selectBookTypeSummary(@RequestBody BookTypeSummaryDto dto) {
        return ZeroResult.success(bookService.selectBookTypeSummary(dto));
    }

    /**
     * 添加图书
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/addBook")
    public ZeroResult<Boolean> addBook(@RequestBody BookDto dto) {
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
    public ZeroResult<List<BookVo>> selectBook(@RequestBody BookDto dto) {
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

}
