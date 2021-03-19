package com.xw.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.domain.SysUser;
import lombok.Data;

import java.util.Date;

@Data
public class SysUserVO extends SysUser {

    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    private Date createTime ;

}
