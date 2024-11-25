package com.pancm.controller;


import cn.dev33.satoken.stp.StpUtil;

import com.pancm.constant.UserConstants;
import com.pancm.enums.PcmYesNoEnum;
import com.pancm.service.ISysPermissionService;
import com.pancm.service.ISysUserInfoService;
import com.pancm.service.ISysUserRoleService;
import com.pancm.vo.ApiResult;
import com.pancm.vo.SysPermissionVO;
import com.pancm.vo.SysUserInfoVO;
import com.pancm.vo.SysUserRoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户登录 controller
 * </p>
 *
 * @author pcm
 * @menu 用户登录
 * @since 2024-11-20
 */
@Api(tags = "REST - 用户登录")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private ISysUserInfoService sysUserInfoService;
    @Resource
    private ISysUserRoleService userRoleService;
    @Resource
    private ISysPermissionService permissionService;


    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "登录")
    public ApiResult login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        SysUserInfoVO userInfoVO = sysUserInfoService.queryByUserName(username);
        if (userInfoVO == null || !Objects.equals(userInfoVO.getPassword(), password)) {
            return ApiResult.error("用户名或密码错误!");
        }
        if(Objects.equals(userInfoVO.getUserEnable(), PcmYesNoEnum.NO.getIndex())){
            return ApiResult.error("该用户已被禁用!请联系管理员!");
        }
        SysUserRoleVO roleDTO = userRoleService.queryById(userInfoVO.getRoleId());
        if(roleDTO == null || Objects.equals(roleDTO.getUserEnable(), PcmYesNoEnum.NO.getIndex())){
            return ApiResult.error("该用户所属角色已被禁用或已删除!请联系管理员!");
        }
        List<SysPermissionVO> list = permissionService.queryRolePermissionByRoleId(userInfoVO.getRoleId());
        if(list == null || list.isEmpty()){
            return ApiResult.error("该用户所属角色未配置权限!请联系管理员!");
        }
        userInfoVO.setPermissionDTOLists(list);
        userInfoVO.setPassword(null);
        StpUtil.login(username);
        StpUtil.getSession().set(UserConstants.CURRENT_USER, userInfoVO);
        return ApiResult.success(userInfoVO);
    }

    @ApiOperation(value = "登录校验", notes = "登录校验")
    @RequestMapping("/isLogin")
    public ApiResult isLogin() {
        return ApiResult.success(StpUtil.isLogin());
    }

    @ApiOperation(value = "登出", notes = "登出")
    @PostMapping("/logout")
    public ApiResult logout() {
        StpUtil.getSession().clear();
        StpUtil.logout();
        return ApiResult.success();
    }


}


