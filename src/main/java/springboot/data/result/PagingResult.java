package springboot.data.result;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页结果
 *
 * @author IssacChow
 */
public class PagingResult<ListItemType> implements Serializable {

    /**
     * 是否跳过记录数和页码
     */
    private boolean isSkipRecordAndPage = true;

    public boolean isSkipRecordAndPage() {
        return isSkipRecordAndPage;
    }

    public void setSkipRecordAndPage(boolean skipRecordAndPage) {
        isSkipRecordAndPage = skipRecordAndPage;
    }

    /**
     * 分页方式
     */
    public enum PagingType {

        //页码分页方式
        PagePaging(0),
        //流水式分页方式
        StreamingPaging(1);

        private int value = -1;

        PagingType(int value) {
            this.value = value;
        }
    }

    /**
     * 当前分页标记
     * 当分页类型paging_type=0时,  page的值为查询页码
     * 当分页类型paging_type=1时,  page的值为用于偏移分页的记录id
     */
    private Integer page = -1;
    /**
     * 分页大小
     */
    private Integer page_size = -1;

    /**
     * 分页类型:
     * 0-页码分页方式(默认),
     * 当has_next为true时, next_page为当前页码 page + 1, 否则next_page为当前page
     * 当has_prev为true是, prev_page为当前页码 page - 1, 否则为空
     * <p>
     * 1-记录id偏移方式(需要手动设置next_page与prev_page)
     * 当has_next为true时,next_page为当前list 的最后一条记录的id,否则next_page为当前page
     * 当has_prev为true是, prev_page为当前页码 page - 1, 否则为空
     */
    private int paging_type = 0;

    /**
     * 下一页分页标记,查询下一页时传递该属性值作为参数
     */
    private String next_page = "";

    /**
     * 下一页分页标记,查询下一页时传递该属性值作为参数
     */
    private String prev_page = "";

    /**
     * 总记录数
     */
    private int total_records = 0;

    /**
     * 扩展字段
     */
    private Object extend;

    public Object getExtend() {
        return extend;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }

    /**
     * 总页数
     */
    private int total_pages = 0;

    /**
     * 是否有下一页
     */
    private boolean has_next = false;

    /**
     * 是否有上一页,对于paging_type=1,不支持该属性
     */
    private boolean has_prev = false;

    /**
     * 是否第一页
     */
    private boolean is_first = false;
    /**
     * 是否最后一页
     */
    private boolean is_last = false;

    private boolean isSuccess = true;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean is_first() {
        return is_first;
    }

    public void setIsFirst(boolean is_first) {
        this.is_first = is_first;
    }


    public boolean is_last() {
        return is_last;
    }

    public void setIsLast(boolean is_last) {
        this.is_last = is_last;
    }


    /**
     * 查询结果
     */
    private List<ListItemType> list;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<ListItemType> getList() {
        if (list == null) {
            list = new ArrayList<ListItemType>();
        }
        return list;
    }

    public void setList(List<ListItemType> list) {
        this.list = list;
    }

    public int getTotal_records() {
        return total_records;
    }

