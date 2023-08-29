package com.demo.domain.system.entry.dto;

import com.demo.domain.system.entry.po.SysMenuPo;
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
