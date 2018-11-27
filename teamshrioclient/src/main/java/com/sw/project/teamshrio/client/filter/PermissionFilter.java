package com.sw.project.teamshrio.client.filter;

import com.sw.project.teamshrio.client.rpc.RpcPermission;
import com.sw.project.teamshrio.client.session.SessionPermission;
import com.sw.project.teamshrio.client.session.SessionUtils;
import com.sw.project.teamshrio.client.session.TeamResultCode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author sw
 * @Title: PermissionFilter
 * @ProjectName knowproject
 * @Description: 权限控制filter
 * @date 18-11-26 下午2:09
 */
@Component
public class PermissionFilter extends  ClientFilter{
    // 当前应用关联权限系统的应用编码
    private String ssoAppCode;
    // 存储已获取最新权限的token集合，当权限发生变动时清空
    private static Set<String> sessionPermissionCache = new CopyOnWriteArraySet<String>();
    @Override
    public boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        if (isPermitted(request, path))
            return true;
        else if (!ApplicationPermission.getApplicationPermissionSet().contains(path))
            return true;
        else {
            responseJson(response, TeamResultCode.TEAMSHRIO_PERMISSION_ERROR, "没有访问权限");
            return false;
        }
    }
    private boolean isPermitted(HttpServletRequest request, String path) {
        Set<String> permissionSet = getLocalPermissionSet(request);
        return permissionSet.contains(path);
    }
    private Set<String> getLocalPermissionSet(HttpServletRequest request) {
        SessionPermission sessionPermission = SessionUtils.getSessionPermission(request);
        String token = SessionUtils.getSessionUser(request).getToken();
        if (sessionPermission == null || !sessionPermissionCache.contains(token)) {
            sessionPermission = invokePermissionInSession(request, token);
        }
        return sessionPermission.getPermissionSet();
    }

    /**
     * 保存权限信息
     * @param request
     * @param token
     * @return
     */
    public SessionPermission invokePermissionInSession(HttpServletRequest request, String token) {
        List<RpcPermission> dbList = authenticationRpcService.findPermissionList(token, ssoAppCode);

        List<RpcPermission> menuList = new ArrayList<RpcPermission>();
        Set<String> operateSet = new HashSet<String>();
        for (RpcPermission menu : dbList) {
            if (menu.isMenu()) {
                menuList.add(menu);
            }
            if (menu.getUrl() != null) {
                operateSet.add(menu.getUrl());
            }
        }

        SessionPermission sessionPermission = new SessionPermission();
        // 设置登录用户菜单列表
        sessionPermission.setMenuModels(menuList);

        // 保存登录用户没有权限的URL，方便前端去隐藏相应操作按钮
        Set<String> noPermissionSet = new HashSet<String>(ApplicationPermission.getApplicationPermissionSet());
        noPermissionSet.removeAll(operateSet);

        sessionPermission.setNoPermissions(StringUtils.arrayToDelimitedString(noPermissionSet.toArray(), ","));

        // 保存登录用户权限列表
        sessionPermission.setPermissionSet(operateSet);
        SessionUtils.setSessionUserPermission(request, sessionPermission);
        // 添加权限监控集合，当前session已更新最新权限
        sessionPermissionCache.add(token);
        return sessionPermission;
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (StringUtils.isEmpty(ssoAppCode)) {
            throw new IllegalArgumentException("ssoAppCode不能为空");
        }
        ApplicationPermission.initApplicationPermissions(authenticationRpcService, ssoAppCode);
    }

    public String getSsoAppCode() {
        return ssoAppCode;
    }

    public void setSsoAppCode(String ssoAppCode) {
        this.ssoAppCode = ssoAppCode;
    }

    public static Set<String> getSessionPermissionCache() {
        return sessionPermissionCache;
    }

    public static void setSessionPermissionCache(Set<String> sessionPermissionCache) {
        PermissionFilter.sessionPermissionCache = sessionPermissionCache;
    }

    /**
     * 清空
     */
    public static void invalidateSessionPermissions() {
        sessionPermissionCache.clear();
    }

}
