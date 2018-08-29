package com.sw.project.task;

import java.util.List;

public class TaskRunnable implements Runnable  {
    private ThreadContext threadContext;
    private BaseTask currentTask;

    public TaskRunnable(ThreadContext threadContext,BaseTask currentTask) {
        this.threadContext=threadContext;
        this.currentTask=currentTask;
    }

    @Override
    public void run() {
        try {
            while (!this.getCurrentTask().getIsEnding()){//如果没有停止
                this.getCurrentTask().before(threadContext);
                try {
                    this.getCurrentTask().run(threadContext);
                }catch (Exception e){
                    System.out.println("执行异常："+e.getMessage());
                    e.printStackTrace();
                }
                this.getCurrentTask().end(threadContext);
            }

        }catch (Exception e){
            System.out.println("当前线程:"+Thread.currentThread().getName()+",执行task:"+currentTask+"异常");
            e.printStackTrace();
        }finally {
            //通知线程池结束运行
            this.getCurrentTask().noticeStopThreadPool();
        }
    }

    public ThreadContext getThreadContext() {
        return threadContext;
    }

    public BaseTask getCurrentTask() {
        return currentTask;
    }

}
