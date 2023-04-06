package com.demo.backend.service.impl;

import com.demo.backend.demo.Book;
import com.demo.backend.mapper.BookeMapper;
import com.demo.backend.service.BookeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * @ClassName BookServiceImpl
 * @Author 王孟伟
 * @Date 2021/11/22 16:39
 * @Version 1.0
 */
@Service
public class BookServiceImpl implements BookeService {

    @Autowired
    private BookeMapper bookeMapper;

    @Override
    @CacheEvict(allEntries = true)
    public void addBook(Book book) {
        bookeMapper.save(book);
    }

    @Override
    public Iterable<Book> getOne(Book book) {
        Iterable<Book> all = bookeMapper.findAll();
        return all;
    }
}
