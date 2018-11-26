package com.sw.project.teamshrio.framework.token;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author sw
 * @Title: TokenManager
 * @ProjectName knowproject
 * @Description: token 管理
 * @date 18-11-26 上午10:13
 */
public abstract class TokenManager {
    public static final String TOKEN="token";
    //令牌有效期为30分钟
    protected int tokenTimeOut=1800;
    private final Timer timer=new Timer(true);

    /**
     * 一分钟执行一次
     */
    public TokenManager(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                verifyExpired();
            }
        },60 * 1000, 60 * 1000);
    }

    public void setTokenTimeout(int tokenTimeOut) {
        this.tokenTimeOut = tokenTimeOut;
    }

    /**
     * 存储用户授权信息
     * @param token
     * @param loginUser
     */
    public abstract void addToken(String token,LoginUser loginUser);

    /**
     * 验证令牌有效性，yes，延长session生命周期
     * @param token
     * @return
     */
    public abstract LoginUser validate(String token);

    /**
     * 删除token令牌
     * @param token
     */
    public abstract void remove(String token);

    /**
     * 验证失效token
     */
    public abstract void verifyExpired();
}
