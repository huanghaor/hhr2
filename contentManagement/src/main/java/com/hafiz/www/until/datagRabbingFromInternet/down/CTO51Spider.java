package com.hafiz.www.until.datagRabbingFromInternet.down;

import com.hafiz.www.po.TechnologyModule;
import com.hafiz.www.shiro.SessionUtils;
import com.hafiz.www.until.GetTheTimeStamp;
import com.hafiz.www.until.datagRabbingFromInternet.parse.ParseCTO51;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.mail.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取51CTO技术网站urlList
 */
public class CTO51Spider {
    public String preurl="http://blog.51cto.com/artcommend/15";
    public List<TechnologyModule> getAllUrls(String url) {
        //设置报头
        Document document = null;
        List <TechnologyModule> list = new ArrayList <TechnologyModule>();
        try {
            document = Jsoup.connect(url).userAgent(
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0"
            ).get();
            String cssQuery = "ul.artical-list li a.tit";
            Elements elements = document.select(cssQuery);

            for (Element element : elements) {
                String hrefList = element.attr("href");
                String title = element.text();
                //if (!href.contains("https")) continue;
                System.out.println("链接href："+hrefList+"===标题："+title);
                TechnologyModule technologyModule = new TechnologyModule();
                technologyModule.setM_title(title);//标题
                technologyModule.setM_datatime(new GetTheTimeStamp().getCurrentTime());//转载时间
                technologyModule.setM_resources_mode("2");//转载
                technologyModule.setM_publish_personid(23);//发布人默认为管理员hhr
                technologyModule.setM_typeid(16);//文章类型
                technologyModule.setReprintUrl(hrefList);//转载的文章url
                technologyModule.setM_fine_paste("0");
                list.add(technologyModule);
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
        CTO51Spider cto51Spider= new CTO51Spider();
        String url = "http://blog.51cto.com/artcommend/p27";
        List<TechnologyModule> list=cto51Spider.getAllUrls(url);
        List<TechnologyModule> lists = new ParseCTO51().parseContent(list);
    }
}
