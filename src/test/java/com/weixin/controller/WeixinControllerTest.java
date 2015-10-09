package com.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.weixin.constant.Constant;
import com.weixin.entity.WeixinMenu;
import com.weixin.junit.BaseControllerTest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by snow on 2015/8/29.
 */
public class WeixinControllerTest extends BaseControllerTest {

    /**
     * 获取token测试
     */
    @Test
    @Rollback(false)
    public void weixinTest() {
        try {
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get("/at");

            //requestBuilder.param("code", "46e3782de0a54642e7ec0be49c347598");

            requestBuilder.characterEncoding("UTF-8");
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            logger.info("result :{}"+result.getResponse().getContentAsString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 创建菜单测试  个人微信公众号没有权限创建菜单
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        WeixinMenu wm = new WeixinMenu();
        List<WeixinMenu> wmList = new ArrayList<>();
        wm.setName("学习网站");
        wm.setType("view");
        wm.setUrl("http://www.konghao.org/index");
        wmList.add(wm);

        WeixinMenu wm2 = new WeixinMenu();
        wm2.setName("测试资源");

        List<WeixinMenu> wm2Sub = new ArrayList<WeixinMenu>();
        wm = new WeixinMenu();
        wm.setName("事件测试");
        wm.setType("click");
        wm.setKey("A0001");
        wm2Sub.add(wm);

        wm = new WeixinMenu();
        wm.setName("扫描测试");
        wm.setType("pic_sysphoto");
        wm.setKey("rselfmenu_1_0");
        wm2Sub.add(wm);
        wm2.setSub_button(wm2Sub);

        wmList.add(wm2);
        Map<String,List<WeixinMenu>> maps = new HashMap<String,List<WeixinMenu>>();
        maps.put("button",wmList);
        String json = JSONObject.toJSONString(maps);
       // System.out.println(s);

        CloseableHttpClient client = HttpClients.createDefault();
        String url = Constant.WEIXIN.MENU_ADD;
        url = url.replace("ACCESS_TOKEN", "MASGlAMNqVK_PNBkRF7wFjPOW5jdn2l5cfpJ3v76PWANDsZHdHkAQxWlMfSaIwkWuVkB01j8hpVVIVNy-TcJ8bzBEadI6j38gIcWFRHoAkw");
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-Type","application/json");
        StringEntity entity = new StringEntity(json,
                ContentType.create("application/json", "utf-8"));
        post.setEntity(entity);
        CloseableHttpResponse resp = null;
        try {
            resp = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sc = resp.getStatusLine().getStatusCode();
        if(sc>=200&&sc<300) {
            System.out.println(EntityUtils.toString(resp.getEntity()));
        }
    }
}