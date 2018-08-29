package com.sw.project.threadpool;


import com.sw.project.task.BaseTask;
import com.sw.project.task.TaskEeventListener;
import com.sw.project.task.TaskFactory;
import com.sw.project.test.ConsumerTask;
import com.sw.project.test.ProduceTask;
import com.sw.project.threadmanager.ThreadPoolManager;
import com.sw.project.threadmanager.ThreadPoolManagerImpl;

import java.util.Arrays;
import java.util.List;

/**
 * 线程池服务
 */
public class ThreadPoolService {

    public void executeSingleTask(Class<? extends BaseTask> currentTask, Class<? extends TaskEeventListener> taskEeventListener, Object data){
       try {
           BaseTask baseTask= TaskFactory.getTaskFactory().getTask(currentTask);
           baseTask.getThreadContext().setTaskData(data);
           TaskEeventListener currentTaskEventListener=null;
           if(null!=taskEeventListener){
               try {
                   currentTaskEventListener=taskEeventListener.newInstance();
                   baseTask.registerTaskEventListener(currentTaskEventListener);
               } catch (Exception e) {
                   System.out.println("注册任务监听动作失败");
                   e.printStackTrace();
               }
           }
           ThreadPoolManager threadPoolManager=new ThreadPoolManagerImpl();
           baseTask.setThreadPoolManager(threadPoolManager);
           threadPoolManager.registereSingleTask(baseTask);
       }catch (Exception e){
           System.out.println("创建任务队列失败");
       }

    }


    public void executePCTask(Class<? extends BaseTask> workTask,Class<? extends BaseTask> consumerTask ,Class<? extends  TaskEeventListener> taskEeventListener,Object data){
      try {

          BaseTask work = TaskFactory.getTaskFactory().getTask(workTask);
          work.getThreadContext().setTaskData(data);

          ThreadPoolManager threadPoolManager = new ThreadPoolManagerImpl();
          work.setThreadPoolManager(threadPoolManager);

          BaseTask consumer = TaskFactory.getTaskFactory().getTask(consumerTask);
          consumer.setThreadPoolManager(threadPoolManager);
          //提前全量数据放入消费 这样做没什么意义
          //consumer.getThreadContext().setTaskData(data);
          threadPoolManager.registerPCTask(work, consumer);
      }catch (Exception e){
          System.out.println("创建任务队列失败");
      }
    }

    public static void main(String[] args) {
        ThreadPoolService threadPoolService=new ThreadPoolService();
        List<Integer> datas= Arrays.asList(1,2,3,4);
        threadPoolService.executePCTask(ProduceTask.class, ConsumerTask.class,null,datas);
    }


}
