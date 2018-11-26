package com.sw.project.teamshrio.client.rpc;

import java.io.Serializable;


/**
 * @author sw
 * @Title: RpcPermission
 * @ProjectName knowproject
 * @Description: rpc回传权限\菜单
 * @date 18-11-26 上午11:04
 */
public class RpcPermission implements Serializable {
    private static final long serialVersionUID = 4328731735072222562L;
    private Long id;

    private Long parentId;
    //图标
    private String icon;
    //名称
    private String name;
    //权限url
    private String url;
    //是否是菜单
    private boolean isMenu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isMenu() {
        return isMenu;
    }

    public void setMenu(boolean menu) {
        isMenu = menu;
    }
}
