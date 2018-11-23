package com.sw.project.teamshrio.framework.session;

import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sw
 * @Title: SessionUtils
 * @ProjectName knowproject
 * @Description: session工具类
 * @date 18-11-23 下午3:52
 */
public class SessionUtils {
    //用户信息
    public static final String SESSION_USER="_sessionUser";
    //用户权限
    public static final String SESSION_USER_PERMISSION="_sessionUserPermission";

    /**
     * 从当前请求中获取session
     * @param request
     * @return
     */
    public static SessionUser getSessionUser(HttpServletRequest request){
        return (SessionUser) WebUtils.getSessionAttribute(request,SESSION_USER);
    }

    /**
     * 当前request放入session
     * @param request
     * @param sessionUser
     */
    public static void setSessionUser(HttpServletRequest request,SessionUser sessionUser){
        WebUtils.setSessionAttribute(request,SESSION_USER,sessionUser);
    }

    /**
     * 设置用权限
     * @param request
     * @param sessionPermission
     */
    public static void setSessionUserPermission(HttpServletRequest request,SessionPermission sessionPermission){
        WebUtils.setSessionAttribute(request,SESSION_USER_PERMISSION,sessionPermission);
    }

    /**
     * 获取权限信息
     * @param request
     * @return
     */
    public static  SessionPermission getSessionPermission(HttpServletRequest request){
        return (SessionPermission)WebUtils.getSessionAttribute(request,SESSION_USER_PERMISSION);
    }

    /**
     * 设置session失效
     * @param request
     */
    public static void invalidate(HttpServletRequest request){
        setSessionUser(request,null);
        setSessionUserPermission(request,null);
    }
}
