package com.dsw.iot.service;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import com.dsw.iot.dto.LogRequest;
import com.dsw.iot.model.LogDo;
import com.dsw.iot.util.PageResult;

public interface LogService {
	/**
	 * 分页查询用户
	 *
	 * @param param
	 * @return
	 * @throws ParseException
	 */
	public PageResult<LogDo> queryPage(LogRequest param) throws ParseException;

	/**
	 * 通过主键查找单条记录
	 *
	 * @param id
	 * @return
	 */
	LogDo selectByPrimaryKey(Long id);

	/**
	 * 通用日志插入接口
	 *
	 * @param module
	 *            常量中定义模块
	 * @param type
	 *            操作类型（常量中定义）
	 * @param content
	 *            操作内容
	 * @return
	 */
	void insertLog(HttpServletRequest request, String module, String type, String content);

	/**
	 * 通用日志插入接口
	 *
	 * @param module
	 *            常量中定义模块
	 * @param type
	 *            操作类型（常量中定义）
	 * @param content
	 *            操作内容
	 * @return
	 */
	void insertLog(String module, String type, String content);
}
