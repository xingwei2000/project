package com.xw.controller;

import com.xw.query.BusRoleQuery;
import com.xw.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色处理器
 */
@RestController
@RequestMapping("role")
public class SysRoleController {


    @Autowired
    private SysRoleService sysRoleService ;

    /**
     * 分页查询角色列表
     * @param busRoleQuery
     * @return
     */
    @RequestMapping("page.do")
    public Object page(BusRoleQuery busRoleQuery){
        return sysRoleService.query(busRoleQuery) ;
    }

}
