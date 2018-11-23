package com.sw.project.teamshrio.framework.session;

import com.sw.project.teamshrio.menu.MenuModel;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author sw
 * @Title: SessionPermission
 * @ProjectName knowproject
 * @Description: 用户权限信息
 * @date 18-11-23 下午3:58
 */
public class SessionPermission implements Serializable {
    private static final long serialVersionUID = -8098709575445142636L;
    //用户拥有的菜单
    private List<MenuModel> menuModels;
    //用户拥有权限
    public Set<String> permissionSet;
    //用户没有的权限
    public String noPermissions;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<MenuModel> getMenuModels() {
        return menuModels;
    }

    public void setMenuModels(List<MenuModel> menuModels) {
        this.menuModels = menuModels;
    }

    public Set<String> getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Set<String> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public String getNoPermissions() {
        return noPermissions;
    }

    public void setNoPermissions(String noPermissions) {
        this.noPermissions = noPermissions;
    }
}
