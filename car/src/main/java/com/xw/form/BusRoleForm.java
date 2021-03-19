package com.xw.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class BusRoleForm {

    private Integer id ;

    @NotEmpty(message = "角色名不能为空")
    @Length(max = 20,message = "角色名称最多20个字符")
    private String name ;

    @NotEmpty(message = "角色标识不能为空")
    @Length(max = 20,message = "角色标识最多20个字符")
    private String tag ;

    @Length(max = 100,message = "描述最多100个字符")
    private String descp ;
}
