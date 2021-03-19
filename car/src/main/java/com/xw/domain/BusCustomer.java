package com.xw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusCustomer {
    /**
    * ID
    */
    private Integer id;

    /**
    * 客户名称
    */
    private String name;

    /**
    * 客户名称
    */
    private String phone;

    /**
    * 客户地址
    */
    private String address;

    /**
    * 客户身份证号
    */
    private String idCard;

    /**
    * 客户性别 1男 2 女
    */
    private Integer sex;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}