package com.hafiz.www.until;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static java.lang.Math.round;

/**
 * 保存上传文件
 */
@Service
public class SaveTheFile {
    public String saveTheFile( MultipartHttpServletRequest multiRequest ,String type) throws IllegalStateException, IOException {
        //取得request中的所有文件名
        List <Map<String,String>> list = new ArrayList <Map<String,String>>();
        JSONObject json = new JSONObject();
        try {
            MultiValueMap <String, MultipartFile> map = multiRequest.getMultiFileMap();
            List <MultipartFile> multipartFile = map.get("file_data");
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
            if(type.equals("articleData")){//技术文章文件保存路径
                uploadDir = Config.getInstance().getProperty("articleData");
            }
            for (int i = 0; i < multipartFile.size(); i++) {
                //取得上传文件
                MultipartFile file = multipartFile.get(i);
                if (file != null) {
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        System.out.println(myFileName);
                        //设置时间戳给文件重命名
                        String newName = new GetTheTimeStamp().getTheTimeStamp();
                        //重命名上传后的文件名
                        String fileName = newName + file.getOriginalFilename();
                        Map<String,String> maps = new HashMap <String, String>();
                        if(type.equals("donkeyTrip")){//旅行游记文件保存路径
                            maps.put("url","file/donkeyTripUploadData/" + fileName);
                            maps.put("e_filetype",file.getContentType());
                            maps.put("e_filename",myFileName);
                            maps.put("e_filesize",sizecount(file.getSize()));
                            list.add(maps);
                        }
                        if(type.equals("messagewal")){//社区文件保存路径
                            maps.put("url","file/messageWalData/" + fileName);
                            maps.put("e_filetype",file.getContentType());
                            maps.put("e_filename",myFileName);
                            maps.put("e_filesize",sizecount(file.getSize()));
                            list.add(maps);
                        }
                        if(type.equals("essayDairyData")){//随笔文件保存路径
                            maps.put("url","file/essayDairyData/" + fileName);
                            maps.put("e_filetype",file.getContentType());
                            maps.put("e_filename",myFileName);
                            maps.put("e_filesize",sizecount(file.getSize()));
                            list.add(maps);
                        }
                        if(type.equals("articleData")){//技术文章文件保存路径
                            maps.put("url","file/articleData/" + fileName);
                            maps.put("e_filetype",file.getContentType());
                            maps.put("e_filename",myFileName);
                            maps.put("e_filesize",sizecount(file.getSize()));
                            list.add(maps);
                        }
                        String path = uploadDir + "/" + fileName;
                        File localFile = new File(path);
                        //判断某文件是否存在，不存在则创建
                        if (!localFile.exists()) {
                            localFile.mkdirs();
                        }
                        file.transferTo(localFile);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        json.put("data", list);
        return json.toString();
    }

    private String sizecount(Long size) {
        String sizes="";
        if (size >= 1073741824) {
            sizes = round(size / 1073741824 * 100) / 100 + "G";
        } else if (size >= 1048576) {
            sizes = round(size / 1048576 * 100) / 100 + "M";
        } else if (size >= 1024) {
            sizes = round(size / 1024 * 100) / 100 + "kb";
        } else {
            sizes = size + "Bytes";
        }
        return sizes;
    }

}
