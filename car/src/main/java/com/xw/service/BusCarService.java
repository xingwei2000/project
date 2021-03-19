package com.xw.service;

import com.xw.common.Result;
import com.xw.form.BusCarForm;
import com.xw.query.BusCarQuery;

public interface BusCarService {

    Result query(BusCarQuery query);

    Result add(BusCarForm form);
}
