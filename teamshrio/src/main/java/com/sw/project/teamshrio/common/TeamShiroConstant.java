package com.sw.project.teamshrio.common;

/**
 * 常量类
 */
public final class TeamShiroConstant {
    public  static Long PARENT_ID=0L;//父级节点标识
    public  static String IS_NOT_LEAF="0";//不是末级节点
    public  static String IS_LEAF="1";//不是末级节点
    public static String DELTYPE="cascade";//是否是级联删除


    public final static int SUCCESS = 1;// 成功

    // 通用错误以9开头
    public final static int ERROR = 9999;// 未知错误
    public final static int APPLICATION_ERROR = 9000;// 应用级错误
    public final static int VALIDATE_ERROR = 9001;// 参数验证错误
    public final static int SERVICE_ERROR = 9002;// 业务逻辑验证错误
    public final static int CACHE_ERROR = 9003;// 缓存访问错误
    public final static int DAO_ERROR = 9004;// 数据访问错误

}
