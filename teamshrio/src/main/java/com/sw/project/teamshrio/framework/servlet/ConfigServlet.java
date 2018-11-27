/*
package com.sw.project.teamshrio.framework.servlet;

import com.sw.project.teamshrio.framework.sysproperties.SysProperties;
import com.sw.project.teamshrio.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

*/
/**
 * @author sw
 * @Title: ConfigServlet
 * @ProjectName knowproject
 * @Description: 初始化全局参数
 * @date 18-11-26 上午9:45
 *//*

@Component
public class ConfigServlet extends HttpServlet {

    private static final long serialVersionUID = 5632202423323695697L;
    private static  final Logger logger= LoggerFactory.getLogger(ConfigServlet.class);

    */
/**
     * 初始化方法
     * @throws ServletException
     *//*

    public void init()throws ServletException {
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("", servletContext.getContextPath());
        SysProperties sysProperties= SpringUtil.getBean(SysProperties.class);
        try {
            //String staticPath = ConfigUtils.getProperty("static.url");
           */
/* servletContext.setAttribute("_staticPath", staticPath != null && staticPath.startsWith("http") ? staticPath
                    : servletContext.getContextPath() + staticPath);*//*

            servletContext.setAttribute("_systemName", sysProperties.getSystemName());
            servletContext.setAttribute("_systemAdminName", sysProperties.getSystemAdminName());
            servletContext.setAttribute("_companyName", sysProperties.getSystemCompanyName());
            servletContext.setAttribute("_companyPhone", sysProperties.getSystemCompayPhone());
            servletContext.setAttribute("_copyRight", sysProperties.getSystemCopyRight());
        }
        catch (Exception e) {
            logger.error("系统初始化参数配置有误", e);
        }
    }
}
*/
