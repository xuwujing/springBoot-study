package com.pancm.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;

/**
 * 
* @Title: ClusterDataSourceConfig
* @Description: 
*  另一个数据源配置
* @Version:1.0.0  
* @author pancm
* @date 2018年4月26日
 */
@Configuration
@MapperScan(basePackages = ClusterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterDataSourceConfig {

 static final String PACKAGE = "com.pancm.dao.cluster";
 static final String MAPPER_LOCATION = "classpath:mapper/cluster/*.xml";

 @Value("${cluster.datasource.url}")
 private String url;

 @Value("${cluster.datasource.username}")
 private String username;

 @Value("${cluster.datasource.password}")
 private String password;

 @Value("${cluster.datasource.driverClassName}")
 private String driverClass;

 
 @Value("${spring.datasource.initialSize}")  
 private int initialSize;  
   
 @Value("${spring.datasource.minIdle}")  
 private int minIdle;  
   
 @Value("${spring.datasource.maxActive}")  
 private int maxActive;  
   
 @Value("${spring.datasource.maxWait}")  
 private int maxWait;  
   
 @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")  
 private int timeBetweenEvictionRunsMillis;  
   
 @Value("${spring.datasource.minEvictableIdleTimeMillis}")  
 private int minEvictableIdleTimeMillis;  
   
 @Value("${spring.datasource.validationQuery}")  
 private String validationQuery;  
   
 @Value("${spring.datasource.testWhileIdle}")  
 private boolean testWhileIdle;  
   
 @Value("${spring.datasource.testOnBorrow}")  
 private boolean testOnBorrow;  
   
 @Value("${spring.datasource.testOnReturn}")  
 private boolean testOnReturn;  
   
 @Value("${spring.datasource.poolPreparedStatements}")  
 private boolean poolPreparedStatements;  
   
 @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")  
 private int maxPoolPreparedStatementPerConnectionSize;  
   
 @Value("${spring.datasource.filters}")  
 private String filters;  
   
 @Value("{spring.datasource.connectionProperties}")  
 private String connectionProperties;
 
 @Bean(name = "clusterDataSource")
 public DataSource clusterDataSource() {
     DruidDataSource dataSource = new DruidDataSource();
     dataSource.setUrl(url);  
     dataSource.setUsername(username);  
     dataSource.setPassword(password);  
     dataSource.setDriverClassName(driverClass);  
       
     //具体配置 
     dataSource.setInitialSize(initialSize);  
     dataSource.setMinIdle(minIdle);  
     dataSource.setMaxActive(maxActive);  
     dataSource.setMaxWait(maxWait);  
     dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);  
     dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);  
     dataSource.setValidationQuery(validationQuery);  
     dataSource.setTestWhileIdle(testWhileIdle);  
     dataSource.setTestOnBorrow(testOnBorrow);  
     dataSource.setTestOnReturn(testOnReturn);  
     dataSource.setPoolPreparedStatements(poolPreparedStatements);  
     dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);  
     try {  
         dataSource.setFilters(filters);  
     } catch (SQLException e) { 
     	e.printStackTrace();
     }  
     dataSource.setConnectionProperties(connectionProperties);  
     return dataSource;
 }

 @Bean(name = "clusterTransactionManager")
 public DataSourceTransactionManager clusterTransactionManager() {
     return new DataSourceTransactionManager(clusterDataSource());
 }

 @Bean(name = "clusterSqlSessionFactory")
 public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource clusterDataSource)
         throws Exception {
     final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
     sessionFactory.setDataSource(clusterDataSource);
     //分页插件
     Interceptor interceptor = new PageInterceptor();
     Properties properties = new Properties();
     //数据库
     properties.setProperty("helperDialect", "mysql");
     //是否将参数offset作为PageNum使用
     properties.setProperty("offsetAsPageNum", "true");
     //是否进行count查询
     properties.setProperty("rowBoundsWithCount", "true");
     //是否分页合理化
     properties.setProperty("reasonable", "false");
     interceptor.setProperties(properties);
     sessionFactory.setPlugins(new Interceptor[] {interceptor});
     
     
     sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(ClusterDataSourceConfig.MAPPER_LOCATION));
     return sessionFactory.getObject();
 }
}