/**
 * 
 */
package com.pancm.storm.bolt;

import java.util.List;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.pancm.pojo.User;
import com.pancm.service.UserService;

/**
 * @Title: InsertBolt
 * @Description: 
 * 写入数据的bolt
 * @Version:1.0.0  
 * @author pancm
 * @date 2018年4月19日
 */
public class InsertBolt extends BaseRichBolt{

		/**
		 * 
		 */
		private static final long serialVersionUID = 6542256546124282695L;

		private static final String field="insert";
		
		@Autowired
		private UserService userService;
		
		@Override
		public void prepare(Map map, TopologyContext arg1, OutputCollector collector) {
		}
	  
		
		@Override
		public void execute(Tuple tuple) {
			String msg=tuple.getStringByField(field);
			List<User> user =JSON.parseArray(msg,User.class);
			if(user!=null){
				userService.insertBatch(user);
			}
		}

		
		/**
	     * cleanup是IBolt接口中定义,用于释放bolt占用的资源。
	     * Storm在终止一个bolt之前会调用这个方法。
		 */
		@Override
		public void cleanup() {
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer arg0) {
				
		}
	
}
