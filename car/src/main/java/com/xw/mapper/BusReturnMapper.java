package com.xw.mapper;

import com.xw.form.BusReturnFrom;
import com.xw.query.BusReturnQuery;
import com.xw.vo.BusReturnVO;

import java.util.List;

public interface BusReturnMapper {

    /**
     * 新增还车记录
     * @param busReturnFrom
     */
    void insert(BusReturnFrom busReturnFrom);

    /**
     * 分页查询
     * @param busReturnQuery
     */
    List<BusReturnVO> selectPage(BusReturnQuery busReturnQuery);
}