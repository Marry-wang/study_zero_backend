package com.demo.domain.controller;

import com.demo.domain.entry.po.SysMenuPo;
import com.demo.domain.entry.po.SysUserPo;
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

    @GetMapping (value = "/getMenu")
    public List<SysMenuPo> menuList(@RequestParam(required = false) Integer menuId) {
        return sysMenuService.queryMenuList(menuId);
    }

    @PostMapping(value = "/getUser")
    public List<SysUserPo> userList() {
        return sysUserService.queryUserList();
    }
}
