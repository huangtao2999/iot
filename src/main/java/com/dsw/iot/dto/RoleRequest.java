package com.dsw.iot.dto;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

@Data
public class RoleRequest extends BaseDto{

	private Long id;
	private Long userId;
	private String ids;
	private String roleName;

	private String search;
	private String distinct;
	private String orderByClause;
	private int offset;
	private int pageSize;
}
