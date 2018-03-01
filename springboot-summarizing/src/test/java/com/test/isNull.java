package com.test;

import org.springframework.util.Assert;

public class isNull {

	public static void main(String[] args) {
		test();
		 System.out.println("111");
	}

	
	private static void test() {
		 String a=null;
		 Assert.notNull(a, "Filter must not be null");
	}
}
