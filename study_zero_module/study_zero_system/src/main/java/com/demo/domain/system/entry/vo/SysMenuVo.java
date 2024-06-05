package com.demo.domain.system.entry.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: 王孟伟
 * @Date: 2023/2/1 22:53
 * @Description:
 */
@Data
public class SysMenuVo {

    private Integer id;

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
     * 跳转路径
     */
    private String redirect;

    /**
     * 对应的路径位置
     */
    private String component;

    /**
     * 子级
     */
    private List<?> children;
}
