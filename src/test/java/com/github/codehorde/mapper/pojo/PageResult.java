package com.github.codehorde.mapper.pojo;

import java.util.Arrays;
import java.util.List;

public class PageResult<T> implements java.io.Serializable {

    private Integer pageSize = 10;

    private Integer pageNo = 1;

    private Long total;

    private List<T> data;

    private List<T>[] datas;

    private T item;

    private T[] items;



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

    public List<T>[] getDatas() {
        return datas;
    }

    public void setDatas(List<T>[] datas) {
        this.datas = datas;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public T[] getItems() {
        return items;
    }

    public void setItems(T[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", total=" + total +
                ", data=" + data +
                ", datas=" + Arrays.toString(datas) +
                ", item=" + item +
                ", items=" + Arrays.toString(items) +
                '}';
    }
}

