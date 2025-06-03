package org.example.emarketmall.utils;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 分页工具类
 * 原理是通过apache dbutils
 * @author: april
 * @date: 2022年05月16日 22:49
 */
public class PageUtils<T> {
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 页容量，每页行数
     */
    private Integer pageSize;
    /**
     * 数据总行数
     */
    private Integer rows;
    /**
     * 数据源
     */
    private List<T> datas = new ArrayList<>();
    /**
     * 页起始索引
     */
    private int index;
    /**
     * 前一夜页码
     */
    private int prevPageNum;
    /**
     * 下一页页码
     */
    private int nextPageNum;

    private int first = 1;

    private int last;

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPrevPageNum() {
        return prevPageNum;
    }

    public void setPrevPageNum(int prevPageNum) {
        this.prevPageNum = prevPageNum;
    }

    public int getNextPageNum() {
        return nextPageNum;
    }

    public void setNextPageNum(int nextPageNum) {
        this.nextPageNum = nextPageNum;
    }

    public PageUtils(Integer rows) {
        this.pageNum = 1;
        this.pageSize = 10;
        this.rows=rows;
        init();
    }

    public PageUtils(Integer pageNum, Integer pageSize, Integer rows) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.rows = rows;
        init();
    }

    private void init() {
        totalPage = rows % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;
        last = totalPage;
        //pageNum have to larger 0,pageNum=1
        prevPageNum = pageNum - 1 <= 0 ? 1 : pageNum - 1;
        nextPageNum = pageNum + 1 >= totalPage ? totalPage : pageNum + 1;
        index = (pageNum - 1) * pageSize;
    }


}
