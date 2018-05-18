package com.dsw.iot.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dsw.iot.dto.UserRequest;
import com.dsw.iot.model.UserDo;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
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
	UserDo getUser(Long id);

	/**
	 * 新增/编辑用户信息
	 * @param record
	 * @param loginUserDo
	 * @return
	 */
	void saveUser(HttpServletRequest request, UserDo record) throws Exception;

    /**
     * 删除用户
     * @param param
     * @return
     */
	void removeUser(UserRequest param);

    /**
     * 用户注册时检查账号是否重复
     * @param param
     * @return
     */
	ActionResult<String> checkAccount(UserRequest param);

	/**
	 * 根据账号查询用户
	 */
	List<UserDo> queryByAccount(String accout);
	
	/**
	 * 更新用户密码
	 *
	 * @param userRequest
	 * @throws BizException
	 */
	void updatePwd(UserRequest userRequest) throws BizException;
}
