package com.isc.application.startup.config.db;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.isc.application.startup.util.BeanInitLogger;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig extends BeanInitLogger {


    @Bean("writeDataSource")
    @ConfigurationProperties("spring.datasource.write")
    public DataSource buildWriteDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("readDataSource")
    @ConfigurationProperties("spring.datasource.read")
    public DataSource buildReadDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 创建数据源代理类
     *
     * @return
     */
    @Bean("PrimaryDataSource")
    @Primary
    public DataSource buildDataSourceProxy(
    ) {
        DataSourceProxy proxy = new DataSourceProxy();
        DataSource writeDataSource = buildWriteDataSource();
        DataSource readDataSource = buildReadDataSource();
        proxy.addDataSource(DataSourceType.WriteDataSource.toString(),writeDataSource);
        proxy.addDataSource(DataSourceType.ReadDataSource.toString(),readDataSource);
        //设置默认数据源
        DataSourceSwitcher.swtich(DataSourceType.WriteDataSource);
        proxy.setDefaultTargetDataSource(writeDataSource);

        return proxy;
    }

}
