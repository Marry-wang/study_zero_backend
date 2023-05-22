package com.demo.domain.entry.vo;

import lombok.Data;

import java.util.List;

@Data
public class SysUserRoleVo {

    private Integer userId;

    private String userName;

    private List<SysRoleVo> sysRoleVos;
}
