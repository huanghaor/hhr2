package com.hafiz.www.until.IndustryInfomationFromInternet.down;

import com.hafiz.www.po.IndustryInformation;
import com.hafiz.www.po.TechnologyModule;
import com.hafiz.www.until.GetTheTimeStamp;
import com.hafiz.www.until.IndustryInfomationFromInternet.parse.ParseIndustry;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取网站urlList
 */
public class IndustrySpider {
    public String preurl="http://tech.ifeng.com/mobile/";
    public List<IndustryInformation> getAllUrls(String url) {
        //设置报头
        Document document = null;
        List <IndustryInformation> list = new ArrayList <IndustryInformation>();
        try {
            document = Jsoup.connect(url).userAgent(
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0"
            ).get();
            String cssQueryUrl = "div.w1200 div.w840 div.ng-scope dl.line_b dd.w570 h3.fc06 a";
            Elements elementsUrl = document.select(cssQueryUrl);
           // String cssQueryKeyWords = "ul.list li div.detail div.binfo div.f1 a.source";
            //Elements elementsKeyWords = document.select(cssQueryKeyWords);
            for (Element element : elementsUrl) {
                String hrefUrl = element.attr("href");//标题url
                String title = element.text();//标题内容
                //if (!href.contains("https")) continue;
                System.out.println("链接href："+hrefUrl+"===标题："+title);
                IndustryInformation industryInformation = new IndustryInformation();
                /*technologyModule.setM_title(title);//标题
                technologyModule.setM_datatime(new GetTheTimeStamp().getCurrentTime());//转载时间
                technologyModule.setM_resources_mode("2");//转载
                technologyModule.setM_publish_personid(22);//发布人默认为管理员hhr
                technologyModule.setM_typeid(3);//文章类型
                technologyModule.setReprintUrl(hrefList);//转载的文章url
                list.add(technologyModule);*/
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //提取url连接
        System.out.println(list.size());
        return list;
    }

    public static void main(String[] arg) {
       IndustrySpider cto51Spider= new IndustrySpider();
        String url = "http://www.donews.com/technology/index";
        List<IndustryInformation> list=cto51Spider.getAllUrls(url);
        /*List<IndustryInformation> lists = new ParseIndustry().parseContent(list);*/
    }
}
