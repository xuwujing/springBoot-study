package com.pancm.storm.test;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;

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
	
	private static final String str1="test1"; 
	private static final String str2="test2"; 

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		//定义一个拓扑
		TopologyBuilder builder=new TopologyBuilder();
		builder.setSpout(str1, new TestSpout());
		builder.setBolt(str2, new TestBolt()).shuffleGrouping(str1);
		Config conf = new Config();
		conf.put("test", "test");
		try{
		  //运行拓扑
	       if(args !=null&&args.length>0){ //有参数时，表示向集群提交作业，并把第一个参数当做topology名称
	       	 System.out.println("远程模式");
			 StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
	      } else{//没有参数时，本地提交
	        //启动本地模式
	     	System.out.println("本地模式");
	        LocalCluster cluster = new LocalCluster();
	        cluster.submitTopology("111" ,conf,  builder.createTopology() );
//	        Thread.sleep(2000);
//	        //关闭本地集群
//	        cluster.shutdown();
	      }
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
