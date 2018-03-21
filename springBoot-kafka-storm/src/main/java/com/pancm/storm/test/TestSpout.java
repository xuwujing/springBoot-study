package com.pancm.storm.test;

import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

/**
 * 
* Title: TestSpout
* Description:
* Spout 发射器
* 用于向Bolt发送消息
* Version:1.0.0  
* @author pancm
* @date 2018年3月6日
 */
public class TestSpout extends BaseRichSpout{

	private static final long serialVersionUID = 225243592780939490L;

	private SpoutOutputCollector collector;
	private String message="这是个测试消息!";
	private static final String field="test";
	private long count=1;
	
	
	/**
     * open()方法中是在ISpout接口中定义，在Spout组件初始化时被调用。
     * 有三个参数:
     * 1.Storm配置的Map;
     * 2.topology中组件的信息;
     * 3.发射tuple的方法;
     */
	@Override
	public void open(Map map, TopologyContext arg1, SpoutOutputCollector collector) {
		System.out.println("open:"+map.get("test"));
		this.collector = collector;
	}

	/**
     * nextTuple()方法是Spout实现的核心。
     * 也就是主要执行方法，用于输出信息,通过collector.emit方法发射。
     */
	@Override
	public void nextTuple() {
		if(count<=2){
			System.out.println("第"+count+"次开始发送数据...");
			this.collector.emit(new Values(message));
		}
		count++;
	}


	/**
     * declareOutputFields是在IComponent接口中定义，用于声明数据格式。
     * 即输出的一个Tuple中，包含几个字段。
     */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		System.out.println("定义格式...");
		declarer.declare(new Fields(field));
	}

	/**
	 * 当一个Tuple处理成功时，会调用这个方法
	 */
	@Override
	public void ack(Object obj) {
		System.out.println("ack:"+obj);
	}
	
	/**
	 * 当Topology停止时，会调用这个方法
	 */
	@Override
	public void close() {
		System.out.println("关闭...");
	}
	
	/**
	 * 当一个Tuple处理失败时，会调用这个方法
	 */
	@Override
	public void fail(Object obj) {
		System.out.println("失败:"+obj);
	}
}
