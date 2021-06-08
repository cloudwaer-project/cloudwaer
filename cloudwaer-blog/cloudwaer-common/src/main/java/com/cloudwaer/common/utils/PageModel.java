package com.cloudwaer.common.utils;

import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @ClassName PageModel
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/7 12:24
 * @Version 1.0
 **/
public class PageModel<T> {
    private static final long serialVersionUID = 1L;

    //public static final int DEFAULT_PAGE = 1;
    //public static final int DEFAULT_PAGESIZE = 5;
    //private int currentPage = DEFAULT_PAGE;// 当前页
    //private int pageSize = DEFAULT_PAGESIZE;// 每页显示条数

    private long total;// 总记录数
    private List<T> rows;// 分页数据
    private int count; // 返回数据条数

    /**
     * 构造函数，初始化list和count
     * @param
     */

    public PageModel(){

    }
    public PageModel(List<T> lists){
        rows = lists;
        count = ObjectUtils.isEmpty(lists) ? 0 : lists.size();
    }

    public PageModel(List<T> lists, long total){
        rows = lists;
        count = ObjectUtils.isEmpty(lists) ? 0 : lists.size();
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getCount() {
        if (!ObjectUtils.isEmpty(rows)) {
            return rows.size();
        }
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
