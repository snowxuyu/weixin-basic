package com.weixin.entity;

/**
 * Created by snow on 2015/8/28.
 */
public class WeixinContext {

    private static String accessToken;

    public static void setAccessToken(String accessToken) {
        WeixinContext.accessToken = accessToken;
    }

    public static String getAccessToken() {
        return WeixinContext.accessToken;
    }
}
