package springboot.data.request;

import java.io.Serializable;

/**
 * 流水式分页请求
 * 分页方式为 以某一个点开始偏移一定的偏移量(即pageSize),然后抓取这一区间的数据
 * Created by IssacChow on 17/6/16.
 */
public class StreamingPagingRequest implements Serializable {

    //分页标记id,表示从这个id 开始偏移pageSize指定的量,并抓取这一区间的数据
    //参考PagingResult.next_page属性
    private String pageId;
    //偏移量
    private int pageSize;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        if ("0".equals(pageId)) {
            this.pageId = "";
        }else {
            this.pageId = pageId;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
