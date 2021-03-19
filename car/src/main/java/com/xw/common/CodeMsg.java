package com.xw.common;

/**
 * 业务消息 枚举
 */
public enum  CodeMsg {

    SUCCESS(200,"操作成功"),
    ERROR(110,"操作失败!"),
    USER_USERNAME_PASSWORD_ERROR(400001,"用户名或密码错误"),
    USER_USER_NAME_EXISTS(400002,"用户名已存在!"),
    USER_PHONE_EXISTS(400003,"手机号已存在!"),
    USER_ID_CARD_EXISTS(400004,"身份证号已存在!"),

    CUSTOMER_PHONE_EXISTS(400100,"客户手机号已存在"),
    CUSTOMER_ID_CARD_EXISTS(400101,"客户手机号已存在"),
    UPLOAD_IMG_ERROR(4000201,"汽车图片上传失败"),

    CAR_NUM_EXISTS(4000301,"车牌号已经存在"),
    CAR_IS_RENT(4000302,"车辆已出租"),

    RENT_CUSTOMER_IC_CARD_NOT_EXISTS(4000401,"客户的身份证号不存在"),
    RENT_FAILED_ERROR(4000402,"车辆出租失败!车辆信息发生了变化!"),

    RETURN_CAR_ERROR(4000501,"车辆已经归还,请不要重复还车"),
    RETURN_FAILED_RENT_CHANGED_ERROR(4000502,"还车失败，出租记录发生变化!"),

    RETURN_FAILED_RENT_CHANG_ERROR(4000503,"还车失败，车辆状态修改失败!");



    public Integer code ;  // 业务码

    public String msg ; // 业务消息

     CodeMsg(Integer code,String msg){
        this.code = code ;
        this.msg = msg ;
    }

}
