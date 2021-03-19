package com.xw.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.xw.mapper.SysRoleMapper;
@Service
public class SysRoleService{

    @Resource
    private SysRoleMapper sysRoleMapper;

}
