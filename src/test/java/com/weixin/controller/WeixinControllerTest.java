package com.weixin.controller;

import com.weixin.junit.BaseControllerTest;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Created by snow on 2015/8/29.
 */
public class WeixinControllerTest extends BaseControllerTest {

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
}