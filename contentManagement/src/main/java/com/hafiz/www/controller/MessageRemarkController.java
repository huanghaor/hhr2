package com.hafiz.www.controller;

import com.hafiz.www.po.MessageRemark;
import com.hafiz.www.po.MessageRemarkReply;
import com.hafiz.www.po.MessageWal;
import com.hafiz.www.service.MessageRemarkReplayService;
import com.hafiz.www.service.MessageRemarkService;
import com.hafiz.www.service.MessageWalService;
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
 * 留言墙中的评论区
 */
@Controller
@RequestMapping("/messageRemark")
public class MessageRemarkController {

    @Autowired
    private MessageWalService messageWalService;
    @Autowired
    private MessageRemarkService messageRemarkService;
    @Autowired
    private MessageRemarkReplayService messageRemarkReplayService;

    /**
     * 跳转到我的留言墙评论
     * @param req 视图
     * @return 视图
     */
    @RequestMapping("/messageRemark_jump")
    public ModelAndView jumpToMessageRemark(HttpServletRequest req){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("/messageWal/messageRemark");
        modelAndView.addObject("w_id",req.getParameter("w_id"));

        return  modelAndView;
    }

    /**
     * 发表留言墙评论
     * @param messageRemark 评论信息
     * @return 返回处理结果信息
     */
    @RequestMapping( value = "/publicMessageRemark",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult publicMessageRemark(MessageRemark messageRemark){
        JsonResult jsonResult = new JsonResult();
        int data = messageRemarkService.insertSelective(messageRemark);
        if(data==0){
            jsonResult=jsonResult.newInstanceFail("系统出错，请稍后再试！");
        }else if(data==-1){
            jsonResult=jsonResult.newInstanceFail("请先登陆系统再进行相关操作！");
        }else{
            jsonResult= jsonResult.newInstanceSucess_d1("发表成功");
        }
        return jsonResult;
    }

    /**
     * 查询当前留言中的所有评论
     * @param w_id 传入当前留言id  messageWal.getW_id()
     * @return 返回查询基本信息
     */
    @RequestMapping( value = "/queryTheMessageRemark",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult queryTheMessageRemark(String w_id){
        JsonResult jsonResult = new JsonResult();
        try {
            List<MessageRemark> list= messageRemarkService.selectRemarkAndReplyData(Integer.parseInt(w_id));
            if(list==null){
                jsonResult =jsonResult.newInstanceFail("查询失败！");
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
     * 提交回复评论
     * @param messageRemarkReply 上传的回复评论数据
     * @return 跳转当前页面，进行刷新
     */
    @RequestMapping("/submitanswerUpForm")
    @ResponseBody
    public JsonResult submitanswerUpForm(MessageRemarkReply messageRemarkReply){
        JsonResult jsonResult = new JsonResult();
        int data =messageRemarkReplayService.insertSelective(messageRemarkReply);
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
}
