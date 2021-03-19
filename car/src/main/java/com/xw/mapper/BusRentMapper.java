package com.xw.mapper;

import com.xw.form.BusRentForm;
import com.xw.query.BusRentQuery;
import com.xw.vo.BusCarVO;
import com.xw.vo.BusRentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusRentMapper {

    /**
     * 新增出租记录
     * @param busRentForm
     */
    void insert(BusRentForm busRentForm);

    /**
     * 分页查询
     * @param busRentQuery
     * @return
     */
    List<BusRentVO> queryPage(BusRentQuery busRentQuery);

    /**
     * 根据出租记录ID 查询出租记录
     * @param rentId
     * @return
     */
    BusRentVO selectOne(Integer rentId);

    /**
     * 修改汽车的出租状态
     * @param rentId 出租记录ID
     * @param oldFlag 未还车的状态
     * @param flag
     * @return
     */
    Integer updateRentFlag(@Param("rentId") Integer rentId,@Param("oldFlag")Integer oldFlag, @Param("flag")Integer flag);
}