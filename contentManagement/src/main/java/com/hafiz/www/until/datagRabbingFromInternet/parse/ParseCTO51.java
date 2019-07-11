package com.hafiz.www.until.datagRabbingFromInternet.parse;

import com.hafiz.www.po.TechnologyModule;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析内容
 */
public class ParseCTO51 {
    public List<TechnologyModule> parseContent(List<TechnologyModule> list){
        Document document=null;
        List<TechnologyModule> list_ = new ArrayList<TechnologyModule>();
        for(TechnologyModule technologyModule:list){
       /* for(int i=0;i<list.size();i++){*/
            /*TechnologyModule technologyModule = list.get(0);*/
            try {
                document = Jsoup.connect(technologyModule.getReprintUrl()).userAgent(
                        "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0"
                ).get();
                String content_="div .artical-Left-blog div.main-content";
                Elements content_s = document.select(content_);
                String m_resources_mode_url_="div .artical-Left-blog div.artical-copyright";
                Elements m_resources_mode_urlS = document.select(m_resources_mode_url_);
                for(int i=0;i<content_s.size();i++){
                    TechnologyModule technologyModule1 = new TechnologyModule();
                    String m_content = content_s.get(i).html();
                    String m_resources_mode_url ="";
                    if(m_resources_mode_urlS.size()>0){
                        m_resources_mode_url = m_resources_mode_urlS.get(i).text();
                        //©著作权归作者所有：来自51CTO博客作者纯洁微笑的原创作品，如需转载，请注明出处，否则将追究法律责任
                        m_resources_mode_url="本文于 "+technologyModule.getM_datatime()+"转载，"+m_resources_mode_url+" , |感谢分享";
                        technologyModule1.setM_resources_mode_url(m_resources_mode_url);
                    }
                    technologyModule1.setM_typeid(technologyModule.getM_typeid());
                    technologyModule1.setM_publish_personid(technologyModule.getM_publish_personid());
                    technologyModule1.setM_title(technologyModule.getM_title());
                    technologyModule1.setM_datatime(technologyModule.getM_datatime());
                    technologyModule1.setM_resources_mode(technologyModule.getM_resources_mode());
                    technologyModule1.setM_content(m_content);
                    technologyModule1.setM_readnum(0);
                    technologyModule1.setM_fine_paste(technologyModule.getM_fine_paste());
                    list_.add(technologyModule1);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println(list_.size());
        return list_;
    }
}
