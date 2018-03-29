package com.dsw.iot.controller.rpc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.UserRequest;
import com.dsw.iot.model.UserDo;
import com.dsw.iot.service.UserService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;

@RestController
@RequestMapping("/User")
public class UserRpc {

    @Autowired
    UserService userService;

	/**
	 * 分页查询用户
	 *
	 * @param resquest
	 * @return
	 */
    @RequestMapping("/queryPage")
    public PageResult<UserDo> queryPage(UserRequest resquest) {
        PageResult<UserDo> pageResult = userService.queryPage(resquest);
        return pageResult;
    }

	/**
	 * 新增/编辑用户
	 *
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public void saveUser(UserDo user, HttpServletRequest request) throws Exception {
		userService.saveUser(request, user);
    }

	/**
	 * 删除用户
	 *
	 * @param param
	 * @return
	 */
	@RequestMapping("/removeUser")
	public void removeUser(UserRequest param) {
		userService.removeUser(param);
    }

	/**
	 * 检查账号是否重复
	 *
	 * @param param
	 * @return
	 */
    @RequestMapping("/checkAccount")
	public ActionResult<String> checkAccount(UserRequest param) {
        return userService.checkAccount(param);
    }

	/**
	 * 更新密码
	 *
	 * @param param
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/updatePwd")
	public void updatePwd(UserRequest param) throws BizException {
		userService.updatePwd(param);
	}
}
