package com.sw.project.teamshrio.client.test;


import com.sw.project.teamshrio.client.task.BaseTask;
import com.sw.project.teamshrio.client.task.ThreadContext;

public class TestTask extends BaseTask {
    @Override
    public void before(ThreadContext threadContext) {

    }

    @Override
    public void run(ThreadContext threadContext) {
        Object o=threadContext.take();
        Integer i=Integer.valueOf(o.toString());
        if(i==3){
            threadContext.markFault();
        }
        System.out.println("当前线程:"+Thread.currentThread().getName()+"执行业务数据："+o);
    }
}
