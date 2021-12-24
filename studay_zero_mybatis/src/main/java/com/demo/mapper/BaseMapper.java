package com.demo.mapper;

import com.demo.model.BasePerson;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaseMapper {
    List<BasePerson> getBase();
}
