package com.xw.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户查询参数类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserQuery extends Query {

    private String realName ;

    private String phone ;

    private String address ;

    private String userName ;

    private String idCard ;

}
