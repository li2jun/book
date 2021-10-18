package com.bean;

import java.util.List;

/**
 * @ahuthor lzy
 * @create 2021-10-10
 */
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;
    //当前页码
    private Integer pageNo;
    //总页码
    private Integer pageTotal;
    //总记录数
    private Integer PageTotalCount;

    //每页显示数量
    private Integer pageSize = PAGE_SIZE;
    //分页的URL
    private String Url;
    //当前页数据
    private List<T> items;

    public Page() {
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", PageTotalCount=" + PageTotalCount +
                ", pageSize=" + pageSize +
                ", items=" + items +
                '}';
    }

    public static Integer getPageSize() {
        return PAGE_SIZE;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return PageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        PageTotalCount = pageTotalCount;
    }

    public Page(Integer pageNo, Integer pageTotal, Integer pageTotalCount, String url, List<T> items) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        PageTotalCount = pageTotalCount;
        Url = url;
        this.items = items;
    }
}
