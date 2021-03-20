package com.xw.controller;

import com.xw.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限接口
 */
@RequestMapping("permission")
@RestController
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService ;

    /**
     * 获取当前用户的左侧菜单
     * @return
     */
    @RequestMapping("leftMenu.do")
    public Object getUserMenu(){
        return sysPermissionService.currentLeftMenu() ;
    }

}
