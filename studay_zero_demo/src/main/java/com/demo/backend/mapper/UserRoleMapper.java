package com.demo.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.backend.model.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
