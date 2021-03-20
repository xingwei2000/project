package com.xw.mapper;

import com.xw.domain.SysRole;
import com.xw.form.BusRoleForm;
import com.xw.query.BusRoleQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {

    /**
     * 查询角色列表
     * @param busRoleQuery
     * @return
     */
    List<SysRole> queryPage(BusRoleQuery busRoleQuery);

    /**
     * 新增角色信息
     * @param busRoleForm
     */
    void insert(BusRoleForm busRoleForm);

    /**
     * 修改角色信息
     * @param busRoleForm
     */
    void update(BusRoleForm busRoleForm);

    /**
     * 根据用户ID查询所有的对应角色
     * @param userId
     * @return
     */
    List<SysRole> selectListByUserId(@Param("userId") Integer userId);

    /**
     * 删除用户 角色关系
     * @param userId
     */
    void deleteUserRole(@Param("userId") Integer userId);

    /**
     * 新增用户角关系
     * @param userId
     * @param roleId
     */
    void batchInsertRoles(@Param("userId") Integer userId, @Param("roleIds") List<Integer> roleId);
}