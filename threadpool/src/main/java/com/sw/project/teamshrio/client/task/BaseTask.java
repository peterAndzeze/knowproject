package com.sw.project.teamshrio.client.task;


import com.sw.project.teamshrio.client.threadmanager.ThreadPoolManager;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基础任务类
 */
public abstract class BaseTask {
    //启动任务线程数量
    private int threadNum;
    //活动的线程数
    protected AtomicInteger activeThreadNum=new AtomicInteger(0);
    //是否结束
    protected AtomicBoolean isEnding=new AtomicBoolean(false);
    //线程上线文
    private  ThreadContext threadContext;
    //线程池监听
    private ThreadPoolManager threadPoolManager;
    //任务事件监听
    private TaskEeventListener taskEeventListener;
    //作为生产者时对应的消费任务
    private BaseTask consumerTask;


    public BaseTask getConsumerTask() {
        return consumerTask;
    }

    /**
     * 关联消费者任务
     * @param consumerTask
     */
    public void relateConsumerTask(BaseTask consumerTask){
        this.consumerTask=consumerTask;
    }

    /**
     * 数据放入消费队列
     * @param data
     */
    public void putDataToConsumer(Object data) throws InterruptedException {
        if(null!=getConsumerTask()){
            synchronized (getConsumerTask()) {
                getConsumerTask().getThreadContext().setTaskData(data);
            }
        }
        //.getThreadContext().setTaskData(data);
    }

    public TaskEeventListener getTaskEeventListener() {
        return taskEeventListener;
    }

    public ThreadPoolManager getThreadPoolManager() {
        return threadPoolManager;
    }

    public void setThreadPoolManager(ThreadPoolManager threadPoolManager) {
        this.threadPoolManager = threadPoolManager;
    }

    /**
     * 任务执行前做的事
     * @param threadContext
     */
    public abstract void before(ThreadContext threadContext);

    /**
     * 任务执行
     * @param threadContext
     */
    public abstract void run(ThreadContext threadContext);

    /**
     * 任务执行后动作
     * @param threadContext
     */
    public synchronized void end(ThreadContext threadContext){
        int totalCount = threadContext.getTotalNumber();
        int limit = threadContext.getLimit();
        int errors = threadContext.getErrors();
        int endNumber=threadContext.getEndNumbers();
        System.out.println(threadContext.getCurrentTask().getClass().getName()+"任务总数："+ totalCount + ",剩余任务数："+limit+",失败任务数："+errors+"已执行:"+endNumber);
        if(limit <= 0){//剩余任务数为0通知线程停止
            System.out.println("通知线程池结束任务执行");
            getThreadPoolManager().interrupt(this);
        }

    }

    /**
     * 停止线程
     */
    public void interrupt(){
        this.isEnding.set(true);
    }

    /**
     * 通知线程池停止当前任务执行
     */
    public void noticeStopThreadPool(){
        this.activeThreadNum.decrementAndGet();
        this.getThreadPoolManager().noticeInterrupt(this);
    }
    /**
     * 判断线程是否停止
     * @return
     */
    public boolean getIsEnding() {

        return this.isEnding.get();
    }

    /**
     * 获取当前活动的线程数
     * @return
     */
    public int getCurrentActiveThreadNum(){
        return this.activeThreadNum.get();
    }



    /**
     * 任务注册监听
     * @param taskEeventListener
     */
    public void registerTaskEventListener(TaskEeventListener taskEeventListener){
        this.taskEeventListener=taskEeventListener;
    }



    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
        this.activeThreadNum.set(threadNum);
    }

    public AtomicInteger getActiveThreadNum() {
        return activeThreadNum;
    }

    public void setActiveThreadNum(AtomicInteger activeThreadNum) {
        this.activeThreadNum = activeThreadNum;
    }

    public ThreadContext getThreadContext() {
        return threadContext;
    }

    public void setThreadContext(ThreadContext threadContext) {
        this.threadContext = threadContext;
    }


}
