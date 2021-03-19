package com.xw.shiro;

import com.xw.domain.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class ActiveUser {

    /**
     * 当前用户
     */
    private SysUser sysUser ;

    /**
     * 用户真实名称
     */
    private String realname ;

    /**
     * 用户所有的角色
     */
    private List<String> roles ;

    /**
     * 用户所有的权限
     */
    private List<String> permission ;

}
