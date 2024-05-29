package com.demo.domain.system.service;

import com.demo.domain.system.entry.dto.AddOrUpdateMenuDto;
import com.demo.domain.system.entry.dto.SysMenuDto;
import com.demo.domain.system.entry.vo.SysMenuVo;
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
     * 查询角色菜单列表
     *
     * @return
     */
    List<SysMenuVo> queryRoleMenuList();

    /**
     * 查询菜单列表
     *
     * @return
     */
    List<SysMenuVo> queryMenuList();

    /**
     * 更新菜单
     *
     * @param dto
     * @return
     */
    Boolean addOrUpdateMenu(AddOrUpdateMenuDto dto);

    /**
     * 删除菜单
     *
     * @param dto
     * @return
     */
    Boolean delMenu(SysMenuDto dto);

}
