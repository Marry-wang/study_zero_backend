package com.demo.domain.entry.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddSysUserRoleDto {

    private String userName;

    private List<Integer> roleIdList;
}
