package com.hafiz.www.controller;

import com.hafiz.www.po.NavigationBarRecord;
import com.hafiz.www.service.IndexService;
import com.hafiz.www.until.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 主页
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private IndexService indexService;

    /**
     * 获取主页导航
     * @return
     */
    @RequestMapping(value = "/nav",method=RequestMethod.GET)
    @ResponseBody
    public List<NavigationBarRecord> getIndexNav(){
        return indexService.getIndexNavList();
    }
    /**
     * 管理员获取主页导航
     * @return
     */
    @RequestMapping(value = "/navByManager",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult getIndexNavByManager(){
        JsonResult jsonObject = new JsonResult();
        jsonObject=indexService.getIndexNavListsByManager();
        return jsonObject;
    }

    /**
     * 管理员导航栏修改数据
     * @param navigationBarRecord
     * @return
     */
    @RequestMapping(value = "/updateNavigationByManager",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult updateNavigationByManager(NavigationBarRecord navigationBarRecord){
        JsonResult jsonObject = new JsonResult();
        jsonObject=indexService.updateNavigation(navigationBarRecord);
        return jsonObject;
    }
    /**
     * 管理员新增导航栏
     * @param navigationBarRecord
     * @return
     */
    @RequestMapping(value = "/insertNavigationByManager",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult insertNavigationByManager(NavigationBarRecord navigationBarRecord){
        JsonResult jsonObject = new JsonResult();
        jsonObject=indexService.insertNavigation(navigationBarRecord);
        return jsonObject;
    }


    /**
     * 获取主页初始化数据
     * @return
     */
    @RequestMapping(value = "/initIndexAllData",method=RequestMethod.GET)
    @ResponseBody
    public JsonResult initIndexAllData(HttpServletRequest req){
        return indexService.initIndexData(req);
    }

    /**
     * 跳转主页具体内容
     * @param modelAndView 返回modelAndView对象
     * @return
     */
    /*@RequestMapping(value = "/indexContent",method = RequestMethod.GET)
    public  ModelAndView  indexContent(ModelAndView modelAndView){
        modelAndView.setViewName("/indexContent.html");
        return modelAndView;
    }*/

    /**
     * 跳转社区问答
     * @param modelAndView 返回modelAndView对象  /messageWal/messageWal
     * @return
     */
    @RequestMapping(value = "/messageWal",method = RequestMethod.GET)
    public  ModelAndView  messageWal(ModelAndView modelAndView){
        modelAndView.setViewName("messageWal/messageWal");
        return modelAndView;
    }

    /**
     * 跳转登陆页面  未用
     * @param modelAndView 返回modelAndView对象
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public  ModelAndView  IndexToLogin(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
     }

    /**
     * 跳转注册页面
     * @param modelAndView 返回modelAndView对象
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public  ModelAndView  IndexToRegister(ModelAndView modelAndView){
        modelAndView.setViewName("register");
        return modelAndView;
    }

    /**
     * 跳转个人中心
     * @param modelAndView 返回modelAndView对象
     * @return
     */
    @RequestMapping(value = "/personalCenter",method = RequestMethod.GET)
    public  ModelAndView  personalCenter(ModelAndView modelAndView){
        modelAndView.setViewName("index/personalCenter");
        return modelAndView;
    }

    /**
     * 跳转技术文章主页
     * @param modelAndView 返回modelAndView对象
     * @return
     */
    @RequestMapping(value = "/articlesIndex",method = RequestMethod.GET)
    public  ModelAndView  jumpThearticlesIndex(ModelAndView modelAndView){
        modelAndView.setViewName("articles/articlesIndex");
        return modelAndView;
    }
    /**
     * 跳转驴行游记主页
     * @param modelAndView 返回modelAndView对象
     * @return
     */
    @RequestMapping(value = "/donkeytripIndex",method = RequestMethod.GET)
    public  ModelAndView  jumpDonkeyTripIndex(ModelAndView modelAndView){
        modelAndView.setViewName("donkeytrip/donkeyTripIndex");
        return modelAndView;
    }


    /**
     * 跳转随笔日记
     * @param modelAndView 返回modelAndView对象
     * @return
     */
    @RequestMapping(value = "/essaydiaryIndex",method = RequestMethod.GET)
    public  ModelAndView  jumpessaydiaryIndex(ModelAndView modelAndView){
        modelAndView.setViewName("essaydiary/essaydiaryIndex");
        return modelAndView;
    }
    /**
     * 跳转行业资讯
     * @param modelAndView 返回modelAndView对象
     * @return
     */
    @RequestMapping(value = "/industryInforIndex",method = RequestMethod.GET)
    public  ModelAndView  jumpindustryInforIndex(ModelAndView modelAndView){
        modelAndView.setViewName("IndustryInformation/industryInformationIndex");
        return modelAndView;
    }
    /**
     * 跳转后台管理
     * @param modelAndView 返回modelAndView对象
     * @return
     */
    @RequestMapping(value = "/backManagementIndex",method = RequestMethod.GET)
    public  ModelAndView  jumpbackManagementIndex(ModelAndView modelAndView){
        modelAndView.setViewName("backManagement/Back-stageManagement");
        return modelAndView;
    }
}
