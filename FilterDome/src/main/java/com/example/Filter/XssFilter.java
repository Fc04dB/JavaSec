package com.example.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/Test")
public class XssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("XssFilter init");
    }

    @Override
    public void destroy() {
        System.out.println("XssFilter destroy");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("XssFilter doFilter");
        //接受参数，处理
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String code = request.getParameter("code");
        if (!code.contains("<script>")) {
            //放行
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            System.out.println("Xss!!!!!!!!!!!!");
            //拦截
        }
    }
}
