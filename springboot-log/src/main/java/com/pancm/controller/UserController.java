package com.pancm.controller;

import com.pancm.vo.UserVO;
import com.pancm.service.IUserService;
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
* @Title: 用户表(User)表控制层
* @Description: 
* @Version:1.0.0 
* @Since:jdk1.8 
* @author pancm
* @date 2024-01-15 15:27:02
*/
@Api(tags = "用户表(User)")
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private IUserService userService;


    /**
     * 新增一条数据
     *
     * @param userVO 实体类
     * @return Response对象
     */
    @ApiOperation(value = "用户表新增",notes = "用户表新增")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ApiResult insert(@RequestBody UserVO userVO, HttpServletRequest httpRequest) {
        int result = userService.insert(userVO);
        if (result > 0) {
           return ApiResult.success();
        }
        return ApiResult.error("新增失败");
    }

    /**
     * 修改一条数据
     *
     * @param userVO 实体类
     * @return Response对象
     */
    @ApiOperation(value = "用户表修改",notes = "用户表修改")
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ApiResult update(@RequestBody UserVO userVO, HttpServletRequest httpRequest) {
         userService.update(userVO); 
          return ApiResult.success();
    }

    /**
     * 删除一条数据
     *
     * @param userVO 参数对象
     * @return Response对象
     */
    @ApiOperation(value = "用户表删除",notes = "用户表删除") 
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public ApiResult delete(@RequestBody UserVO userVO, HttpServletRequest httpRequest) {
        userService.deleteById(userVO.getId());
        return ApiResult.success();   
    }

  

    /**
     * 分页查询
     *
     */
    @ApiOperation(value = "用户表查询",notes = "用户表查询")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ApiResult list(@RequestBody UserVO userVO) {
       return   userService.list(userVO);
    }

     /**
     * 详情查询
     *
     */  
    @ApiOperation(value = "用户表详情",notes = "用户表详情") 
    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ApiResult view( @RequestParam("id") Long id) {
        return ApiResult.success(userService.queryById(id));   
    }
}
