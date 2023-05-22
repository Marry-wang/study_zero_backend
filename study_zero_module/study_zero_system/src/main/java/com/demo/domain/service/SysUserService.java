package com.demo.domain.service;

import com.demo.domain.entry.dto.SysUserDto;
import com.demo.domain.entry.dto.AddSysUserRoleDto;
import com.demo.domain.entry.dto.UpdateSysUserRoleDto;
import com.demo.domain.entry.po.SysUserPo;
import com.demo.domain.entry.vo.SysUserRoleVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 王孟伟
 * @Date: 2023/2/21 21:51
 * @Description:
 */
@Service
public interface SysUserService {

    /**
     * 查询用户列表
     *
     * @return
     */
    List<SysUserRoleVo> queryUserList(SysUserDto dto);

    /**
     * 更新用户
     *
     * @param dto
     * @return
     */
    Boolean updateUser(UpdateSysUserRoleDto dto);

    /**
     * 查询用户
     *
     * @param sysUserPo
     * @return
     */
    SysUserRoleVo selectUser(SysUserPo sysUserPo);

    /**
     * 删除用户
     *
     * @param sysUserPo
     * @return
     */
    Boolean delUser(SysUserPo sysUserPo);

    /**
     * 添加角色权限
     *
     * @param dto
     * @return
     */
    Boolean addUserRole(AddSysUserRoleDto dto);
}
