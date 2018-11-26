package com.sw.project.teamshrio.client.task;

/**
 * 队列任务工厂
 */
public class TaskFactory {
    public static TaskFactory taskFactory;

    public TaskFactory(){

    }
    //单利
    public static TaskFactory getTaskFactory(){
        if(null==taskFactory){
            taskFactory=new TaskFactory();
        }
        return taskFactory;
    }

    /**
     * 初始化任务
     * @param currentTask
     * @return
     */
    public BaseTask getTask(Class<? extends BaseTask> currentTask){
        String taskName=currentTask.getName();
        System.out.println("任务名字："+taskName+"获取实例");
        try {
            BaseTask task=currentTask.newInstance();
            task.setThreadNum(1);//默认5个,从缓存里取动态配置和修改
            ThreadContext threadContext=ThreadContext.instansThreadContext(task);
            task.setThreadContext(threadContext);
            return task;
        } catch (Exception e) {
            System.out.println("获取任务实例异常");
            e.printStackTrace();
        }
        return null;
    }

}
