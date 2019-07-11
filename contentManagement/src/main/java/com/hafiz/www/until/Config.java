package com.hafiz.www.until;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件
 * @author Administrator
 *
 */
public class Config extends Properties {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static Config config = null;
    private Config(){
        InputStream is = getClass().getResourceAsStream("/config.properties");
        try {
            this.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static synchronized Config getInstance(){
        if(config ==null){
            config = new Config();
        }
        return config;
    }
    //	String filePath = Config.getInstance().getProperty("noticePath");
}
