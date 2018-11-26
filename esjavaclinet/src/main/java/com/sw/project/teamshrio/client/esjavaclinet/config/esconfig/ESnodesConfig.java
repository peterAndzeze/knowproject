package com.sw.project.teamshrio.client.esjavaclinet.config.esconfig;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;

import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * ｅｓ　集群配置链接
 */
@Configuration
public class ESnodesConfig {
    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;//集群名称
    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNoes;//集群节点
   /* @Bean("restHighLevelClient")
    public RestHighLevelClient createTransportClient(){
        Settings settings=Settings.builder().put("client.transport.sniff", true).put("cluster.name",getClusterName()).build();
        RestClient restClient=null;
        String [] nodes=getClusterNoes().split(",");
        HttpHost [] httpHosts=new HttpHost[nodes.length];
        for(int i=0;i< nodes.length;i++){
            String node=nodes[i];
            String ip=node.substring(0,node.lastIndexOf(":"));
            int port=Integer.valueOf(node.substring(node.lastIndexOf(":")+1,node.length()));
            httpHosts[i]=new HttpHost(ip,port,"http");
            System.out.println(ip+"::::"+port);

        }
        System.out.println(httpHosts.length);
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(httpHosts));

        System.out.println(getClusterNoes());
        return client;

    }*/
   @Bean("transportClient")
   public TransportClient transportClient() {
       Settings settings = Settings.builder()
               .put("cluster.name", getClusterName()).put("client.transport.sniff", true).build();
       TransportClient client = new PreBuiltTransportClient(settings);
       String[] nodes = getClusterNoes().split(",");

       for (int i = 0; i < nodes.length; i++) {
           String node = nodes[i];
           String ip = node.substring(0, node.lastIndexOf(":"));
           int port = Integer.valueOf(node.substring(node.lastIndexOf(":") + 1, node.length()));
           try {
               client.addTransportAddress(new TransportAddress(InetAddress.getByName(ip), port));
           } catch (Exception e) {
               System.out.println("链接失败××××××××××××××××××××");
               e.printStackTrace();
           }

       }
       return client;
   }


    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getClusterNoes() {
        return clusterNoes;
    }

    public void setClusterNoes(String clusterNoes) {
        this.clusterNoes = clusterNoes;
    }
}
