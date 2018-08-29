package com.sw.project.threadmanager;


import com.sw.project.task.BaseTask;

/**
 * 线程池监听　动作
 */
public interface ThreadPoolManager {

    /**
     *终止任务
     * @param currentTask 当前执行任务
     */
    public void interrupt(BaseTask currentTask);


    /**
     * 是否关闭线程池
     * @return boolean
     */
    public boolean isShutDown();
    /**
     * 关闭线程池
     */
    public void shutDown();
    /**
     * 终止任务
     * @param currentTask 当前任务
     */
    public void noticeInterrupt(BaseTask currentTask);

    /**
     * 单一任务使用
     * @param tasks
     */
    public  void registereSingleTask(BaseTask... tasks);

    /**
     * 生产消费任务使用
     * @param workerTask
     * @param consumorTask
     */
    public  void registerPCTask(BaseTask workerTask, BaseTask consumorTask);
}
