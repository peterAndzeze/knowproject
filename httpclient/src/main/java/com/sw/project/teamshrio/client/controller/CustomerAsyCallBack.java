package com.sw.project.teamshrio.client.controller;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by sw on 2018/8/1.
 */
public class CustomerAsyCallBack implements FutureCallback<HttpResponse> {
    private HttpEntity httpEntity;

    public CustomerAsyCallBack(HttpEntity httpEntity) {
        this.httpEntity = httpEntity;
    }

    @Override
    public void completed(HttpResponse httpResponse) {
        System.out.println("success");
    }

    @Override
    public void failed(Exception e) {
        System.out.println("失败了："+e.getMessage());
        try {
            System.out.println(JSON.toJSONString(EntityUtils.toString(httpEntity)));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void cancelled() {

    }
}