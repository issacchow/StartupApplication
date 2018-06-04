/**
 *  
 *  @ClassName: ResponseDataGeneric 
 *  @author   周伟锋
 *  @date 2016年6月12日 下午9:03:35 
 *  
 */
package com.isc.application.startup.data.web;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.isc.application.startup.util.DataTypeUtil;
import com.isc.application.startup.data.result.PagingResult;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 *  
 *  @ClassName: ResponseDataGeneric 
 *  @Description:  
 *  @author 周伟锋  
 *  @date 2016年6月12日 
 *  
 */

public class ResponseDataGeneric<DataType>  implements Serializable {


    /**
     * 空Value序列化
     *
     * @author 周伟锋
     */
    protected static class SetNullValueSerilizer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider)
                throws IOException {
            jgen.writeString("");

        }
    }

    /**
     * 空Key序列化
     *
     * @author 周伟锋
     */
    protected static class SetNullKeySerilizer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider)
                throws IOException {
            jgen.writeString("");

        }
    }

    private static SetNullValueSerilizer setNullValueSerilizer = new SetNullValueSerilizer();
    private static SetNullKeySerilizer setNullKeySerilizer = new SetNullKeySerilizer();
    private static DateFormat datetimeFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public ResponseDataGeneric() {
        //this(Errcode.NO_ERROR, Errcode.getErrMsg(Errcode.NO_ERROR));
        this(Errcode.NO_ERROR, "操作成功");

    }

    public ResponseDataGeneric(Integer errcode, String errmsg) {
        super();
        setErrcode(errcode);
        setErrmsg(errmsg);
//        setPrettyPrint(true);
//		SerializerProvider provider = this.getObjectMapper().getSerializerProvider();
//		provider.setNullKeySerializer(setNullKeySerilizer);
//		provider.setNullValueSerializer(setNullValueSerilizer);
//        this.getObjectMapper().setDateFormat(datetimeFmt);
    }


    private static final long serialVersionUID = 1L;
    private Integer errcode = Errcode.NO_ERROR;
    private String errmsg;
    private DataType data;
    private boolean hasData = true;
    private HashMap<String, Object> appVersion = new HashMap<String, Object>();


    public HashMap<String, Object> getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(HashMap<String, Object> appVersion) {
        this.appVersion = appVersion;
    }

    public boolean isSuccess() {
        return errcode == Errcode.NO_ERROR;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
        if (DataTypeUtil.isNullOrEmpty(errmsg)) {
            String s = Errcode.getErrMsg(errcode);
            if (DataTypeUtil.isNullOrEmpty(s) == false) {
                setErrmsg(s);
            }
        }

    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public void setErrmsg(String format, Object... args) {
        String s = String.format(format, args);
        this.errmsg = s;
    }

    public DataType getData() {
        return data;
    }

    public void setData(DataType data) {
        this.data = data;
    }


    public boolean isHas_Data() {
        return hasData;
    }

    public void setHas_Data(boolean hasData) {
        this.hasData = hasData;
    }



    /**
     * 获取当前用于输出json 的对象模型
     *
     * @return Map<String,Object>
     * @author 周伟锋
     * @date 2016年7月15日
     */
    public Map<String, Object> getJsonModel() {
        Map<String, Object> resolveData = new HashMap<String, Object>();
        resolveData.put("errcode", this.getErrcode());
        resolveData.put("errmsg", this.getErrmsg());

        //处理ResponsePagingResult的输出
        if (this instanceof ResponsePagingResult) {

            ResponsePagingResult me = (ResponsePagingResult) this;


            PagingResult pagingResult = null;

            Object data = this.getData();
            if (data != null && data instanceof PagingResult) {
                pagingResult = (PagingResult) data;
            } else {
                pagingResult = PagingResult.empty();
            }

            //PagingResult 转化格式
            Map<String, Object> dataMap = pagingResult.toMap();
            dataMap.put("total_records", me.isSkipTotalRecords() ? -1 : pagingResult.getTotal_records());
            dataMap.put("total_pages", me.isSkipTotalPages() ? -1 : pagingResult.getTotal_pages());
            resolveData.put("data", dataMap);

        } else {

            Object data = this.getData();
            if (data != null && data instanceof PagingResult) {
                //PagingResult 转化格式
                PagingResult pagingResult = (PagingResult)data;
                Map<String, Object> dataMap = pagingResult.toMap();
                resolveData.put("data", dataMap);
            } else {
                resolveData.put("data", this.getData());
            }
        }

        resolveData.put("appVersion", this.getAppVersion());

//        String s = DataTypeUtil.getStr(SpringContextUtil.getHttpRequest().getParameter("callback_data"));
//        resolveData.put("callback_data", s);
        return resolveData;
    }


}
