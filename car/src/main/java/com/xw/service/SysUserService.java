package com.xw.service;

import com.xw.common.Result;
import com.xw.form.SysUserForm;
import com.xw.query.SysUserQuery;

public interface SysUserService {

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    Result queryUser(String username, String password);
    /**
     * 分页查询
     * @param sysUserQuery
     * @return
     */
    Result queryPage(SysUserQuery sysUserQuery);

    /**
     * 新增用户
     * @param sysUserForm
     * @return
     */
    Result add(SysUserForm sysUserForm);

    /**
     * 重置密码
     * @param id
     * @return
     */
    Result reset(Integer id);
}
