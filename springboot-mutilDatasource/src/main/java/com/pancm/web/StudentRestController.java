package com.pancm.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pancm.pojo.Student;
import com.pancm.service.StudentService;


/**
 * 
* Title: StudentRestController
* Description: 
* 用户控制层
* Version:1.0.0  
* @author pancm
* @date 2018年3月19日
 */
@RestController
@RequestMapping(value = "/api")
public class StudentRestController {
	@Autowired
    private StudentService service;
 
	@RequestMapping(value = "/student", method = RequestMethod.POST)
    public boolean addStudent(@RequestBody Student student) {
    	System.out.println("开始新增...");
        return service.insert(student);
    }
    
	@RequestMapping(value = "/student", method = RequestMethod.PUT)
    public boolean updateStudent(@RequestBody Student student) {
    	System.out.println("开始更新...");
        return service.update(student);
    }
	
	@RequestMapping(value = "/student", method = RequestMethod.DELETE)
    public boolean delete(@RequestBody Student student) {
    	System.out.println("开始删除...");
        return service.delete(student);
    }
	
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public Student findByStudent(Student student) {
    	System.out.println("开始查询...");
        return service.findByEntity(student);
    }
    
    @RequestMapping(value = "/student/findAll", method = RequestMethod.GET)
    public List<Student> findAll() {
    	System.out.println("开始查询...");
        return service.findAll();
    }
}
