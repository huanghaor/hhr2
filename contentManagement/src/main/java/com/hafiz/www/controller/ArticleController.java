package com.hafiz.www.controller;

import com.hafiz.www.po.PublicPagingQuery;
import com.hafiz.www.po.TechnologyModule;
import com.hafiz.www.po.TypeOfArticle;
import com.hafiz.www.service.ArticleService;
import com.hafiz.www.until.*;
import com.sun.tools.javac.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 技术文章
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private SaveTheFile saveTheFile;
    /**
     * 跳转发布技术文章页面
     * @param modelAndView 返回modelAndView对象
     * @return
     */
    @RequestMapping(value = "/publishArticle",method = RequestMethod.GET)
    public ModelAndView messageWal(ModelAndView modelAndView){
        modelAndView.setViewName("articles/publishArticle");
        return modelAndView;
    }
    /**
     * 跳转发布技术文章页面
     * @param req 返回modelAndView对象
     * @return
     */
    @RequestMapping(value = "/jumpToTheIndexContent",method = RequestMethod.GET)
    public ModelAndView jumpToTheIndexContent(HttpServletRequest req){

        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("articles/articlesIndexContent");
        modelAndView.addObject("a_id",req.getParameter("a_id"));
        return modelAndView;
    }
    /**
     * 跳转发布技术文章详情页面
     * @param req 返回modelAndView对象
     * @return
     */
    @RequestMapping(value = "/jumpToTheArticleDetailContent",method = RequestMethod.GET)
    public ModelAndView jumpToTheArticleDetailContent(HttpServletRequest req){

        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("articles/articledetail");
        modelAndView.addObject("m_id",req.getParameter("m_id"));
        return modelAndView;
    }
    /**
     * 初始化文章分类列表
     * @return json数据
     */
    @RequestMapping(value = "/getTheArticleTypeList",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getTheArticleTypeList(){
        JsonResult jsonResult = new JsonResult();
        Object result= new Object();
        try {
            List<TypeOfArticle> listData = articleService.getTheTypeOfArtileList();
            jsonResult =JsonResult.newInstanceSucess_d2(listData);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult =JsonResult.newInstanceFail("网络出错，请稍后再试！");
        }

        return jsonResult;
    }
    /**
     * 发布技术文章图片上传
     * @param file  上传图片
     * @return  返回图片保存路径
     */
    @RequestMapping(value="/uploadPicture",method = RequestMethod.POST)
    @ResponseBody
    public String uploadThePicture(HttpServletRequest request, @RequestParam("myFileName")MultipartFile file){
        String type="articleData";
        return new SaveThePicture().savePicture(file,type);
    }

    /**
     * 保存上传文件
     * @param request 上传文件
     * @return 返回文件保存路径
     */
    @RequestMapping(value = "/saveTheFile",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult saveTheFile(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();

        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            try {
                String type="articleData";
                String result=saveTheFile.saveTheFile(multiRequest,type);
                jsonResult =JsonResult.newInstanceSucess_d1(result);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return jsonResult;
    }

    /**
     * 发布技术文章数据保存
     * @param technologyModule  上传数据对象
     * @return 返回保存状态
     */
    @RequestMapping(value = "/publishTheArticle",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult publishTheArticle(TechnologyModule technologyModule){
        JsonResult jsonResult = new JsonResult();

        int data =articleService.insertSelective(technologyModule);
        if(data==-1){
            jsonResult=JsonResult.newInstanceFail("请先登陆系统，再进行操作!");
        }else if(data>0){
            jsonResult=JsonResult.newInstanceSucess_d1("保存成功!"+data);
        }else{
            jsonResult=JsonResult.newInstanceFail("保存失败，请稍后再试!");
        }
        return jsonResult;
    }

    /**
     * 初始化技术文章数据
     * @return
     */
    @RequestMapping(value = "/getTheTechnologyDataList" ,method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getTheessaydiaryDataList(PublicPagingQuery publicPagingQuery){
        return articleService.selectTechnoDataByConditon(publicPagingQuery);
    }

    /**
     * 下载文件
     * @param request
     * @param response
     */
    @RequestMapping(value = "/dowloadFile")
    @ResponseBody
    public void dowloadFile(String path,HttpServletRequest request, HttpServletResponse response){
        DowloadFile dowloadFile = new  DowloadFile();
        String path_s = Config.getInstance().getProperty("articleData");
        path=path.replace("file/articleData",path_s);
        dowloadFile.download(path,response);
    }

    /**
     * 管理员条件查询技术文章数据
     * @return json数据
     */
    @RequestMapping(value = "/getTheArticleByManager",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getTheArticleByManager(TechnologyModule technologyModule){
        JsonResult jsonObject = new JsonResult();
        jsonObject=articleService.selectArticleDataByManager(technologyModule);
        return jsonObject;
    }
    /**
     * 管理员删除技术文章数据
     * @return json数据
     */
    @RequestMapping(value = "/deleteTheArticleByManager",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteTheArticleByManager(TechnologyModule technologyModule){
        JsonResult jsonObject = new JsonResult();
        jsonObject=articleService.deleteArticleDataByManager(technologyModule);
        return jsonObject;
    }

    /**
     * 管理员跟新技术文章数据==设置为精帖
     * @return json数据
     */
    @RequestMapping(value = "/updateTheArticleByManager",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateTheArticleByManager(TechnologyModule technologyModule){
        JsonResult jsonObject = new JsonResult();
        jsonObject=articleService.updateArticleDataByManager(technologyModule);
        return jsonObject;
    }
}
