package com.isc.application.startup.data.web;

import java.util.HashMap;

/***
 * 公共异常
 *
 * @author 陈健才
 */
public class Errcode {

    /**
     * 正常
     */
    public final static int NO_ERROR = 0;

    /**
     * 后台内部异常
     */
    public final static int UNKNOW_EXCEPTION = -1;

    /***
     * 没找到数据
     */
    public final static int NO_DATA = -7;

    /**
     * 参数丢失
     */
    public final static int MISS_PARAMS = -2;

    /**
     * 参数错误
     */
    public final static int INCORRECT_PARAMS = -3;

    /**
     * 没有登录
     */
    public final static int Not_Login = -4;

    /**
     * 上传文件错误
     */
    public final static int UploadFile_Error = -8;

    /**
     * 无效Token
     */
    public final static int Invalid_Token = 2000001;

    /**
     * App客户端版本过低
     */
    public final static int AppVersionTooLow = 1000001;

    /**
     * 无效Token
     */
    public final static int Expired_Token = -6;

    public final static int Not_Allow_Access = -9;

    public static HashMap<Integer, String> codeMAP = new HashMap<Integer, String>();

    static {
        codeMAP.put(NO_ERROR, "正常");
        codeMAP.put(UNKNOW_EXCEPTION, "系统异常");
        //codeMAP.put(NO_DATA, "数据记录不存在");
    }

    //获取错误代码
    public static String getErrMsg(int code) {
        return codeMAP.get(code);
    }

}
