package com.sw.project.teamshrio.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户信息
 */
@Service
public class UserService {
    @Autowired
    UserModelMapper userModelMapper;
    /**
     *
     * @param userName
     * @param userPwd
     * @return
     */
    public UserModel getUserByNameAndPass(String userName,String userPwd){
        return userModelMapper.selectByNameAndPass(userName,userPwd);
    }

}
