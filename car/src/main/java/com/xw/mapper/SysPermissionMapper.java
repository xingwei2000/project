package com.xw.mapper;

import com.xw.vo.SysPermissionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermissionMapper {

    /**
     * 根据用户ID 和类型 查询权限
     * @param userId
     * @param type
     * @return
     */
    List<SysPermissionVO> selectUserPermission(@Param("userId") Integer userId,@Param("type") Integer type) ;

}