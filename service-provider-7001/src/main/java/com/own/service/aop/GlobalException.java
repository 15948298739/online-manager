package com.own.service.aop;

import com.own.common.vo.SysResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wjl
 * 2023/1/8 14:01
 */
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler({RuntimeException.class})
    public Object exception(Exception e) {
        e.printStackTrace();
        return SysResult.fail();
    }
}
