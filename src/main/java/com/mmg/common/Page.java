package com.mmg.common;

import java.util.List;

/**
 * Created by yj on 2017/6/16.
 */
public class Page<T> {
    private int curPage = 1;
    private int pageSize = 5;
    private List<T> pageList;
    private int startRow;
    private int count;

    public int getCurPage() {
        return this.curPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getStartRow() {
        return (this.curPage - 1) * this.pageSize;
    }

    public int getCount() {
        return this.count;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getPageList() {
        return this.pageList;
    }
}
