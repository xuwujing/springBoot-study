package com.pancm.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.pancm.pojo.Book;

/**
* @Title: dd
* @Description: 
* @Version:1.0.0  
* @author pancm
* @date 2019年2月28日
*/
public interface BookRepository extends ElasticsearchRepository<Book, String> {
	
	
	/*
	 *  自定义的查询接口名称
	 */
	
    List<Book> findByNameAndPrice(String name, Integer price);

    List<Book> findByNameOrPrice(String name, Integer price);
    
    Page<Book> findByName(String name,Pageable page);

    Page<Book> findByNameNot(String name,Pageable page);

    Page<Book> findByPriceBetween(int price,Pageable page);

    Page<Book> findByNameLike(String name,Pageable page);

    @Query("{\"bool\" : {\"must\" : {\"term\" : {\"message\" : \"?0\"}}}}")
    Page<Book> findByMessage(String message, Pageable pageable);
}
