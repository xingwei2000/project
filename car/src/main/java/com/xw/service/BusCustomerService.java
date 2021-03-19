package com.xw.service;

import com.xw.common.Result;
import com.xw.form.BusCustomerForm;
import com.xw.query.BusCustomerQuery;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface BusCustomerService {

    /**
     * 分页查询客户
     * @param busCustomerQuery
     * @return
     */
    Result queryPage(BusCustomerQuery busCustomerQuery);


    Result add(BusCustomerForm busCustomerForm);

    Result update(BusCustomerForm busCustomerForm);

    /**
     * 导出客户
     * @param query
     * @param response
     */
    void exportCustomer(BusCustomerQuery query, HttpServletResponse response) throws Exception;

    /**
     * 处理上传Excel中的文件信息
     * 将Excel中的文件信息插入到数据库中
     * @param file
     * @return
     */
    Result batchAddFile(MultipartFile file) throws Exception;
}
