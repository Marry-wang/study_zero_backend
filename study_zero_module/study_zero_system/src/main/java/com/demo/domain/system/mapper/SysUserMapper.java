package com.demo.domain.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.domain.system.entry.po.SysUserPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 王孟伟
 * @Date: 2023/1/16 16:10
 * @Description:
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserPo> {
}
