package com.sw.project.teamshrio.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserModelMapper {
    /**
     * 主键删除用户
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增用户
     * @param record
     * @return
     */
    int insert(UserModel record);

    /**
     * 新增部分属性
     * @param record
     * @return
     */
    int insertSelective(UserModel record);

    /**
     * 主键查询
     * @param id
     * @return
     */
    UserModel selectByPrimaryKey(Long id);

    /**
     * 用户名和密码查询用户
     * @param userName
     * @param userPwd
     * @return
     */
    UserModel selectByNameAndPwd(@Param("userName") String userName, @Param("userPwd") String userPwd);

    /**
     * 主键更新一般属性
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(UserModel record);

    /**
     * 主键更新全部
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserModel record);


}