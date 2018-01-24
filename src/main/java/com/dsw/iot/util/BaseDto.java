package com.dsw.iot.util;

import lombok.Data;

/**
 * 请求参数基类
 *
 * @author huangt
 * @create 2018-01-18 9:52
 **/
@Data
public class BaseDto {

    private int page = 1;
    private int limit = 10;
}
