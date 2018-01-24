package com.dsw.iot.util;

import java.util.List;

import lombok.Data;

/**
 * 返回给前端的分页数据对象
 *
 * @author huangt
 * @create 2018-01-18 8:41
 **/
@Data
public class PageResult<T> extends BaseModel {
    private int code = 0;
    private String msg;
    private int count;
    private List<T> data;

}
