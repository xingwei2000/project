package com.xw.controller;

import com.xw.common.validator.ValidatorUtil;
import com.xw.form.BusRentForm;
import com.xw.query.BusRentQuery;
import com.xw.service.BusRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 出租记录的控制器
 */
@RestController
@RequestMapping("rent")
public class BusRentController {

    @Autowired
    private BusRentService busRentService ;

    @RequestMapping("add.do")
    public Object add(BusRentForm busRentFrom){
        // 数据校验
        ValidatorUtil.validator(busRentFrom);
        return busRentService.add(busRentFrom) ;
    }

    @RequestMapping("page.do")
    public Object list(BusRentQuery busRentQuery){
        // 数据校验
        return busRentService.queryPage(busRentQuery) ;
    }

}
