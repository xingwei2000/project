package com.xw.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BusCar {
    /**
     * ID
     */
    private Integer id;

    /**
     * 车牌号
     */
    private String carNum;

    /**
     * 车型 1 轿车 2 SUV 3跑车 4 房车
     */
    private Integer type;

    /**
     * 汽车颜色
     */
    private String color;

    /**
     * 汽车价格
     */
    private Integer price;

    /**
     * 出租价格
     */
    private Integer rentPrice;

    /**
     * 押金
     */
    private Integer deposity;

    /**
     * 出租状态 1 未出租 2 已出租
     */
    private Integer isRent;

    /**
     * 描述
     */
    private String descp;

    /**
     * 汽车图片
     */
    private String img;

    /**
     * 版本号标识
     */
    private Integer version;

    /**
     * 创建时间
     */
    private Date createTime;

}