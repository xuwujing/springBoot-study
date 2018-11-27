package com.pancm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pancm.commons.annotation.Check;
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
	
	private  final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
    private UserService userService;
	
	/**
	 * @ApiOperation注解来给API增加说明、通过@ApiImplicitParams注解来给参数增加说明。
	 * value 是标题,notes是详细说明
	 * @param user
	 * @return
	 */
	@ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@PostMapping("/user")
	@Check(value="name")
    public boolean insert(@RequestBody User user) {
		logger.info("开始新增用户信息！请求参数:{}",user);
        return userService.insert(user);
    }
    
	@ApiOperation(value="更新用户", notes="根据User对象更新用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@PutMapping("/user")
    public boolean update(@RequestBody User user) {
    	logger.info("开始更新用户信息！请求参数:{}",user);
        return userService.update(user);
    }
	
	@ApiOperation(value="删除用户", notes="根据User对象删除用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@DeleteMapping("/user")
    public boolean delete(@RequestBody User user)  {
    	logger.info("开始删除用户信息！请求参数:{}",user);
        return userService.delete(user);
    }
	
	/**
	 * 注：@GetMapping("/user")是spring 4.3的新注解等价于：
	 * @RequestMapping(value = "/user", method = RequestMethod.GET)
	 * @param user
	 * @return
	 */
	@ApiOperation(value="获取用户列表", notes="根据User对象查询用户信息")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @GetMapping("/user")
    public User findByUser(User user) {
    	logger.info("开始查询用户列表，请求参数:{}",user);
        return userService.findByEntity(user);
    }
    
}
