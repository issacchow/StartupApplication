package com.isc.application.startup.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  
 *  
 * <ul>
 * <li>@ClassName: JsonUtil</li>
 *  
 * <li>@Description: JsonUtil</li>
 *  
 * <li>@author 周伟锋</li>
 *  
 * <li>@date 2016年6月21日</li>
 *  
 * <li></li>
 * </ul>
 */

public class JsonUtil {

	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final ObjectWriter MAPPTER_1 = new ObjectMapper().writer().withDefaultPrettyPrinter();
	/**
	 * 
	 * 转换成json字符串
	 * <ul>
	 * <li>@author 周伟锋</li>
	 * <li>@date 2016年6月21日</li>
	 * <li>@param obj
	 * <li>@return</li>
	 * <li>String</li>
	 * </ul>
	 */
	public static String toJsonString(Object obj) {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try{
			return ow.writeValueAsString(obj);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
			return "{}";
		}
//      此处注释掉是因为阿里巴巴的 fastjson 会导致启动 search_provider 出现问题
//		return com.alibaba.fastjson.JSON.toJSONString(obj);
	}

	public static <T> T jsonToObject(String json,Class<T> beanType){
		try {
			return  MAPPER.readValue(json,beanType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> List<T> jsonToList(String json,Class<T> beanType){
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			return MAPPER.readValue(json,javaType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public static String toJsonString(Object obj, boolean enableFormat) {
		return com.alibaba.fastjson.JSON.toJSONString(obj, enableFormat);
	}

	public static com.alibaba.fastjson.JSONObject toJsonObject(String json) {
		return com.alibaba.fastjson.JSON.parseObject(json);
	}

	public static com.alibaba.fastjson.JSONArray toJsonArray(String json) {
		try {
			return com.alibaba.fastjson.JSON.parseArray(json);
		} catch (Exception e) {
			logger.error("{}", e);
			return com.alibaba.fastjson.JSON.parseArray("[]");
		}
	}

	public static String toJsonStringFormatter(Object value) throws Exception{
		return MAPPTER_1.writeValueAsString(value);
	}

}
