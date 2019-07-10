package com.hhr.springbootstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 整合Controller
 */
@Controller
public class FTLController {

    @RequestMapping("ftlIndex")
    public String ftlIndex(Map<String,Object> map){
        map.put("name","hhr");
        map.put("age","1888");
        map.put("sex","男12");
        return "ftlIndex";
    }
}
