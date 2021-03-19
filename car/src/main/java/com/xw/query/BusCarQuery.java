package com.xw.query;

import lombok.Data;

@Data
public class BusCarQuery extends Query{

    private String carNum ;

    private Integer minPrice ;

    private Integer maxPrice ;

    private Integer minRentPrice ;

    private Integer maxRentPrice ;

    private String descp ;

    private String color ;

    private Integer isRent ;

    private Integer type ;

}
