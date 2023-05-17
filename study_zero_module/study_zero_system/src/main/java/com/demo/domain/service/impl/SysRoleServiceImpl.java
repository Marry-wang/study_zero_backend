package com.demo.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.domain.entry.dto.SysRoleDto;
import com.demo.domain.entry.po.SysRolePo;
import com.demo.domain.entry.po.SysUserRolePo;
import com.demo.domain.mapper.SysRoleMapper;
import com.demo.domain.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author: 王孟伟
 * @Date: 2023/2/21 21:52
 * @Description:
 */
@Service
public class SysRoleServiceImpl implements SysRoleService{

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRolePo> queryRoleList(SysRoleDto dto) {
        LambdaQueryWrapper<SysRolePo> queryWrapper = new QueryWrapper<SysRolePo>().lambda();
        if(!Objects.isNull(dto.getRoleId())){
            queryWrapper.eq(SysRolePo::getId,dto.getRoleId());
        }
        return  sysRoleMapper.selectList(queryWrapper);
    }

    @Override
    public SysRolePo selectRole(SysRolePo sysRolePo) {
        return sysRoleMapper.selectById(sysRolePo.getId());
    }

    @Override
    public Integer addRole(SysRolePo sysRolePo) {
        return sysRoleMapper.insert(sysRolePo);
    }

    @Override
    public Integer updateRole(SysRolePo sysRolePo) {
        return sysRoleMapper.updateById(sysRolePo);
    }

    @Override
    public Integer delRole(SysRolePo sysRolePo) {
        return sysRoleMapper.deleteById(sysRolePo.getId());
    }
}
