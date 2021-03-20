package com.xw.service;

import com.xw.common.Result;
import com.xw.form.BusRoleForm;
import com.xw.query.BusRoleQuery;

import java.util.List;

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

    /**
     * 查询所有的角色
     * @return
     */
    Result queryAll();

    /**
     * 根据用户ID插叙用户所有的角色
     * @param userId
     * @return
     */
    Result queryUseRoleByID(Integer userId);

    /**
     * 设置用户的角色
     * @param userId
     * @param roleId
     * @return
     */
    Result insertUserRoles(Integer userId, List<Integer> roleId);
}
