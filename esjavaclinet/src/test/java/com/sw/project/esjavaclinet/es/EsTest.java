package com.sw.project.esjavaclinet.es;

import com.sw.project.esjavaclinet.EsjavaclinetApplicationTests;
import com.sw.project.esjavaclinet.common.framework.es.EsInfoService;
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
