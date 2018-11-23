package com.sw.project.teamshrio.framework;

import com.sw.project.teamshrio.admin.AdminController;
import com.sw.project.teamshrio.framework.filter.TeamShrioFilter;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * 自定义filter
 */
@Configuration
public class CustomerFilter{
    @Bean
    public FilterRegistrationBean getDelegatingFilterProxy(TeamShrioFilter teamShrioFilter){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("teamShrioFilter");
        filterRegistrationBean.setFilter(proxy);
        filterRegistrationBean.addUrlPatterns("/admin/*");
        return filterRegistrationBean;
    }



}
