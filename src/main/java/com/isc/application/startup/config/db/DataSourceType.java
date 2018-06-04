package com.isc.application.startup.config.db;

public enum DataSourceType{
    WriteDataSource("writeDataSource"),
    ReadDataSource("readDataSource");

    private  String name = null;
    DataSourceType(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
