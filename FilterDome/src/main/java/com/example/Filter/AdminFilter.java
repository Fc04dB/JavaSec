package com.example.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/admin")
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("AdminFilter init");
    }

    @Override
    public void destroy() {
        System.out.println("AdminFilter destroy");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("AdminFilter doFilter");
        //检测cookie过滤
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            String cookieValue = cookie.getValue();
            System.out.println("Cookie Name: " + cookieName);
            System.out.println("Cookie Value: " + cookieValue);
            if(cookieName.contains("user")  && cookieValue.contains("admin")) {
                filterChain.doFilter(request, servletResponse);
            }else {
                System.out.println("非管理员访问");
            }
        }
    }
}
