package com.demo.domain.controller;

import com.demo.api.ZeroResult;
import com.demo.domain.entry.dto.SysUserDto;
import com.demo.domain.entry.po.SysMenuPo;
import com.demo.domain.entry.vo.SysUserRoleVo;
import com.demo.domain.service.SysMenuService;
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

    @PostMapping(value = "/getMenu")
    public ZeroResult<List<SysMenuPo>> menuList() {
        return ZeroResult.success(sysMenuService.queryMenuList());
    }

    @PostMapping(value = "/getUser")
    public ZeroResult<List<SysUserRoleVo>> userList(@RequestBody SysUserDto dto) {
        return ZeroResult.success(sysUserService.queryUserList(dto));
    }
}
