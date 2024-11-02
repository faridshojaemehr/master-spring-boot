package com.master.spring_boot;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;

@Component
@Order()
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("LoggingFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.getHeaderNames()
                .asIterator()
                .forEachRemaining(x -> System.out.println(x + ": " + request.getHeader(x)));
        String token = request.getHeader("token");
        if (token != null && token.equals("reject")){
            response.sendError(403,"Forbidden");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
