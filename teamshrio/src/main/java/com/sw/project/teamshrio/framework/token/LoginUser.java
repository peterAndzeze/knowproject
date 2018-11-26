package com.sw.project.teamshrio.framework.token;

import java.io.Serializable;

/**
 * @author sw
 * @Title: LoginUser
 * @ProjectName knowproject
 * @Description: 登录用户
 * @date 18-11-26 上午10:18
 */
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 2861107763765578732L;
    //登录id
    private Long userId;
    //登录成功用户
    private String account;


    public Long getUserId() {
        return userId;
    }

    public LoginUser(Long userId, String account) {

        this.userId = userId;
        this.account = account;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LoginUser other = (LoginUser) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
