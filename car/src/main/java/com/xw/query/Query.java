package com.xw.query;

import lombok.Data;

/**
 * 所有查询参数的基类
 */
@Data
public class Query {

    /**
     * 页码
     */
    private Integer page = 1 ;
    /**
     * 每页数据条数
     */
    private Integer limit = 10 ;

}
