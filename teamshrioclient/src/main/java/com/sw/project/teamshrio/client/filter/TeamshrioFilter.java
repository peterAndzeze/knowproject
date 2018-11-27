package com.sw.project.teamshrio.client.filter;

import com.sw.project.teamshrio.client.rpc.RpcUser;
import com.sw.project.teamshrio.client.session.SessionUser;
import com.sw.project.teamshrio.client.session.SessionUtils;
import com.sw.project.teamshrio.client.session.TeamResultCode;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author sw
 * @Title: TeamshrioFilter
 * @ProjectName knowproject
 * @Description: 单点登录及权限验证filter
 * @date 18-11-26 上午11:27
 */
@Component
public class TeamshrioFilter extends  ClientFilter {
    //teamshiro授权回调参数token标识
    public static final String SSO_TOKEN_NAME = "__vt_param__";

    @Override
    public boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token=getLocalToken(request);
        if(null==token){//没有
            token=request.getParameter(SSO_TOKEN_NAME);
            if(null!=token){
                invokeAuthInfoSession(request,token);
                // 再跳转一次当前URL，以便去掉URL中token参数
                response.sendRedirect(getRemoveTokenBackUrl(request));
                return false;
            }
        }else if(authenticationRpcService.validate(token)){//已经登录
            return true;
        }
        redirctLogin(request,response);
        return false;
    }

    /**
     * 跳转登录
     * @param request
     * @param response
     * @throws IOException
     */
    private void redirctLogin(HttpServletRequest request,HttpServletResponse response) throws IOException{
        if(isAjaxRequest(request)){//ajax
          responseJson(response, TeamResultCode.TEAMSHRIO_TOKEN_ERROR,"未登录或者超时");
        }else{//非ajax
            SessionUtils.invalidate(request);
            String teamshrioLoginUrl=new StringBuilder().append(teamShrioUrl)
                    .append("/login?backUrl=").append(URLEncoder.encode(getBackUrl(request),"UTF-8")).toString();
            response.sendRedirect(teamshrioLoginUrl);
        }

    }



    /**
     * 获取session中的token
     * @param request
     * @return
     */
    private String getLocalToken(HttpServletRequest request){
        SessionUser sessionUser= SessionUtils.getSessionUser(request);
        return null==sessionUser?null:sessionUser.getToken();
    }

    /**
     * 获取rpc回传的登录用户信息
     * @param request
     * @param token
     */
    private void invokeAuthInfoSession(HttpServletRequest request,String token){
        RpcUser rpcUser=authenticationRpcService.findAuthInfo(token);
        if(null!=rpcUser){
            SessionUtils.setSessionUser(request,new SessionUser(token,rpcUser.getAccount()));
        }
    }

    /**
     * 删除返回地址中的token
     * @param request
     * @return
     */
    private String getRemoveTokenBackUrl(HttpServletRequest request){
        String backUrl=getBackUrl(request);
        return backUrl.substring(0,backUrl.indexOf(SSO_TOKEN_NAME)-1);
    }

    /**
     * 获取请求中的返回地址
     * @param request
     * @return
     */
    private String getBackUrl(HttpServletRequest request){
        return new StringBuilder().append(request.getRequestURL()).append(request.getQueryString()==null?"":"?"+request.getQueryString()).toString();
    }


}
