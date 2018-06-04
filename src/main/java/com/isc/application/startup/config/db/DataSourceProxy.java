package com.isc.application.startup.config.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源代理，用于动态切换数据源
 */
public class DataSourceProxy extends AbstractRoutingDataSource {


    Map<Object,Object> mapDataSorce;

    public DataSourceProxy() {
        mapDataSorce = new HashMap<>();
    }

    @Override
    protected Object determineCurrentLookupKey(){
        return DataSourceSwitcher.getCurrent().toString();
    }

    @Override
    protected DataSource determineTargetDataSource() {
        return (DataSource)mapDataSorce.get(determineCurrentLookupKey());
    }

    public void addDataSource(String key,DataSource dataSource){
        this.mapDataSorce.put(key,dataSource);
    }


    @Override
    public void afterPropertiesSet() {
        this.setTargetDataSources(mapDataSorce);
    }
}