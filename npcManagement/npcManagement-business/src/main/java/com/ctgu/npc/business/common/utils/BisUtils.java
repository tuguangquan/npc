package com.ctgu.npc.business.common.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class BisUtils {

    // 上传图片
    public static boolean uploadPicture(MultipartFile file,
            HttpServletRequest request) {
        Properties properties = PropertyUtil.loadProperties("jeesite.properties");
        String fileUploadPath = properties.getProperty("npc.photo");
        String fileName = request.getParameter("fileName");
        File targetFile = new File(fileUploadPath,fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    
    // 读取图片
    public static void readNpcImage(String image, HttpServletResponse response)
            throws IOException {
        Properties properties = PropertyUtil.loadProperties("jeesite.properties");
        String fileUploadPath = properties.getProperty("npc.photo");
        if (StringUtils.isNotEmpty(image)) {
            FileInputStream fio = new FileInputStream(fileUploadPath + File.separator
                    + image);
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = fio.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
        }
    }
    
    // 判断数组中是否包含某一元素
    public static boolean isContained(String[] str,String st){
        for(String string : str){
            if(string.equals(st)){
                return true;
            }
        }
        return false;
    }
    
    public static String getCurrentTime(String pattern){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
}
