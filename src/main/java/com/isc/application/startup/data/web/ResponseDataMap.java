/**
 *    @ClassName: ResponseMap   @author   周伟锋  @date 2016年6月12日 下午9:05:31   
 */
package com.isc.application.startup.data.web;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应数据字典
 * @ClassName: ResponseDataMap 
 * @Description:  
 * @author 周伟锋  
 * @date 2016年6月12日 
 * 
 */
public class ResponseDataMap extends ResponseDataGeneric<Map<String, Object>> {

	/**
	 * 
	 */
	public ResponseDataMap() {
		super();
	}
	
	/**
	 * 提供操作data属性遍历方法
	 * @author 周伟锋
	 * @date 2016年6月12日
	 * @param key
	 * @param value
	 * void
	 */
	public void setDataValue(String key,Object value) {
		if(this.getData()==null){
			this.setData(new HashMap<String,Object>());
		}
		
		this.getData().put(key, value);
	}
}
