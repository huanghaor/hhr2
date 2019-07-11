package com.hafiz.www.controller;

import com.hafiz.www.po.EssayDiary;
import com.hafiz.www.po.EssayDiaryType;
import com.hafiz.www.po.PublicPagingQuery;
import com.hafiz.www.service.EssayDiaryService;
import com.hafiz.www.until.JsonResult;
import com.hafiz.www.until.SaveTheFile;
import com.hafiz.www.until.SaveThePicture;
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

/***
 * 随笔日记
 */
@Controller
@RequestMapping("essaydiary")
public class EssayDiaryController {
    @Autowired
    private EssayDiaryService essayDiaryService;
    @Autowired
    private SaveTheFile saveTheFile;
    /**
     * 跳转到发布随笔
     * @param req 视图
     * @return 视图
     */
    @RequestMapping("/publishEssayDiary")
    public ModelAndView jumpTopublishEssayDiary(HttpServletRequest req){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("/essaydiary/publishEssaydiary");
        return  modelAndView;
    }
    /**
     * 跳转到随笔日记详情
     * @param req 视图
     * @return 视图
     */
    @RequestMapping("/essaydiarydetail")
    public ModelAndView jumpToEssayDiaryDetail(HttpServletRequest req){
        //跳转一次+1一次阅读量
        essayDiaryService.addTheReadNum(req.getParameter("e_id"));
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("/essaydiary/essaydiarydetail");
        return  modelAndView;
    }
    /**
     * 初始化类别列表
     * @return
     */
    @RequestMapping(value = "getTheessaydiaryTypeList" ,method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getTheEssaydiaryTypeList(){
        return essayDiaryService.initTheDiaryTypeList();
    }

    /**
     * 初始化日记数据
     * @return
     */
    @RequestMapping(value = "getTheessaydiaryDataList" ,method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getTheessaydiaryDataList(PublicPagingQuery publicPagingQuery){
        return essayDiaryService.initEssayDiaryData( publicPagingQuery);
    }
    /**
     * 根据日记id查询日记详细数据
     * @return
     */
    @RequestMapping(value = "getTheessaydiaryDataDetail" ,method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getTheessaydiaryDataDetail(PublicPagingQuery publicPagingQuery){
        return essayDiaryService.initEssayDiaryData( publicPagingQuery);
    }

    /**
     * 添加日记类别数据
     * @return
     */
    @RequestMapping(value = "addTheessaydiaryTypedata" ,method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addTheessaydiaryTypedata(EssayDiaryType essayDiaryType){
        return essayDiaryService.addTheDiaryType(essayDiaryType);
    }

    /**
     * 添加日记数据
     * @return
     */
    @RequestMapping(value = "addTheessaydiarydata" ,method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addTheessaydiarydata(EssayDiary essayDiary){
        return essayDiaryService.addEssayDiaryData(essayDiary);
    }
    /**
     * 发布留言图片上传
     * @param file  上传图片
     * @return  返回图片保存路径
     */
    @RequestMapping(value="/uploadPicture",method = RequestMethod.POST)
    @ResponseBody
    public String uploadThePicture(HttpServletRequest request, @RequestParam("myFileName")MultipartFile file){
        String type="essayDairyData";
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
                String type="essayDairyData";
                String result=saveTheFile.saveTheFile(multiRequest,type);
                jsonResult =jsonResult.newInstanceSucess_d1(result);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return jsonResult;
    }

    /**
     * 管理员条件查询随笔日记数据
     * @return json数据
     */
    @RequestMapping(value = "/getTheEssayDiaryByManager",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getTheEssayDiaryByManager(EssayDiary essayDiary){
        JsonResult jsonObject = new JsonResult();
        jsonObject=essayDiaryService.selectEssayDiaryDataByManager(essayDiary);
        return jsonObject;
    }
    /**
     * 管理员删除随笔日记数据
     * @return json数据
     */
    @RequestMapping(value = "/deleteTheEssayDiaryByManager",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteTheEssayDiaryByManager(EssayDiary essayDiary){
        JsonResult jsonObject = new JsonResult();
        jsonObject=essayDiaryService.deleteEssayDiaryDataByManager(essayDiary);
        return jsonObject;
    }

    /**
     * 管理员跟新随笔日记数据==设置为精帖
     * @return json数据
     */
    @RequestMapping(value = "/updateTheAEssayDiaryByManager",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateTheEssayDiaryByManager(EssayDiary essayDiary){
        JsonResult jsonObject = new JsonResult();
        jsonObject=essayDiaryService.updateEssayDiaryDataByManager(essayDiary);
        return jsonObject;
    }

}
