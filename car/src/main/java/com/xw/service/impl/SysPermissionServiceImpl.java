package com.xw.service.impl;

import com.xw.common.Constant;
import com.xw.common.Result;
import com.xw.service.SysPermissionService;
import com.xw.shiro.ActiveUser;
import com.xw.vo.SysPermissionVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.xw.mapper.SysPermissionMapper;

import java.util.*;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;


    @Override
    public Result currentLeftMenu() {
        // 获取认证主体
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
        Integer userId = activeUser.getSysUser().getId();

        // 根据用户ID 和权限类型 查询所有的菜单
        List<SysPermissionVO> sysPermissionVOS = sysPermissionMapper.selectUserPermission(userId, Constant.PERMISSION_TYPE_MENU);
        // 将数组转化为父子关系
        Map<Integer,SysPermissionVO> menu = new HashMap<>() ;
        long start = System.currentTimeMillis();
        // 遍历所有的菜单
        for (SysPermissionVO sysPermissionVO : sysPermissionVOS) {
            // 获取父菜单ID 如果这个菜单的ID 为 0 则说明 父菜单
            Integer parentId = sysPermissionVO.getParentId();
            if (parentId.equals(Constant.MENU_LV1)){
                // 初始化父菜单容器
                sysPermissionVO.setChildren(new ArrayList<SysPermissionVO>());
                menu.put(sysPermissionVO.getId(),sysPermissionVO) ;
            }
        }
        // 遍历所有菜单 为一级菜单设置二级菜单
        for (SysPermissionVO sysPermissionVO : sysPermissionVOS) {
            // 获取带单的父菜单
            Integer parentId = sysPermissionVO.getParentId();
            // 判断一级菜单容器中  是否有当前菜单的父菜单
            if (menu.containsKey(parentId)){
                // 获取对应的父菜单
                SysPermissionVO sysPermissionVO1 = menu.get(parentId);
                // 将当前的菜单 放入对应的 父菜单的子容器中
                sysPermissionVO1.getChildren().add(sysPermissionVO) ;
            }
        }
        // 获取的map中所有的一级菜单集合
        Collection<SysPermissionVO> values = menu.values();
        // 返回给controller
        return new Result(values);
    }
}
