package com.hafiz.www.filter;

import com.hafiz.www.shiro.SessionUtils;

import javax.servlet.*;
import java.io.IOException;

public class UserCommonFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("=========过滤器启动=======");
        //在获取首页初始化数据时，获取系统当前访问者的电脑IP和用户id
        if(SessionUtils.isLoggedIn()){
            String remoteAddr = servletRequest.getRemoteAddr();
            System.out.println("当前系统用户IP====:"+remoteAddr+"====用户账号："+SessionUtils.getLoginUserId());
        }else{
            String remoteAddr = servletRequest.getRemoteAddr();
            System.out.println("当前系统用户IP====:"+remoteAddr+"====用户账号：000");
        }
    }

    public void destroy() {

    }
}
