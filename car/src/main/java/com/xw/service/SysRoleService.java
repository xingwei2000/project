package com.xw.service;

import com.xw.common.Result;
import com.xw.query.BusRoleQuery;

public interface SysRoleService{
    /**
     *
     * @param busRoleQuery
     * @return
     */
    Result query(BusRoleQuery busRoleQuery);
}
