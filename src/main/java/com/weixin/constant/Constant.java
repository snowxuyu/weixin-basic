package com.weixin.constant;

/**
 * Created by snow on 2015/8/23.
 */
public interface Constant {

    public interface WEIXIN{
        //接入微信的TOKEN
        public static final String TOKEN = "snowxuyuwx";
        //获取token的url 需要进行替换
        public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        //微信appid
        public static final String APPID = "wxdade0dede62a78a2";
        //微信应用密钥
        public static final String APPSECRET = "feccd28ff451e3191e9c6055ef0915bd";
        //添加微信菜单的URL请求
        public final static String MENU_ADD = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

        //消息类型
        public final static String MSG_TEXT_TYPE = "text";
        public final static String MSG_IMAGE_TYPE = "image";
        public final static String MSG_VOICE_TYPE = "voice";
        public final static String MSG_VIDEO_TYPE = "video";
        public final static String MSG_SHORTVIDEO_TYPE = "shortvideo";
        public final static String MSG_LOCATION_TYPE = "location";
        public final static String MSG_EVENT_TYPE = "event";

        public final static String POST_MEDIA="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
        public final static String GET_MEDIA="https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

    }

}
