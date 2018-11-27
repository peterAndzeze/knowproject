package com.sw.project.teamshrio.framework;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author sw
 * @Title: WebConfig
 * @ProjectName knowproject
 * @Description: TODO
 * @date 18-11-23 下午4:52
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addRedirectViewController("/","admin/admin");
    }
}
