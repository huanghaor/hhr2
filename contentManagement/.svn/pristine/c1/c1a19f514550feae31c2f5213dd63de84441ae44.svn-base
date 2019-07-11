package com.hafiz.www.filter;

import com.alibaba.fastjson.JSON;
import com.hafiz.www.until.AppConst;
import com.hafiz.www.until.JsonResult;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

/**
 * Desc:认证验证过滤器
 * Created by hafiz.zhang on 2017/7/21.
 */
public class AuthorizeFilter extends AuthorizationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        return super.onAccessDenied(servletRequest, servletResponse);
    }

    /**
     * 根据请求头判断是不是ajax请求
     *
     * @param request 请求实体
     *
     * @return
     */
    private Boolean isAjax(HttpServletRequest request) {
        Boolean isAjax = request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals( request.getHeader("X-Requested-With").toString());
        return isAjax;
    }

    /**
     * 判断用户是否可以访问请求的资源
     *
     * @param request 用户请求
     *
     * @param response 响应
     *
     * @param mappedValue
     *
     * @return
     *
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
                                      Object mappedValue) throws Exception {
        // 登陆请求直接放行
        if (isLoginRequest(request, response)) {
            return true;
        }

        // 获取用户认证信息
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated()) {
            HttpServletRequest httpServletRequest = (HttpServletRequest)request;
            HttpSession session = httpServletRequest.getSession();
            String requestUrl = httpServletRequest.getRequestURL().toString();
            session.setAttribute(AppConst.LAST_URL_KEY, requestUrl);
            return false;
        }

        // 判断请求资源是否授权(如果项目不需要授权，下面省略，直接return true,否则加上下面注释掉的代码，然后最后return false;)
        /*String resource = getPathWithinApplication(request);
        if (subject.isPermitted(resource)) {
            return true;
        }*/
        return true;
    }
}
