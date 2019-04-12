package com.pancm.service;

import java.util.List;

import com.pancm.pojo.User;


/**
 * 
* Title: UserService
* Description:用户接口 
* Version:1.0.0  
* @author pancm
* @date 2018年1月9日
 */
public interface UserService {
	
	/**
     * 新增用户信息
     *
     * @param user
     * @return
     */
    boolean insert(User user);

    /**
     * 根据关键字进行全文搜索
     * @param searchContent
     * @return
     */
    List<User> search(String searchContent);
    
    
    /**
     * 根据名字进行分页查询
     * @param searchContent
     * @return
     */
    List<User> searchByName(Integer pageNumber, Integer pageSize,String name);
    
    /**
     * 根据关键字进行搜索并分页
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
    List<User> searchUser(Integer pageNumber, Integer pageSize, String searchContent);
	
    /**
     * 根据关键词权重进行查询
     * @param searchContent
     * @return
     */
    List<User> searchUserByWeight(String searchContent);
	
    
}
