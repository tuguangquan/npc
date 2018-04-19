package com.ctgu.npc.business.wechat.util;

import com.ctgu.npc.fundamental.config.FundamentalConfigProvider;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/12/12.
 */
public class OpenIdUtil {

    public static void main(String[] args) {
       // System.out.println(session_key("0132nbFN1EbA921qEBEN1XFnFN12nbF1"));
    }

    public static String getOpenId(String code) {
        String appid= FundamentalConfigProvider.get("npc.xcxAppid");
        String appsecret= FundamentalConfigProvider.get("npc.xcxAppsecret");
        //授权（必填）
        String grant_type = "authorization_code";
        //URL
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        //请求参数
        String params = "appid=" + appid + "&secret=" + appsecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String data = get(requestUrl, params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.fromObject(data);
        //用户的唯一标识（openid）
        //System.out.println(Openid);
        return String.valueOf(json.get("openid"));
    }



    public static String getSession_key(String code) {
        String appid= FundamentalConfigProvider.get("npc.xcxAppid");
        String appsecret= FundamentalConfigProvider.get("npc.xcxAppsecret");
        //授权（必填）
        String grant_type = "authorization_code";
        //URL
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        //请求参数
        String params = "appid=" + appid + "&secret=" + appsecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String data = get(requestUrl, params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.fromObject(data);
        //用户的唯一标识（openid）
        //System.out.println(Openid);
        return String.valueOf(json.get("session_key"));
    }

    public static String get(String url,String param){
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            //System.out.println(urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            /*for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }*/
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
