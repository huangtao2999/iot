package com.dsw.iot.dto;

import lombok.Data;

import com.dsw.iot.util.BaseDto;

@Data
public class RoleRequest extends BaseDto{

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 多个id拼接
	 */
	private String ids;
	/**
	 * 角色名
	 */
	private String roleName;
	/**
	 * 超级角色
	 */
	private Integer isSuper;
	/**
	 * 模糊查询参数
	 */
	private String search;
	/**
	 * 排序
	 */
	private String orderByClause;
}
