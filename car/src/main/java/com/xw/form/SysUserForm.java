package com.xw.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 接收用户表单的更新数据
 */
@Data
public class SysUserForm {

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户名
     */
    @NotEmpty(message = "登录名不能为空")
    @Length(min = 6,max = 15,message = "登录名是6-15位")
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 性别 1 男  2 女
     */
    @NotNull(message = "性别不能为空")
    @Range(min = 1,max = 2,message = "性别只能是男(1)或女(2)")
    private Integer sex;

    /**
     * 用户电话
     */
    @NotEmpty(message = "手机号不能为空")
    @Length(min = 11,max = 11,message = "手机号必须为11位")
    private String phone;

    /**
     * 真实姓名
     */
    @NotEmpty(message = "真实姓名不能为空")
    @Length(max = 20,message = "真实姓名最多20位字符")
    private String realName;

    /**
     * 身份证
     */
    @NotEmpty(message = "身份证号不能为空")
    @Length(max = 18,message = "身份证号18位")
    private String idCard;

    /**
     * 用户地址
     */
    @NotEmpty(message = "地址不能为空")
    @Length(max = 100,message = "地址最多100位字符")
    private String address;

    /**
     * 用户图片
     */
    private String img;


}
