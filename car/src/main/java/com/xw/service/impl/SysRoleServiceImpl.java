package com.xw.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xw.common.Result;
import com.xw.domain.SysRole;
import com.xw.mapper.SysRoleMapper;
import com.xw.query.BusRoleQuery;
import com.xw.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper ;

    @Override
    public Result query(BusRoleQuery busRoleQuery) {
        Page<SysRole> sysRoles = PageHelper.startPage(busRoleQuery.getPage(), busRoleQuery.getLimit());
        sysRoleMapper.queryPage(busRoleQuery) ;
        return new Result(sysRoles.toPageInfo());
    }
}
