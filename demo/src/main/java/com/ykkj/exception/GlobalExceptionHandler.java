package com.ykkj.exception;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName GlobalExceptionHandler.java
 * @Description TODO
 * @createTime 2022年09月08日
 */


import com.ykkj.model.ResultMsg;
import com.ykkj.model.ServiceRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceRuntimeException.class)
    public ResultMsg handleServiceException(ServiceRuntimeException e, HttpServletRequest request)
    {
        return ResultMsg.error(e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ResultMsg handleException(Exception e, HttpServletRequest request)
    {
        return ResultMsg.error("系统异常");
    }

}
