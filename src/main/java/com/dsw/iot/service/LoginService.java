package com.dsw.iot.service;

import com.dsw.iot.model.UserDo;
import com.dsw.iot.util.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    /**
     * 根据用户账号和密码登录系统
     */
    public UserDo login(String userName, String passWord, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 用户退出
     *
     * @param request
     * @param response
     * @return
     */
    public ActionResult logout(HttpServletRequest request, HttpServletResponse response);
}
