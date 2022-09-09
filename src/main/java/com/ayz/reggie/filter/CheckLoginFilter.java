package com.ayz.reggie.filter;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.ayz.reggie.common.R;
import com.ayz.reggie.common.UserIdContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "checkLoginFilter",urlPatterns = "/*")
@Slf4j
public class CheckLoginFilter implements Filter {
    //路径匹配器
    private static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String requestURI=request.getRequestURI();
        String[] uris=new String[]{
                "/backend/page/login/login.html",
                "/backend/page/login/login.do",
                "/backend/logout.do",
                "/front/page/login/sendMsg.do",
                "/front/page/login.html",
                "/front/page/login/login.do"
        };
        //1:直接放行
        if(checkURI(requestURI,uris)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //2:如果已经登录直接放行
        if(request.getSession().getAttribute("employee")!=null){
            UserIdContextHolder.setContextHolder((Long) request.getSession().getAttribute("employee"));
            filterChain.doFilter(servletRequest,servletResponse);
            UserIdContextHolder.remove();
            return;
        }
        if(request.getSession().getAttribute("user")!=null){
            UserIdContextHolder.setContextHolder((Long) request.getSession().getAttribute("user"));
            filterChain.doFilter(servletRequest,servletResponse);
            UserIdContextHolder.remove();
            return;
        }
        //3:未登录，如果访问的是后台controller直接拦截
        String[] needChecks=new String[]{"*.do","/backend/index.html","/backend/page/**"
        ,"/front/index.html","/front/page/**","/employee/page"};
        if(checkURI(requestURI,needChecks)){
            if(PATH_MATCHER.match("/backend/**",requestURI)){
                response.sendRedirect("/backend/page/login/login.html");
            }else if(PATH_MATCHER.match("/front/**",requestURI)){
                response.sendRedirect("/front/page/login.html");
            }
            return;
        }
        //4:未登录访问的是其它的资源，放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    private boolean checkURI(String target,String[] uris){
        for (String s : uris) {
            if(PATH_MATCHER.match(s,target)){
                return true;
            }
        }
        return false;
    }
}
