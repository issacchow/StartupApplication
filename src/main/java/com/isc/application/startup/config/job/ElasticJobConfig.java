package com.isc.application.startup.config.job;

import org.springframework.context.annotation.Configuration;

/**
 * Created by IssacChow on 18/5/31.
 */
@Configuration
//@ImportResource("classpath:elastic-job.xml")
public class ElasticJobConfig {

//    @Bean
//    public CoordinatorRegistryCenter createRegistryCenter() {
//        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(
//                new ZookeeperConfiguration("localhost:2182", "elastic-job-demo")
//        );
//        regCenter.init();
//        return regCenter;
//    }
//
//    @Bean
//    public LiteJobConfiguration jobConfiguration(){
//        // 定义作业核心配置
//        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder("demoSimpleJob", "0/5 * * * * ?",1).build();
//        // 定义SIMPLE类型配置
//        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, MyJob001.class.getCanonicalName());
//
//
//        // 定义Lite作业根配置
//        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).build();
//        return simpleJobRootConfig;
//    }
//
//
//
//
//    /**** job 定义 ****/
//    @Bean
//    public JobScheduler buildJobScheduler(){
//        JobScheduler jobScheduler = new JobScheduler(createRegistryCenter(),jobConfiguration());
//        jobScheduler.init();
//        return jobScheduler;
//    }


}
