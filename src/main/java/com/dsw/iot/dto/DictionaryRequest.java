package com.dsw.iot.dto;

import com.dsw.iot.util.BaseDto;

import lombok.Data;
@Data
public class DictionaryRequest extends BaseDto{
	/**
	 * 字典表主键id
	 */
	private Long id;
	/**
	 * 父节点id
	 */
	private Long pid;
	/**
	 * 字典唯一编码
	 */
	private String code;
	/**
	 * 字典类型
	 */
	private String type;
	/**
	 * 字典名
	 */
	private String name;
	/**
	 * 是否系统参数
	 */
	private String isSystem;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 查询字典的层级，为空时默认全部查
	 */
	private int lay;
}
