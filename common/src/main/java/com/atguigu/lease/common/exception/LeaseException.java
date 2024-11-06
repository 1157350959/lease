package com.atguigu.lease.common.exception;

import com.atguigu.lease.common.result.ResultCodeEnum;
import lombok.Data;

import java.rmi.dgc.Lease;

/**
 * ClassName: LeaseException
 * Package: com.atguigu.lease.common.exception
 * Description:
 *
 * @Author HL
 * @Create 11/5/2024 3:13 PM
 * @Version:
 */
@Data
public class LeaseException extends RuntimeException{
    private Integer code;

    public LeaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public LeaseException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
}
