package com.hhr.springbootstudy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局捕获异常  思路：
 * 使用AOP技术，采用异常通知
 */
@RestController
public class ErrorController {
    @RequestMapping("getUser")
    public String getUser(double i){
        double j = 1 + i;
        return "success"+j;
    }
}
