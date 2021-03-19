package com.xw.controller;

import com.xw.common.Result;
import com.xw.common.validator.ValidatorUtil;
import com.xw.form.BusCustomerForm;
import com.xw.query.BusCustomerQuery;
import com.xw.service.BusCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("customer")
public class BusCustomerController {

    @Autowired
    private BusCustomerService busCustomerService ;

    /**
     * 分页查询客户列表
     * @param busCustomerQuery
     * @return
     */
    @RequestMapping("page.do")
    public Object page(BusCustomerQuery busCustomerQuery){

        return busCustomerService.queryPage(busCustomerQuery);
    }

    @RequestMapping("add.do")
    public Object add(BusCustomerForm busCustomerForm){
        // 数据的格式校验
        ValidatorUtil.validator(busCustomerForm);
        return busCustomerService.add(busCustomerForm);
    }

    /**
     * 修改客户信息
     * @param busCustomerForm
     * @return
     */
    @RequestMapping("update.do")
    public Object update(BusCustomerForm busCustomerForm){
        // 数据的格式校验
        ValidatorUtil.validator(busCustomerForm);
        return busCustomerService.update(busCustomerForm);
    }

    /**
     * 导出符合条件的客户
     * @param query
     * @param response
     */
    @RequestMapping("export.do")
    public void export(BusCustomerQuery query, HttpServletResponse response) throws Exception {
        busCustomerService.exportCustomer(query,response);
    }

    @RequestMapping("import.do")
    public Object customerImport(@RequestParam("customer") MultipartFile file) throws Exception {
        return busCustomerService.batchAddFile(file);
    }
}
