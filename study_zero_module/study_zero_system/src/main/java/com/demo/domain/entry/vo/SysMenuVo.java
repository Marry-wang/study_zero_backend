package com.demo.domain.entry.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: 王孟伟
 * @Date: 2023/2/1 22:53
 * @Description:
 */
@Data
public class SysMenuVo {

    private Integer menuId;

    /**
     * 对应前端菜单名
     */
    private String menuName;

    /**
     * 对应前端路径
     */
    private String path;

    /**
     * 对应前端图标
     */
    private String icon;

    /**
     * 子级
     */
    private List<?> children;
}
