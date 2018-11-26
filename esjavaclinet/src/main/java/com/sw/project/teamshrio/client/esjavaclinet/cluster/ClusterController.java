package com.sw.project.teamshrio.client.esjavaclinet.cluster;

import com.alibaba.fastjson.JSON;
import com.sw.project.teamshrio.client.esjavaclinet.common.framework.es.EsInfoService;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 集群信息
 */
@RestController
@RequestMapping("/cluster")
public class ClusterController {

    @RequestMapping("/getConnnectionNodes")
    public String getConnectionNodes(){
        List<DiscoveryNode>  discoveryNodes= EsInfoService.getConnectionDiscoverNodes();
        return JSON.toJSONString(discoveryNodes);
    }

}
