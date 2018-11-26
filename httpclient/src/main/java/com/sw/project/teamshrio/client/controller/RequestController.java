package com.sw.project.teamshrio.client.controller;

import com.alibaba.fastjson.JSON;
import com.sw.project.teamshrio.client.httpconfiguration.HttpConfiguration;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sw on 2018/8/1.
 */
@RestController
public class RequestController {
    @Autowired
    private HttpConfiguration httpConfiguration;
    @RequestMapping("request")
    public void sendRequest(){
        CloseableHttpAsyncClient closeableHttpAsyncClient=httpConfiguration.closeableHttpAsyncClient();
        HttpPost httpPost=new HttpPost("http://localhost/httpclient/receive");
        UserInfo userInfo=new UserInfo("张三");
        StringEntity  httpEntity=new StringEntity(JSON.toJSONString(userInfo),"utf-8");
        httpPost.setEntity(httpEntity);
        try {
            closeableHttpAsyncClient.execute(httpPost,new CustomerAsyCallBack(httpEntity));
        } catch (Exception  e) {
            System.out.println("shibai*******"+e.getMessage());
            e.printStackTrace();
        }
    }
}