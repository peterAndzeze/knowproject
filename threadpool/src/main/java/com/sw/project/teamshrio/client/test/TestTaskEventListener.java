package com.sw.project.teamshrio.client.test;


import com.sw.project.teamshrio.client.task.TaskEeventListener;
import com.sw.project.teamshrio.client.task.ThreadContext;

public class TestTaskEventListener implements TaskEeventListener {
    @Override
    public void before(ThreadContext threadContext) {
        System.out.println("hello ***********"+threadContext.getLimit());
    }

    @Override
    public void end(ThreadContext threadContext) {
        if(threadContext.getCurrentTask().getIsEnding()){
            System.out.println("hello ---------------game over!!!");
        }
    }
}
