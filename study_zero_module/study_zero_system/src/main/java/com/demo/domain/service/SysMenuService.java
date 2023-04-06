package com.demo.domain.service;

import com.demo.domain.entry.po.SysMenuPo;
import com.demo.domain.entry.po.SysUserPo;
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
     * 查询菜单列表
     *
     * @return
     */
    List<SysMenuPo> queryMenuList();

    /**
     * 查询菜单详情
     *
     * @param sysMenuPo
     * @return
     */
    SysMenuPo selectMenu(SysMenuPo sysMenuPo);

    /**
     * 新增菜单
     *
     * @param sysMenuPo
     * @return
     */
    Integer addMenu(SysMenuPo sysMenuPo);

    /**
     * 更新菜单
     *
     * @param sysMenuPo
     * @return
     */
    Integer updateMenu(SysMenuPo sysMenuPo);

    /**
     * 删除菜单
     *
     * @param sysMenuPo
     * @return
     */
    Integer delMenu(SysMenuPo sysMenuPo);

}
