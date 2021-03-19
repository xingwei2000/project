package com.xw.domain;

import lombok.Data;

@Data
public class SysRole {
    /**
    * 角色ID
    */
    private Integer id;

    /**
    * 角色名称
    */
    private String name;

    /**
    * 角色标识
    */
    private String tag;

    /**
    * 角色描述
    */
    private String descp;
}