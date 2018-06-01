/**
 *  
 *  @ClassName: Result 
 *  @author   周伟锋
 *  @date 2016年6月3日 下午3:51:05 
 *  
 */
package springboot.data.result;


import java.io.Serializable;

/** 
 * Service处理结果
 * @ClassName: Result 
 * @Description:  
 * @author 周伟锋  
 * @date 2016年6月3日 
 * 
 */

public class Result implements Serializable {


    //无错误代码
    public static final int ERRORCODE_NO_ERROR = 0;
    //未知错误代码
    public static final int ERRORCODE_UNKONW_EXCEPTION = -1;

    private String errmsg;
    private int errcode;
    private boolean isSuccees;

    public Result(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        isSuccees = true;
    }

    public Result() {
        errmsg = "";
        errcode = 0;
        isSuccees = true;
    }

    /**
     * @return 返回处理错误消息
     */
    public String getErrmsg() {
        return errmsg;
    }

    /**
     * @param 错误消息
     */
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public void setErrmsg(String format, Object... objs) {
        this.errmsg = String.format(format, objs);
    }

    public void setErrmsg(Exception e) {
        this.errmsg = e.getMessage();
    }


    /**
     * @return 返回Service处理错误代码
     */
    public int getErrcode() {
        return errcode;
    }

    /**
     * @param 设置Service处理错误代码
     */
    public void setErrcode(int errcode) {
        if (errcode != 0) {
            setSuccess(false);
        }
        this.errcode = errcode;
    }


    /**
     * @return 表示当前Service处理是否成功
     */
    public boolean isSuccess() {
        return isSuccees;
    }

    /**
     * @param 设置Service操作是否成功,当设置为false时,应该同时设置errcode
     */
    public void setSuccess(boolean isSuccess) {
        this.isSuccees = isSuccess;
        if (isSuccess)
            this.errcode = ERRORCODE_NO_ERROR;
    }

    public static final Result SUCCESS;
    public static final Result FAILTURE;

    static {
        SUCCESS = new Result();
        SUCCESS.setSuccess(true);
        SUCCESS.setErrcode(0);
        SUCCESS.setErrmsg("操作成功");


        FAILTURE = new Result();
        FAILTURE.setSuccess(false);
        FAILTURE.setErrcode(-1);
        FAILTURE.setErrmsg("操作失败");
    }
}
