package com.pancm.storm.demo.test2;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * 
* Title: App
* Description:
* storm测试 
* Version:1.0.0  
* @author pancm
* @date 2018年3月6日
 */
public class App {
	
	private static final String test_spout="test_spout"; 
	private static final String test_bolt="test_bolt"; 
	private static final String test2_bolt="test2_bolt"; 

	public static void main(String[] args)  {
		//定义一个拓扑
		TopologyBuilder builder=new TopologyBuilder();
		//设置1个Executeor(线程)，默认一个
		builder.setSpout(test_spout, new TestSpout(),1);
		//shuffleGrouping:表示是随机分组
		//设置1个Executeor(线程)，和两个task
		builder.setBolt(test_bolt, new TestBolt(),1).setNumTasks(1).shuffleGrouping(test_spout);
		//fieldsGrouping:表示是按字段分组
		//设置1个Executeor(线程)，和1个task
		builder.setBolt(test2_bolt, new Test2Bolt(),1).setNumTasks(1).fieldsGrouping(test_bolt, new Fields("count"));
		Config conf = new Config();
		conf.put("test", "test");
		try{
		  //运行拓扑
	       if(args !=null&&args.length>0){ //有参数时，表示向集群提交作业，并把第一个参数当做topology名称
	       	 System.out.println("运行远程模式");
			 StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
	      } else{//没有参数时，本地提交
	        //启动本地模式
	     	System.out.println("运行本地模式");
	        LocalCluster cluster = new LocalCluster();
	        cluster.submitTopology("Word-counts" ,conf,  builder.createTopology() );
	        Thread.sleep(20000);
//	        //关闭本地集群
	        cluster.shutdown();
	      }
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
