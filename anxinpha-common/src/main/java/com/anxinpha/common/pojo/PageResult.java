package com.anxinpha.common.pojo;

import java.util.List;

/**
 * @author 尹硕硕
 * @description 页面结果对象 总条数 页面数 对象集
 **/
public class PageResult<T> {

    private Long total;
    private Integer pageTotal;
    private List<T> data;

    public PageResult() {
    }

    public PageResult(Long total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public PageResult(Long total, Integer pageTotal, List<T> data) {
        this.total = total;
        this.pageTotal = pageTotal;
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
