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
        return new Result(403, "user unauthorized", null);
    }

    @ExceptionHandler(RuntimeException.class)
    public Result serverException() {
        return new Result(500, "error in server", null);
    }
}
