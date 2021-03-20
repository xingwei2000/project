package com.xw.domain;

import lombok.Data;

@Data
public class SysPermission {
    /**
    * 权限ID
    */
    private Integer id;

    /**
    * 权限名称
    */
    private String title;

    /**
    * 菜单的链接
    */
    private String href;

    /**
    * 权限的图标
    */
    private String icon;

    /**
    * 是否展开 1 展开  0 不展开
    */
    private Boolean spread;

    /**
    * 权限类型 1 菜单 2 按钮
    */
    private Integer type;

    /**
    * 权限的表达式
    */
    private String tag;

    /**
    * 排序值 控制权限的顺序显示
    */
    private Integer sort;

    /**
    * 父权限ID
    */
    private Integer parentId;
}