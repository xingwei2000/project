package com.xw.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义业务异常
 * 在很多业务中 存在多张表的操作
 * 如果A表更新成功 B表更新失败 需要事务的回滚
 * 而事务的回滚 依赖于异常，只有发生了异常 事务才会回滚
 * 此时需要程序自己抛出异常 所以需要自定义异常
 * 因为程序抛出的异常是千奇百怪的，无法控制，所以需要走自定义异常 开发者在程序中捕获异常
 * 既可以回滚事务 也可以提高与用户的交互性
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BussiException extends RuntimeException{


    private Integer code ;  // 异常码

    private String msg ;  // 异常消息



}
