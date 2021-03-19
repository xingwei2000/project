package com.xw.domain;

import java.util.Date;

public class SysUser {
    /**
    * 用户ID
    */
    private Integer id;

    /**
    * 用户名
    */
    private String userName;

    /**
    * 用户密码
    */
    private String password;

    /**
    * 性别 1 男  2 女
    */
    private Integer sex;

    /**
    * 用户电话
    */
    private String phone;

    /**
    * 真实姓名
    */
    private String realName;

    /**
    * 身份证
    */
    private String idCard;

    /**
    * 用户地址
    */
    private String address;

    /**
    * 用户图片
    */
    private String img;

    /**
    * 创建时间
    */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}