package com.dsw.iot.service.impl;

import com.dsw.iot.dal.UserDoMapperExt;
import com.dsw.iot.model.UserDo;
import com.dsw.iot.model.UserDoExample;
import com.dsw.iot.service.LoginService;
import com.dsw.iot.util.*;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired(required = false)
    private UserDoMapperExt userDoMapperExt;

    @Override
    public UserDo login(String userName, String passWord, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StringUtils.isEmpty(userName)) {
            throw new BizException("用户名为空!");
        }
        if (StringUtils.isEmpty(passWord)) {
            throw new BizException("密码为空!");
        }

        passWord = MD5Util.MD5(passWord);
        UserDoExample example = new UserDoExample();
        example.createCriteria().andAccountEqualTo(userName).andPasswordEqualTo(passWord);
        List<UserDo> list = userDoMapperExt.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("用户名或密码错误!");
        }
        UserDo userDo = list.get(0);
        CookieUtil.addUserToCookie(request, response, userDo);
        return userDo;
    }
}
