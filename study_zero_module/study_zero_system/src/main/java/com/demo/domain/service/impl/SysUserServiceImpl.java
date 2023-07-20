package com.demo.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlHelper;
import com.demo.domain.entry.dto.SysUserDto;
import com.demo.domain.entry.dto.AddOrUpdateSysUserRoleDto;
import com.demo.domain.entry.po.SysRolePo;
import com.demo.domain.entry.po.SysUserPo;
import com.demo.domain.entry.po.SysUserRolePo;
import com.demo.domain.entry.vo.SysRoleVo;
import com.demo.domain.entry.vo.SysUserRoleVo;
import com.demo.domain.mapper.SysRoleMapper;
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

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysUserRoleVo> queryUserList(SysUserDto dto) {
        LambdaQueryWrapper<SysUserPo> sysUserPoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!Objects.isNull(dto.getUserId())) {
            sysUserPoLambdaQueryWrapper.eq(SysUserPo::getId, dto.getUserId());
        }

        List<SysUserPo> sysUserPos = sysUserMapper.selectList(sysUserPoLambdaQueryWrapper);

        List<SysUserRoleVo> sysUserRoleVos = new ArrayList<>();
        if (sysUserPos.size() > 0) {

            sysUserPos.forEach(
                    sysUserPo -> {
                        SysUserRoleVo sysUserRoleVo = new SysUserRoleVo();
                        sysUserRoleVo.setUserId(sysUserPo.getId());
                        sysUserRoleVo.setUserName(sysUserPo.getUserName());
                        selectUserRoleByUserId(sysUserRoleVo);
                        sysUserRoleVos.add(sysUserRoleVo);
                    }
            );
        }
        return sysUserRoleVos;
    }

    @Override
    public Boolean addOrUpdateUserRole(AddOrUpdateSysUserRoleDto dto) {
        if(ObjectUtil.isNotNull(dto.getUserId())){
            Integer userId = dto.getUserId();
            String userName = dto.getUserName();
            List<Integer> roleIdList = dto.getRoleIdList();

            SysUserPo sysUserPo = new SysUserPo();
            sysUserPo.setId(userId);
            sysUserPo.setUserName(userName);
            sysUserMapper.updateById(sysUserPo);

//            LambdaQueryWrapper<SysUserRolePo> delWrapper = new QueryWrapper<SysUserRolePo>().lambda().eq(SysUserRolePo::getUserId, userId);
//            sysUserRoleMapper.delete(delWrapper);
//
//            roleIdList.forEach(
//                    roleId -> {
//                        SysUserRolePo sysUserRolePo = new SysUserRolePo();
//                        sysUserRolePo.setRoleId(roleId);
//                        sysUserRolePo.setUserId(sysUserPo.getId());
//                        sysUserRoleMapper.insert(sysUserRolePo);
//                    }
//            );
            return true;
        }else{
            String userName = dto.getUserName();
            List<Integer> roleIdList = dto.getRoleIdList();

            SysUserPo sysUserPo = new SysUserPo();
            sysUserPo.setUserName(userName);
            sysUserMapper.insert(sysUserPo);

//            roleIdList.forEach(
//                    roleId -> {
//                        SysUserRolePo sysUserRolePo = new SysUserRolePo();
//                        sysUserRolePo.setRoleId(roleId);
//                        sysUserRolePo.setUserId(sysUserPo.getId());
//                        sysUserRoleMapper.insert(sysUserRolePo);
//                    }
//            );
            return true;
        }
    }

    @Override
    public SysUserRoleVo selectUser(SysUserPo sysUserPo) {

        SysUserRoleVo sysUserRoleVo = new SysUserRoleVo();

        SysUserPo userPo = sysUserMapper.selectById(sysUserPo.getId());
        sysUserRoleVo.setUserId(userPo.getId());
        sysUserRoleVo.setUserName(userPo.getUserName());
        selectUserRoleByUserId(sysUserRoleVo);
        return sysUserRoleVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delUser(SysUserDto dto) {
        LambdaQueryWrapper<SysUserRolePo> queryWrapper = new QueryWrapper<SysUserRolePo>().lambda()
                .eq(SysUserRolePo::getUserId, dto.getUserId());
        sysUserRoleMapper.delete(queryWrapper);
        return SqlHelper.retBool(sysUserMapper.deleteById(dto.getUserId()));
    }

    private SysUserRoleVo selectUserRoleByUserId(SysUserRoleVo userRoleVo) {

        LambdaQueryWrapper<SysUserRolePo> userRoleWrapper = new QueryWrapper<SysUserRolePo>().lambda()
                .eq(SysUserRolePo::getUserId, userRoleVo.getUserId());
        List<SysUserRolePo> sysUserRolePos = sysUserRoleMapper.selectList(userRoleWrapper);

        if (sysUserRolePos.size() > 0) {
            List<SysRoleVo> sysRoleVos = new ArrayList<>();
            sysUserRolePos.forEach(userRolePo -> {
                SysRolePo sysRolePo = sysRoleMapper.selectById(userRolePo.getRoleId());

                SysRoleVo sysRoleVo = new SysRoleVo();
                sysRoleVo.setRoleId(sysRolePo.getId());
                sysRoleVo.setRoleName(sysRolePo.getRoleName());
                sysRoleVos.add(sysRoleVo);
            });
            userRoleVo.setSysRoleVos(sysRoleVos);
        }
        return userRoleVo;
    }

    ;
}
