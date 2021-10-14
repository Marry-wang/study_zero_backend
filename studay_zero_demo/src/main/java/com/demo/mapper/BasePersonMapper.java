package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.model.BasePerson;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BasePersonMapper extends BaseMapper<BasePerson> {
}
