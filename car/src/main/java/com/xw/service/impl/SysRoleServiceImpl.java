package com.xw.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xw.common.Result;
import com.xw.domain.SysRole;
import com.xw.form.BusRoleForm;
import com.xw.mapper.SysRoleMapper;
import com.xw.query.BusRoleQuery;
import com.xw.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public Result add(BusRoleForm busRoleForm) {
        sysRoleMapper.insert(busRoleForm) ;
        return new Result();
    }

    @Override
    public Result update(BusRoleForm busRoleForm) {
        sysRoleMapper.update(busRoleForm) ;
        return new Result();
    }

    @Override
    public Result queryAll() {
        BusRoleQuery busRoleQuery = new BusRoleQuery();
        return new Result( sysRoleMapper.queryPage(busRoleQuery));
    }

    @Override
    public Result queryUseRoleByID(Integer userId) {
        List<SysRole> roles = sysRoleMapper.selectListByUserId(userId) ;
        return new Result(roles);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result insertUserRoles(Integer userId, List<Integer> roleId) {
        // 删除用户的所有角色关系
        sysRoleMapper.deleteUserRole(userId) ;
        // 新增
        sysRoleMapper.batchInsertRoles(userId,roleId);
        return new Result();
    }
}
