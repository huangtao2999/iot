package com.dsw.iot.dto;

import java.util.Date;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

@Data
public class IndexChartRequest extends BaseDto{

	/**
	 * 统计日期
	 */
	private Date countDate;
	/**
	 * 传入第几周，201701，17年第一周
	 */
	private String inWeek;
	/**
	 * 开始结束时间区间查询
	 */
	private String startDate;
	private String endDate;
	/**
	 * 传入第几月，201701，17年第一月
	 */
	private String inMonth;
	/**
	 * 传入年份，2017
	 */
	private String inYear;
}
