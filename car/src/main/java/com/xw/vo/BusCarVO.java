package com.xw.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.domain.BusCar;
import lombok.Data;

import java.util.Date;

@Data
public class BusCarVO extends BusCar {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime ;

}
