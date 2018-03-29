package com.dsw.iot.dto;

import com.dsw.iot.util.BaseDto;
import com.dsw.iot.util.PageDto;

import lombok.Data;

@Data
public class UserRequest extends BaseDto{

	private Long id;
	private String ids;
	private String account;
	private String idcard;
	private String realName;
	private String org;
	private String tel;
	private String phone;
	private String status;

	private String search;
	private String distinct;
	/**
	 * 排序
	 */
	private String orderByClause;
	/**
	 * 分页
	 */
	PageDto pager;

	/**
	 * 旧密码
	 */
	private String oldPwd;
	/**
	 * 新密码
	 */
	private String newPwd;
	/**
	 * 新密码确认密码
	 */
	private String newPwdConfirm;
}
