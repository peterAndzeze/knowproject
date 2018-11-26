package com.sw.project.teamshrio.authentication;

import com.sw.project.teamshrio.client.rpc.AuthenticationRpcService;
import com.sw.project.teamshrio.client.rpc.RpcPermission;
import com.sw.project.teamshrio.client.rpc.RpcUser;
import com.sw.project.teamshrio.Permession.PermessionService;
import com.sw.project.teamshrio.framework.token.LoginUser;
import com.sw.project.teamshrio.framework.token.TokenManager;
import com.sw.project.teamshrio.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 身份认证授权接口
 */
@Service
public class AuthenTicationRpcServiceImpl implements AuthenticationRpcService {

    @Autowired
    private UserService userService;
    @Autowired
    private PermessionService permessionService;
    @Autowired
    private TokenManager tokenManager;

    @Override
    public boolean validate(String token) {
        return tokenManager.validate(token)!=null;
    }

    @Override
    public RpcUser findAuthInfo(String token) {
        LoginUser loginUser=tokenManager.validate(token);
        if(null!=loginUser){
            return  new RpcUser(loginUser.getAccount());
        }
        return  null;
    }

    @Override
    public List<RpcPermission> findPermissionList(String token, String appCode) {

        if(StringUtils.isEmpty(token)){
            return  permessionService.findListById(appCode,null);
        }else{
            LoginUser user=tokenManager.validate(token);
            if(null!=user){
                return  permessionService.findListById(appCode,user.getUserId());
            }else{
                return new ArrayList<RpcPermission>(0);
            }
        }
    }


}
