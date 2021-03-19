package com.xw.controller;

import com.xw.common.Constant;
import com.xw.domain.BusCar;
import com.xw.service.BusCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 负责跳转的controller
 * 因为所有的页面都在WEB-INF下面，是无法直接访问的，必须使用服务器内部转发使用
 *
 */
@Controller
@RequestMapping("page")
public class PageController {

    @Autowired
    private BusCarService busCarService ;

    /**
     * 跳转到主页面
     * @return
     */
    @RequestMapping("main.do")
    public String main(){
        return "main" ;
    }

    @RequestMapping("user/list.do")
    public String list(){
        return "user/list" ;
    }

    /**
     * 跳转到客户列标签
     * @return
     */
    @RequestMapping("customer/list.do")
    public String customerList(){
        return "customer/list" ;
    }

    @RequestMapping("car/list.do")
    public String carList(){
        return "car/list" ;
    }

    /**
     * 跳转到租车列表
     * @return
     */
    @RequestMapping("rent/list.do")
    public String rentList(){
        return "rent/list" ;
    }

    @RequestMapping("return/list.do")
    public String returnList(){
        return "return/list" ;
    }

}
