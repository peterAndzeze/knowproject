package com.sw.project.user;

import java.io.Serializable;

/**
 * Created by sw on 2018/8/5.
 */
public class UserInfoModel implements Serializable {
    private static final long serialVersionUID = -3684601786077769215L;
    private Long id;
    private String userName;
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserInfoModel{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}