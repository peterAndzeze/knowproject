package com.sw.project.teamshrio.client.esjavaclinet.es;

import com.sw.project.teamshrio.client.esjavaclinet.EsjavaclinetApplicationTests;
import com.sw.project.teamshrio.client.esjavaclinet.common.framework.es.EsInfoService;
import org.junit.Test;

public class EsTest extends EsjavaclinetApplicationTests {
    /**
     * 获取索引
     */
    @Test
    public  void getIndexs(){
        String [] indexs= EsInfoService.getIndexs();
        System.out.println("num:"+indexs.length);
        for(String index:indexs){
            System.out.println(index);
        }
    }
}
