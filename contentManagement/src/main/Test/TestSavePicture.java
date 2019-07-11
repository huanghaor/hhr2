import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hafiz.www.po.MessageWal;
import com.hafiz.www.po.PublicPagingQuery;
import com.hafiz.www.po.TechnologyModule;
import com.hafiz.www.service.ArticleService;
import com.hafiz.www.service.IndexService;
import com.hafiz.www.service.MessageWalService;
import com.hafiz.www.until.Config;
import com.hafiz.www.until.GetTheTimeStamp;
import com.hafiz.www.until.JsonResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;


public class TestSavePicture {

    @Test
    public void testpic(){
        String pdfpathDoc = Config.getInstance().getProperty("filepath");
        File file = new File(pdfpathDoc);
        if(!file.exists()){
            file.mkdirs();
        }
        System.out.println(file.exists());
    }

    @Test
    public void testTime(){
        GetTheTimeStamp get = new GetTheTimeStamp();
        System.out.println("picture"+get.getTheTimeStamp());
    }
    @Autowired
    private MessageWalService messageWalService;
    private IndexService indexService;
    @Test
    public void TestMessageData(){
       /* MessageWal messageWal = new MessageWal();
        PublicPagingQuery publicPagingQuery = new PublicPagingQuery();
        publicPagingQuery.setPageNumber(1);
        publicPagingQuery.setPageSize(5);
        messageWal.setW_uid(0);
        messageWal.setW_status("1");
        publicPagingQuery.setQueryObj(messageWal);
        //messageWalService.selectMessageData(publicPagingQuery);
        //Object jsonObject = JSONObject.toJSON(publicPagingQuery);
        int count = messageWalService.getCount("1");*/

        System.out.println(indexService.getIndexNavList());
    }
    @Autowired
    private ArticleService articleService;
    @Test
    public void addData(){
        TechnologyModule technologyModule = new TechnologyModule();
        technologyModule.setM_content("123456qwerty");
        technologyModule.setM_publish_personid(21);
        technologyModule.setM_typeid(11);
        technologyModule.setM_title("123木头人");
        int data = articleService.insertSelective(technologyModule);
        System.out.println(data);
    }


    @Test
    public void changeTheTime(){
        /*String logDate = "Wed Oct 12 2016 00:00:00 GMT+0800 (中国标准时间)";
        logDate = logDate.replace("GMT", "").replaceAll("\\(.*\\)", "");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss z",Locale.ENGLISH);
        try{
            Date logDate1 = sdf.parse(logDate);
            System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(logDate1));
        }catch(Exception e){
            e.printStackTrace();
        }*/
       /* String datdString="Wed Oct 12 2016 00:00:00 GMT+0800 (中国标准时间)";
        datdString = datdString.replace("GMT", "").replaceAll("\\(.*\\)", "");
        //将字符串转化为date类型，格式2016-10-12
        SimpleDateFormat format =  new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss z",Locale.ENGLISH);
        Date dateTrans = format.parse(datdString);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(dateTrans));*/
        System.out.println("的海军回复");

    }

}
