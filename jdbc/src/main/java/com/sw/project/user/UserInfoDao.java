package com.sw.project.user;

import com.sw.project.jdbctemplate.BaseJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

/**
 * Created by sw on 2018/8/5.
 */
@Repository
public class UserInfoDao extends BaseJdbcTemplate {
    public UserInfoModel queryUserInfo(Long id){
        String sql="select id,age,user_name as userName from tp_user_info where id=?";
        Long [] params=new Long[]{id} ;
        return (UserInfoModel) queryForObject(sql,UserInfoModel.class,params);
    }

    /**
     * 根据条件获取用户集合
     * @param age
     * @param userName
     * @return
     */
    public List<UserInfoModel> queryUserInfos(int age,String userName){
        Object [] params=new Object[]{age,userName};
        int [] types=new int[]{Types.INTEGER,Types.VARCHAR};
        String sql="select id,user_name as userName,age from tp_user_info where age=? and  user_name= ?";
        return queryForList(sql,UserInfoModel.class,types,params);
    }

    public List<UserInfoModel> queryUserInfos(){
        String sql="select id,user_name as userName,age from tp_user_info";
        return queryForList(sql,UserInfoModel.class);
    }
}