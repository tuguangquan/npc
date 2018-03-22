package com.ctgu.npc.business.wechat.util;

import com.ctgu.npc.business.coreServlet.Constants;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
public class CommonUtil {

    public static JSONObject httpsRequestToJsonObject(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            StringBuffer buffer = httpsRequest(requestUrl, requestMethod, outputStr);
            jsonObject = JSONObject.fromObject(buffer.toString());
        }  catch (Exception e) {
            System.out.println("https请求异常："+e.getMessage());
        }
        return jsonObject;
    }


    private static StringBuffer httpsRequest(String requestUrl, String requestMethod, String output)
            throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException, MalformedURLException,
            IOException, ProtocolException, UnsupportedEncodingException {

        URL url = new URL(requestUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod(requestMethod);
        if (null != output) {
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(output.getBytes("UTF-8"));
            outputStream.close();
        }

        // 从输入流读取返回内容
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        StringBuffer buffer = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }

        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        inputStream = null;
        connection.disconnect();
        return buffer;
    }

    /**
     * 获取用户的openId，并放入session
     * @param code 微信返回的code
     */
    private void setOpenId(String code) {
        //session.put("code", code);
        String oauth2_url = Constants.oauth2_url.replace("APPID", com.ctgu.npc.business.wechat.coreServlet.Constants.appid).replace("SECRET", com.ctgu.npc.business.wechat.coreServlet.Constants.appsecret).replace("CODE", String.valueOf(code));
       // log.info("oauth2_url:"+oauth2_url);
        JSONObject jsonObject = com.ctgu.npc.business.wechat.coreServlet.CommonUtil.httpsRequestToJsonObject(oauth2_url, "POST", null);
       // log.info("jsonObject:"+jsonObject);
        Object errorCode = jsonObject.get("errcode");
        if(errorCode != null) {
       //     log.info("code不合法");
        }else{
            String openId = jsonObject.getString("openid");
        //    log.info("openId:"+openId);
        //    session.put("openId", openId);
        }
    }
}
