package com.hafiz.www.until;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 保存用户上传图片
 */
@Service
public class SaveThePicture {

    public String savePicture(MultipartFile file,String type) {
        //设置时间戳给文件重命名
        String newName = new GetTheTimeStamp().getTheTimeStamp();
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;

        try {
            //获取文件存储路径
            String uploadDir = "";
            if(type.equals("donkeyTrip")){//旅行游记文件保存路径
                uploadDir = Config.getInstance().getProperty("donkeyTrip");
            }
            if(type.equals("messagewal")){//社区文件保存路径
                uploadDir = Config.getInstance().getProperty("messagewal");
            }
            if(type.equals("essayDairyData")){//随笔文件保存路径
                uploadDir = Config.getInstance().getProperty("essayDairyData");
            }
            if(type.equals("userhead")){//用户头像文件保存路径
                uploadDir = Config.getInstance().getProperty("userhead");
            }
            if(type.equals("articleData")){//技术文章文件保存路径
                uploadDir = Config.getInstance().getProperty("articleData");
            }
            File files = new File(uploadDir);
            //判断某文件是否存在，不存在则创建
            if (!files.exists()) {
                files.mkdirs();
            }
            String fileName = file.getOriginalFilename();//得到上传的文件名
            //重新命名的文件
            newName = newName + fileName.substring(fileName.lastIndexOf("."));
            FileOutputStream imgOut=new FileOutputStream(new File(files,newName));//根据 files 抽象路径名和 img 路径名字符串创建一个新 File 实例。

            imgOut.write(file.getBytes());//返回一个字节数组文件的内容
            imgOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        if(type.equals("donkeyTrip")){//旅行游记文件保存路径
            array.put("/file/donkeyTripUploadData/"+newName);//将图片的引用url放入JSON返回给富文本编辑器进行回显
        }
        if(type.equals("messagewal")){//社区文件保存路径
            array.put("/file/messageWalData/"+newName);//将图片的引用url放入JSON返回给富文本编辑器进行回显
        }
        if(type.equals("essayDairyData")){//随笔文件保存路径
            array.put("/file/essayDairyData/"+newName);//将图片的引用url放入JSON返回给富文本编辑器进行回显
        }
        if(type.equals("userhead")){//随笔文件保存路径
            array.put("/file/userhead/"+newName);//将图片的引用url放入JSON返回给富文本编辑器进行回显
        }
        if(type.equals("articleData")){//技术文章文件保存路径
            array.put("/file/articleData/"+newName);//将图片的引用url放入JSON返回给富文本编辑器进行回显
        }
        json.put("errno",0);
        json.put("data",array);
        return json.toString();
    }
}
