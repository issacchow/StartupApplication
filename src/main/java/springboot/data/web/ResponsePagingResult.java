package springboot.data.web;


import springboot.data.result.PagingResult;

/**
 * 分页输出结果
 * @author IssacChow
 *
 */
public class ResponsePagingResult<ListItemType> extends ResponseDataGeneric<PagingResult<ListItemType>> {

    //是否跳过total_records属性的数据响应: ***这个属性属于敏感数据,应该根据情况进行处理***
    private boolean skipTotalRecords = true;
    //是否跳过total_records属性的数据响应 ***这个属性属于敏感数据,应该根据情况进行处理***
    private boolean skipTotalPages = true;

    public boolean isSkipTotalRecords() {
        return skipTotalRecords;
    }

    public void setSkipTotalRecords(boolean skipTotalRecords) {
        this.skipTotalRecords = skipTotalRecords;
    }

    public boolean isSkipTotalPages() {
        return skipTotalPages;
    }

    public void setSkipTotalPages(boolean skipTotalPages) {
        this.skipTotalPages = skipTotalPages;
    }
}
