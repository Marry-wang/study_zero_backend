package com.demo.domain.system.service;

import com.demo.domain.system.entry.dto.AddOrUpdateRoleDto;
import com.demo.domain.system.entry.dto.SysRoleDto;
import com.demo.domain.system.entry.vo.SysRoleMenuVo;
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
    List<Integer> getRoleMenuIds(Integer roleId);

    /**
     * 新增角色
     *
     * @param dto
     * @return
     */
    Boolean addOrUpdateRole(AddOrUpdateRoleDto dto);

    /**
     * 删除角色
     *
     * @param dto
     * @return
     */
    Boolean delRole(SysRoleDto dto);


}
