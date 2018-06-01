package springboot.data.result;

/**
 * Service处理结果数据
 *  @ClassName: ResultData 
 *  @Description:  
 *  @author 周伟锋  
 *  @date 2016年6月3日 
 *  @param <T>
 */
public class ResultData<T> extends Result {

    private T data;

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

}
