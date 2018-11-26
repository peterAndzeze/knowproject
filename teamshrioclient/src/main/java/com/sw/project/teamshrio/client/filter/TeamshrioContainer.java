package com.sw.project.teamshrio.client.filter;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sw
 * @Title: TeamshrioContainer
 * @ProjectName knowproject
 * @Description: teamshrio容器
 * @date 18-11-26 下午12:04
 */
public class TeamshrioContainer extends ParamFilter implements Filter {
    //是否是服务器端 默认为fase
    private boolean isServer=false;
    //加载的filter
    private ClientFilter [] filters;

    private PathMatcher pathMatcher=new AntPathMatcher();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if(isServer){//如果是服务器端
            teamShrioUrl=filterConfig.getServletContext().getContextPath();
        }else if(StringUtils.isEmpty(teamShrioUrl)){//为空
            throw new IllegalArgumentException("teamshrioUrl不能为空");
        }
        if(authenticationRpcService==null){
            System.out.println("为空××××××××××××××××××××××××××");
           /* try {

            }catch (MalformedURLException e){

            }*/
        }
        if(null==filters || filters.length==0){
            throw  new IllegalArgumentException("filters 不能为空");
        }
        for(ClientFilter filter:filters){
            filter.setTeamShrioUrl(teamShrioUrl);
            filter.setAuthenticationRpcService(authenticationRpcService);
            filter.init(filterConfig);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        for(ClientFilter filter:filters){
            if(matchPath(filter.getPattern(),request.getServletPath()) && !filter.isAccessAllowed(request,response)){//
                return;
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }


    protected boolean matchPath(String pattern,String path){
        return StringUtils.isEmpty(pattern) ||pathMatcher.match(pattern,path);
    }
    public void setIsServer(boolean isServer) {
        this.isServer = isServer;
    }


    @Override
    public void destroy() {//销毁所有的filter
        if (filters == null || filters.length == 0)
            return;
        for (ClientFilter filter : filters) {
            filter.destroy();
        }
    }
    public void setFilters(ClientFilter[] filters) {
        this.filters = filters;
    }

}
