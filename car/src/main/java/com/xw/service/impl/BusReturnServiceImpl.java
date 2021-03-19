package com.xw.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xw.common.CodeMsg;
import com.xw.common.Constant;
import com.xw.common.Result;
import com.xw.common.exception.BussiException;
import com.xw.form.BusReturnFrom;
import com.xw.mapper.BusCarMapper;
import com.xw.mapper.BusRentMapper;
import com.xw.mapper.BusReturnMapper;
import com.xw.query.BusReturnQuery;
import com.xw.service.BusReturnService;
import com.xw.shiro.ActiveUser;
import com.xw.vo.BusCarVO;
import com.xw.vo.BusRentVO;
import com.xw.vo.BusReturnVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Service
public class BusReturnServiceImpl implements BusReturnService {

    @Autowired
    private BusReturnMapper busReturnMapper ;

    @Autowired
    private BusRentMapper busRentMapper ;

    @Autowired
    private BusCarMapper busCarMapper ;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result add(BusReturnFrom busReturnFrom) {
        // 业务数据校验
        // 查询出租记录
        // 查询出租记录状态
        /**
         * 1、查询出租记录
         * 2、检查出租记录是否已还车
         * 3、只有未还车，才可以还车
         * 4、修改车辆的出租状态 改为已还车
         * 5、插入还车记录
         * 6、修改车辆的出租状态 改为未出租
         * 计算总金额
         */
        // 根据出租记录ID查询出租记录
        BusRentVO busRentVO = busRentMapper.selectOne(busReturnFrom.getRentId());
        // 如果出租状态已经是还车
        if (busRentVO.getFlag().equals(Constant.CAR_RETURN_ED)){
            return new Result(CodeMsg.RETURN_CAR_ERROR) ;
        }
        // 如果没有还 则修改还车记录状态
        Integer rows = busRentMapper.updateRentFlag(busReturnFrom.getRentId(),busRentVO.getFlag(),Constant.CAR_RETURN_ED) ;
        if (rows!=1){
            return new Result(CodeMsg.RETURN_FAILED_RENT_CHANGED_ERROR) ;
        }
        // 修改车辆状态
        BusCarVO busCarVO = busCarMapper.selectOneByCarNum(busRentVO.getCarNum());
        rows = busCarMapper.update(busCarVO.getId(), Constant.IS_NOT_RENT, busCarVO.getVersion());
        if (rows!=1){
            throw  new BussiException(CodeMsg.RETURN_FAILED_RENT_CHANG_ERROR.code,CodeMsg.RETURN_FAILED_RENT_CHANG_ERROR.msg) ;
        }
        // 计算出租金额
        String beginTime = busRentVO.getBeginTime() + " 00:00:00";
        String returnTime = busReturnFrom.getReturnTime() + " 23:59:59";
        int rentPrice = busRentVO.getRentPrice() ;
        // 计算天数
        Date begin = DateUtil.parse(beginTime,"yyyy-MM-dd HH:mm:ss") ;
        Date end = DateUtil.parse(returnTime,"yyyy-MM-dd HH:mm:ss") ;
        // 计算租了多少天
        int days = (int) DateUtil.betweenDay(begin, end, true) + 1 ;
        // 判断 是否是同一天
        if (DateUtil.isSameDay(begin,end)){
            days = 1 ;
        }
        // 租金
        rentPrice = days * rentPrice ;
        // 总金额
        int totalPrice = rentPrice + busReturnFrom.getPayMoney() ;
        busReturnFrom.setRentPrice(rentPrice);
        busReturnFrom.setTotalMoney(totalPrice);
        // 获取认证主题
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
        busReturnFrom.setUserId(activeUser.getSysUser().getId());

        busReturnMapper.insert(busReturnFrom) ;
        return new Result();
    }


    @Override
    public Result queryPage(BusReturnQuery busReturnQuery) {
        Page<BusReturnVO> busReturnVOS = PageHelper.startPage(busReturnQuery.getPage(), busReturnQuery.getLimit());
        busReturnMapper.selectPage(busReturnQuery) ;
        return new Result(busReturnVOS.toPageInfo());
    }
}
