package com.xw.common.handler;

import com.xw.common.CodeMsg;
import com.xw.common.Result;
import com.xw.common.exception.BussiException;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    @ResponseBody
    public Object handlerException(Exception exception) {
        exception.printStackTrace();
        // 判断是不是程序员自己抛出的异常
        if (exception instanceof BussiException) {
            BussiException bussiException = (BussiException) exception;
            return new Result(bussiException);
        }
        // shiro相关的处理
        if (exception instanceof ShiroException){
            return null;
        }
        // 可能发生其他异常
        return new Result(CodeMsg.ERROR);
    }

}
