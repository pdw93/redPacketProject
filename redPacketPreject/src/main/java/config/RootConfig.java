package config;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import redis.clients.jedis.JedisPoolConfig;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName RootConfig
 * @Description TODO
 * @Author shnstt
 * @Date 2019/4/20 15:54
 * @Version 1.0
 **/
@Configuration
@ComponentScan(basePackages = {"service.*"}, includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Service.class})})
@EnableTransactionManagement
public class RootConfig implements TransactionManagementConfigurer {
    private DataSource dataSource = null;

    /**
     * 配置数据库
     * @return 数据连接池
     */
    @Bean(name="dataSource")
    public DataSource initDataSource(){
        if (dataSource != null) {
            return dataSource;
        }
        Properties properties = new Properties();
        properties.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/ssmr?serverTimezone=GMT");
        properties.setProperty("username", "root");
        properties.setProperty("password", "root123456");
        properties.setProperty("maxIdle", "20");
        properties.setProperty("maxTotal", "200");
        properties.setProperty("maxWaitMillis", "30000");
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean initSqlSessionFactory() throws IOException {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(initDataSource());
        Resource mybatisConfig = new ClassPathResource("mybatis/mybatis_config.xml");
        sqlSessionFactory.setConfigLocation(mybatisConfig);
        // mapper.xml配置文件
        Resource[] mapperLocations = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*Mapper.xml");
        sqlSessionFactory.setMapperLocations(mapperLocations);
        return sqlSessionFactory;
    }

    /**
     * 通过扫描注册mybatis mapper接口
     * @return
     */
    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer initMapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("dao");
        msc.setSqlSessionFactoryBeanName("sqlSessionFactory");
        msc.setAnnotationClass(Repository.class);
        return msc;
    }
    @Override
    @Bean(name = "annotationDrivenTransactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(initDataSource());
        return transactionManager;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate initRedisTemplate(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(50);
        poolConfig.setMaxTotal(100);
        poolConfig.setMaxWaitMillis(10000);
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setPort(6379);
        connectionFactory.setHostName("localhost");
        connectionFactory.setPassword("shns");
        connectionFactory.setPoolConfig(poolConfig);
        // 实现了InitializingBean
        connectionFactory.afterPropertiesSet();
        RedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        RedisSerializer stringRedisSerializer = new StringRedisSerializer();
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setDefaultSerializer(stringRedisSerializer);
        redisTemplate.setStringSerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);
        return redisTemplate;
    }
}
