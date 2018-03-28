package com.ctgu.npc.business.wechat.util;

import com.ctgu.npc.fundamental.config.FundamentalConfigProvider;
import com.ctgu.npc.fundamental.logger.PlatformLogger;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class WeChatUtil {
    static PlatformLogger logger = PlatformLogger.getLogger(WeChatUtil.class);
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public final static String  get_unionId_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    public final static String appId = FundamentalConfigProvider.get("npc.appId");
    public final static String appSecret = FundamentalConfigProvider.get("npc.appSecret");

    public  static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);
            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            System.out.println("Weixin server connection timed out.");
        } catch (Exception e) {
            System.out.println("https request error:");
        }
        return jsonObject;
    }

    public static AccessToken getAccessToken() {
        AccessToken accessToken = null;
        String requestUrl = access_token_url.replace("APPID", appId).replace("APPSECRET", appSecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                //System.out.println("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return accessToken;
    }

    //
    public static String getUnionId(String openId) {
        // 拼装创建菜单的url
        String accessToken = getAccessToken().getToken();
        String url = get_unionId_url.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "GET", null);
        if (null != jsonObject) {
            if (jsonObject.get("unionid")==null){
                return "";
            }
            logger.info("unionId:"+jsonObject.get("unionid").toString());
            return jsonObject.get("unionid").toString();
        }
        return "";
    }

    public static String sendCustomToUser(String content){
        String tmpurl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
        String token =  getAccessToken().getToken();  //微信凭证，access_token
        String url = tmpurl.replace("ACCESS_TOKEN", token);
        try {
            JSONObject result = httpRequest(url, "POST", content);
             String errmsg = (String) result.get("errmsg");
            if(!"ok".equals(errmsg)){  //如果为errmsg为ok，则代表发送成功，公众号推送信息给用户了。
                return "error";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "success";
    }


    public static String sendTemplateToUser(String content){
        String tmpurl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        String token =  getAccessToken().getToken(); //微信凭证，access_token
        String url = tmpurl.replace("ACCESS_TOKEN", token);
        try {
            JSONObject result = httpRequest(url, "POST", content);
            String errmsg = (String) result.get("errmsg");
            if(!"ok".equals(errmsg)){  //如果为errmsg为ok，则代表发送成功，公众号推送信息给用户了。
                return "error";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "success";
    }

    public static void main(String[] args) throws Exception {
      String openid = "onnhj0vaczZouldH1DHR6nUIaHl4";
        System.out.println(getUnionId(openid));
    }

}
