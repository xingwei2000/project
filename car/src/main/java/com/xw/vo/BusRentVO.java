package com.xw.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.domain.BusRent;
import lombok.Data;

import java.util.Date;

@Data
public class BusRentVO extends BusRent {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime ;

}
