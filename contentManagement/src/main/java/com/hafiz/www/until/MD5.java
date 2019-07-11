package com.hafiz.www.until;

import com.hafiz.www.po.Userinfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

//添加user的密码加密方法
public class MD5 {
    public static String  SysMd5(Userinfo userinfo) {
        String hashAlgorithmName = "MD5";//加密方式

        Object crdentials =userinfo.getU_password();//密码原值

        ByteSource salt = ByteSource.Util.bytes(userinfo.getU_name());//以账号作为盐值

        int hashIterations = 1024;//加密1024次

        SimpleHash hash = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);

        return hash.toString();
    }
}
