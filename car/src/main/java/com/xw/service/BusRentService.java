package com.xw.service;

import com.xw.common.Result;
import com.xw.form.BusRentForm;
import com.xw.query.BusRentQuery;

public interface BusRentService {

    /**
     * 新增出租记录
     * @param busRentFrom
     * @return
     */
    Result add(BusRentForm busRentFrom);


    Result queryPage(BusRentQuery busRentQuery);
}
