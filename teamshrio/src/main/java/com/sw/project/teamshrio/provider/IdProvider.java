package com.sw.project.teamshrio.provider;

import java.util.UUID;

/**
 * @author sw
 * @Title: IdProvider
 * @ProjectName knowproject
 * @Description: id生成工具
 * @date 18-11-26 下午5:41
 */
public class IdProvider {
    /**
     * Description:通过uuid生成唯一的记录id
     * @author 唐海洋
     * @Version 1.0 2016-8-24下午8:40:48
     * @return 生成的id
     */
    public static String createUUIDId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
}
