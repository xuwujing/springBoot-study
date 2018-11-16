package com.pancm.commons.config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.pancm.commons.util.AESUtil;
import com.pancm.commons.util.SetSystemProperty;

/**
 * 
* @Title: MasterDataSourceConfig
* @Description:
* 主数据源配置
* @Version:1.0.0  
* @author pancm
* @date 2018年4月26日
 */
@Configuration
public class MasterDataSourceConfig {
	private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.datasource.url}")  
    private String url;  
      
    @Value("${spring.datasource.username}")  
    private String username;  
      
    @Value("${spring.datasource.password}")  
    private String password;  
      
    @Value("${spring.datasource.driverClassName}")  
    private String driverClassName;  
      
    
    
    
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
    
    
    @Bean(name = "masterDataSource")
    @Primary //标志这个 Bean 如果在多个同类 Bean 候选时，该 Bean 优先被考虑。 
    public DataSource masterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);  
        dataSource.setUsername(username);  
//        dataSource.setPassword(password);  
//        dataSource.setPassword("23654");  
        dataSource.setDriverClassName(driverClassName);  
      
        
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
        	logger.error(e.getMessage());
        }  
        dataSource.setConnectionProperties(connectionProperties);  
        String password2=AESUtil.decrypt(password);
        //如果为空,说明解密失败,那么就直接连接，若还是失败，说明密码错误，直接退出程序
        dataSource.setPassword(password2==null?password:password2);
    	//如果连接失败，则直接退出程序!
    	if(!testCon(dataSource)){
    		logger.error("数据库连接失败!程序退出!");
    		System.exit(1);
    	}
    	//连接成功并且是明文的话,就直接回写密文
    	if(password2==null){
	    	SetSystemProperty ssp = new SetSystemProperty();
	    	Map<String, String> map = new HashMap<String, String>();
	    	map.put("spring.datasource.password", AESUtil.encrypt(password));
			ssp.updateProperties("application.properties", map, "password Encode");
			logger.error("密文回写成功!");
    	}
        return dataSource;
    }

    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        //分页插件
        PageHelper pageHelper = new PageHelper();  
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect", "mysql");
        //是否将参数offset作为PageNum使用
        properties.setProperty("offsetAsPageNum", "true");
        //是否进行count查询
        properties.setProperty("rowBoundsWithCount", "true");
        //是否分页合理化
        properties.setProperty("reasonable", "false");
        pageHelper.setProperties(properties);
        sessionFactory.setPlugins(new Interceptor[] {pageHelper});
        
        return sessionFactory.getObject();
    }
    
    
    /**
     * 连接测试
     * @param dataSource
     * @param username
     * @param password
     * @return
     */
    public boolean testCon(DataSource dataSource,String username,String password){
    	try{
    		dataSource.getConnection(username, password);
    	}catch(Exception e){
    		return false;
    	}
    	return false;
    }
    
    /**
     * 连接测试
     * @param dataSource
     * @param username
     * @param password
     * @return
     */
    public boolean testCon(DataSource dataSource){
    	try{
    		dataSource.getConnection();
    	}catch(Exception e){
    		return false;
    	}
    	return true;
    }
    
    /**
     * 密文回写
     * @param path  文件的路径
     * @param key   回写的key
     * @param value 回写的value
     * @return
     * @throws IOException 
     */
    public boolean writeBackConfig(String path,String key,String value) throws IOException{
    	Properties prop=new Properties();
    	prop.setProperty(key, value);
    	FileWriter fw = new FileWriter(path);  
        BufferedWriter bw=new BufferedWriter(fw);
        bw.write(prop.toString());  
        bw.close();
        fw.close(); 
		return false;
    }
    
    
}