    public void setTotal_records(int total_records) {
        this.total_records = total_records;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

    public boolean isHas_next() {
        return has_next;
    }

    public void setHas_next(boolean has_next) {
        this.has_next = has_next;
    }

    public boolean isHas_prev() {
        return has_prev;
    }

    public void setHas_prev(boolean has_prev) {
        this.has_prev = has_prev;
    }

    public boolean isIs_first() {
        return is_first;
    }

    public void setIs_first(boolean is_first) {
        this.is_first = is_first;
    }

    public boolean isIs_last() {
        return is_last;
    }

    public void setIs_last(boolean is_last) {
        this.is_last = is_last;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }


    public String getNext_page() {
        return next_page;
    }

    public void setNext_page(String next_page) {
        this.next_page = next_page;
    }

    public void setNext_page(Object next_page) {
        this.next_page = String.valueOf(next_page);
    }


    public int getPaging_type() {
        return paging_type;
    }

    public void setPaging_type(int paging_type) {
        this.paging_type = paging_type;
    }

    public void setPaging_type(PagingType pagingType) {
        this.paging_type = pagingType.value;
    }


    public String getPrev_page() {
        return prev_page;
    }

    public void setPrev_page(String prev_page) {
        this.prev_page = prev_page;
    }

    /**
     * 创建一个空数据的分页结果
     *
     * @return
     * @date 2016年9月23日
     * @author 周伟锋
     */
    public static <ListItemType> PagingResult<ListItemType> empty() {
        PagingResult<ListItemType> result = new PagingResult<ListItemType>();
        result.setTotal_records(0);
        result.setTotal_pages(0);
        result.setPage(0);
        result.setPage_size(0);
        result.setPaging_type(0);
        result.setNext_page("");
        result.setPrev_page("");
        result.setHas_next(false);
        result.setHas_prev(false);
        result.calc();
        return result;
    }


    /**
     * 根据total_records 和 page_size , page 计算 total_pages,is_first,is_last,has_prev,has_next
     */
    public void calc() {
        int pagingType = this.getPaging_type();
        switch (pagingType) {
            case 0:
                calcByPageNumber();
                break;
            case 1:
                calcByPageId();
                break;
        }


    }


    //已页码方式分页
    private void calcByPageNumber() {
        if (this.total_records <= 0 || this.page <= 0 || this.page_size <= 0) {
            this.is_last = true;
            return;
        }

        //计算总页数
        this.total_pages = (int) this.total_records / this.page_size;
        if ((this.total_records % this.page_size) > 0) {
            this.total_pages++;
        }

        if (page > total_pages) {
            page = total_pages;
        }

        //计算是否第一页
        this.is_first = page == 1;

        //计算是否最后一页
        this.is_last = page == this.total_pages;

        //计算是否有上一页和下一页
        if (this.is_first) {
            this.has_prev = false;
        } else {
            this.has_prev = (this.page > 1);
        }

        if (this.is_last) {
            this.has_next = false;
        } else {
            this.has_next = this.page < this.total_pages;
        }

        //计算下一页的页码
        if (this.has_next) {
            this.next_page = String.valueOf(this.page + 1);
        } else {
            this.next_page = String.valueOf(this.page);
        }

        //计算上一页的页码
        if (this.has_prev) {
            this.prev_page = String.valueOf(this.page - 1);
        } else {
            this.prev_page = "";
        }

    }

    //以记录id方式分页查询
    private void calcByPageId() {
        if (this.total_records <= 0 || this.page_size <= 0) {
            return;
        }

        //计算总页数
        this.total_pages = (int) this.total_records / this.page_size;
        if ((this.total_records % this.page_size) > 0) {
            this.total_pages++;
        }


        //上一页不作计算
        this.has_prev = false;


        //计算是否有下一页
        this.has_next = this.list.size() >= this.page_size;


    }


    /**
     * 装换成用于输出给接口使用的Map数据
     *
     * @return
     */
    public Map<String, Object> toMap() {
        return toMap(this,isSkipRecordAndPage);
    }


    public Map<String, Object> toMap(PagingResult pagingResult, boolean hide_total_info) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("has_next", pagingResult.isHas_next() ? 1 : 0);
        dataMap.put("has_prev", pagingResult.isHas_prev() ? 1 : 0);
        dataMap.put("is_first", pagingResult.is_first() ? 1 : 0);
        dataMap.put("is_last", pagingResult.is_last() ? 1 : 0);
        dataMap.put("next_page", pagingResult.getNext_page());
        dataMap.put("prev_page", pagingResult.getPrev_page());
        dataMap.put("list", pagingResult.getList());
        dataMap.put("page", String.valueOf(pagingResult.getPage()));
        dataMap.put("page_size", pagingResult.getPage_size());
        dataMap.put("paging_type", pagingResult.getPaging_type());
        dataMap.put("total_records", hide_total_info ? -1 : pagingResult.getTotal_records());
        dataMap.put("total_pages", hide_total_info ? -1 : pagingResult.getTotal_pages());
        return dataMap;
    }




}
