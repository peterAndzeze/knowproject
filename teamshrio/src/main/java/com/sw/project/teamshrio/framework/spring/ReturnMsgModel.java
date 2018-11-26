package com.sw.project.teamshrio.framework.spring;
/**
 * 
 * @ClassName: ReturnMsgModel  
 * @Description: 前后台交互model  
 * @author sw  
 * @date 2018年4月17日
 */
public class ReturnMsgModel {
    private boolean isSuccess;
    private String msg;
    private Object data;

    /**
     * 状态码
     */
    protected Integer code;

    public Integer getCode() {
        return code;
    }

    public ReturnMsgModel setCode(Integer code) {
        this.code = code;
        return this;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
    public ReturnMsgModel setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
        return this;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public ReturnMsgModel() {
	super();
	// TODO Auto-generated constructor stub
    }
    public ReturnMsgModel(boolean isSuccess, String msg, Object data) {
	super();
	this.isSuccess = isSuccess;
	this.msg = msg;
	this.data = data;
    }
    
    
    
}
