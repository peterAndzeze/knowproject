package com.sw.project.teamshrio.client.filter;

import com.sw.project.teamshrio.client.rpc.AuthenticationRpcService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sw
 * @Title: ParamFilter
 * @ProjectName knowproject
 * @Description: 参数注入
 * @date 18-11-26 上午11:01
 */
public class ParamFilter {
    //单点登录服务端url
    protected String teamShrioUrl;
    protected AuthenticationRpcService authenticationRpcService;

    public String getTeamShrioUrl() {
        return teamShrioUrl;
    }

    public void setTeamShrioUrl(String teamShrioUrl) {
        this.teamShrioUrl = teamShrioUrl;
    }

    public AuthenticationRpcService getAuthenticationRpcService() {
        return authenticationRpcService;
    }

    public void setAuthenticationRpcService(AuthenticationRpcService authenticationRpcService) {
        this.authenticationRpcService = authenticationRpcService;
    }
}
