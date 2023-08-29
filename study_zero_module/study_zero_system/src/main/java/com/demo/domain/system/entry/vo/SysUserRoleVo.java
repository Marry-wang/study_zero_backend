package com.demo.domain.system.entry.vo;

import lombok.Data;

import java.util.List;

@Data
public class SysUserRoleVo {

    private Integer userId;

    private String userName;

    private List<SysRoleVo> sysRoleVos;
}
