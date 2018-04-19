package com.pancm.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pancm.pojo.User;
import com.pancm.service.UserService;



/**
 * 
* Title: UserRestController
* Description: 
* 用户控制层
* Version:1.0.0  
* @author pancm
* @date 2018年3月19日
 */
@Controller
public class UserRestController {
	@Autowired
    private UserService userService;
 
		@RequestMapping("/")
	    public String index() {
	        return "redirect:/list";
	    }

	    @RequestMapping("/list")
	    public String list(Model model) {
	    	System.out.println("查询所有");
	        List<User> users=userService.findAll();
	        model.addAttribute("users", users);
	        return "user/list";
	    }

	    @RequestMapping("/toAdd")
	    public String toAdd() {
	        return "user/userAdd";
	    }

	    @RequestMapping("/add")
	    public String add(User user) {
	        userService.addUser(user);
	        return "redirect:/list";
	    }

	    @RequestMapping("/toEdit")
	    public String toEdit(Model model,int id) {
	        User user=userService.findUserById(id);
	        model.addAttribute("user", user);
	        return "user/userEdit";
	    }

	    @RequestMapping("/edit")
	    public String edit(User user) {
	        userService.updateUser(user);
	        return "redirect:/list";
	    }


	    @RequestMapping("/delete")
	    public String delete(int id) {
	        userService.deleteUser(id);
	        return "redirect:/list";
	    }
}
