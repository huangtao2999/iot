package com.dsw.iot.util;

import lombok.Data;

/**
 * 分页对象
 *
 * @author huangt
 * @create 2018-01-18 10:00
 **/
@Data
public class Page {

    private int currentPage;
    private int pageSize;
    private int total;
    private int offset;

    public Page(int currentPage, int pageSize, int total) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = total;
        //计算偏移量
        if (currentPage == 1) {
            this.offset = 0;
        } else {
            this.offset = (currentPage - 1) * pageSize + 1;
        }
    }
}
