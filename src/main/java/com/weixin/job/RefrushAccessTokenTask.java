package com.weixin.job;

import com.alibaba.fastjson.JSONObject;
import com.weixin.constant.Constant;
import com.weixin.entity.AccessToken;
import com.weixin.entity.WeixinContext;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by snow on 2015/8/28.
 * 刷新access_token
 */
@Component
public class RefrushAccessTokenTask {

    public void refreshToken() {
        HttpGet get = null;
		CloseableHttpResponse resp = null;
		CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();
            String url = Constant.WEIXIN.ACCESS_TOKEN_URL;
            url = url.replaceAll("APPID", Constant.WEIXIN.APPID);
            url = url.replaceAll("APPSECRET", Constant.WEIXIN.APPSECRET);
            get = new HttpGet(url);
            resp = client.execute(get);
            int statusCode = resp.getStatusLine().getStatusCode();
            if(statusCode>=200&&statusCode<300) {
                HttpEntity entity = resp.getEntity();
                if (entity!=null) {
                    String content = EntityUtils.toString(entity);
                    AccessToken at = JSONObject.parseObject(content, AccessToken.class);
                    WeixinContext.setAccessToken(at.getAccess_token());
                    System.out.println("刷新AccessToken："+at.getAccess_token());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(resp!=null) resp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(client!=null) client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
