package com.xw.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xw.common.CodeMsg;
import com.xw.common.Constant;
import com.xw.common.Result;
import com.xw.common.exception.BussiException;
import com.xw.form.BusRentForm;
import com.xw.mapper.BusCarMapper;
import com.xw.mapper.BusCustomerMapper;
import com.xw.mapper.BusRentMapper;
import com.xw.query.BusCustomerQuery;
import com.xw.query.BusRentQuery;
import com.xw.service.BusRentService;
import com.xw.shiro.ActiveUser;
import com.xw.vo.BusCarVO;
import com.xw.vo.BusCustomerVO;
import com.xw.vo.BusRentVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BusRentServiceImpl  implements BusRentService {

    @Autowired
    private BusRentMapper busRentMapper ;

    @Autowired
    private BusCustomerMapper busCustomerMapper ;

    @Autowired
    private BusCarMapper busCarMapper ;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result add(BusRentForm busRentForm) {

        // 根据身份证号查询客户 校验客户是否存在
        BusCustomerQuery busCustomerQuery = new BusCustomerQuery() ;
        busCustomerQuery.setIdCard(busRentForm.getIdCard());
        // 校验客户的身份证
        BusCustomerVO busCustomerVO = busCustomerMapper.selectCustomerByPhoneAndIdCard(busCustomerQuery);
        if (busCustomerVO == null){
            return new Result(CodeMsg.RENT_CUSTOMER_IC_CARD_NOT_EXISTS);
        }
        // 校验出租的车是否存在
        // 根据车牌号查询车辆信息
        BusCarVO busCarVO = busCarMapper.selectOneByCarNum(busRentForm.getCarNum());
        // 判断出租状态是否是未出租
        if (busCarVO.getIsRent().equals(Constant.IS_RENT)){
            return new Result(CodeMsg.CAR_IS_RENT) ;
        }
        // 乐观锁处理并发问题
        // 修改车辆状态
        Integer rows = busCarMapper.update(busCarVO.getId(), Constant.IS_RENT, busCarVO.getVersion());
        if (rows != 1){
            throw new BussiException(CodeMsg.RENT_FAILED_ERROR.code,CodeMsg.RENT_FAILED_ERROR.msg) ;
        }
        // 获取出租的时间
        String rentTime = busRentForm.getRentTime();
        // 将时间以~分割
        String[] split = rentTime.split("~");
        busRentForm.setBeginTime(split[0].trim());
        busRentForm.setEndTime(split[1].trim());
        // 设置客户名称
        busRentForm.setName(busCustomerVO.getName());
        // 设置业务员iD
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
        busRentForm.setUserId(activeUser.getSysUser().getId());
        busRentMapper.insert(busRentForm) ;
        return new Result();
    }


    @Override
    public Result queryPage(BusRentQuery busRentQuery) {
        Page<BusRentVO> busRentVOS = PageHelper.startPage(busRentQuery.getPage(), busRentQuery.getLimit());
        if (!StrUtil.isEmpty(busRentQuery.getBeginTime())){
            String beginTime = busRentQuery.getBeginTime();
            String[] split = beginTime.split("~");
            busRentQuery.setMinBeginTime(split[0].trim());
            busRentQuery.setMaxBeginTime(split[1].trim());
        }
        busRentMapper.queryPage(busRentQuery) ;
        return new Result(busRentVOS.toPageInfo());
    }
}
