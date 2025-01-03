package com.demo.domain.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.demo.domain.system.entry.dto.AddOrUpdateRoleDto;
import com.demo.domain.system.entry.dto.SysRoleDto;
import com.demo.domain.system.entry.po.SysRoleMenuPo;
import com.demo.domain.system.entry.po.SysRolePo;
import com.demo.domain.system.entry.po.SysUserRolePo;
import com.demo.domain.system.entry.vo.SysRoleMenuVo;
import com.demo.domain.system.mapper.SysRoleMapper;
import com.demo.domain.system.mapper.SysRoleMenuMapper;
import com.demo.domain.system.mapper.SysUserRoleMapper;
import com.demo.domain.system.service.SysRoleService;
import com.demo.enums.BaseResultEnum;
import com.demo.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Page<SysRoleMenuVo> queryRoleList(SysRoleDto dto) {
        LambdaQueryWrapper<SysRolePo> queryWrapper = new QueryWrapper<SysRolePo>().lambda();
        if (!Objects.isNull(dto.getRoleId())) {
            queryWrapper.eq(SysRolePo::getId, dto.getRoleId());
        }
        Page<SysRolePo> sysRolePoPage = sysRoleMapper.selectPage(new Page<>(dto.getPageNum(), dto.getPageSize()), queryWrapper);
        Page<SysRoleMenuVo> sysRoleVoPage = new Page<>();
        BeanUtil.copyProperties(sysRolePoPage, sysRoleVoPage);
        List<SysRolePo> sysRolePos = sysRolePoPage.getRecords();
        List<SysRoleMenuVo> sysRoleMenuVos = new ArrayList<>();
        if (sysRolePos.size() > 0) {
            sysRolePos.forEach(sysRolePo -> {
                SysRoleMenuVo sysRoleMenuVo = new SysRoleMenuVo();
                sysRoleMenuVo.setRoleId(sysRolePo.getId());
                sysRoleMenuVo.setRoleName(sysRolePo.getRoleName());
                sysRoleMenuVos.add(sysRoleMenuVo);
            });
        }

        return sysRoleVoPage.setRecords(sysRoleMenuVos);
    }

    @Override
    public List<Integer> getRoleMenuIds(Integer roleId) {

        LambdaQueryWrapper<SysRoleMenuPo> lambda = new QueryWrapper<SysRoleMenuPo>().lambda();
        lambda.eq(SysRoleMenuPo::getRoleId, roleId);
        lambda.select(SysRoleMenuPo::getMenuId);
        List<SysRoleMenuPo> sysRoleMenuPos = sysRoleMenuMapper.selectList(lambda);

        List<Integer> roleMenuIds = new ArrayList<>();
        if (sysRoleMenuPos.size() > 0) {
            roleMenuIds = sysRoleMenuPos.stream().map(SysRoleMenuPo::getMenuId).collect(Collectors.toList());
        }

        return roleMenuIds;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addOrUpdateRole(AddOrUpdateRoleDto dto) {
        SysRolePo sysRolePo = new SysRolePo();
        BeanUtil.copyProperties(dto, sysRolePo);
        Integer roleId;
        if (ObjectUtil.isNotNull(sysRolePo.getId())) {
            if (ObjectUtil.isNotNull(sysRolePo.getRoleName())) {
                sysRoleMapper.updateById(sysRolePo);
            }
        } else {
            sysRoleMapper.insert(sysRolePo);
        }
        roleId = sysRolePo.getId();
        if (ObjectUtil.isNotNull(sysRolePo.getRoleMenuIds()) && sysRolePo.getRoleMenuIds().size() > 0) {
            LambdaQueryWrapper<SysRoleMenuPo> lambda = new QueryWrapper<SysRoleMenuPo>().lambda();
            lambda.eq(SysRoleMenuPo::getRoleId, roleId);
            sysRoleMenuMapper.delete(lambda);

            sysRolePo.getRoleMenuIds().forEach(roleMenuId -> {
                SysRoleMenuPo sysRoleMenuPo = new SysRoleMenuPo();
                sysRoleMenuPo.setRoleId(roleId);
                sysRoleMenuPo.setMenuId(roleMenuId);
                sysRoleMenuMapper.insert(sysRoleMenuPo);
            });
        }
        return true;
    }

    @Override
    public Boolean delRole(SysRoleDto dto) {

        LambdaQueryWrapper<SysUserRolePo> wrapper = new QueryWrapper<SysUserRolePo>().lambda()
                .eq(SysUserRolePo::getRoleId, dto.getRoleId());
        List<SysUserRolePo> sysUserRolePos = sysUserRoleMapper.selectList(wrapper);
        if (sysUserRolePos.size() > 0) {
            throw new BaseException(BaseResultEnum.SYSTEM_ROLE_IS_USER);
        }
        return SqlHelper.retBool(sysRoleMapper.deleteById(dto.getRoleId()));
    }
}
