package com.pancm.web;

import com.pancm.config.PrometheusCustomMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	PrometheusCustomMonitor prometheusCustomMonitor;


	@RequestMapping("/hello/{name}")
	public String index(@PathVariable String name) throws Exception {
		if(!"pancm".equals(name)){
			throw new Exception("出错了");
		}
		return "Hello " + name;
	}

}
