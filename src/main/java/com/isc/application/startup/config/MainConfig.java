package com.isc.application.startup.config;

import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import com.isc.application.startup.beans.MyBean;
import com.isc.application.startup.config.db.DataSourceConfig;
import com.isc.application.startup.config.custom.MyConfig;
import com.isc.application.startup.util.BeanInitLogger;

import javax.annotation.Resource;

/**
 * 主配置
 * 所有扩展配置类(不能使用注射成为Bead的注解)均从这里开始: 通过@Import注解实现
 */
@Import(value={DataSourceConfig.class,MyConfig.class})
@Configuration
public class MainConfig extends BeanInitLogger{


    @Resource
    MyConfig myConfig;



    @Bean("myBean")
    @Order(1)
    public MyBean getMyBean(){
//        log("order 2");
        return new MyBean();
    }

    @Bean("myBean2")
    @Order(2)
    public MyBean getMyBean2(){
//        log("order 1");
        return new MyBean();
    }


    //测试其他配置bean加载情况
    @Bean("testResourceBean")
    @Order(4)
    public MyBean testResource(){
//        log("myConfig==null:%s",myConfig==null);
        return new MyBean();
    }

//    测试注入当前类其他bean
    @Bean("testInjectOtherBean")
    @Order(3)
    public MyBean testInjectOtherBean(){
        MyBean otherBean = testResource();
        MyBean myBean = new MyBean();
        return myBean;
    }












}
