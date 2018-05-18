package com.dsw.iot.dto;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

@Data
public class SwytJjRequest extends BaseDto{
	
	/**
	 * 接警编号 
	 */
    private String jjbh;
	/**
	 * 警情编号
	 */
    private String jqbh;
	/**
	 * 报警形式 
	 */
    private String bjxs;
	/**
	 * 警情类别 
	 */
    private String jqlb;
	/**
	 * 发现地点 
	 */
    private String fxdd;
	/**
	 * 接警人 
	 */
    private String jjr;
	/**
	 * 接警时间 
	 */
    private String jjsj;
	/**
	 * 接警单位
	 */
    private String jjdw;
	/**
	 * 处警标识 
	 */
    private String cjbs;
	/**
	 * 报警人姓名 
	 */
    private String bjrxm;
	/**
	 * 报警人性别
	 */
    private String bjrxb;
	/**
	 * 报警人出生日期 
	 */
    private String bjrcsrq;
	/**
	 * 报警人证件号码
	 */
    private String bjrsfzh;
	/**
	 * 接警时间 开始
	 */	
    private String jjsjStart;
    /**
	 * 接警时间 结束
	 */
    private String jjsjEnd;
    
    
}
