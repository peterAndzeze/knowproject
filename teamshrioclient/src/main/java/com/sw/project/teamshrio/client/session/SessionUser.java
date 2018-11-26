package com.sw.project.teamshrio.client.session;

import java.io.Serializable;

/**
 * @author sw
 * @Title: SessionUser
 * @ProjectName knowproject
 * @Description: session信息
 * @date 18-11-23 下午3:48
 */
public class SessionUser implements Serializable {
    private static final long serialVersionUID = -6463282920426182439L;
    //登录用户访问的tocken
    private String token;
    //登录用户名称
    private String account;

    public SessionUser() {

    }

    public SessionUser(String token, String account) {
        this.token = token;
        this.account = account;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
