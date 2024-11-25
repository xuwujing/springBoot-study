package com.pancm.controller;

import com.pancm.vo.SysPermissionVO;
import com.pancm.service.ISysPermissionService;
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
* @Title: 权限表(SysPermission)表控制层
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:42
*/
@Api(tags = "权限表(SysPermission)")
@RestController
@RequestMapping("sysPermission")
public class SysPermissionController {
    /**
     * 服务对象
     */
    @Autowired
    private ISysPermissionService sysPermissionService;


    /**
     * 新增一条数据
     *
     * @param sysPermissionVO 实体类
     * @return Response对象
     */
    @ApiOperation(value = "权限表新增",notes = "权限表新增")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ApiResult insert(@RequestBody SysPermissionVO sysPermissionVO, HttpServletRequest httpRequest) {
        int result = sysPermissionService.insert(sysPermissionVO);
        if (result > 0) {
           return ApiResult.success();
        }
        return ApiResult.error("新增失败");
    }

    /**
     * 修改一条数据
     *
     * @param sysPermissionVO 实体类
     * @return Response对象
     */
    @ApiOperation(value = "权限表修改",notes = "权限表修改")
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ApiResult update(@RequestBody SysPermissionVO sysPermissionVO, HttpServletRequest httpRequest) {
         sysPermissionService.update(sysPermissionVO); 
          return ApiResult.success();
    }

    /**
     * 删除一条数据
     *
     * @param sysPermissionVO 参数对象
     * @return Response对象
     */
    @ApiOperation(value = "权限表删除",notes = "权限表删除") 
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public ApiResult delete(@RequestBody SysPermissionVO sysPermissionVO, HttpServletRequest httpRequest) {
        sysPermissionService.deleteById(sysPermissionVO.getId());
        return ApiResult.success();   
    }

  

    /**
     * 分页查询
     *
     */
    @ApiOperation(value = "权限表查询",notes = "权限表查询")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ApiResult list(@RequestBody SysPermissionVO sysPermissionVO) {
       return   sysPermissionService.list(sysPermissionVO);
    }

     /**
     * 详情查询
     *
     */  
    @ApiOperation(value = "权限表详情",notes = "权限表详情") 
    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ApiResult view( @RequestParam("id") Integer id) {
        return ApiResult.success(sysPermissionService.queryById(id));   
    }
}
