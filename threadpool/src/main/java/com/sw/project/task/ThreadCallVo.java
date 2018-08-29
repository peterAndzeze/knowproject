package com.sw.project.task;

public class ThreadCallVo {
    private String state;//运行状态
    private Object customerMsg;//自定义信息

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getCustomerMsg() {
        return customerMsg;
    }

    public void setCustomerMsg(Object customerMsg) {
        this.customerMsg = customerMsg;
    }
}
