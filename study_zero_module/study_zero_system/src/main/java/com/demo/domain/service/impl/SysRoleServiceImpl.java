package com.demo.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlHelper;
import com.demo.domain.entry.dto.SysRoleDto;
import com.demo.domain.entry.po.SysRolePo;
import com.demo.domain.entry.po.SysUserRolePo;
import com.demo.domain.mapper.SysRoleMapper;
import com.demo.domain.mapper.SysUserRoleMapper;
import com.demo.domain.service.SysRoleService;
import com.demo.enums.ZeroResultEnum;
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

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

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
    public Boolean addRole(SysRolePo sysRolePo) {
        return SqlHelper.retBool(sysRoleMapper.insert(sysRolePo));
    }

    @Override
    public Boolean updateRole(SysRolePo sysRolePo) {
        return SqlHelper.retBool(sysRoleMapper.updateById(sysRolePo));
    }

    @Override
    public Boolean delRole(SysRolePo sysRolePo) {

        LambdaQueryWrapper<SysUserRolePo> wrapper = new QueryWrapper<SysUserRolePo>().lambda()
                .eq(SysUserRolePo::getRoleId, sysRolePo.getId());
        List<SysUserRolePo> sysUserRolePos = sysUserRoleMapper.selectList(wrapper);
        if(sysUserRolePos.size()>0){
            throw new RuntimeException(ZeroResultEnum.SYSTEM_ROLE_IS_USER.toString());
        }
        return SqlHelper.retBool(sysRoleMapper.deleteById(sysRolePo.getId()));
    }
}
