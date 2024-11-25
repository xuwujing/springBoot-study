package com.pancm.controller;

import com.pancm.vo.SysUserRoleVO;
import com.pancm.service.ISysUserRoleService;
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
* @Title: 用户角色表(SysUserRole)表控制层
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:43
*/
@Api(tags = "用户角色表(SysUserRole)")
@RestController
@RequestMapping("sysUserRole")
public class SysUserRoleController {
    /**
     * 服务对象
     */
    @Autowired
    private ISysUserRoleService sysUserRoleService;


    /**
     * 新增一条数据
     *
     * @param sysUserRoleVO 实体类
     * @return Response对象
     */
    @ApiOperation(value = "用户角色表新增",notes = "用户角色表新增")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ApiResult insert(@RequestBody SysUserRoleVO sysUserRoleVO, HttpServletRequest httpRequest) {
        int result = sysUserRoleService.insert(sysUserRoleVO);
        if (result > 0) {
           return ApiResult.success();
        }
        return ApiResult.error("新增失败");
    }

    /**
     * 修改一条数据
     *
     * @param sysUserRoleVO 实体类
     * @return Response对象
     */
    @ApiOperation(value = "用户角色表修改",notes = "用户角色表修改")
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ApiResult update(@RequestBody SysUserRoleVO sysUserRoleVO, HttpServletRequest httpRequest) {
         sysUserRoleService.update(sysUserRoleVO); 
          return ApiResult.success();
    }

    /**
     * 删除一条数据
     *
     * @param sysUserRoleVO 参数对象
     * @return Response对象
     */
    @ApiOperation(value = "用户角色表删除",notes = "用户角色表删除") 
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public ApiResult delete(@RequestBody SysUserRoleVO sysUserRoleVO, HttpServletRequest httpRequest) {
        sysUserRoleService.deleteById(sysUserRoleVO.getId());
        return ApiResult.success();   
    }

  

    /**
     * 分页查询
     *
     */
    @ApiOperation(value = "用户角色表查询",notes = "用户角色表查询")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ApiResult list(@RequestBody SysUserRoleVO sysUserRoleVO) {
       return   sysUserRoleService.list(sysUserRoleVO);
    }

     /**
     * 详情查询
     *
     */  
    @ApiOperation(value = "用户角色表详情",notes = "用户角色表详情") 
    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ApiResult view( @RequestParam("id") Integer id) {
        return ApiResult.success(sysUserRoleService.queryById(id));   
    }
}
