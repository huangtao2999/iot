package com.dsw.iot.dto;

import com.dsw.iot.util.BaseDto;
import com.dsw.iot.util.Page;

import lombok.Data;

@Data
public class MenuRequest extends BaseDto{

	private Long id;
	private Long pid;
	private String text;
	private String isValid;
	private Long userId;
	private Long roleId;

	private String search;
	private String distinct;
	private String orderByClause;
	private int offset;
	private int pageSize;
}
