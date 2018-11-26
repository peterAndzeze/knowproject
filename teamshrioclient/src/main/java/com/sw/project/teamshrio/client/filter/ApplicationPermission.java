package com.sw.project.teamshrio.client.filter;

import com.sw.project.teamshrio.client.rpc.AuthenticationRpcService;
import com.sw.project.teamshrio.client.rpc.RpcPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author sw
 * @Title: ApplicationPermission
 * @ProjectName knowproject
 * @Description: 当前应用的所有权限
 * @date 18-11-26 下午2:11
 */
public class ApplicationPermission {
    private static final Logger logger=LoggerFactory.getLogger(ApplicationPermission.class);
    //应用所有权限url
    private static Set<String> applicationPermissionSet=null;
    //应用所有菜单
    private static List<RpcPermission> applicationMenuList=null;
    //并发监控
    private static Object monitor=new Object();
    /**
     * 1.应用初始化，获取应用所有的菜单及权限
     * 2.权限有变动修改，JMS通知重新加载
     */
    public static void initApplicationPermissions(AuthenticationRpcService authenticationRpcService,
                                                  String ssoAppCode) {
        List<RpcPermission> dbList = null;
        try {
            dbList = authenticationRpcService.findPermissionList(null, ssoAppCode);
        }
        catch (Exception e) {
            dbList = new ArrayList<RpcPermission>(0);
            logger.error("无法连接到单点登录服务端,请检查配置sso.server.url", e);
        }

        synchronized (monitor) {
            applicationMenuList = new ArrayList<RpcPermission>();
            applicationPermissionSet = new HashSet<String>();
            for (RpcPermission menu : dbList) {
                if (menu.isMenu()) {
                    applicationMenuList.add(menu);
                }
                if (!StringUtils.isEmpty(menu.getUrl())) {
                    applicationPermissionSet.add(menu.getUrl());
                }
            }
        }
    }

    public static Set<String> getApplicationPermissionSet() {
        synchronized (monitor) {
            return applicationPermissionSet;
        }
    }

    public static List<RpcPermission> getApplicationMenuList() {
        synchronized (monitor) {
            return applicationMenuList;
        }
    }

}
