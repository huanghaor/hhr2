package com.hafiz.www.until.IndustryInfomationFromInternet.main;

import com.hafiz.www.mapper.IndustryInformationMapper;
import com.hafiz.www.po.IndustryInformation;
import com.hafiz.www.po.TechnologyModule;
import com.hafiz.www.service.ArticleService;
import com.hafiz.www.until.IndustryInfomationFromInternet.down.IndustrySpider;
import com.hafiz.www.until.IndustryInfomationFromInternet.parse.ParseIndustry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/spring.xml" })
public class IndustryMain {
    @Autowired
    private IndustryInformationMapper industryInformationMapper;
    @Test
    public void IndustryMain(){
        /*List<TypeOfArticle> list= articleService.getTheTypeOfArtileList();
        System.out.println(list);*/
        for(int i=1;i<2;i++){
            System.out.println(i);
            String url="http://blog.51cto.com/artcommend/15/p"+i+"";
            List<IndustryInformation> list =new IndustrySpider().getAllUrls(url);
            List<IndustryInformation> lists = new ParseIndustry().parseContent(list);
            int data = industryInformationMapper.insertSelective(lists);
            System.out.println(data);
        }
    }
}
