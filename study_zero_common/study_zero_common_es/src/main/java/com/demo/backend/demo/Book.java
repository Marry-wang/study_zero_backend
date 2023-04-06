package com.demo.backend.demo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @ClassName Book
 * @Author 王孟伟
 * @Date 2021/11/22 16:15
 * @Version 1.0
 */

/**
 * indexName：索引名称 可以理解为数据库名 必须为小写 不然会报org.elasticsearch.indices.InvalidIndexNameException异常
 * type：类型 可以理解为表名
 */

@Data
@Document(indexName = "book" ,createIndex = true)
public class Book {
    @Id
    @Field(type = FieldType.Text)
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String author;

    @Field(type = FieldType.Text)
    private String price;

    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    private Date createTime;

    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    private Date updateTime;

}
