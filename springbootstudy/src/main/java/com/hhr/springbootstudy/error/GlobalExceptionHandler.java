package com.hhr.springbootstudy.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局捕获异常公共类
 * 1、捕获返回json格式
 * 2、捕获返回页面错误
 */
@ControllerAdvice(basePackages = "com.hhr.springbootstudy.controller")
public class GlobalExceptionHandler {

    /**
     * @ResponseBody是为了返回json格式；如果需要跳转页面则不需要加@ResponseBody
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,Object> exceptionHandler(){
        //实际开发项目中会将错误记录在日志中
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("errorCode","500");
        map.put("errorMsg","全局捕获异常");

        return map;
    }
}
