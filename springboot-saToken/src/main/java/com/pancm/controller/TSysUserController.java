package com.pancm.controller;

import com.pancm.vo.TSysUserVO;
import com.pancm.service.ITSysUserService;
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
* @Title: 用户表(TSysUser)表控制层
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-04-01 09:30:51
*/
@Api(tags = "用户表(TSysUser)")
@RestController
@RequestMapping("tSysUser")
public class TSysUserController {
    /**
     * 服务对象
     */
    @Autowired
    private ITSysUserService tSysUserService;


    /**
     * 新增一条数据
     *
     * @param tSysUserVO 实体类
     * @return Response对象
     */
    @ApiOperation(value = "用户表新增",notes = "用户表新增")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ApiResult insert(@RequestBody TSysUserVO tSysUserVO, HttpServletRequest httpRequest) {
        int result = tSysUserService.insert(tSysUserVO);
        if (result > 0) {
           return ApiResult.success();
        }
        return ApiResult.error("新增失败");
    }

    /**
     * 修改一条数据
     *
     * @param tSysUserVO 实体类
     * @return Response对象
     */
    @ApiOperation(value = "用户表修改",notes = "用户表修改")
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ApiResult update(@RequestBody TSysUserVO tSysUserVO, HttpServletRequest httpRequest) {
         tSysUserService.update(tSysUserVO); 
          return ApiResult.success();
    }

    /**
     * 删除一条数据
     *
     * @param tSysUserVO 参数对象
     * @return Response对象
     */
    @ApiOperation(value = "用户表删除",notes = "用户表删除") 
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public ApiResult delete(@RequestBody TSysUserVO tSysUserVO, HttpServletRequest httpRequest) {
        tSysUserService.deleteById(tSysUserVO.getId());
        return ApiResult.success();   
    }

  

    /**
     * 分页查询
     *
     */
    @ApiOperation(value = "用户表查询",notes = "用户表查询")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ApiResult list(@RequestBody TSysUserVO tSysUserVO) {
       return   tSysUserService.list(tSysUserVO);
    }

     /**
     * 详情查询
     *
     */  
    @ApiOperation(value = "用户表详情",notes = "用户表详情") 
    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ApiResult view( @RequestParam("id") String id) {
        return ApiResult.success(tSysUserService.queryById(id));
    }
}
