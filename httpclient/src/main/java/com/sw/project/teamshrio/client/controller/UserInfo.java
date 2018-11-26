package com.sw.project.teamshrio.client.controller;

/**
 * Created by sw on 2018/8/1.
 */
public class UserInfo {
    private String name;

    public UserInfo(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}