package com.sw.project.teamshrio.client.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sw on 2018/8/5.
 */
@Service
public class UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    public UserInfoModel queryUserInfoModel(Long id){
        String sql="select user_name as userName,age,id from tp_user_info where id=?";
        UserInfoModel userInfoModel=userInfoDao.queryUserInfo(id);
        System.out.println(userInfoModel.getUserName());
        return userInfoModel;
    }

    public List<UserInfoModel> queryUserInfos(int age,String userName){
       List<UserInfoModel> userInfoModels=userInfoDao.queryUserInfos(age,userName);
       userInfoModels.stream().forEach(e->{
           System.out.println(e.getUserName());
       });
       return userInfoModels;
    }


    public List<UserInfoModel> queryUserInfos(){
        List<UserInfoModel> userInfoModels=userInfoDao.queryUserInfos();
        userInfoModels.stream().forEach(e->{
            System.out.println(e.getUserName());
        });
        return userInfoModels;
    }


}