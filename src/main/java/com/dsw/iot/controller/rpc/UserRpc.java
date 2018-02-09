package com.dsw.iot.controller.rpc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.UserRequest;
import com.dsw.iot.model.UserDo;
import com.dsw.iot.service.UserService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.CookieUtil;
import com.dsw.iot.util.PageResult;

@RestController
@RequestMapping("/User")
public class UserRpc {

    @Autowired
    UserService userService;

    @RequestMapping("/queryPage")
    public PageResult<UserDo> queryPage(UserRequest resquest) {
        PageResult<UserDo> pageResult = userService.queryPage(resquest);
        return pageResult;
    }

    @RequestMapping("/addEdit")
    public int addEdit(UserDo user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserDo loginUserDo = CookieUtil.getUserFromCookie(request, response);
        int i = userService.addEdit(request, user, loginUserDo);
        return i;
    }

    @RequestMapping("/del")
    public int del(UserRequest param) {
        int i = userService.del(param);
        return i;
    }

    @RequestMapping("/checkAccount")
    public ActionResult checkAccount(UserRequest param) {
        return userService.checkAccount(param);
    }
}
