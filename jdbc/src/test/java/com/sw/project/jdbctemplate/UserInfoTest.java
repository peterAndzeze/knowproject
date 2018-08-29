package com.sw.project.jdbctemplate;

import com.sw.project.JdbcApplication;
import com.sw.project.user.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sw on 2018/8/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JdbcApplication.class)
public class UserInfoTest {
    @Autowired
    private UserInfoService userInfoService;
    @Test
    public void getUserInfoModel(){
        userInfoService.queryUserInfoModel(137048L);
    }
    @Test
    public void queryUserInfos(){
        userInfoService.queryUserInfos(11,"里斯");
    }
}