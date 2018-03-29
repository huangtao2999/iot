package com.dsw.iot.vo;

import com.dsw.iot.util.BaseModel;

import lombok.Data;

@Data
public class IndexChartVo extends BaseModel{
	//sql返回的map
	/**
	 * 统计数
	 */
	private int cnt;
	/**
	 * 入办案区时间
	 */
	private String inTime;
	/**
	 * 统计数据的，周几
	 */
	private int inWeek;
	/**
	 * 到案方式类型
	 */
	private String inType;

	//返回给页面的map
	/**
	 * 周一~周日
	 */
	private String weeks;
	/**
	 * 数据，对应weeks;
	 */
	private String datas;
	/**
	 * 类型
	 */
	private String types;
	/**
	 * 名称
	 */
	private String names;
}
