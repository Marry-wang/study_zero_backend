package com.demo.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.backend.model.BasePerson;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BasePersonMapper extends BaseMapper<BasePerson> {
}
