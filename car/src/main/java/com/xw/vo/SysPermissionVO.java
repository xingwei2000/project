package com.xw.vo;

import com.xw.domain.SysPermission;
import lombok.Data;

import java.util.List;


@Data
public class SysPermissionVO extends SysPermission {

    /**
     * 菜单里面的子菜单
     */
    private List<SysPermissionVO> children ;

}
