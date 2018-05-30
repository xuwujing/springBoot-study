package com.pancm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pancm.pojo.User;
import com.pancm.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;



/**
 * 
* @Title: UserRestController
* @Description: 
* 用户控制层
* 在浏览器输入 http://localhost:8180/swagger-ui.html 即可查看
* @Version:1.0.0  
* @author pancm
* @date 2018年3月19日
 */
@RestController
@RequestMapping(value = "/api")
public class UserRestController {
	@Autowired
    private UserService userService;
	
	/**
	 * @ApiOperation注解来给API增加说明、通过@ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明。
	 * @param user
	 * @return
	 */
	@ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@RequestMapping(value = "/user", method = RequestMethod.POST)
    public boolean insert(@RequestBody User user) {
    	System.out.println("开始新增...");
        return userService.insert(user);
    }
    
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
    public boolean update(@RequestBody User user) {
    	System.out.println("开始更新...");
        return userService.update(user);
    }
	
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public boolean delete(@RequestBody User user)  {
    	System.out.println("开始删除...");
        return userService.delete(user);
    }
	
	/**
	 * 注：@GetMapping("/user")是spring 4.3的新注解等价于：
	 * @RequestMapping(value = "/user", method = RequestMethod.GET)
	 * @param user
	 * @return
	 */
	@ApiOperation(value="获取用户列表", notes="")
    @GetMapping("/user")
    public User findByUser(User user) {
    	System.out.println("开始查询...");
        return userService.findByEntity(user);
    }
    
}
