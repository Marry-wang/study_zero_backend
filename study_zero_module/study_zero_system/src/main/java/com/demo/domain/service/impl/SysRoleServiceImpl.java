package com.demo.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlHelper;
import com.demo.domain.entry.dto.SysRoleDto;
import com.demo.domain.entry.po.SysRoleMenuPo;
import com.demo.domain.entry.po.SysRolePo;
import com.demo.domain.entry.po.SysUserRolePo;
import com.demo.domain.entry.vo.SysRoleMenuVo;
import com.demo.domain.mapper.SysRoleMapper;
import com.demo.domain.mapper.SysRoleMenuMapper;
import com.demo.domain.mapper.SysUserRoleMapper;
import com.demo.domain.service.SysRoleService;
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
    public List<Integer> getRoleMenuIds(Integer roleId) {

        LambdaQueryWrapper<SysRoleMenuPo> lambda = new QueryWrapper<SysRoleMenuPo>().lambda();
        lambda.eq(SysRoleMenuPo::getRoleId,roleId);
        lambda.select(SysRoleMenuPo::getMenuId);
        List<SysRoleMenuPo> sysRoleMenuPos = sysRoleMenuMapper.selectList(lambda);

        List<Integer> roleMenuIds = new ArrayList<>();
        if(sysRoleMenuPos.size()>0){
            roleMenuIds = sysRoleMenuPos.stream().map(SysRoleMenuPo::getMenuId).collect(Collectors.toList());
        }

        return roleMenuIds;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addOrUpdateRole(SysRolePo sysRolePo) {
        Integer roleId ;
        if(ObjectUtil.isNotNull(sysRolePo.getId())){
            if(ObjectUtil.isNotNull(sysRolePo.getRoleName())) {
                sysRoleMapper.updateById(sysRolePo);
            }
        }else{
           sysRoleMapper.insert(sysRolePo);
        }
        roleId = sysRolePo.getId();
        if(sysRolePo.getRoleMenuIds().size()>0){
            LambdaQueryWrapper<SysRoleMenuPo> lambda = new QueryWrapper<SysRoleMenuPo>().lambda();
            lambda.eq(SysRoleMenuPo::getRoleId,roleId);
            sysRoleMenuMapper.delete(lambda);

            sysRolePo.getRoleMenuIds().forEach(roleMenuId->{
                SysRoleMenuPo sysRoleMenuPo = new SysRoleMenuPo();
                sysRoleMenuPo.setRoleId(roleId);
                sysRoleMenuPo.setMenuId(roleMenuId);
                sysRoleMenuMapper.insert(sysRoleMenuPo);
            });
        }
        return true;
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
