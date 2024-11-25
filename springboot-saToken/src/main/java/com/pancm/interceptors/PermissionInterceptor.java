package com.pancm.interceptors;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;

import com.pancm.constant.UserConstants;
import com.pancm.vo.ApiResult;
import com.pancm.vo.SysPermissionVO;
import com.pancm.vo.SysUserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 权限拦截器
 */
@Slf4j
@Component
public class PermissionInterceptor extends HandlerInterceptorAdapter {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (verify(request,handler)) {
            return true;
        }
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(
                JSONObject.toJSONString(ApiResult.error("该用户没有配置该操作权限，请联系管理员！")));
        response.setContentType(FastJsonJsonView.DEFAULT_CONTENT_TYPE);
        return false;
    }



    private boolean verify(HttpServletRequest request, Object handler) {
        String uri = request.getRequestURI();
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 获取类名
        String className = handlerMethod.getBeanType().getSimpleName();
        // 获取方法名
        String methodName = handlerMethod.getMethod().getName();
        String path = className.concat("#").concat(methodName);
        SysUserInfoVO userInfoDTO = (SysUserInfoVO) StpUtil.getSession().get(UserConstants.CURRENT_USER);
        if(userInfoDTO == null){
            return false;
        }
        List<SysPermissionVO> permissionDTOLists =  userInfoDTO.getPermissionDTOLists();
        List<String> permissionList = permissionDTOLists.stream().map(SysPermissionVO::getPermissionCode).collect(Collectors.toList());
        boolean valid = permissionList.contains(path);
        log.info("api_path:{}, {}", path, valid);
        return valid;
    }

}
