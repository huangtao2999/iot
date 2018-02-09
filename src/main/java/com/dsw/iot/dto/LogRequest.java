package com.dsw.iot.dto;

import java.util.Date;

import com.dsw.iot.util.BaseDto;

import lombok.Data;
@Data
public class LogRequest extends BaseDto{
    private Long id;
    /**
     * 对应操作模块
     */
    private String module;
    private String type;
    private String content;
    private String url;
    private String param;
    private String ip;
    private String browser;
    private String isDeleted;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;

    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
}
