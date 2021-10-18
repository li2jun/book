package com.filter;

import com.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ahuthor lzy
 * @create 2021-10-14
 */
public class MangerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    //拦截，请求      过滤响应
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        User user = (User)httpServletRequest.getSession().getAttribute("user");
        if(user ==null ){
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest, servletResponse);
        }else{
            if("admin".equals(user.getUsername())){
                filterChain.doFilter(servletRequest, servletResponse);
            }else {
                HttpServletResponse servletResponse1 = (HttpServletResponse) servletResponse;
                servletResponse1.sendRedirect(httpServletRequest.getHeader("Referer"));
            }
        }
    }

    @Override
    public void destroy() {

    }
}
