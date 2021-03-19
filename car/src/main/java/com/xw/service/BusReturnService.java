package com.xw.service;

import com.xw.common.Result;
import com.xw.form.BusReturnFrom;
import com.xw.query.BusReturnQuery;

public interface BusReturnService{

    /**
     * 新增还车记录
     * @param busReturnFrom
     * @return
     */
    Result add(BusReturnFrom busReturnFrom);

    /**
     * 分页查询
     * @param busReturnQuery
     * @return
     */
    Result queryPage(BusReturnQuery busReturnQuery);
}
