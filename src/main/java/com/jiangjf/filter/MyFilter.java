package com.jiangjf.filter;

import com.jiangjf.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * @author jiangjf
 * @date 2022/1/28
 */
@Component
@WebFilter(filterName = "authFilter", urlPatterns = "/**")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter初始化。。。");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token)) {
            String parseToken = JwtUtil.parseToken(token);
            if (!StringUtils.isEmpty(parseToken)) {
                System.out.println("认证成功");
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                System.out.println("认证失败，token无效");
            }
        } else {
            System.out.println("认证失败，token不能为空");
        }
    }

//    public static void main(String[] args) {
//        String token = JwtUtil.createToken("test", new Date());
//        System.out.println(token);
//    }
}
