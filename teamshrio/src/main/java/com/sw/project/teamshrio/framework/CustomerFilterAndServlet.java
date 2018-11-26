package com.sw.project.teamshrio.framework;

import com.sw.project.teamshrio.captcha.CaptchaFilter;
import com.sw.project.teamshrio.client.filter.ClientFilter;
import com.sw.project.teamshrio.client.filter.PermissionFilter;
import com.sw.project.teamshrio.client.filter.TeamshrioContainer;
import com.sw.project.teamshrio.client.filter.TeamshrioFilter;
import com.sw.project.teamshrio.client.rpc.AuthenticationRpcService;
import com.sw.project.teamshrio.framework.servlet.ConfigServlet;
import com.sw.project.teamshrio.framework.sysproperties.SysProperties;
import com.sw.project.teamshrio.framework.token.LocalTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
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

    /**
     * 过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean getDelegatingFilterProxy(TeamshrioBusisnessFilter teamshrioBusisnessFilter){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("teamshrioBusisnessFilter");
        filterRegistrationBean.setFilter(proxy);
        filterRegistrationBean.addUrlPatterns("/admin/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean2(CaptchaFilter  captchaFilter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(captchaFilter);
        filterRegistrationBean.addUrlPatterns("/captcha");
        filterRegistrationBean.setOrder(7);
        return filterRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean getServletRegistrationBean(ConfigServlet configServlet){
        ServletRegistrationBean servletRegistration=new ServletRegistrationBean();
        servletRegistration.setLoadOnStartup(2);
        servletRegistration.setServlet(configServlet);
        servletRegistration.setAsyncSupported(true);
        return servletRegistration;
    }
    /**
     * 初始化自己的容器
     * @param authenticationRpcService
     * @return
     */
  /*  @Bean
    public FilterRegistrationBean filterRegistrationBean3(AuthenticationRpcService authenticationRpcService){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        TeamshrioContainer teamshrioContainer=new TeamshrioContainer();
        teamshrioContainer.setIsServer(true);
        teamshrioContainer.setAuthenticationRpcService(authenticationRpcService);
        TeamshrioFilter teamshrioFilter=new TeamshrioFilter();
        teamshrioContainer.setIsServer(true);
        PermissionFilter permissionFilter=new PermissionFilter();
        permissionFilter.setSsoAppCode(sysProperties.getAppCode());
        permissionFilter.setAuthenticationRpcService(authenticationRpcService);
        ClientFilter[] clientFilters={teamshrioFilter,permissionFilter};
        teamshrioContainer.setFilters(clientFilters);
        filterRegistrationBean.setFilter(teamshrioContainer);
        return filterRegistrationBean;
    }*/



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
