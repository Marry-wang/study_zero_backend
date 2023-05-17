package com.demo.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.domain.entry.dto.SysUserDto;
import com.demo.domain.entry.po.SysUserPo;
import com.demo.domain.mapper.SysUserMapper;
import com.demo.domain.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public List<SysUserPo> queryUserList(SysUserDto dto) {
        LambdaQueryWrapper<SysUserPo> sysUserPoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(!Objects.isNull(dto.getUserId())){
            sysUserPoLambdaQueryWrapper.eq(SysUserPo::getId,dto.getUserId());
        }
        return sysUserMapper.selectList(sysUserPoLambdaQueryWrapper);
    }

    @Override
    public Integer addUser(SysUserPo sysUserPo) {
        return sysUserMapper.insert(sysUserPo);
    }

    @Override
    public Integer updateUser(SysUserPo sysUserPo) {
        return sysUserMapper.updateById(sysUserPo);
    }

    @Override
    public SysUserPo selectUser(SysUserPo sysUserPo) {
        return sysUserMapper.selectById(sysUserPo.getId());
    }

    @Override
    public Integer delUser(SysUserPo sysUserPo) {
        return sysUserMapper.deleteById(sysUserPo.getId());
    }
}
