package com.hhr.springbootstudy.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Data注解 ===@Getter+@Setter
 */
@Slf4j
@Data
/*@Getter
@Setter*/
public class UserInfo {
    private Integer id;
    private String name;
    private Integer age;


    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
