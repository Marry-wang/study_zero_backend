package com.demo.domain.service;

import com.demo.domain.entry.dto.SysMenuDto;
import com.demo.domain.entry.po.SysMenuPo;
import com.demo.domain.entry.vo.SysMenuVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 王孟伟
 * @Date: 2023/1/16 14:46
 * @Description:
 */
@Service
public interface SysMenuService {

    /**
     * 查询用户下的菜单
     *
     * @param userId
     * @return
     */
    List<SysMenuVo> queryMenuByUserId(Integer userId);

    /**
     * 查询角色下的菜单
     *
     * @param roleId
     * @return
     */
    List<SysMenuVo> queryMenuByTRoleId(Integer roleId);

    /**
     * 查询菜单列表
     *
     * @return
     */
    List<SysMenuPo> queryMenuList();

    /**
     * 查询菜单详情
     *
     * @param dto
     * @return
     */
    SysMenuPo selectMenu(SysMenuDto dto);

    /**
     * 新增菜单
     *
     * @param sysMenuPo
     * @return
     */
    Boolean addMenu(SysMenuPo sysMenuPo);

    /**
     * 更新菜单
     *
     * @param sysMenuPo
     * @return
     */
    Boolean updateMenu(SysMenuPo sysMenuPo);

    /**
     * 删除菜单
     *
     * @param dto
     * @return
     */
    Boolean delMenu(SysMenuDto dto);

}
