package com.dsw.iot.dto;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

/**
 * locker查询请求头
 *
 * @author huangt
 * @create 2018-01-18 9:49
 **/
@Data
public class LockerResquest extends BaseDto {

    private String lockerNo;
}
