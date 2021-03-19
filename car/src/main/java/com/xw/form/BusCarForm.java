package com.xw.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class BusCarForm{


    /**
     * ID
     */
    private Integer id;

    /**
     * 车牌号
     */
    @NotEmpty(message = "类型不能为空")
    @Length(min = 8,max = 8,message = "车牌号不能必须是8位")
    private String carNum;

    /**
     * 车型 1 轿车 2 SUV 3跑车 4 房车
     */

    @NotNull(message = "车牌号不能为空")
    @Range(message = "类型不能为空")
    private Integer type;

    /**
     * 汽车颜色
     */
    @NotEmpty(message = "颜色不能为空")
    @Length(min = 2,max = 10,message = "颜色不能为空")
    private String color;

    /**
     * 汽车价格
     */
    @NotNull(message = "价格不能为空")
    @Range(min = 10,max = 100000000,message = "价格不能为空")
    private Integer price;

    /**
     * 出租价格
     */
    @NotNull(message = "出租价格不能为空")
    @Range(min = 1,max = 100000000,message = "出租价格不能为空")
    private Integer rentPrice;

    /**
     * 押金
     */
    @NotNull(message = "押金不能为空")
    @Range(min = 1,max = 1000000,message = "押金不能为空")
    private Integer deposity;

    /**
     * 描述
     */
    @Length(max = 100,message = "汽车描述最多100个字符")
    private String descp;

    /**
     * 汽车图片
     */
    @NotNull(message = "汽车图片不能为空")
    @Length(max = 100,message = "汽车图片最多100位字符")
    private String img;

}
