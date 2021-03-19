package com.xw.mapper;

import com.xw.form.BusCarForm;
import com.xw.query.BusCarQuery;
import com.xw.vo.BusCarVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusCarMapper {


    List<BusCarVO> queryPage(BusCarQuery query);

    BusCarVO selectOneByCarNum(@Param("carNum") String carNum);

    void insert(BusCarForm form);
    /**
     * 修改车辆状态
     * @param id
     * @param isRent
     * @param version
     * @return
     */
    Integer update(@Param("id") Integer id, @Param("isRent")  Integer isRent,@Param("version")  Integer version);
}