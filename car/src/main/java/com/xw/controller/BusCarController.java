package com.xw.controller;

import com.xw.common.validator.ValidatorUtil;
import com.xw.form.BusCarForm;
import com.xw.query.BusCarQuery;
import com.xw.service.BusCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("car")
@RestController
public class BusCarController {

    @Autowired
    private BusCarService busCarService ;

    @RequestMapping("page.do")
    public Object page(BusCarQuery query){
        return busCarService.query(query);
    }

    @RequestMapping("add.do")
    public Object add(BusCarForm form){
        ValidatorUtil.validator(form);
        return busCarService.add(form);
    }


}
