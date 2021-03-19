package com.xw.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class BusRentForm {

    /**
     * 出租记录ID
     */
    private Integer id;

    /**
     * 车牌号
     */
    @NotEmpty(message = "车牌号不能为空")
    @Length(min = 8,max = 8,message = "车牌号最多只能为8位")
    private String carNum;

    /**
     * 车型 1 轿车 2 SUV 3 跑车 4 房车
     */
    @NotNull(message = "类型不能为空")
    @Range(min = 1,max = 3,message = "车牌号的值只能为1~3")
    private Integer type;

    /**
     * 租金
     */
    @NotNull(message = "租金不能为空")
    @Range(min = 1,max = 100000000,message = "租金范围：1-100000000")
    private Integer rentPrice;

    /**
     * 押金
     */
    @NotNull(message = "押金不能为空")
    @Range(min = 1,max = 100000000,message = "押金范围：1-100000000")
    private Integer deposity;

    /**
     * 客户名称
     */
    private String name;

    /**
     * 客户身份证号
     */
    @NotEmpty(message = "身份证号不能为空")
    @Length(min = 18,max =18,message = "身份证号为18位")
    private String idCard;

    @NotEmpty(message = "出租时间不能为空")
    private String rentTime ;

    /**
     * 计划租车的开始时间
     */
    private String beginTime;

    /**
     * 计划租车的结束时间
     */
    private String endTime;

    private Integer userId ;

}