package com.sw.project.teamshrio.client.filter;

import org.springframework.http.HttpStatus;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author sw
 * @Title: ClientFilter
 * @ProjectName knowproject
 * @Description: filter基础类
 * @date 18-11-26 上午11:21
 */
public abstract class ClientFilter extends ParamFilter implements Filter {
    //匹配路径（? 匹配1个字符，* 匹配0个或多个字符，** 中的0个或多个目录）
    protected String pattern;

    /**
     * 是否允许登录
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public abstract boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * 是否是ajax请求
     * @param request
     * @return
     */
    public  boolean isAjaxRequest(HttpServletRequest request){
        String requestedWith=request.getHeader("X-Requested-With");
        return requestedWith!=null?"XMLHttpRequest".equals(requestedWith):false;
    }

    /**
     * ajax方式跳转
     * @param response
     * @param code
     * @param message
     * @throws IOException
     */
    protected void responseJson(HttpServletResponse response, int code, String message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        PrintWriter writer = response.getWriter();
        writer.write(new StringBuilder().append("{\"code\":").append(code).append(",\"message\":\"").append(message)
                .append("\"}").toString());
        writer.flush();
        writer.close();
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException {
    }

    @Override
    public void destroy() {
    }


}
