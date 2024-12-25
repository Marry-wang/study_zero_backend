package com.demo.domain.system.entry.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddOrUpdateUserDto {
    private Integer userId;

    private String userName;

    private List<Integer> roleIdList;
}
