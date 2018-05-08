package com.pancm.storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pancm.storm.bolt.InsertBolt;
import com.pancm.storm.spout.KafkaInsertDataSpout;

/**
 * 
 * @Title: TopologyApp
 * @Description: TopologyApp
 * @Version:1.0.0
 * @author pancm
 * @date 2018年5月8日
 */
@Component
public class TopologyApp {
	private  final Logger logger = LoggerFactory.getLogger(TopologyApp.class);

	
	private  final String KAFKA_SPOUT="KAFKA_SPOUT"; 
	private  final String INSERT_BOLT="INSERT_BOLT"; 
	
	
	public  void runStorm(String[] args) {
		// 定义一个拓扑
		TopologyBuilder builder = new TopologyBuilder();
		// 设置1个Executeor(线程)，默认一个
		builder.setSpout(KAFKA_SPOUT, new KafkaInsertDataSpout(), 1);
		// shuffleGrouping:表示是随机分组
		// 设置1个Executeor(线程)，和两个task
		builder.setBolt(INSERT_BOLT, new InsertBolt(), 1).setNumTasks(1).shuffleGrouping(KAFKA_SPOUT);
		Config conf = new Config();
		try {
			// 运行拓扑
			if (args != null && args.length > 0) { // 有参数时，表示向集群提交作业，并把第一个参数当做topology名称
				logger.info("运行远程模式");
				StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
			} else {// 没有参数时，本地提交
				// 启动本地模式
				logger.info("运行本地模式");
				LocalCluster cluster = new LocalCluster();
				cluster.submitTopology("TopologyApp", conf, builder.createTopology());
//				Thread.sleep(20000);
				// //关闭本地集群
//				cluster.shutdown();
			}
		} catch (Exception e) {
			logger.error("storm启动失败!程序退出!",e);
			System.exit(1);
		}

		logger.info("storm启动成功...");

	}
}
