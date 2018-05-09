package com.pancm.storm.demo.test2;

import java.util.HashMap;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

/**
 * 
* Title: Test2Bolt
* Description:
* 统计单词出现的次数 
* Version:1.0.0  
* @author pancm
* @date 2018年3月16日
 */
public class Test2Bolt extends BaseRichBolt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4743224635827696343L;
	
	
	/**
	 * 保存单词和对应的计数
	 */
	private HashMap<String, Integer> counts = null;
	 
	private long count=1;
	/**
    * 在Bolt启动前执行，提供Bolt启动环境配置的入口
    * 一般对于不可序列化的对象进行实例化。
    * 注:如果是可以序列化的对象，那么最好是使用构造函数。
    */
	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map map, TopologyContext arg1, OutputCollector collector) {
		System.out.println("prepare:"+map.get("test"));
		this.counts=new HashMap<String, Integer>();
	}
  
	/**
	 * execute()方法是Bolt实现的核心。
	 * 也就是执行方法，每次Bolt从流接收一个订阅的tuple，都会调用这个方法。
	 * 
	 */
	@Override
	public void execute(Tuple tuple) {
		String msg=tuple.getStringByField("count");
		System.out.println("第"+count+"次统计单词出现的次数");
		/**
		 * 如果不包含该单词，说明在该map是第一次出现
		 * 否则进行加1
		 */
		if (!counts.containsKey(msg)) {
			counts.put(msg, 1);
		} else {
			counts.put(msg, counts.get(msg)+1);
		}
		count++;
	}

	
	/**
     * cleanup是IBolt接口中定义,用于释放bolt占用的资源。
     * Storm在终止一个bolt之前会调用这个方法。
	 */
	@Override
	public void cleanup() {
		System.out.println("===========开始显示单词数量============");
		for (Map.Entry<String, Integer> entry : counts.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		System.out.println("===========结束============");
	    System.out.println("Test2Bolt的资源释放");
	}
	
	/**
	 * 声明数据格式
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		
	}
}
