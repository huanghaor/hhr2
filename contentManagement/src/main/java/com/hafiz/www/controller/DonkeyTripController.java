package com.hafiz.www.controller;

import com.hafiz.www.mapper.TravelCollectionMapper;
import com.hafiz.www.po.*;
import com.hafiz.www.service.DonkeyTripCommentService;
import com.hafiz.www.service.DonkeyTripService;
import com.hafiz.www.shiro.SessionUtils;
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
import java.util.List;

/**
 * 驴行游记API
 */
@Controller
@RequestMapping("/donkeytrip")
public class DonkeyTripController {
    @Autowired
    private DonkeyTripService donkeyTripService;
    @Autowired
    private DonkeyTripCommentService donkeyTripCommentService;
    @Autowired
    private SaveTheFile saveTheFile;


    /**
     * 跳转到发布游记
     * @param req 视图
     * @return 视图
     */
    @RequestMapping("/publishDonkey")
    public ModelAndView jumpTopublishDonkey(HttpServletRequest req){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("/donkeytrip/publishDonkeyTrip");
        return  modelAndView;
    }

    /**
     * 跳转到游记详情页面评论
     * @param req 视图
     * @return 视图
     */
    @RequestMapping("/travledetail_jump")
    public ModelAndView jumpTotravledetail(HttpServletRequest req){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("/donkeytrip/DonkeyTripDetail");
        modelAndView.addObject("w_id",req.getParameter("_id"));

        return  modelAndView;
    }

    /**
     * 发布留言图片上传
     * @param file  上传图片
     * @return  返回图片保存路径
     */
    @RequestMapping(value="/uploadPicture",method = RequestMethod.POST)
    @ResponseBody
    public String uploadThePicture(HttpServletRequest request, @RequestParam("myFileName")MultipartFile file){
        String type="donkeyTrip";
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
                String type="donkeyTrip";
                String result=saveTheFile.saveTheFile(multiRequest,type);
                jsonResult =jsonResult.newInstanceSucess_d1(result);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return jsonResult;
    }

