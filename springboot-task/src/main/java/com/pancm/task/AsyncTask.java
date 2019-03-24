package com.pancm.task;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * @Title: AsyncTask
 * @Description: 异步调用测试 EnableAsync: 开启异步调用，异步调用的方法只需加上@Async注解即可，
 *               一般放到启动类中，需要注意的是@Async所修饰的函数不要定义为static类型，这样异步调用不会生效
 * 
 * @Version:1.0.0
 * @author pancm
 * @date 2019年2月18日
 */
@Component
public class AsyncTask {
	
	/*
	 * 可以利用Future<T>来进行回调
	 */
	@Async
	public Future<String> test1() {
		System.out.println("开始一");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("结束一");
		return new AsyncResult<>("任务一完成");
	}

	@Async
	public Future<String> test2() {
		System.out.println("开始二");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("结束二");
		return new AsyncResult<>("任务二完成");
	}

	@Async
	public Future<String> test3() {

		System.out.println("开始三");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("结束三");
		return new AsyncResult<>("任务三完成");
	}

}
