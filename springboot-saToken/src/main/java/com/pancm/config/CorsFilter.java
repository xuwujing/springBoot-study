package com.pancm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author pancm
 * @Description
 * @Date  2019/2/17
 * @Param
 * @return
 **/
@Configuration
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String origin = ((HttpServletRequest) servletRequest).getHeader("Origin");
        if (StringUtils.isEmpty(origin)) {
            origin = "*";
        }
        String rh = ((HttpServletRequest) servletRequest).getHeader("Access-Control-Request-Headers");
        if (StringUtils.isEmpty(origin)) {
            rh = "DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control," +
                    "Content-Type,Authorization,SessionToken,Content-Disposition";
        }
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", origin);
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", rh);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
