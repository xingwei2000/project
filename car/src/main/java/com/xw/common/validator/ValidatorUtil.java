package com.xw.common.validator;

import com.xw.common.Constant;
import com.xw.common.exception.BussiException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 数据校验器
 * 数据校验分为数据格式校验和数据业务校验
 */
public class ValidatorUtil {
    /**
     * 数据校验器对象
     */
    private static Validator validator ;

    /**
     * 实例化数据校验器对象
     */
    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 进行数据校验
     * @param object
     */
    public static void validator(Object object){
        Set<ConstraintViolation<Object>> validateSet = validator.validate(object);
        // 如果数据校验器不为空 说明校验有不通过的
        if (validateSet!=null && !validateSet.isEmpty()){
            for (ConstraintViolation<Object> objectConstraintViolation : validateSet) {
                // 校验不通过的原因
                String message = objectConstraintViolation.getMessage();
                Integer code = Constant.PARAM_CHECKED_ERROR ;
                throw new BussiException(code,message);
            }
        }
    }
}
