package com.xw.service;

import com.xw.common.Result;
import com.xw.form.BusRoleForm;
import com.xw.query.BusRoleQuery;

public interface SysRoleService{
    /**
     *
     * @param busRoleQuery
     * @return
     */
    Result query(BusRoleQuery busRoleQuery);

    /**
     * 新增角色信息
     * @param busRoleForm
     * @return
     */
    Result add(BusRoleForm busRoleForm);

    /**
     * 修改角色信息
     * @param busRoleForm
     * @return
     */
    Result update(BusRoleForm busRoleForm);
}
