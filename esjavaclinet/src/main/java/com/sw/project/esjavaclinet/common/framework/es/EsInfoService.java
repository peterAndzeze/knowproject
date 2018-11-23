package com.sw.project.esjavaclinet.common.framework.es;

import com.sw.project.esjavaclinet.common.framework.spring.SpringContextUtil;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.transport.TransportAddress;

import java.util.List;

/**
 *admin　响应信息
 */
public  class EsInfoService  {




    public static TransportClient getTransportClient(){
        TransportClient transportClient= SpringContextUtil.getBeanByClass(TransportClient.class);
        return transportClient;
    }
    /**
     * 获取正在链接的节点信息
     * @return
     */
    public static List<DiscoveryNode> getConnectionDiscoverNodes(){
        List<DiscoveryNode> discoveryNodes= getTransportClient().connectedNodes();
        return  discoveryNodes;
    }

    /**
     * 获取所有节点
      * @return
     */
    public static List<DiscoveryNode> getListedNodes(){
        return getTransportClient().listedNodes();
    }

    /**
     * 获取远程地址信息
     * @return
     */
    public static List<TransportAddress> getTransportAddress(){
       return  getTransportClient().transportAddresses();
    }

    /**
     * 获取集群操作clinet
     * @return
     */
    public static ClusterAdminClient getClusterClinet(){
        return getAdminClient().cluster();
    }

    /**
     * 获取所有索引
     * @return
     */
    public static String []  getIndexs(){
        String [] indexs=getTransportClient().admin().indices().prepareGetIndex().execute().actionGet().getIndices();
        return indexs;
    }

    /**
     * 获取索引操作client
     * @return
     */
    public static IndicesAdminClient getIndicesClient(){
        return getAdminClient().indices();
    }
    /**
     * 获取索引响应对象
     * @return
     */
    public static  GetIndexResponse getIndexResponse(){
        return getAdminClient().indices().prepareGetIndex().get();
    }

    /**
     * 获取adminClient
     * @return
     */
    public static AdminClient getAdminClient(){
        return getTransportClient().admin();
    }

    /**
     * 请求构建
     * @return
     */
    public static GetRequestBuilder getRequestBuilder(){
        return  getTransportClient().prepareGet();
    }

    /**
     *
     * @return
     */
    public static BulkRequestBuilder getBulkRequestBuilder(){
        return getTransportClient().prepareBulk();
    }







}
