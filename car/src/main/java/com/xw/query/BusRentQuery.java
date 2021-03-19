package com.xw.query;

import lombok.Data;

@Data
public class BusRentQuery extends Query {


    private String carNum ;

    private String beginTime ;

    private String name ;

    private Integer flag ;

    private String minBeginTime ;

    private String maxBeginTime ;

}
