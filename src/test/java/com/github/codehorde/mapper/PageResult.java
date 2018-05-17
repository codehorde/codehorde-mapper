package com.github.codehorde.mapper;

import java.util.List;

public class PageResult<T> implements java.io.Serializable {

    private Integer pageSize = 10;

    private Integer pageNo = 1;

    private Long total;

    private List<T> data;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{" +
                "pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", total=" + total +
                ", data=" + data +
                '}';
    }
}

