package com.pancm.pojo;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @Title: Book
 * @Description: 书籍实体类
 * @Version:1.0.0
 * @author pancm
 * @date 2019年2月28日
 */
@Document(indexName = "bookindex", type = "book")
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private Integer price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	/** 
	 * 
	 */
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
	
}
