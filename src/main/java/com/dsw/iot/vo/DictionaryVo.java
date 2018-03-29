package com.dsw.iot.vo;

import com.dsw.iot.util.BaseModel;

import lombok.Data;

/**
 * 字典对象
 *
 * @author huangt
 * @create 2018-01-27 18:34
 **/
@Data
public class DictionaryVo extends BaseModel{

    private Long id;
    private String code;
    private String name;
}
