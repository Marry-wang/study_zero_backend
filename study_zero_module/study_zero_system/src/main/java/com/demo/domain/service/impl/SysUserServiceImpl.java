package com.demo.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlHelper;
import com.demo.domain.entry.dto.SysUserDto;
import com.demo.domain.entry.po.SysUserPo;
import com.demo.domain.entry.po.SysUserRolePo;
import com.demo.domain.mapper.SysUserMapper;
import com.demo.domain.mapper.SysUserRoleMapper;
import com.demo.domain.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUserPo> queryUserList(SysUserDto dto) {
        LambdaQueryWrapper<SysUserPo> sysUserPoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(!Objects.isNull(dto.getUserId())){
            sysUserPoLambdaQueryWrapper.eq(SysUserPo::getId,dto.getUserId());
        }
        return sysUserMapper.selectList(sysUserPoLambdaQueryWrapper);
    }

    @Override
    public Boolean addUser(SysUserPo sysUserPo) {
        return SqlHelper.retBool(sysUserMapper.insert(sysUserPo));
    }

    @Override
    public Boolean updateUser(SysUserPo sysUserPo) {
        return SqlHelper.retBool(sysUserMapper.updateById(sysUserPo));
    }

    @Override
    public SysUserPo selectUser(SysUserPo sysUserPo) {
        return sysUserMapper.selectById(sysUserPo.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delUser(SysUserPo sysUserPo) {
        LambdaQueryWrapper<SysUserRolePo> queryWrapper = new QueryWrapper<SysUserRolePo>().lambda()
                .eq(SysUserRolePo::getUserId, sysUserPo.getId());
        sysUserRoleMapper.delete(queryWrapper);
        return SqlHelper.retBool(sysUserMapper.deleteById(sysUserPo.getId()));
    }
}
