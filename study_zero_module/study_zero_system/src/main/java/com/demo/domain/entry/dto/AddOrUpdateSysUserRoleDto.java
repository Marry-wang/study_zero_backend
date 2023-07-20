package com.demo.domain.entry.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddOrUpdateSysUserRoleDto {
    private Integer  userId;

    private String userName;

    private List<Integer> roleIdList;
}
