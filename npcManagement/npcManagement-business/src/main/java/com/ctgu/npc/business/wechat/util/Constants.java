package com.ctgu.npc.business.wechat.util;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
public class Constants {
    // 第三方用户唯一凭证
    public static String appid = "wx802551bb1cceab23";
    // 第三方用户唯一凭证密钥
    public static String appsecret = "075c0000ec26a11b22a66ee4260437c4";
    //商户ID
    public static String mch_id="1491636222";
    //商户KEY
    public static String mch_key="d6c6c7265c7cd56b0898f080d2131957";
// 第三方用户唯一凭证
     public static String xcxAppid = "wxff81bafb82109b7b";
//    // 第三方用户唯一凭证密钥
    public static String xcxAppsecret = "591d4cb65c9390b11d9dfdbb5f4efa51";
//    //商户ID
//    public static String mch_id="1235250302";
//    //商户KEY
//    public static String mch_key="123456789eeffddaa5599883344dqlcj";

    //获取openId
    public static String oauth2_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    public final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public final static String get_user_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
    public final static String send_msg_url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
    public final static String get_qrCode_ticket_url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
    public final static String get_qrCode_url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
    public final static String send_temp_msg_url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    public final static String ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    public final static String localhostIP = "106.14.182.80";
}
