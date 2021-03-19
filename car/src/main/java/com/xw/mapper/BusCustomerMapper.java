package com.xw.mapper;

import com.xw.form.BusCustomerForm;
import com.xw.query.BusCustomerQuery;
import com.xw.vo.BusCustomerVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusCustomerMapper {

    /**
     * 分页查询客户
     * @param busCustomerQuery
     * @return
     */
    List<BusCustomerVO> queryPge(BusCustomerQuery busCustomerQuery);

    /**
     * 根据手机号和身份证号查询用户
     * @param busCustomerQuery
     * @return
     */
    BusCustomerVO selectCustomerByPhoneAndIdCard(BusCustomerQuery busCustomerQuery);

    /**
     * 新增客户
     * @param busCustomerForm
     */
    void insert(BusCustomerForm busCustomerForm);

    /**
     * 更新客户信息
     * @param busCustomerForm
     */
    void update(BusCustomerForm busCustomerForm);

    /**
     * 批量新增
     * @param busCustomerVOS
     */
    void batchInsert(@Param("customers") List<BusCustomerVO> busCustomerVOS);
}