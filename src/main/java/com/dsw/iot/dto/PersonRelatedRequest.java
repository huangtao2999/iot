package com.dsw.iot.dto;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

@Data
public class PersonRelatedRequest extends BaseDto{
	/**
	 * 绑定的人员注册表id
	 */
	private String registerId;
	/**
	 * 证件号
	 */
	private String cardNo;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 关系
	 */
	private String relationship;
	/**
	 * 语种（翻译人专用）
	 */
	private String language;
	/**
	 * 陪同人类型（字典：1-监护人；2-翻译人；3-社区人员；4-协同办案民警等）
	 */
	private String type;
	/**
	 * 芯片编号
	 */
	private String chipNo;
	/**
	 * 排序
	 */
	private String orderByClause;
}
