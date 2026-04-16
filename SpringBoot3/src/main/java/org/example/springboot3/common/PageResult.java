package org.example.springboot3.common;

import java.util.List;

public class PageResult<T> {
    private List<T> list;   //当前数据列表
    private int page;       //当前页数
    private int size;       //当前数据数
    private int total;      //总共页数

    public PageResult() {}

    public PageResult(List<T> list, int page, int size, int total) {
        this.list = list;
        this.page = page;
        this.size = size;
        this.total = total;

    }
    public List<T> getList() {return list;}
    public void setList(List<T> list) {this.list = list;}
    public int getPage() {return page;}
    public void setPage(int page) {this.page = page;}
    public int getSize() {return size;}
    public void setSize(int size) {this.size = size;}
    public int getTotal() {return total;}
    public void setTotal(int total) {this.total = total;}


}
