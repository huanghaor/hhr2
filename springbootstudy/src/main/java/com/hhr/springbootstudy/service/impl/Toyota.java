package com.hhr.springbootstudy.service.impl;

import com.hhr.springbootstudy.service.Car;
import org.springframework.stereotype.Component;

@Component
public class Toyota implements Car {

    public void print() {
        System.out.println("我是一个丰田车！");
    }
}
