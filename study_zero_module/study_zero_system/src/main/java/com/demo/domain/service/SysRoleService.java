package com.demo.domain.service;

import com.demo.domain.entry.dto.SysRoleDto;
import com.demo.domain.entry.po.SysRolePo;
import com.demo.domain.entry.vo.SysRoleMenuVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 王孟伟
 * @Date: 2023/2/21 21:52
 * @Description:
 */
@Service
public interface SysRoleService {

    /**
     * 查询角色
     *
     * @return
     */
    List<SysRoleMenuVo> queryRoleList(SysRoleDto dto);

    /**
     * 查看角色详情
     *
     * @return
     */
    SysRoleMenuVo selectRole(SysRolePo sysRolePo);

    /**
     * 新增角色
     *
     * @param sysRolePo
     * @return
     */
    Boolean addRole(SysRolePo sysRolePo);

    /**
     * 更新角色
     *
     * @param sysRolePo
     * @return
     */
    Boolean updateRole(SysRolePo sysRolePo);

    /**
     * 删除角色
     *
     * @param sysRolePo
     * @return
     */
    Boolean delRole(SysRolePo sysRolePo);


}
