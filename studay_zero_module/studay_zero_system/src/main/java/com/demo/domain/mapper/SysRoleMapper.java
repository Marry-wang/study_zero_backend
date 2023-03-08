package com.demo.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.domain.entry.po.SysRolePo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 王孟伟
 * @Date: 2023/2/21 21:53
 * @Description:
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRolePo> {
}
