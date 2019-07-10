package com.hhr.springbootstudy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * SpringCloud依赖于SpringBoota实现微服务，SpringBoot默认集成web组件；
 * SpringCloud使用SpringMvc编写微服务http接口、微服务、通讯接口（http+json格式）
 * =======注解解释==========
 * @RestController:表示该类中所有方法都会返回json格式
 * 第一种启动SpringBoot项目方式：@EnableAutoConfiguration：自动配置：在于让SpringBoot根据所申明的依赖来对Spring框架进行自动配置；扫包范围默认是在当前类中
 * 第二种启动SpringBoot项目：@ComponentScan(basePackages={"",""}),该方式是需要进行包扫描注解，对于有多个Controller包是，将写多次，写起来麻烦
 * 第三种启动SpringBoot项目：@SpringBootApplication  等同于@EnableAutoConfiguration+@ComponentScan(进行同级包扫描，如果不在ComponentScan中声名，则是不会进行扫描其他包的)
 *
 * 1、SpringBoot的启动原理是：通过SpringMVC的注解方式进行启动
 *
 *  @Value("${http_url}")获取配置文件的相应参数   ${http_url}为参数名称
 */
@RestController
@EnableAutoConfiguration
public class MemberController {
    @Value("${http_url}")
    private String http_url;

    @RequestMapping("/memberIndex")
    public String memberIndex(){
        return "SpringBoot2.0，欢迎使用！";
    }
    @RequestMapping("/http_url")
    public String http_url(){
        return http_url;
    }

    public  static void  main(String[] args){
        //整个程序的主入口 ，启动springboot项目
        SpringApplication.run(MemberController.class,args);
    }
}
