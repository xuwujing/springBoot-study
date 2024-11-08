package com.pancm.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

import static com.pancm.config.PancmConfiguration.PACKAGE;

/**
 * @Author pancm
 * @Description 
 * @Date  2019/2/17
 * @Param 
 * @return 
 **/
@Configuration
@MapperScan(basePackages = {PACKAGE}, sqlSessionFactoryRef = "pancmSqlSessionFactory")
public class PancmConfiguration {

    static final String PACKAGE = "com.pancm.dao";
    static final String MAPPER_LOCATION = "classpath:pancm/mapper/*.xml";
    static final String CONFIG_LOCATION = "classpath:pancm/mybatis-config.xml";



    @Value("${spring.datasource.pancm.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.pancm.url}")
    private String url;

    @Value("${spring.datasource.pancm.username}")
    private String username;

    @Value("${spring.datasource.pancm.password}")
    private String password;


    @Bean(name = "pancmDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pancm")
    @Primary
    public DataSource pancmDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(10);
        dataSource.setMinIdle(10);
        dataSource.setMaxActive(30);
        dataSource.setMaxWait(30000);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(1800);
        dataSource.setLogAbandoned(false);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(500);
        dataSource.setKeepAlive(true);
        dataSource.setDbType("mysql");
        return dataSource;
    }

    @Bean(name = "pancmSqlSessionFactory")
    @Primary
    public SqlSessionFactory pancmSqlSessionFactory(@Qualifier("pancmDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});

        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(CONFIG_LOCATION));
        Objects.requireNonNull(bean.getObject()).getConfiguration().setMapUnderscoreToCamelCase(true);
        return bean.getObject();
    }

    @Bean(name = "pancmTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("pancmDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "pancmSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("pancmSqlSessionFactory") SqlSessionFactory sqlSessionFactory)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
