package com.sw.project.teamshrio.client.rpc;

import java.io.Serializable;

/**
 * @author sw
 * @Title: RpcUser
 * @ProjectName knowproject
 * @Description: Rpc回传用户
 * @date 18-11-26 上午11:03
 */
public class RpcUser implements Serializable {

    private static final long serialVersionUID = 2639164390359273471L;
    private String account;

    public RpcUser(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
