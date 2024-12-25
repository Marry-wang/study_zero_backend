package com.demo.domain.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.demo.domain.system.entry.dto.SysUserDto;
import com.demo.domain.system.entry.dto.AddOrUpdateUserDto;
import com.demo.domain.system.entry.po.SysRolePo;
import com.demo.domain.system.entry.po.SysUserPo;
import com.demo.domain.system.entry.po.SysUserRolePo;
import com.demo.domain.system.entry.vo.SysRoleVo;
import com.demo.domain.system.entry.vo.SysUserRoleVo;
import com.demo.domain.system.mapper.SysRoleMapper;
import com.demo.domain.system.mapper.SysUserMapper;
import com.demo.domain.system.mapper.SysUserRoleMapper;
import com.demo.domain.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Page<SysUserRoleVo> queryUserList(SysUserDto dto) {
        LambdaQueryWrapper<SysUserPo> sysUserPoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!Objects.isNull(dto.getUserId())) {
            sysUserPoLambdaQueryWrapper.eq(SysUserPo::getId, dto.getUserId());
        }

        Page<SysUserPo> sysUserPoPage = sysUserMapper.selectPage(new Page<>(dto.getPageNum(), dto.getPageSize()), sysUserPoLambdaQueryWrapper);
        Page<SysUserRoleVo> sysUserVoPage = new Page<>();
        List<SysUserPo> sysUserPos = sysUserPoPage.getRecords();
        BeanUtil.copyProperties(sysUserPoPage, sysUserVoPage);

        List<SysUserRoleVo> sysUserRoleVos = new ArrayList<>();
        if (sysUserPos.size() > 0) {

            sysUserPos.forEach(
                    sysUserPo -> {
                        SysUserRoleVo sysUserRoleVo = new SysUserRoleVo();
                        sysUserRoleVo.setUserId(sysUserPo.getId());
                        sysUserRoleVo.setUserName(sysUserPo.getUserName());
                        sysUserRoleVos.add(sysUserRoleVo);
                    }
            );
        }
        sysUserVoPage.setRecords(sysUserRoleVos);
        return sysUserVoPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addOrUpdateUserRole(AddOrUpdateUserDto dto) {
        if (ObjectUtil.isNotNull(dto.getUserId())) {
            Integer userId = dto.getUserId();
            if (ObjectUtil.isNotNull(dto.getUserName())) {
                String userName = dto.getUserName();
                SysUserPo sysUserPo = new SysUserPo();
                sysUserPo.setId(userId);
                sysUserPo.setUserName(userName);
                sysUserMapper.updateById(sysUserPo);
            }

            List<Integer> roleIdList = dto.getRoleIdList();
            if (ObjectUtil.isNotNull(dto.getRoleIdList()) && roleIdList.size() > 0) {
                LambdaQueryWrapper<SysUserRolePo> delWrapper = new QueryWrapper<SysUserRolePo>().lambda().eq(SysUserRolePo::getUserId, userId);
                sysUserRoleMapper.delete(delWrapper);
                roleIdList.forEach(
                        roleId -> {
                            SysUserRolePo sysUserRolePo = new SysUserRolePo();
                            sysUserRolePo.setRoleId(roleId);
                            sysUserRolePo.setUserId(userId);
                            sysUserRoleMapper.insert(sysUserRolePo);
                        }
                );
            }
            return true;
        } else {
            String userName = dto.getUserName();
            List<Integer> roleIdList = dto.getRoleIdList();

            SysUserPo sysUserPo = new SysUserPo();
            sysUserPo.setUserName(userName);
            sysUserMapper.insert(sysUserPo);

            if (ObjectUtil.isNotNull(dto.getRoleIdList()) && roleIdList.size() > 0) {
                roleIdList.forEach(
                        roleId -> {
                            SysUserRolePo sysUserRolePo = new SysUserRolePo();
                            sysUserRolePo.setRoleId(roleId);
                            sysUserRolePo.setUserId(sysUserPo.getId());
                            sysUserRoleMapper.insert(sysUserRolePo);
                        }
                );
            }
            return true;
        }
    }

    @Override
    public List<Integer> selectUserRole(Integer userId) {
        List<SysRoleVo> sysRoleVos = selectUserRoleByUserId(userId);
        List<Integer> roleIds = new ArrayList<>();
        if (sysRoleVos.size() > 0) {
            roleIds = sysRoleVos.stream().map(SysRoleVo::getRoleId).collect(Collectors.toList());
        }
        return roleIds;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delUser(SysUserDto dto) {
        LambdaQueryWrapper<SysUserRolePo> queryWrapper = new QueryWrapper<SysUserRolePo>().lambda()
                .eq(SysUserRolePo::getUserId, dto.getUserId());
        sysUserRoleMapper.delete(queryWrapper);
        return SqlHelper.retBool(sysUserMapper.deleteById(dto.getUserId()));
    }

    private List<SysRoleVo> selectUserRoleByUserId(Integer userId) {

        LambdaQueryWrapper<SysUserRolePo> userRoleWrapper = new QueryWrapper<SysUserRolePo>().lambda()
                .eq(SysUserRolePo::getUserId, userId);
        List<SysUserRolePo> sysUserRolePos = sysUserRoleMapper.selectList(userRoleWrapper);
        List<SysRoleVo> sysRoleVos = new ArrayList<>();
        if (sysUserRolePos.size() > 0) {
            sysUserRolePos.forEach(userRolePo -> {
                SysRolePo sysRolePo = sysRoleMapper.selectById(userRolePo.getRoleId());

                SysRoleVo sysRoleVo = new SysRoleVo();
                sysRoleVo.setRoleId(sysRolePo.getId());
                sysRoleVo.setRoleName(sysRolePo.getRoleName());
                sysRoleVos.add(sysRoleVo);
            });
        }
        return sysRoleVos;
    }

    ;
}
