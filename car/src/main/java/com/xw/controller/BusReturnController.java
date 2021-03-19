package com.xw.controller;

import com.xw.common.validator.ValidatorUtil;
import com.xw.form.BusReturnFrom;
import com.xw.query.BusReturnQuery;
import com.xw.service.BusReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("return")
public class BusReturnController {

    @Autowired
    private BusReturnService busReturnService ;


    @RequestMapping("add.do")
    public Object add(BusReturnFrom busReturnFrom){
        ValidatorUtil.validator(busReturnFrom);
        return  busReturnService.add(busReturnFrom) ;
    }

    @RequestMapping("page.do")
    public Object page(BusReturnQuery busReturnQuery){
        return  busReturnService.queryPage(busReturnQuery) ;
    }


}
