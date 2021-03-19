package com.xw.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xw.common.CodeMsg;
import com.xw.common.Result;
import com.xw.domain.BusCar;
import com.xw.form.BusCarForm;
import com.xw.mapper.BusCarMapper;
import com.xw.query.BusCarQuery;
import com.xw.service.BusCarService;
import com.xw.vo.BusCarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusCarServiceImpl implements BusCarService {

    @Autowired
    private BusCarMapper busCarMapper ;

    @Override
    public Result query(BusCarQuery query) {
        Page<BusCarVO> busCarVOS = PageHelper.startPage(query.getPage(), query.getLimit());
        busCarMapper.queryPage(query);
        return new Result(busCarVOS.toPageInfo());
    }

    @Override
    public Result add(BusCarForm form) {
        // 业务校验
        // 车牌号不能重复
        String carNum = form.getCarNum();
        BusCarVO busCarVO = busCarMapper.selectOneByCarNum(carNum);
        if (busCarVO!=null){
            return new Result(CodeMsg.CAR_NUM_EXISTS);
        }
        busCarMapper.insert(form);
        return new Result();
    }
}
