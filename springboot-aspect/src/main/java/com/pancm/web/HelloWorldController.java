package com.pancm.web;

import com.pancm.pojo.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@PostMapping("/hello")
	public String index(@RequestBody User user) {
		System.out.println("user:"+user);
		return "Hello " + user.getName()+"，age  "+user.getAge();
	}

}