    /**
     * 发布游记数据保存
     * @param travelCollection  上传数据对象
     * @return 返回保存状态
     */
    @RequestMapping(value = "/publishDonkeytripData",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult publishDonkeytripData(TravelCollection travelCollection){
        JsonResult jsonResult = new JsonResult();

        int data =donkeyTripService.insertSelective(travelCollection);
        if(data==-1){
            jsonResult=jsonResult.newInstanceFail("请先登陆系统，再进行操作!");
        } else if(data>0){
            jsonResult=jsonResult.newInstanceSucess_d1("保存成功!"+data);
        }else{
            jsonResult=jsonResult.newInstanceFail("保存失败，请稍后再试!");
        }
        return jsonResult;
    }

    /**
     * 查询驴行游记数据
     * @return json数据 PublicPagingQuery publicPagingQuery,MessageWal messageWal
     */
    @RequestMapping(value = "/queryDonkeytripData",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getTheDonkeytripData(PublicPagingQuery publicPagingQuery){
        JsonResult jsonObject = new JsonResult();
        TravelCollection travelCollection = new TravelCollection();
        if(publicPagingQuery.getFYType().equals("owner") && publicPagingQuery.getFYType()!=null){
            if(SessionUtils.isLoggedIn()){
                int id=new SessionUtils().getLoginUserId();
                travelCollection.setU_id(SessionUtils.getLoginUserId());;//用于登陆用户根据用户id查询;
            }else{
                jsonObject=jsonObject.newInstanceFail("请先登录系统，再查询！");
                return jsonObject;
            }
        }
        if(publicPagingQuery.getFYType().equals("detailById") && publicPagingQuery.getFYType()!=null){
            travelCollection.setT_id(Integer.parseInt(publicPagingQuery.getT_id()));
        }
        //查询是否为精贴
        if(publicPagingQuery.getT_fine_paste()!=null){
            travelCollection.setT_fine_paste(publicPagingQuery.getT_fine_paste());
        }
        try{
            publicPagingQuery.setQueryObj(travelCollection);
            donkeyTripService.selectMessageData(publicPagingQuery);
            jsonObject= jsonObject.newInstanceSuccess(publicPagingQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 管理员查询驴行游记数据
     * @return json数据 PublicPagingQuery publicPagingQuery,MessageWal messageWal
     */
    @RequestMapping(value = "/queryDonkeytripDataByManager",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getTheDonkeytripDataByManager(TravelCollection travelCollection){
        JsonResult jsonObject = new JsonResult();
        jsonObject=donkeyTripService.selectMessageDataByManager(travelCollection);
        return jsonObject;
    }

    /**
     * 驴行游记点赞
     * @param t_id 数据id
     * @return 之行状态
     */
    @RequestMapping(value = "/giveThumbsUpByDonkey" ,method = RequestMethod.POST)
    @ResponseBody
    public JsonResult giveThumbsUpByDonkey(String t_id){
        JsonResult jsonResult = new JsonResult();
        if(t_id==null || t_id==""){
            jsonResult=jsonResult.newInstanceFail("系统出错，请稍后再试！");
        }else{
            TravelCollection travelCollection =new TravelCollection();
            try {
                travelCollection = donkeyTripService.selectByPrimaryKey(Integer.parseInt(t_id));
                Integer number =travelCollection.getT_praisenumber();//数据库存储的点赞数
                if(number==null || number.equals("")){
                    number=0;
                }
                number =(number==null?0:number)+1;
                travelCollection.setT_id(Integer.parseInt(t_id));
                travelCollection.setT_praisenumber(number);
                int data =donkeyTripService.updateByPrimaryKeySelective(travelCollection);
                if(data>0){
                    jsonResult= jsonResult.newInstanceSuccess(number);
                }else{
                    jsonResult=jsonResult.newInstanceFail("操作失败，请稍后再试！");
                }
            }catch (Exception e){
                e.printStackTrace();
                jsonResult=jsonResult.newInstanceFail("系统出错，请稍后再试！");
            }
        }
        return jsonResult;
    }

    /**
     * 发表评论
     * @param travelComment 上传数据
     * @return 保存状态
     */
    @RequestMapping(value = "/publicTravel_comment",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult publicTravel_comment(TravelComment travelComment){
        int data=0;
        JsonResult jsonResult = new JsonResult();
        try{
            data = donkeyTripCommentService.insertSelective(travelComment);
        }catch (Exception e){
            e.printStackTrace();
            data=0;
        }

        if(data==-1){
            jsonResult=jsonResult.newInstanceFail("评论失败，请稍后再试！");
        }else if(data>=1){
            jsonResult=jsonResult.newInstanceSucess_d1("评论成功！");
        }else if(data==-2){
            jsonResult=jsonResult.newInstanceFail("评论失败，请登陆系统！");
        }else{
            jsonResult=jsonResult.newInstanceFail("系统出错，请稍后再试！");
        }
        return jsonResult;
    }

    /**
     * 初始化游记所有评论
     * @param t_id 数据id
     * @return 评论数据
     */
    @RequestMapping(value = "/getDonkeyComment",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getDonkeyComment(String t_id){
        JsonResult jsonResult = new JsonResult();
        try {
            List<TravelComment> list= donkeyTripCommentService.selectCommentAndReplyData(Integer.parseInt(t_id));
            if(list==null){
                jsonResult =jsonResult.newInstanceFail("暂无数据！");
            }else{
                jsonResult =jsonResult.newInstanceSucess_d2(list);
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult =jsonResult.newInstanceFail("查询失败,请稍后再试！");
        }

        return jsonResult;
    }

    /**
     * 提交评论回复数据
     * @param travelCommentReply
     * @return
     */
    @RequestMapping(value = "/Travel_commentReply",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult submitAnswerUpFormInDonkey(TravelCommentReply travelCommentReply){
        JsonResult jsonResult = new JsonResult();
        int data =donkeyTripCommentService.saveTheTravelReply(travelCommentReply);
        if(data==0){
            jsonResult = jsonResult.newInstanceFail("保存失败");
        }
        if(data==-1){
            jsonResult=jsonResult.newInstanceFail("请先登陆系统再进行相关操作！");
        }
        if(data==1){
            jsonResult=jsonResult.newInstanceSucess_d1("提交成功！");
        }
        return jsonResult;
    }

    /**
     * 删除游记数据
     * @param travelCollection
     * @return
     */
    @RequestMapping(value = "/deleteDonkeyTripById",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteDonkeyTripById(TravelCollection travelCollection){
        JsonResult jsonResult = new JsonResult();
        jsonResult = donkeyTripService.deleteByPrimaryKey(travelCollection.getT_id());
        return jsonResult;
    }

    /**
     * 管理员跟新数据
     * @param travelCollection
     * @return
     */
    @RequestMapping(value = "/updateDonkeyTripById",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateDonkeyTripById(TravelCollection travelCollection){
        JsonResult jsonResult = new JsonResult();
        jsonResult = donkeyTripService.updateDonkeyTripById(travelCollection);
        return jsonResult;
    }
}
