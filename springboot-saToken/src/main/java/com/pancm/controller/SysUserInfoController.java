package com.pancm.controller;

import com.pancm.vo.SysUserInfoVO;
import com.pancm.service.ISysUserInfoService;
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
* @Title: 系统用户表(SysUserInfo)表控制层
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-11-23 09:31:42
*/
@Api(tags = "系统用户表(SysUserInfo)")
@RestController
@RequestMapping("sysUserInfo")
public class SysUserInfoController {
    /**
     * 服务对象
     */
    @Autowired
    private ISysUserInfoService sysUserInfoService;


    /**
     * 新增一条数据
     *
     * @param sysUserInfoVO 实体类
     * @return Response对象
     */
    @ApiOperation(value = "系统用户表新增",notes = "系统用户表新增")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ApiResult insert(@RequestBody SysUserInfoVO sysUserInfoVO, HttpServletRequest httpRequest) {
        int result = sysUserInfoService.insert(sysUserInfoVO);
        if (result > 0) {
           return ApiResult.success();
        }
        return ApiResult.error("新增失败");
    }

    /**
     * 修改一条数据
     *
     * @param sysUserInfoVO 实体类
     * @return Response对象
     */
    @ApiOperation(value = "系统用户表修改",notes = "系统用户表修改")
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ApiResult update(@RequestBody SysUserInfoVO sysUserInfoVO, HttpServletRequest httpRequest) {
         sysUserInfoService.update(sysUserInfoVO); 
          return ApiResult.success();
    }

    /**
     * 删除一条数据
     *
     * @param sysUserInfoVO 参数对象
     * @return Response对象
     */
    @ApiOperation(value = "系统用户表删除",notes = "系统用户表删除") 
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public ApiResult delete(@RequestBody SysUserInfoVO sysUserInfoVO, HttpServletRequest httpRequest) {
        sysUserInfoService.deleteById(sysUserInfoVO.getId());
        return ApiResult.success();   
    }

  

    /**
     * 分页查询
     *
     */
    @ApiOperation(value = "系统用户表查询",notes = "系统用户表查询")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ApiResult list(@RequestBody SysUserInfoVO sysUserInfoVO) {
       return   sysUserInfoService.list(sysUserInfoVO);
    }

     /**
     * 详情查询
     *
     */  
    @ApiOperation(value = "系统用户表详情",notes = "系统用户表详情") 
    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ApiResult view( @RequestParam("id") Integer id) {
        return ApiResult.success(sysUserInfoService.queryById(id));   
    }
}
