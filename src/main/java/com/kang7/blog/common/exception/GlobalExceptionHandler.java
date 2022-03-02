package com.kang7.blog.common.exception;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.kang7.blog.common.Result;

@Slf4j
@RestController
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value= RuntimeException.class)
    public Result handler(RuntimeException e){
        log.error("problems"+e);
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)     //判断返回消息是否正常,没有权限异常
    @ExceptionHandler(value = ShiroException.class)
    public Result handler(ShiroException e){
        log.error("shiro异常---------->>>" + e);
        return Result.fail(401,e.getMessage(),null);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)     //判断返回消息是否正常,没有权限异常
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e){
        log.error("实体检验异常异常---------->>>" + e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.fail(objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)     //判断返回消息是否正常,没有权限异常
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e){
        log.error("断言异常异常---------->>>" + e);
        return Result.fail(e.getMessage());
    }

}
