package com.cloudwaer.common.dto;

/**
 * @ClassName PageVo
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/7 11:40
 * @Version 1.0
 **/
public class PageVo {
    //当前页
    private int current;
    //每页数
    private int pageSize;
    private int start;
    private int end;
    private String sortOrder;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
