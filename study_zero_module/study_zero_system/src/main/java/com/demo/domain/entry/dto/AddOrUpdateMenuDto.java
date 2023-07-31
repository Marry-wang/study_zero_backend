package com.demo.domain.entry.dto;

import com.demo.domain.entry.po.SysMenuPo;
import lombok.Data;

import java.util.List;

@Data
public class AddOrUpdateMenuDto {
    private Integer id;

    private Integer parentId;

    private String path;

    private String menuName;

    private String icon;

    private List<SysMenuPo> children;
}
