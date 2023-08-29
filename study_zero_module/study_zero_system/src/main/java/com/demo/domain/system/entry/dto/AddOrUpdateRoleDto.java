package com.demo.domain.system.entry.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddOrUpdateRoleDto {
    private Integer id;

    private String roleName;

    private List<Integer> roleMenuIds;
}
