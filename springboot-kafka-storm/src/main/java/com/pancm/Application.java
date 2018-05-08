package com.pancm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pancm.storm.bolt.InsertBolt;
import com.pancm.storm.spout.KafkaInsertDataSpout;
/**
 * 
* Title: Application
* Description:
* springBoot 主程序 
* Version:1.0.0  
* @author pancm
* @date 2018年1月5日
 */
@SpringBootApplication
public class Application{

	private static final String KAFKA_SPOUT="KAFKA_SPOUT"; 
	private static final String INSERT_BOLT="INSERT_BOLT"; 
	
	public static void main(String[] args) {
		
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(Application.class, args);
		System.out.println("程序正在运行...");
		runStorm(args);
	}
	
	
	private static void runStorm(String[] args){
		
		//定义一个拓扑
		TopologyBuilder builder=new TopologyBuilder();
		//设置1个Executeor(线程)，默认一个
		builder.setSpout(KAFKA_SPOUT, new KafkaInsertDataSpout(),1);
		//shuffleGrouping:表示是随机分组
		//设置1个Executeor(线程)，和两个task
		builder.setBolt(INSERT_BOLT, new InsertBolt(),1).setNumTasks(1).shuffleGrouping(KAFKA_SPOUT);
		Config conf = new Config();
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
//			        //关闭本地集群
	        cluster.shutdown();
	      }
		}catch (Exception e){
			e.printStackTrace();
		}
		
		System.out.println("storm启动...");
		
	}
}
