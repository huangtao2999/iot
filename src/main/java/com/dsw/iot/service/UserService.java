package com.dsw.iot.service;

import javax.servlet.http.HttpServletRequest;

import com.dsw.iot.dto.UserRequest;
import com.dsw.iot.model.UserDo;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.PageResult;

public interface UserService {
	/**
	 * 分页查询用户
	 * @param param
	 * @return
	 */
	public PageResult<UserDo> queryPage(UserRequest param);
	/**
	 * id查询记录
	 * @param id
	 * @return
	 */
	UserDo selectByPrimaryKey(Long id);
	/**
	 * 新增/编辑用户信息
	 * @param record
	 * @param loginUserDo
	 * @return
	 */
    int addEdit(HttpServletRequest request, UserDo record, UserDo loginUserDo)throws Exception ;
    /**
     * 删除用户
     * @param param
     * @return
     */
    int del(UserRequest param);
    /**
     * 用户注册时检查账号是否重复
     * @param param
     * @return
     */
	ActionResult<String> checkAccount(UserRequest param);
}
