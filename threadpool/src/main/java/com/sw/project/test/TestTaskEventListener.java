package com.sw.project.test;


import com.sw.project.task.TaskEeventListener;
import com.sw.project.task.ThreadContext;

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
