package com.demo.domain.system.entry.dto;

import lombok.Data;

@Data
public class SysRoleDto {

    private Integer roleId;

    private int pageNum = 1;

    private int pageSize = 10;
}
