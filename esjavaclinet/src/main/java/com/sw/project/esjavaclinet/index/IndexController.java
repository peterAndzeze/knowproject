package com.sw.project.esjavaclinet.index;


import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

/**
 * index
 */

@RestController
@RequestMapping("index")
public class IndexController {
    @Autowired
    private TransportClient transportClient;
    @RequestMapping("/test")
    public  String test(){
        return "ｔｅｓｔ";
    }


    /**
     * 获取集群信息
     * @return
     */
    @RequestMapping("/getHelth")
    public String getClusterInfo(){
        try {
            System.out.println("获取集群信息");
            ClusterAdminClient clusterAdminClient=transportClient.admin().cluster();
            return JSON.toJSONString(clusterAdminClient);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/getIndexInfo")
    public String getEsIndexs(){
        System.out.println("请求是");
        GetIndexResponse getIndexResponse= transportClient.admin().indices().prepareGetIndex().execute().actionGet();
        return JSON.toJSONString(getIndexResponse.getIndices());

    }


}
