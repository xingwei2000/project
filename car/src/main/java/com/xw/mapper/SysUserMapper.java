package com.xw.mapper;

import com.xw.domain.SysUser;
import com.xw.form.SysUserForm;
import com.xw.query.SysUserQuery;
import com.xw.vo.SysUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper {

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    SysUser selectUserByNameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据参数查询用户列表
     * @param sysUserQuery
     * @return
     */
    List<SysUserVO> selectList(SysUserQuery sysUserQuery);

    /**
     * 根据用户名或者手机号或者身份证号查询用户
     * @param sysUserQuery
     * @return
     */
    SysUserVO selectUserByNameOrPhoneOrIdCard(SysUserQuery sysUserQuery);

    /**
     * 新增用户数据
     * @param sysUserForm
     */
    void insert(SysUserForm sysUserForm);

    /**
     * 修改密码
     * @param id
     * @param password
     */
    void updatePassword(@Param("id") Integer id,@Param("password") String password);
}