package com.weixin.controller;

import com.weixin.constant.Constant;
import com.weixin.entity.WeixinContext;
import com.weixin.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by snow on 2015/8/23.
 */
@Controller
public class WeixinController {

    /**
     * 接受微信参数初始化 完成微信验证
     * signature 	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     timestamp 	时间戳
     nonce 	随机数
     echostr 	随机字符串
     */
    @RequestMapping("/init")
    public void init(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature =  request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        String[] arrs = {Constant.WEIXIN.TOKEN, timestamp, nonce};
        Arrays.sort(arrs);
        StringBuilder sb = new StringBuilder();

        for (String a : arrs) {
            sb.append(a);
        }

        String shal = SecurityUtils.shal(sb.toString());
        //System.out.println(shal.equals(signature));
        if (shal.equals(signature)) {
            response.getWriter().println(echostr);
        }

    }

    @RequestMapping("/at")
    public void accessToken(HttpServletResponse resp) throws IOException {
        resp.getWriter().println(WeixinContext.getAccessToken());
    }
}
