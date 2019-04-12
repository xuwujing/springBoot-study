package com.pancm.service.impl;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.pancm.dao.UserDao;
import com.pancm.pojo.User;
import com.pancm.service.UserService;

/**
 * 
* Title: UserServiceImpl
* Description:
* 用户操作实现类 
* Version:1.0.0  
* @author pancm
* @date 2018年1月9日
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private UserDao userDao;
	@Override
	public boolean insert(User user) {
		boolean falg=false;
		try{
			userDao.save(user);
			falg=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return falg;
	}

	@Override
	public List<User> search(String searchContent) {
		  QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
		  System.out.println("查询的语句:"+builder);
          Iterable<User> searchResult = userDao.search(builder);
          //等价于下面的这个方法
          List<User> list= Lists.newArrayList(searchResult);
//          Iterator<User> iterator = searchResult.iterator();
//          while (iterator.hasNext()) {
//       	   	list.add(iterator.next());
//          }
       return list;
	}
	
	
	
	/** 
	 * 
	 */
	@Override
	public List<User> searchByName(Integer pageNumber, Integer pageSize, String name) {
		
		 Page<User> searchPageResults = userDao.findUserByName(name, PageRequest.of(pageNumber, pageSize));
		return searchPageResults.getContent();
	}
	
	@Override
	public List<User> searchUser(Integer pageNumber, Integer pageSize,String searchContent) {
		 // 分页参数
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(builder).build();
        System.out.println("查询的语句:" + searchQuery.getQuery().toString());
        Page<User> searchPageResults = userDao.search(searchQuery);
        return searchPageResults.getContent();
	}
	

	@Override
	public List<User> searchUserByWeight(String searchContent) {
        // 根据权重进行查询
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(QueryBuilders.matchQuery("name", searchContent));
        System.out.println("查询的语句:" + functionScoreQueryBuilder.toString());
        Iterable<User> searchResult = userDao.search(functionScoreQueryBuilder);
        List<User> list= Lists.newArrayList(searchResult);
        return list;
	}



}
