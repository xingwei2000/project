package com.xw.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.domain.BusReturn;
import lombok.Data;

import java.util.Date;

@Data
public class BusReturnVO extends BusReturn {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime ;

}
