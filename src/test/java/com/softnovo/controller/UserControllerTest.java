package com.softnovo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    protected MockMvc mockMvc;

    @Resource
    protected WebApplicationContext wac;

    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }

    @Test
    public void testhandle41() {
        String responseString = null;   //将相应的数据转换为字符串
        try {
            responseString = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/user/test")    //请求的url,请求的方法是get
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("pcode","root")         //添加参数
            ).andExpect(MockMvcResultMatchers.status().isOk())    //返回的状态是200
                    .andDo(MockMvcResultHandlers.print())         //打印出请求和相应的内容
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("--------返回的json = " + responseString);
    }

}