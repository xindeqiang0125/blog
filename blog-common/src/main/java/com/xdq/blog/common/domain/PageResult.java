package com.xdq.blog.common.domain;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageResult {
    private int nextPage;
    private int prePage;
    private int nowPage;
    private int pageCount;
    private List result;

    public PageResult(PageInfo info) {
        this.nextPage=info.getNextPage();
        this.prePage=info.getPrePage();
        this.nowPage=info.getPageNum();
        this.pageCount=info.getNavigateLastPage();
        this.result=info.getList();
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public int getNowPage() {
        return nowPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public List getResult() {
        return result;
    }
}
