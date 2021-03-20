package com.xw.controller;

import com.xw.common.validator.ValidatorUtil;
import com.xw.form.BusRoleForm;
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

    /**
     * 添加用户角色
     * @param busRoleForm
     * @return
     */
    @RequestMapping("add.do")
    public Object add(BusRoleForm busRoleForm){
        ValidatorUtil.validator(busRoleForm);
        return sysRoleService.add(busRoleForm) ;
    }

    /**
     * 修改用户角色
     * @param busRoleForm
     * @return
     */
    @RequestMapping("update.do")
    public Object update(BusRoleForm busRoleForm){
        return sysRoleService.update(busRoleForm) ;
    }


}
