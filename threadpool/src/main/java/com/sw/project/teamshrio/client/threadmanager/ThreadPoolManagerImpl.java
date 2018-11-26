package com.sw.project.teamshrio.client.threadmanager;


import com.sw.project.teamshrio.client.task.BaseTask;
import com.sw.project.teamshrio.client.task.TaskEeventListener;
import com.sw.project.teamshrio.client.task.TaskRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManagerImpl implements ThreadPoolManager {

    // 生产任务task
    private BaseTask workerTask = null;
    // 消费任务task
    private BaseTask consumorTask = null;
    //普通任务
    private BaseTask[] tasks;
    //管理器运行状态
    //private boolean manageRunningState = false;
    //线程池
    private ExecutorService executorService;
    //是否单一任务 true 是 false 否
    private boolean isSingle=false;

   /* *//**
     * 初始化线程池
     *//*
    public void init(boolean isSingle,BaseTask task,BaseTask workerTask,BaseTask consumorTask){
        if(isSingle){
            this.task=task;
        }else{
            this.workerTask=workerTask;
            this.consumorTask=consumorTask;
        }

    }*/

    /**
        //先实验单任务执行吧
     * 注册任务支持批量
     * @param tasks
     */
    public synchronized void registereSingleTask(BaseTask...tasks){
        this.tasks=tasks;
        isSingle=true;
        for(BaseTask baseTask:tasks){
            startTask(baseTask);
        }
    }

    /**
     * 注册生产消费
     * @param workerTask
     * @param consumorTask
     */
    public synchronized void registerPCTask(BaseTask workerTask,BaseTask consumorTask){
        this.workerTask=workerTask;
        this.consumorTask=consumorTask;
        isSingle=false;
        this.workerTask.relateConsumerTask(consumorTask);
        startTask(workerTask);
        startTask(consumorTask);
    }

    /**
     * 启动任务
     */
    private void startTask(BaseTask task){
        try {
            int taskNum = task.getThreadNum();//任务数
            //BlockingQueue<Runnable> runnables=new ArrayBlockingQueue<Runnable>(taskNum);
            executorService = Executors.newFixedThreadPool(2);//线程池总数 默认100 缓存配置，动态获取
            for (int i = 0; i < taskNum; i++) {
                TaskRunnable taskRunnable = new TaskRunnable(task.getThreadContext(), task);
                //  runnables.add(taskRunnable);
                executorService.submit(taskRunnable);
            }
        }catch (Exception e){//失败策略，线程池策略
            System.out.println("任务执行失败");
        }
    }




    @Override
    public void interrupt(BaseTask currentTask) {
        currentTask.interrupt();
    }

    @Override
    public boolean isShutDown() {
        return false;
    }

    /**
     * 停止任务
     */
    @Override
    public void shutDown() {
        boolean isShutdown=false;
        if(isSingle) {//单一
            for (BaseTask task : tasks) {
                if (!task.getIsEnding()) {
                    isShutdown = false;
                    break;
                }

            }
        }else {
            boolean workEnding=getWorkerTask().getIsEnding();
            boolean consumerEnding=getConsumorTask().getIsEnding();
            isShutdown=workEnding & consumerEnding;
            System.out.println("生产:"+getWorkerTask().getClass().getName()+"消费者:"+getWorkerTask().getClass().getName()+"执行结果："+isShutdown);
        }
        System.out.println("isSingle:"+isSingle+"isShutdown："+isShutdown);
        if(isShutdown) {
            System.out.println("线程池结束××××××××××××××××××××××");
        }
    }

    public BaseTask getWorkerTask() {
        return workerTask;
    }

    public BaseTask getConsumorTask() {
        return consumorTask;
    }

    public BaseTask[] getTasks() {
        return tasks;
    }

    /**
     * 通知线程池停止执行当前任务
     * @param currentTask 当前任务
     */
    @Override
    public void noticeInterrupt(BaseTask currentTask) {
        System.out.println("通知我要结束线程池了*********************");
        int acticeThreadNum=currentTask.getCurrentActiveThreadNum();
        if(acticeThreadNum==0){//没有活动的线程
            //triggerAfterTaskEvent(currentTask);
            shutDown();
            currentTask.setThreadNum(0);
        }else {
            System.out.println(currentTask.getClass().getName() + "活动线程:" + acticeThreadNum);
        }
    }
    /**
     * 触发After任务事件
     */
    private void triggerAfterTaskEvent(BaseTask currentTask) {
        TaskEeventListener taskEventListenerList = currentTask.getTaskEeventListener();
        System.out.println("任务执行后监听end()开始执行**************");
        taskEventListenerList.end(currentTask.getThreadContext());
        System.out.println("任务执行后监听end()结束执行**************");

    }

}
