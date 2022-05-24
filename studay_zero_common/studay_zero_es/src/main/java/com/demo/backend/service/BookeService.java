package com.demo.backend.service;

import com.demo.backend.demo.Book;

public interface BookeService {

    void addBook(Book book);

    Iterable<Book> getOne(Book book);
}
