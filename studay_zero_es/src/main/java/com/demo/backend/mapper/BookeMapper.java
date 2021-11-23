package com.demo.backend.mapper;

import com.demo.backend.demo.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookeMapper extends ElasticsearchRepository<Book,String> {


}
