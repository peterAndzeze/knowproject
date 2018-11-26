package com.sw.project.teamshrio.client.rpc;

import java.util.List;

/**
 * 身份认证授权接口
 */
public interface AuthenticationRpcService {
    /**
     * 验证是否已经登录
     * @param token
     * @return
     */
    public boolean validate(String token);

    /**
     * 根据登录的token
     * @param token 和 应用编码获取授权用户信息
     * @return
     */
    public RpcUser findAuthInfo(String token);

    /**
     * 获取当前应用所有权限（包含菜单）
     * @param token 授权码（如果token不为空，获取当前用户的所有权限）
     * @param appCode 应用code
     * @return
     */
    public List<RpcPermission> findPermissionList(String token,String appCode);

}
