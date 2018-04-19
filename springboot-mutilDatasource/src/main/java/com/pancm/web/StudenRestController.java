package com.pancm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class StudenRestController {
	@Autowired
    private StudentService service;
 
	@RequestMapping(value = "/student", method = RequestMethod.POST)
    public int addStudent(@RequestBody Student student) throws Exception {
    	System.out.println("开始新增...");
        return service.insert(student);
    }
    
	@RequestMapping(value = "/student", method = RequestMethod.PUT)
    public int updateStudent(@RequestBody Student student) throws Exception {
    	System.out.println("开始更新...");
        return service.update(student);
    }
	
	@RequestMapping(value = "/student", method = RequestMethod.DELETE)
    public int delete(@RequestParam(value = "studentId", required = true) int studentId) throws Exception {
    	System.out.println("开始删除...");
        return service.deleteByPrimaryKey(studentId);
    }
	
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public Student findByStudentName(Student student) {
    	System.out.println("开始查询...");
        return service.findByEntity(student);
    }
    
}
