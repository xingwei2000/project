package com.xw.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.domain.BusCustomer;
import lombok.Data;

import java.util.Date;

/**
 * 视图对象
 */
@Data
public class BusCustomerVO extends BusCustomer {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime ;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime ;

}
