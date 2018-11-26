package com.sw.project.teamshrio.framework.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sw
 * @Title: LocalTokenManager
 * @ProjectName knowproject
 * @Description: 单机令牌
 * @date 18-11-26 上午10:24
 */
@Component()
public class LocalTokenManager extends TokenManager {
    private static final Logger logger= LoggerFactory.getLogger(LocalTokenManager.class);
    private final ConcurrentHashMap<String,DummyUser> tokenMap=new ConcurrentHashMap<>();

    @Override
    public void addToken(String token, LoginUser loginUser) {
        DummyUser dummyUser=new DummyUser();
        dummyUser.loginUser=loginUser;
        extendExpiredTime(dummyUser);
        tokenMap.put(token,dummyUser);
    }


    /**
     * 扩展过期时间
     * @param dummyUser
     */
    private void extendExpiredTime(DummyUser dummyUser){
        dummyUser.expired=new Date(new Date().getTime()+tokenTimeOut*1000);
    }

    @Override
    public LoginUser validate(String token) {
        DummyUser dummyUser = tokenMap.get(token);
        if (dummyUser == null) {
            return null;
        }
        extendExpiredTime(dummyUser);
        return dummyUser.loginUser;
    }

    @Override
    public void remove(String token) {
        tokenMap.remove(token);
    }

    @Override
    public void verifyExpired() {
        Date now=new Date();
        for (Map.Entry<String,DummyUser> entry:tokenMap.entrySet()){
            DummyUser dummyUser=entry.getValue();
            String token=entry.getKey();
            //当前时间大于过期时间
            if(now.compareTo(dummyUser.expired)>0){
                tokenMap.remove(token);
                logger.info("token:{},已时效",token);
            }
        }
    }
    //内部
    private class DummyUser{
        private LoginUser loginUser;
        private Date expired;//过期时间
    }
}
