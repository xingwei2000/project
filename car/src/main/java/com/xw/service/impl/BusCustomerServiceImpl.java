package com.xw.service.impl;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xw.common.CodeMsg;
import com.xw.common.Result;
import com.xw.domain.BusCustomer;
import com.xw.form.BusCustomerForm;
import com.xw.mapper.BusCustomerMapper;
import com.xw.query.BusCustomerQuery;
import com.xw.service.BusCustomerService;
import com.xw.vo.BusCustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Service
public class BusCustomerServiceImpl implements BusCustomerService {

    @Autowired
    private BusCustomerMapper busCustomerMapper ;

    @Override
    public Result queryPage(BusCustomerQuery busCustomerQuery) {
        Page<BusCustomerVO> busCustomerVO = PageHelper.startPage(busCustomerQuery.getPage(), busCustomerQuery.getLimit());
        busCustomerMapper.queryPge(busCustomerQuery);
        return new Result(busCustomerVO.toPageInfo());
    }

    @Override
    public Result add(BusCustomerForm busCustomerForm) {
        // 进行数据的业务校验
        BusCustomerQuery busCustomerQuery = new BusCustomerQuery();
        // 校验手机号
        busCustomerQuery.setPhone(busCustomerForm.getPhone());
        BusCustomerVO busCustomerVO = busCustomerMapper.selectCustomerByPhoneAndIdCard(busCustomerQuery);
        if (busCustomerVO != null){
            return new Result(CodeMsg.CUSTOMER_PHONE_EXISTS) ;
        }
        // 校验身份证号
        busCustomerQuery = new BusCustomerQuery();
        busCustomerQuery.setIdCard(busCustomerForm.getIdCard());
        busCustomerVO = busCustomerMapper.selectCustomerByPhoneAndIdCard(busCustomerQuery);
        if (busCustomerVO!=null){
            return new Result(CodeMsg.CUSTOMER_ID_CARD_EXISTS) ;
        }
        busCustomerMapper.insert(busCustomerForm);
        return new Result();
    }

    /**
     * 修改客户信息
     * @param busCustomerForm
     * @return
     */
    @Override
    public Result update(BusCustomerForm busCustomerForm) {
        // 数据业务校验
        /**
         * 修改客户信息
         * 数据相同存在两种情况
         * 1、原始数据 ID一致
         * 2、其他数据 ID不一致
         */
        // 校验手机号
        BusCustomerQuery busCustomerQuery = new BusCustomerQuery();
        // 校验手机号
        busCustomerQuery.setPhone(busCustomerForm.getPhone());
        BusCustomerVO busCustomerVO = busCustomerMapper.selectCustomerByPhoneAndIdCard(busCustomerQuery);
        if (busCustomerVO != null && !busCustomerVO.getId().equals(busCustomerForm.getId())){
            return new Result(CodeMsg.CUSTOMER_PHONE_EXISTS) ;
        }
        // 校验身份证号
        busCustomerQuery = new BusCustomerQuery();
        busCustomerQuery.setIdCard(busCustomerForm.getIdCard());
        busCustomerVO = busCustomerMapper.selectCustomerByPhoneAndIdCard(busCustomerQuery);
        if (busCustomerVO!=null && !busCustomerVO.getId().equals(busCustomerForm.getId())){
            return new Result(CodeMsg.CUSTOMER_ID_CARD_EXISTS) ;
        }
        // 更新数据
        busCustomerMapper.update(busCustomerForm);
        return new Result();
    }

    @Override
    public void exportCustomer(BusCustomerQuery query, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        // 设置编码
        String fileName = URLEncoder.encode("客户信息.xls","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        // 查询符合条件的信息
        List<BusCustomerVO> busCustomerVOList = busCustomerMapper.queryPge(query);
        // 通过工具类创建writer，默认创建xls格式
        // 将客户转化成符合条件的Excel数据流
        ExcelWriter writer = ExcelUtil.getWriter();
        // 设置别名
        writer.addHeaderAlias("id","编号");
        writer.addHeaderAlias("name","姓名");
        writer.addHeaderAlias("phone","手机号");
        writer.addHeaderAlias("address","地址");
        writer.addHeaderAlias("idCard","身份证号");
        writer.addHeaderAlias("sex","性别");
        writer.addHeaderAlias("createTime","创建时间");
        writer.addHeaderAlias("updateTime","修改时间");
        // 将Excel数据流输出给浏览器
        writer.write(busCustomerVOList,true);
        // 关闭流
        writer.flush(outputStream,true);
    }

    // 批量添加
    @Override
    public Result batchAddFile(MultipartFile file) throws Exception {
        // 将文件转为成流
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        // 将数据转化成对象
        List<BusCustomerVO> busCustomerVOS = reader.readAll(BusCustomerVO.class);
        System.out.println(busCustomerVOS);
        // 批量添加
        busCustomerMapper.batchInsert(busCustomerVOS);
        // 解析文件上传
        return new Result();
    }
}
