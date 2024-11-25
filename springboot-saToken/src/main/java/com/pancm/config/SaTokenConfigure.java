package com.pancm.config;


import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.stp.StpUtil;

import com.pancm.interceptors.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/***
 * 登录权限验证
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {


    @Resource
    private PermissionInterceptor permissionInterceptor;

    // 需要排除的路径
    private static final String[] EXCLUDE_PATHS = {"/login/**"};

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registerSaTokenInterceptor(registry);
        registerPermissionInterceptor(registry);
    }

    // 注册 Sa-Token 拦截器
    private void registerSaTokenInterceptor(InterceptorRegistry registry) {
        registry.addInterceptor(new SaRouteInterceptor((r, s, o) -> StpUtil.checkLogin()))
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATHS);
    }

    // 注册权限拦截器
    private void registerPermissionInterceptor(InterceptorRegistry registry) {
        registry.addInterceptor(permissionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATHS);
    }



}
