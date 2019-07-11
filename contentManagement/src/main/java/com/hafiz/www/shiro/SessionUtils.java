package com.hafiz.www.shiro;

import com.hafiz.www.po.Userinfo;
import com.hafiz.www.service.UserService;
import com.hafiz.www.service.impl.UserServiceImpl;
import com.hafiz.www.until.JsonResult;
import com.hafiz.www.until.MD5;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc: 用户Session工具类
 * Created by hafiz.zhang on 2017/7/22.
 */
public class SessionUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionUtils.class);

    /**
     * 获取登陆的key,即用户名
     *
     * @return
     */
    public static String getLoginKey() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return (String)subject.getPrincipal();
        }
        return null;
    }

    /**
     * 获取用户权限
     * @return
     */
    public static String getUserJurisdiction(){
        Userinfo user = getLoginUser();
        if (user != null) {
            return user.getU_jurisdiction();
        }
        return "";
    }

    /**
     * 获取当前登陆用户
     *
     * @return
     */
    public static Userinfo getLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            Session session = subject.getSession();
            Object loginUser = session.getAttribute("loginUser");
            return loginUser == null ? null : (Userinfo)loginUser;
        }
        return null;
    }

    /**
     * 获取当前登陆用户id
     *
     * @return
     */
    public static int getLoginUserId() {
        Userinfo user = getLoginUser();
        if (user != null) {
            return user.getU_id();
        }
        return 0;
    }

    /**
     * 获取当前用户是否登陆
     *
     * @return
     */
    public static Boolean isLoggedIn() {
        boolean isLoggedIn = SecurityUtils.getSubject().isAuthenticated();
        return isLoggedIn;
    }

    public static JsonResult login(String userName, String password,String code,HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(userName, password);
        JsonResult jr = new JsonResult();
        if(SessionUtils.isLoggedIn()){
            LOGGER.error("登陆失败");
            jr.setSuccess(false);
            jr.setMsg("登陆失败，请先退出当前账户！");
        }else{
            if(code.toLowerCase().equals( ((String) request.getSession().getAttribute("checkCode")).toLowerCase())){
                try {
                    subject.login(token);
                } catch (UnknownAccountException ue) {
                    LOGGER.error("用户不存在：{}", userName);
                    jr.setSuccess(false);
                    jr.setMsg("没有该账号");
                } catch (LockedAccountException le) {
                    LOGGER.error("用户名重复");
                    jr.setSuccess(false);
                    jr.setMsg("用户名重复,请联系技术");
                } catch (IncorrectCredentialsException ie) {
                    LOGGER.error("密码错误");
                    jr.setSuccess(false);
                    jr.setMsg("密码错误");
                } catch (Exception e) {
                    LOGGER.error("登陆出错:{}", e.getLocalizedMessage());
                    jr.setSuccess(false);
                    jr.setMsg("登陆出错:" + e.getLocalizedMessage());
                }
            }else{
                jr.setSuccess(false);
                jr.setMsg("验证码输入错误");
            }
        }


        return jr;
    }

    /**
     * 用户退出登陆
     */
    public static void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

}
