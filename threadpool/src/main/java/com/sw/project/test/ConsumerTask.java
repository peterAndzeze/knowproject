package com.sw.project.test;


import com.sw.project.task.BaseTask;
import com.sw.project.task.ThreadContext;

public class ConsumerTask extends BaseTask {
    @Override
    public void before(ThreadContext threadContext) {

    }

    @Override
    public void run(ThreadContext threadContext) {
        try {
            Object o=threadContext.take();
            System.out.println("当前线程："+Thread.currentThread().getName()+",消费者执行业务数据:"+o);
        }catch (Exception e){//标记失败（暂时不记录详细信息）
            threadContext.markFault();
        }
    }
}
