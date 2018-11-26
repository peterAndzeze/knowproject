package com.sw.project.teamshrio.Permession;

import com.sw.project.teamshrio.client.rpc.RpcPermission;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sw
 * @Title: PermessionService
 * @ProjectName knowproject
 * @Description: 权限service
 * @date 18-11-26 下午1:47
 */
@Service
public class PermessionService {
    /**
     * 应用id 角色 查询权限
     * @param appId
     * @param roleId
     * @param isEnable
     * @return
     */
    List<Permession> findByAppId(Long appId,Long roleId,boolean isEnable){
        return null;
    }
    /**
     * 根据应用编码和用户ID查权限
     * @param appCode 应用编码
     * @param userId 用户ID
     * @return
     */
    public List<RpcPermission> findListById(String appCode, Long userId){
        return new ArrayList<>();
    }

}
