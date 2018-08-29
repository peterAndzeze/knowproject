package com.sw.project.task;

/**
 * 任务事件监听
 */
public interface TaskEeventListener {



    /**
     * 监听任务开始
     * 参数线程上下文
     */
    public void before(ThreadContext threadContext);

    /**
     * 监听任务结束
     * @param threadContext
     */
    public void end(ThreadContext threadContext);

}
