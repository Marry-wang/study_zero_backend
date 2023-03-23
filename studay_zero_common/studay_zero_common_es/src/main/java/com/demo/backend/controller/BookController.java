package com.demo.backend.controller;

import com.demo.backend.demo.Book;
import com.demo.backend.service.BookeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BookController
 * @Author 王孟伟
 * @Date 2021/11/22 16:46
 * @Version 1.0
 */
@RestController
public class BookController {

    @Autowired
    private BookeService bookeService;

    @PostMapping("/addBook")
    public void addBook(@RequestBody Book book){
        bookeService.addBook(book);
    }

    @PostMapping("/getBook")
    public Iterable<Book> getBook(Book book){
        Iterable<Book> one = bookeService.getOne(book);
        return one;
    }
}
