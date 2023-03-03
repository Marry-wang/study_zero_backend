package com.demo.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.domain.entry.po.SysUserPo;
import com.demo.domain.mapper.SysUserMapper;
import com.demo.domain.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 王孟伟
 * @Date: 2023/2/21 21:51
 * @Description:
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUserPo> queryUserList() {
        LambdaQueryWrapper<SysUserPo> sysUserPoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<SysUserPo> sysUserPoList = sysUserMapper.selectList(sysUserPoLambdaQueryWrapper);
        return sysUserPoList;
    }
}
