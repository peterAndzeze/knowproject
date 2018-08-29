package com.sw.project.task;

import com.sw.project.queue.BlockingQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程上线文
 */
public class ThreadContext {
    //业务数据
    private BlockingQueue blockingQueue=new BlockingQueue(new LinkedBlockingQueue<Object>());
    //总数据量
    private int totalNumber=0;
    //剩余任务数
    private AtomicInteger limitNumber=new AtomicInteger(0);
    //失败任务数
    private AtomicInteger errorsNumber=new AtomicInteger(0);
    //已执行任务数
    private AtomicInteger endNumber=new AtomicInteger(0);

    //当前执行任务
    private BaseTask currentTask;




    public BaseTask getCurrentTask() {
        return currentTask;
    }

    public static  ThreadContext instansThreadContext(BaseTask currentTask){
        return new ThreadContext(currentTask);
    }

    public ThreadContext (BaseTask currentTask){
        this.currentTask=currentTask;
    }




    /**
     * 放入数据
     * @param datas
     */
    public  void setTaskData(Object datas) throws InterruptedException {
        synchronized (this) {
            if(datas instanceof List){
                blockingQueue.putAll((List<? extends Object>) datas);
            }else{
                blockingQueue.put(datas);
            }
            setTotalNumber(blockingQueue.size());
        }
    }

    /**
     * 取数据
     * @return
     */
    public Object take() {
        Object result = null;
        //result = this.dataQueueConverter.take(currentTask().getTaskPattern());
        try {
            System.out.println(getCurrentTask().getClass().getName()+"from data queue take object to hanlder ...["+this.blockingQueue.size()+"]");
            result = this.blockingQueue.take();
            this.currentTask.putDataToConsumer(result);
            if(result!=null){
                this.limitNumber.decrementAndGet();
                System.out.println("from data queue take object to hanlder ...result:["+result+"]");
            }
        } catch (Exception e) {
            System.out.println("取出数据异常");
            e.printStackTrace();
        }
        return result;
    }

    public int getLimit(){
        return this.limitNumber.get();
    }

    public int getErrors(){
        return this.errorsNumber.get();
    }

    /**
     * 标记错误数据
     */
    public void markFault(){
        this.errorsNumber.incrementAndGet();
    }


    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
        this.limitNumber.set(totalNumber);
    }

    public int getEndNumbers(){
        return this.endNumber.incrementAndGet();
    }

}
