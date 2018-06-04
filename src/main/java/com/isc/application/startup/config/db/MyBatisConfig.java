package com.isc.application.startup.config.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.isc.application.startup.dal.DALMarker;

import javax.sql.DataSource;

@Configuration
@AutoConfigureAfter(value = {DataSourceProxy.class})
@EnableTransactionManagement
@MapperScan(
        basePackageClasses = {DALMarker.class},
        sqlSessionFactoryRef = "sqlSessionFactory"
)
public class MyBatisConfig {

    @Autowired
    @Qualifier("PrimaryDataSource")
    DataSource dataSource;

    @Bean
    @ConfigurationProperties("mybatis.configuration")
    public org.apache.ibatis.session.Configuration buildMyBatisConfig(){
        return new org.apache.ibatis.session.Configuration();
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory buildSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();

        bean.setMapperLocations(
                resourceLoader.getResources("classpath:springboot/dal/**/*Mapper.xml")
        );
        bean.setDataSource(dataSource);
        //bean.setConfigLocation(resourceLoader.getResource("classpath:mybatis-config.xml"));
        //使用setConfiguration代替xml文件
        bean.setConfiguration(buildMyBatisConfig());
        return bean.getObject();
    }




}
