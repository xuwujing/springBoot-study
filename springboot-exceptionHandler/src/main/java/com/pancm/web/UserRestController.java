package com.pancm.web;

import com.pancm.enums.CommonEnum;
import com.pancm.exception.BizException;
import com.pancm.pojo.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
 * 
* @Title: UserRestController
* @Description:
* 用户控制层
* @Version:1.0.0
* @author pancm
* @date 2018年3月19日
 */
@RestController
@RequestMapping(value = "/api")
public class UserRestController {

	@PostMapping("/user")
    public boolean insert(@RequestBody User user) {
    	System.out.println("开始新增...");
    	//如果姓名为空就抛出一个异常！
        if(user.getName()==null){
            throw  new BizException(CommonEnum.BODY_NOT_MATCH.getResultCode(),"用户姓名不能为空！");
        }
        return true;
    }

    @PutMapping("/user")
    public boolean update(@RequestBody User user) {
    	System.out.println("开始更新...");
        if(user.getId()==null){
            throw  new BizException(CommonEnum.BODY_NOT_MATCH);
        }
        return true;
    }

    @DeleteMapping("/user")
    public boolean delete(@RequestBody User user)  {
        System.out.println("开始删除...");
        if(user.getId()==null){
            throw  new BizException(CommonEnum.BODY_NOT_MATCH);
        }
        return true;
    }

    @GetMapping("/user")
    public List<User> findByUser(User user) {
    	System.out.println("开始查询...");
    	//这里故意造成一个异常，并且不进行处理
        Integer.parseInt("abc123");
        return null;
    }
    
}
