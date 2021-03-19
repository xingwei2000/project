package com.xw.query;

import lombok.Data;

/**
 * 客户分页搜索条件
 */
@Data
public class BusCustomerQuery extends Query{

    private String name ;

    private String phone ;

    private String address ;

    private String idCard ;

}
