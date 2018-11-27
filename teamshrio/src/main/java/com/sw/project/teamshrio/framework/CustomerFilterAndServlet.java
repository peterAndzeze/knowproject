
package com.sw.project.teamshrio.framework;


import com.sw.project.teamshrio.captcha.CaptchaFilter;
import com.sw.project.teamshrio.client.filter.ClientFilter;
import com.sw.project.teamshrio.client.filter.PermissionFilter;
import com.sw.project.teamshrio.client.filter.TeamshrioContainer;
import com.sw.project.teamshrio.client.filter.TeamshrioFilter;
import com.sw.project.teamshrio.client.rpc.AuthenticationRpcService;
import com.sw.project.teamshrio.framework.sysproperties.SysProperties;
import com.sw.project.teamshrio.framework.token.LocalTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * 自定义filter
 */

@Configuration
public class CustomerFilterAndServlet{
    @Autowired
    SysProperties sysProperties;

    @Bean
    public FilterRegistrationBean registrationBeanCaptchaFilter(CaptchaFilter captchaFilter){
        FilterRegistrationBean filterRegistrationBean =new FilterRegistrationBean();
        filterRegistrationBean.setFilter(captchaFilter);
        filterRegistrationBean.addUrlPatterns("/captchaFilter/*");
        filterRegistrationBean.setOrder(4);
        return filterRegistrationBean;
    }

    /**
     * 过滤器
     * @return
     */

    @Bean
    public FilterRegistrationBean registrationBeanTeamFilter(TeamshrioFilter teamshrioFilter){
        FilterRegistrationBean filterRegistrationBean =new FilterRegistrationBean();
        filterRegistrationBean.setFilter(teamshrioFilter);
        filterRegistrationBean.addUrlPatterns("/admin/*");
       /* DelegatingFilterProxy delegatingFilterProxy=new DelegatingFilterProxy();
        delegatingFilterProxy.setTargetFilterLifecycle(true);
        delegatingFilterProxy.setTargetBeanName("teamshrioFilter");
        filterRegistrationBean.setFilter(delegatingFilterProxy);*/
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean registrationBeanPermessionFilter(PermissionFilter permissionFilter, AuthenticationRpcService authenticationRpcService){
        permissionFilter.setSsoAppCode(sysProperties.getAppCode());
        permissionFilter.setAuthenticationRpcService(authenticationRpcService);
        FilterRegistrationBean filterRegistrationBean =new FilterRegistrationBean();
        filterRegistrationBean.setFilter(permissionFilter);
        filterRegistrationBean.addUrlPatterns("/admin/*");
       /* DelegatingFilterProxy delegatingFilterProxy=new DelegatingFilterProxy();
        delegatingFilterProxy.setTargetFilterLifecycle(true);
        delegatingFilterProxy.setTargetBeanName("teamshrioFilter");
        filterRegistrationBean.setFilter(delegatingFilterProxy);*/
        filterRegistrationBean.setOrder(3);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean registrationBeanContainerFilter(TeamshrioContainer teamshrioContainer, AuthenticationRpcService authenticationRpcService, TeamshrioFilter teamshrioFilter, PermissionFilter permissionFilter){
        FilterRegistrationBean filterRegistrationBean =new FilterRegistrationBean();
        teamshrioContainer.setIsServer(true);
        teamshrioContainer.setAuthenticationRpcService(authenticationRpcService);
        ClientFilter[] filters={teamshrioFilter,permissionFilter};
        teamshrioContainer.setFilters(filters);
        filterRegistrationBean.setFilter(teamshrioContainer);
        filterRegistrationBean.addUrlPatterns("/admin/*");
        /*DelegatingFilterProxy delegatingFilterProxy=new DelegatingFilterProxy();
        delegatingFilterProxy.setTargetFilterLifecycle(true);
        delegatingFilterProxy.setTargetBeanName("teamshrioFilter");
        filterRegistrationBean.setFilter(delegatingFilterProxy);*/
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }



    /**
     * 初始化本地token管理器
     * @return
     */

    @Bean
    public LocalTokenManager localTokenManager(){
        LocalTokenManager localTokenManager=new LocalTokenManager();
        localTokenManager.setTokenTimeout(sysProperties.getTeamshrioTimeOut());
        return localTokenManager;
    }






}

