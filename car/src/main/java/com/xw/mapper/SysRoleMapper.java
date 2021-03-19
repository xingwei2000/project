package com.xw.mapper;

import com.xw.domain.SysRole;
import com.xw.query.BusRoleQuery;

import java.util.List;

public interface SysRoleMapper {

    /**
     * 查询角色列表
     * @param busRoleQuery
     * @return
     */
    List<SysRole> queryPage(BusRoleQuery busRoleQuery);


}