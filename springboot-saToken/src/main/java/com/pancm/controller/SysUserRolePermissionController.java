package com.pancm.controller;

import com.pancm.vo.SysUserRolePermissionVO;
import com.pancm.service.ISysUserRolePermissionService;
import org.springframework.web.bind.annotation.*;


import com.pancm.vo.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;



/**
* @Title: 用户角色权限表(SysUserRolePermission)表控制层
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:43
*/
@Api(tags = "用户角色权限表(SysUserRolePermission)")
@RestController
@RequestMapping("sysUserRolePermission")
public class SysUserRolePermissionController {
    /**
     * 服务对象
     */
    @Autowired
    private ISysUserRolePermissionService sysUserRolePermissionService;


    /**
     * 新增一条数据
     *
     * @param sysUserRolePermissionVO 实体类
     * @return Response对象
     */
    @ApiOperation(value = "用户角色权限表新增",notes = "用户角色权限表新增")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ApiResult insert(@RequestBody SysUserRolePermissionVO sysUserRolePermissionVO, HttpServletRequest httpRequest) {
        int result = sysUserRolePermissionService.insert(sysUserRolePermissionVO);
        if (result > 0) {
           return ApiResult.success();
        }
        return ApiResult.error("新增失败");
    }

    /**
     * 修改一条数据
     *
     * @param sysUserRolePermissionVO 实体类
     * @return Response对象
     */
    @ApiOperation(value = "用户角色权限表修改",notes = "用户角色权限表修改")
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ApiResult update(@RequestBody SysUserRolePermissionVO sysUserRolePermissionVO, HttpServletRequest httpRequest) {
         sysUserRolePermissionService.update(sysUserRolePermissionVO); 
          return ApiResult.success();
    }

    /**
     * 删除一条数据
     *
     * @param sysUserRolePermissionVO 参数对象
     * @return Response对象
     */
    @ApiOperation(value = "用户角色权限表删除",notes = "用户角色权限表删除") 
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public ApiResult delete(@RequestBody SysUserRolePermissionVO sysUserRolePermissionVO, HttpServletRequest httpRequest) {
        sysUserRolePermissionService.deleteById(sysUserRolePermissionVO.getId());
        return ApiResult.success();   
    }

  

    /**
     * 分页查询
     *
     */
    @ApiOperation(value = "用户角色权限表查询",notes = "用户角色权限表查询")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ApiResult list(@RequestBody SysUserRolePermissionVO sysUserRolePermissionVO) {
       return   sysUserRolePermissionService.list(sysUserRolePermissionVO);
    }

     /**
     * 详情查询
     *
     */  
    @ApiOperation(value = "用户角色权限表详情",notes = "用户角色权限表详情") 
    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ApiResult view( @RequestParam("id") Integer id) {
        return ApiResult.success(sysUserRolePermissionService.queryById(id));   
    }
}
