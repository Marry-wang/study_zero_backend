package com.demo.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlHelper;
import com.demo.domain.entry.dto.SysRoleDto;
import com.demo.domain.entry.po.SysRolePo;
import com.demo.domain.entry.po.SysUserRolePo;
import com.demo.domain.entry.vo.SysRoleMenuVo;
import com.demo.domain.mapper.SysRoleMapper;
import com.demo.domain.mapper.SysUserRoleMapper;
import com.demo.domain.service.SysRoleService;
import com.demo.enums.BaseResultEnum;
import com.demo.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: 王孟伟
 * @Date: 2023/2/21 21:52
 * @Description:
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysRoleMenuVo> queryRoleList(SysRoleDto dto) {
        LambdaQueryWrapper<SysRolePo> queryWrapper = new QueryWrapper<SysRolePo>().lambda();
        if (!Objects.isNull(dto.getRoleId())) {
            queryWrapper.eq(SysRolePo::getId, dto.getRoleId());
        }
        List<SysRolePo> sysRolePos = sysRoleMapper.selectList(queryWrapper);
        List<SysRoleMenuVo> sysRoleMenuVos = new ArrayList<>();
        if (sysRolePos.size() > 0) {
            sysRolePos.forEach(sysRolePo -> {
                SysRoleMenuVo sysRoleMenuVo = new SysRoleMenuVo();
                sysRoleMenuVo.setRoleId(sysRolePo.getId());
                sysRoleMenuVo.setRoleName(sysRolePo.getRoleName());
                sysRoleMenuVos.add(sysRoleMenuVo);
            });
        }
        return sysRoleMenuVos;
    }

    @Override
    public SysRoleMenuVo selectRole(SysRolePo sysRolePo) {
        SysRolePo rolePo = sysRoleMapper.selectById(sysRolePo.getId());
        SysRoleMenuVo sysRoleMenuVo = new SysRoleMenuVo();
        sysRoleMenuVo.setRoleId(rolePo.getId());
        sysRoleMenuVo.setRoleName(rolePo.getRoleName());
        return sysRoleMenuVo;
    }

    @Override
    public Boolean addOrUpdateRole(SysRolePo sysRolePo) {
        if(ObjectUtil.isNotNull(sysRolePo.getId())){
            return SqlHelper.retBool(sysRoleMapper.updateById(sysRolePo));
        }else{
            return SqlHelper.retBool(sysRoleMapper.insert(sysRolePo));
        }
    }

    @Override
    public Boolean delRole(SysRolePo sysRolePo) {

        LambdaQueryWrapper<SysUserRolePo> wrapper = new QueryWrapper<SysUserRolePo>().lambda()
                .eq(SysUserRolePo::getRoleId, sysRolePo.getId());
        List<SysUserRolePo> sysUserRolePos = sysUserRoleMapper.selectList(wrapper);
        if (sysUserRolePos.size() > 0) {
            throw new BaseException(BaseResultEnum.SYSTEM_ROLE_IS_USER);
        }
        return SqlHelper.retBool(sysRoleMapper.deleteById(sysRolePo.getId()));
    }
}
