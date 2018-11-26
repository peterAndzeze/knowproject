package com.sw.project.teamshrio.client.test;


import com.sw.project.teamshrio.client.task.BaseTask;
import com.sw.project.teamshrio.client.task.ThreadContext;

public class ProduceTask extends BaseTask {


    @Override
    public void before(ThreadContext threadContext) {

    }

    @Override
    public void run(ThreadContext threadContext) {
        try {
            Object o=threadContext.take();
            System.out.println("当前线程："+Thread.currentThread().getName()+",生产者执行业务数据:"+o+",");
        }catch (Exception e){//标记失败（暂时不记录详细信息）
            threadContext.markFault();
            e.printStackTrace();
        }



    }
}
