package com.isc.application.startup;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.isc.application.startup.advice.AdviceMarker;
import com.isc.application.startup.config.SpringConfigMarker;
import com.isc.application.startup.controller.ControllerMarker;
import com.isc.application.startup.service.ServiceMarker;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;

/**
 * 多数据源应用程序(读写分离)
 */
@SpringBootApplication(
        scanBasePackageClasses = {
                SpringConfigMarker.class,
                ControllerMarker.class,
                ServiceMarker.class,
                AdviceMarker.class
        },
        exclude = {
                DruidDataSourceAutoConfigure.class,
                DataSourceAutoConfiguration.class,
                MybatisAutoConfiguration.class,
                JmsAutoConfiguration.class
        }
)
public class BootApplication {

    public static  void main(String...args){
       SpringApplication.run(BootApplication.class,args);
    }

}
