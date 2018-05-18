package com.dsw.iot.dto;

import java.util.List;

import lombok.Data;

/**
 * 达梦数据查询人员信息响应2
 * @author guox
 *
 */
@Data
public class DmDataDockListDto {
	
	/**
	 * 返回数据
	 */
	private List<DmDataPopulationDto> entity;
	
	/**
	 * 返回信息
	 */
	private String msgstr;
	
	/**
	 * 其它结果
	 */
	private Object otherResult;
	
	/**
	 * 状态码
	 */
	private String statusCode;
	
	/**
	 * 是否成功
	 */
	private boolean success;
	
}
