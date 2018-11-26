package com.sw.project.teamshrio.framework.sysproperties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author sw
 * @Title: SysProperties
 * @ProjectName knowproject
 * @Description: 配置文件
 * @date 18-11-26 上午9:40
 */
@Configuration
public class SysProperties {
    @Value("${teamshrio.app.code}")
    private String appCode;
    @Value("${teamshrio.timeout}")
    private int teamshrioTimeOut;
    @Value("${teamshrio.system.name}")
    private String systemName;
    @Value("${teamshrio.system.admin.name}")
    private String systemAdminName;//系统名称
    @Value("${teamshrio.system.company.name}")
    private String systemCompanyName;//公司名称
    @Value("${teamshrio.system.company.phone}")
    private String systemCompayPhone;//公司电话
    @Value("${teamshrio.system.copy.right}")
    private String systemCopyRight;
    @Value("${teamshrio.system.reset.password}")
    private String systemResetPassword;//重置密码

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemAdminName() {
        return systemAdminName;
    }

    public void setSystemAdminName(String systemAdminName) {
        this.systemAdminName = systemAdminName;
    }

    public String getSystemCompanyName() {
        return systemCompanyName;
    }

    public void setSystemCompanyName(String systemCompanyName) {
        this.systemCompanyName = systemCompanyName;
    }

    public String getSystemCompayPhone() {
        return systemCompayPhone;
    }

    public void setSystemCompayPhone(String systemCompayPhone) {
        this.systemCompayPhone = systemCompayPhone;
    }

    public String getSystemCopyRight() {
        return systemCopyRight;
    }

    public void setSystemCopyRight(String systemCopyRight) {
        this.systemCopyRight = systemCopyRight;
    }

    public String getSystemResetPassword() {
        return systemResetPassword;
    }

    public void setSystemResetPassword(String systemResetPassword) {
        this.systemResetPassword = systemResetPassword;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public int getTeamshrioTimeOut() {
        return teamshrioTimeOut;
    }

    public void setTeamshrioTimeOut(int teamshrioTimeOut) {
        this.teamshrioTimeOut = teamshrioTimeOut;
    }
}
