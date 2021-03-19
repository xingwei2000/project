package com.xw.form;

import com.xw.domain.BusCustomer;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class BusCustomerForm extends BusCustomer {


    /**
     * ID
     */
    private Integer id;

    /**
     * 客户名称
     */
    @NotEmpty(message = "客户名称不能为空")
    @Length(min = 2,max = 10,message = "客户姓名长度为2-10位")
    private String name;

    /**
     * 客户名称
     */
    @NotEmpty(message = "手机号不能为空")
    @Length(min =11,max = 11,message = "手机号长度为11位")
    private String phone;

    /**
     * 客户地址
     */
    @NotEmpty(message = "地址不能为空")
    @Length(max = 100,message = "地址长度为最多100位")
    private String address;

    /**
     * 客户身份证号
     */
    @NotEmpty(message = "身份证号不能为空")
    @Length(min = 18,max = 18,message = "身份证号长度为18位")
    private String idCard;

    /**
     * 客户性别 1男 2 女
     */
    @NotNull(message = "客户性别不能为空")
    @Range(min = 1,max = 2,message = "客户性别只能为男(1)女(2)")
    private Integer sex;


}
