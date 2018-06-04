package com.isc.application.startup.data.request;



import java.io.Serializable;

/**
 * 分页请求基础类
 *
 * @author tommy
 */
public class PagingRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 每页大小
     */
    private int pageSize = 10;

    /**
     * 页码
     */
    private int page = 1;

    /**
     * 当前页
     */
    private int indexFrom;


    private String sidx;//排序名称

    private String sord;//排序类型

    private int maxSize = 30;//一页显示的最大值

    public static final String MAX_SIZE_KEY = "芝麻开门";

    public int getPageSize() {
        return pageSize;
    }

    public void setPage_size(int pageSize) {
		if(pageSize > maxSize){
			pageSize = maxSize;
		}
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page < 1) {
            page = 1;
        }
        this.page = page;
    }

    /**
     * 只能从这里get
     *
     * @return
     */
    public int getIndexFrom() {
        this.indexFrom = (this.getPage() - 1) * getPageSize();
        this.indexFrom = this.indexFrom < 0 ? 0 : this.indexFrom;
        return indexFrom;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize,String maxSizeChangeKey) {
            this.maxSize = maxSize;
    }


}
