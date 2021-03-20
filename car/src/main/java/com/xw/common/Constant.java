package com.xw.common;

/**
 * 常量接口
 */
public interface Constant {

    /**
     * 校验参数不通过
     */
    Integer PARAM_CHECKED_ERROR = 4000001 ;
    /**
     * MD5加密的盐
     */
    String MD5_SALT = "day day up" ;
    /**
     * 密码常量 默认的登录密码
     */
    String DEFAULT_PASSWORD = "123456";
    /**
     * 上传文件的文件夹
     */
    String UPLOAD_FOLDER = "upload" ;

    /**
     * 没有出租
     */
    Integer IS_NOT_RENT = 1;
    /**
     * 已出租
     */
    Integer IS_RENT = 2 ;

    /**
     * 车辆未归还
     */
    Integer CAR_RETURN_NOT = 1 ;
    /**
     * 车辆已还车
     */
    Integer CAR_RETURN_ED = 2 ;
    /**
     * 菜单权限 1
     */
    Integer PERMISSION_TYPE_MENU = 1;
    /**
     * 按钮权限 2
     */
    Integer PERMISSION_TYPE_BUTTON = 2;
    /**
     * 一级菜单
     */
    Integer MENU_LV1 = 0 ;

}
