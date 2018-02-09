package com.dsw.iot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsw.iot.model.UserDo;

public interface LoginService {

    /**
     * 根据用户账号和密码登录系统
     */
    public UserDo login(String userName, String passWord, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
