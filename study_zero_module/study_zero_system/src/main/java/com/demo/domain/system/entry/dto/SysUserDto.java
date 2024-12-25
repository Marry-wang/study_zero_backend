package com.demo.domain.system.entry.dto;

import lombok.Data;

@Data
public class SysUserDto {

    private Integer userId;

    private int  pageNum =1;

    private int pageSize =10;
}
