package com.dsw.iot.controller.html;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsw.iot.model.AlarmManageDo;
import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.model.UserDo;
import com.dsw.iot.service.AlarmManageService;
import com.dsw.iot.service.PersonRegisterService;
import com.dsw.iot.util.CookieUtil;


@Controller
@RequestMapping("/AlarmManageHtml")
public class AlarmManageHtml {

	@Autowired
	AlarmManageService alarmManageService;
	@Autowired
	PersonRegisterService personRegisterService;
	
	/**
	 * 预警管理首页
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		return "alarmManage/index";
	}

	/**
	 * 新增界面
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model,Long registerId,HttpServletRequest request,HttpServletResponse response) {
		PersonRegisterDo personRegisterDo = new PersonRegisterDo();
		if (null != registerId) {
			personRegisterDo = personRegisterService.getPersonRegister(registerId);
		}
		model.addAttribute("personRegisterDo", personRegisterDo);
		
		UserDo userDo = CookieUtil.getUserFromCookie(request, response);
        userDo.setPassword(null);
        model.addAttribute("userDo", userDo);
		return "alarmManage/add";
	}

	/**
	 * 编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(Model model, Long id, HttpServletRequest request, HttpServletResponse response){
		AlarmManageDo alarmManageDo = new AlarmManageDo();
		PersonRegisterDo personRegisterDo = new PersonRegisterDo();
		if(null != id){
			alarmManageDo = alarmManageService.getAlarmManage(id);
		}
		if (null != alarmManageDo.getRegisterId()) {
			personRegisterDo = personRegisterService.getPersonRegister(alarmManageDo.getRegisterId());
		}
		model.addAttribute("alarmManageDo", alarmManageDo);
		model.addAttribute("personRegisterDo", personRegisterDo);
		
		UserDo userDo = CookieUtil.getUserFromCookie(request, response);
        userDo.setPassword(null);
        model.addAttribute("userDo", userDo);
		return "alarmManage/edit";
	}

	/**
	 * 查看详情
	 *
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(Model model, Long id) {
		AlarmManageDo alarmManageDo = new AlarmManageDo();
		PersonRegisterDo personRegisterDo = new PersonRegisterDo();
		if (null != id) {
			alarmManageDo = alarmManageService.getAlarmManage(id);
		}
		if (null != alarmManageDo.getRegisterId()) {
			personRegisterDo = personRegisterService.getPersonRegister(alarmManageDo.getRegisterId());
		}
		model.addAttribute("alarmManageDo", alarmManageDo);
		model.addAttribute("personRegisterDo", personRegisterDo);
		return "alarmManage/detail";
	}
}
