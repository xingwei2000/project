package com.xw.common;

import com.xw.common.exception.BussiException;
import lombok.Data;

/**
 * 业务结果包装类
 * 以为layui前端技术 需要 code msg data 数据格式
 *
 */
@Data
public class Result {

    private Integer code ;  // 业务码

    private String msg ; // 业务消息

    private Object data ;  // 数据

    /**
     * 所以的业务只分为两种情况
     * 1、成功
     *      1.1 成功没有数据 删除
     *      1.2 成功有数据 查询
     * 2、失败
     *      失败没有数据  但是需要有失败原因
     */
    public Result(){
        this.code = CodeMsg.SUCCESS.code ;
        this.msg = CodeMsg.SUCCESS.msg ;
    }

    public Result(Object data){
        this();
        this.data = data ;
    }

    /**
     * 失败没有数据
     * 但是需要有失败的业务码 和 业务消息
     * @param codeMsg
     */
    public Result(CodeMsg codeMsg){
        this.code = codeMsg.code ;
        this.msg = codeMsg.msg ;
    }

    /**
     * 兼容异常信息
     * @param bussiException
     */
    public Result(BussiException bussiException){
        this.code = bussiException.getCode() ;
        this.msg = bussiException.getMsg() ;
    }

}
