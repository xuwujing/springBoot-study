package com.pancm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pancm.dao.BaseDao;
import com.pancm.dao.cluster.StudentDao;
import com.pancm.pojo.Student;
import com.pancm.service.StudentService;


/**
 * 
* Title: StudentServiceImpl
* Description: 
* 用户操作实现类 
* Version:1.0.0  
* @author pancm
* @date 2018年3月19日
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student>  implements  StudentService {
	@Autowired
	private StudentDao studentDao;
	
	@Override
	protected BaseDao<Student> getMapper() {
		return this.studentDao;
	}
	
}
