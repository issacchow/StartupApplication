package com.isc.application.startup.mapper.order;

import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * Created by IssacChow on 18/4/12.
 */
public interface OrderMapper {

    @Select("select * from ")
    Map<String,Object> selectAll();
}
