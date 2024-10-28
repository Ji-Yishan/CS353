package com.parttime.cs353.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-26 16:58
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public Result accessDeniedException() {
        return new Result(403, "用户权限不足！", null);
    }

    @ExceptionHandler(RuntimeException.class)
    public Result serverException() {
        return new Result(500, "服务出现异常！", null);
    }
}
