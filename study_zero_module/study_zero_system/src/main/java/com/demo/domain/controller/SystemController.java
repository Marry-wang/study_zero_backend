package com.demo.domain.controller;

import com.demo.api.ZeroResult;
import com.demo.domain.entry.dto.*;
import com.demo.domain.entry.vo.SysMenuVo;
import com.demo.domain.entry.vo.SysRoleMenuVo;
import com.demo.domain.entry.vo.SysUserRoleVo;
import com.demo.domain.service.SysMenuService;
import com.demo.domain.service.SysRoleService;
import com.demo.domain.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 王孟伟
 * @Date: 2023/1/17 14:23
 * @Description:
 */
@RestController
@RequestMapping(value = "/system")
public class SystemController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping(value = "/getMenu")
    public ZeroResult<List<SysMenuVo>> menuList() {
        return ZeroResult.success(sysMenuService.queryMenuList());
    }

    @PostMapping(value = "/addOrUpdateMenu")
    public ZeroResult<Boolean> updateMenu(@RequestBody AddOrUpdateMenuDto dto) {
        return ZeroResult.success(sysMenuService.addOrUpdateMenu(dto));
    }

    @PostMapping(value = "/delMenu")
    public ZeroResult<Boolean> delMenu(@RequestBody SysMenuDto sysMenuDto) {
        return ZeroResult.success(sysMenuService.delMenu(sysMenuDto));
    }

    @PostMapping(value = "/getUser")
    public ZeroResult<List<SysUserRoleVo>> userList(@RequestBody SysUserDto dto) {
        return ZeroResult.success(sysUserService.queryUserList(dto));
    }

    @GetMapping(value = "/selectUserRole")
    public ZeroResult<List<Integer>> selectUserRole(Integer userId) {
        return ZeroResult.success(sysUserService.selectUserRole(userId));
    }

    @PostMapping(value = "/addOrUpdateUser")
    public ZeroResult<Boolean> addOrUpdateUserRole(@RequestBody AddOrUpdateUserDto dto) {
        return ZeroResult.success(sysUserService.addOrUpdateUserRole(dto));
    }


    @PostMapping(value = "/delUser")
    public ZeroResult<Boolean> delUser(@RequestBody SysUserDto dto) {
        return ZeroResult.success(sysUserService.delUser(dto));
    }

    @PostMapping(value = "/getRole")
    public ZeroResult<List<SysRoleMenuVo>> getRole(@RequestBody SysRoleDto dto) {
        return ZeroResult.success(sysRoleService.queryRoleList(dto));
    }

    @GetMapping(value = "/getRoleMenuIds")
    public ZeroResult<List<Integer>> getRoleMenuIds(Integer roleId) {
        return ZeroResult.success(sysRoleService.getRoleMenuIds(roleId));
    }

    @PostMapping(value = "/addOrUpdateRole")
    public ZeroResult<Boolean> addOrUpdateRole(@RequestBody AddOrUpdateRoleDto dto) {
        return ZeroResult.success(sysRoleService.addOrUpdateRole(dto));
    }


    @PostMapping(value = "/delRole")
    public ZeroResult<Boolean> delRole(@RequestBody SysRoleDto dto) {
        return ZeroResult.success(sysRoleService.delRole(dto));
    }
}
