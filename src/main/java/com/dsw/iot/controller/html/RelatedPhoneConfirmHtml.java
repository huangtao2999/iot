package com.dsw.iot.controller.html;

import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.model.RelatedPhoneConfirmDo;
import com.dsw.iot.model.UserDo;
import com.dsw.iot.service.PersonRegisterService;
import com.dsw.iot.service.RelatedPhoneConfirmService;
import com.dsw.iot.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/RelatedPhoneConfirmHtml")
public class RelatedPhoneConfirmHtml {

    @Autowired
    RelatedPhoneConfirmService relatedPhoneConfirmService;
    @Autowired
    PersonRegisterService personRegisterService;
    @Value("${user.phone.url}")
    private String userPhoneUrl;

    /**
     * 家属通知首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        return "relatedPhoneConfirm/index";
    }

    /**
     * 新增界面
     *
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model, Long registerId, HttpServletRequest request, HttpServletResponse response) {
        PersonRegisterDo personRegisterDo = new PersonRegisterDo();
        if (null != registerId) {
            personRegisterDo = personRegisterService.getPersonRegister(registerId);
        }
        model.addAttribute("personRegisterDo", personRegisterDo);
        
        UserDo userDo = CookieUtil.getUserFromCookie(request, response);
        userDo.setPassword(null);
        model.addAttribute("userDo", userDo);
        
        return "relatedPhoneConfirm/add";
    }

    /**
     * 电话使用
     *
     * @param model
     * @return
     */
    @RequestMapping("/usePhone")
	public String usePhone(Model model, Long registerId,
			HttpServletRequest request, HttpServletResponse response) {
		// return "relatedPhoneConfirm/usePhone";
		PersonRegisterDo personRegisterDo = new PersonRegisterDo();
		if (null != registerId) {
			personRegisterDo = personRegisterService
					.getPersonRegister(registerId);
		}
		model.addAttribute("userPhoneUrl",userPhoneUrl);
		model.addAttribute("personRegisterDo", personRegisterDo);
		return "relatedPhoneConfirm/usePhone";
    }

    /**
     * 编辑页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        RelatedPhoneConfirmDo relatedPhoneConfirmDo = new RelatedPhoneConfirmDo();
        PersonRegisterDo personRegisterDo = new PersonRegisterDo();
        if (null != id) {
            relatedPhoneConfirmDo = relatedPhoneConfirmService.getRelatedPhoneConfirm(id);
        }
        if (null != relatedPhoneConfirmDo.getRegisterId()) {
            personRegisterDo = personRegisterService.getPersonRegister(relatedPhoneConfirmDo.getRegisterId());
        }
        model.addAttribute("relatedPhoneConfirmDo", relatedPhoneConfirmDo);
        model.addAttribute("personRegisterDo", personRegisterDo);
        return "relatedPhoneConfirm/edit";
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
        RelatedPhoneConfirmDo relatedPhoneConfirmDo = new RelatedPhoneConfirmDo();
        PersonRegisterDo personRegisterDo = new PersonRegisterDo();
        if (null != id) {
            relatedPhoneConfirmDo = relatedPhoneConfirmService.getRelatedPhoneConfirm(id);
        }
        if (null != relatedPhoneConfirmDo.getRegisterId()) {
            personRegisterDo = personRegisterService.getPersonRegister(relatedPhoneConfirmDo.getRegisterId());
        }
        model.addAttribute("relatedPhoneConfirmDo", relatedPhoneConfirmDo);
        model.addAttribute("personRegisterDo", personRegisterDo);
        return "relatedPhoneConfirm/detail";
    }
}
