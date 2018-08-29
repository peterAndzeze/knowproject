package com.sw.project.httpconfiguration;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sw on 2018/8/1.
 */
@Configuration
public class HttpConfiguration {

    /**
     * 初始化连接池
     * @return
     */
    @Bean
    public PoolingNHttpClientConnectionManager poolingNHttpClientConnectionManager(){
        // 配置io线程
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setIoThreadCount(Runtime.getRuntime().availableProcessors()).build();
        // 设置连接池大小
        ConnectingIOReactor ioReactor= null;
        try {
            ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        } catch (IOReactorException e) {
            e.printStackTrace();
        }
        PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor);
        connManager.setMaxTotal(100);
        connManager.setDefaultMaxPerRoute(100);
        return connManager;
    }

    @Bean
    public RequestConfig requestConfig(){
        //请求相关配置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(2000)
                .setSocketTimeout(10)
                .setConnectionRequestTimeout(5000).build();
        return  requestConfig;
    }

    public CloseableHttpAsyncClient closeableHttpAsyncClient(){
        CloseableHttpAsyncClient closeableHttpAsyncClient= HttpAsyncClients.custom()
                                        .setConnectionManager(poolingNHttpClientConnectionManager())
                                        .setDefaultRequestConfig(requestConfig()).build();
        closeableHttpAsyncClient.start();
        return  closeableHttpAsyncClient;

    }


}