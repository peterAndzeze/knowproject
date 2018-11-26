package com.sw.project.teamshrio.client.queue;

import org.omg.CORBA.TIMEOUT;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 自定义数据队列方法
 */
public class BlockingQueue {
    //数据信息
    private LinkedBlockingQueue<Object> data;

    public BlockingQueue(LinkedBlockingQueue<Object> data) {
        this.data = data;
    }

    /**
     * @author sw
     * @param o
     */
    public void put(Object o) {
        synchronized (this) {
            data.offer(o);
        }
    }
    /**
     * @author sw
     * @return Object
     * @throws InterruptedException
     */
    public synchronized Object take() throws InterruptedException {
        Object o = null;
        synchronized (this) {
            o = data.poll(5L, TimeUnit.SECONDS);//没有数据等5s
        }
        return o;
    }
    /**
     * @author sw
     * @param taskList
     */
    public void putAll(List<? extends Object> taskList) throws InterruptedException {
        synchronized (this) {
            data.addAll(taskList);
        }
    }
    /**
     * @author sw
     * @return int
     */
    public int size() {
        synchronized (this) {
            return data.size();
        }
    }

}
