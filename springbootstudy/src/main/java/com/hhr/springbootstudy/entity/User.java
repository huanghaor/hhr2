package com.hhr.springbootstudy.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
/**
 * 使用lombok
 */
@Slf4j//等同于日志打印代码
@Getter
@Setter
public class User {

    private String name;

    private Integer age;



    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args){
        User user = new User();
        user.setName("hhr");
        user.setAge(18);
        log.info(user.toString());
    }

}
