package com.dsw.iot.dto;

import java.sql.Timestamp;
import java.util.Date;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

@Data
public class AlarmManageRequest extends BaseDto{
    private Long id;

    
    /**
     * 人员姓名（列表显示用）
     */
    private String activePerson;

    /**
     * 处理方式（1-处理方式一；2-处理方式二；3-处理方式三；4-处理方式四）
     */
    private String handleMethod;

    /**
     * 预警时间
     */
    private Timestamp alarmTime;
    private Date alarmStartTime;//预警开始时间
    private Date alarmEndTime;//预警结束时间

    
    /**
     * 排序
     */
    private String orderByClause;
}
