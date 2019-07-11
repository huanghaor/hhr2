package com.hafiz.www.until.datagRabbingFromInternet.main;
import java.util.List;
import com.hafiz.www.po.TechnologyModule;
import com.hafiz.www.po.TypeOfArticle;
import com.hafiz.www.service.ArticleService;
import com.hafiz.www.until.datagRabbingFromInternet.db.SaveTheDataToDB;
import com.hafiz.www.until.datagRabbingFromInternet.down.CTO51Spider;
import com.hafiz.www.until.datagRabbingFromInternet.parse.ParseCTO51;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/spring.xml" })
public class CTO51Main {
    @Autowired
    private ArticleService articleService;
    @Test
    public void Ccto51Main(){
        /*List<TypeOfArticle> list= articleService.getTheTypeOfArtileList();
        System.out.println(list);*/
        for(int i=1;i<65;i++){
            System.out.println(i);
            String url="http://blog.51cto.com/artcommend/83/p"+i+"";
            List<TechnologyModule> list =new CTO51Spider().getAllUrls(url);
            List<TechnologyModule> lists = new ParseCTO51().parseContent(list);
            int data = articleService.SaveTheArticle(lists);
            System.out.println(data);
        }
    }
}
