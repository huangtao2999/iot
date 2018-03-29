package com.dsw.iot.controller.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsw.iot.model.AttachDo;
import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.service.PersonFormService;
import com.dsw.iot.service.PersonRegisterService;
import com.dsw.iot.vo.PersonFormVo;
import com.dsw.iot.vo.PersonRegisterVo;

@Controller
@RequestMapping("/PersonRegisterHtml")
public class PersonRegisterHtml {

    @Autowired
    PersonRegisterService personRegisterService;
	@Autowired
	PersonFormService personFormService;

    /**
     * 人员登记首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        return "personRegister/index";
    }

    /**
     * 新增界面
     *
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model) {
        return "personRegister/add";
    }

    /**
     * 编辑页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        PersonRegisterDo personRegisterDo = new PersonRegisterDo();
        if (null != id) {
            personRegisterDo = personRegisterService.getPersonRegister(id);
        }
        model.addAttribute("item", personRegisterDo);
        return "personRegister/edit";
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
        PersonRegisterDo personRegisterDo = new PersonRegisterDo();
        if (null != id) {
            personRegisterDo = personRegisterService.getPersonRegister(id);
        }
        model.addAttribute("item", personRegisterDo);
        return "personRegister/detail";
    }

    /**
     * 点击流程图里的，跳转到第几步
     *
     * @param model
     * @param step
     * @return
     */
    @RequestMapping("/stepTo")
    public String stepTo(Model model, String step, Long registerId, String cardNo, String name) {
        model.addAttribute("step", step);
        model.addAttribute("registerId", registerId);
        model.addAttribute("cardNo", cardNo);
        model.addAttribute("name", name);
        return "personRegister/add";
    }

    /**
     * 点击 人员登记表，显示台账
     *
     * @param model
     * @return
     */
    @RequestMapping("/registerForm")
	public String registerForm(Model model, Long registerId) {
        PersonRegisterVo personRegisterVo = new PersonRegisterVo();
		PersonFormVo personFormVo = new PersonFormVo();
		if (null != registerId) {
			personRegisterVo = personRegisterService.getPersonRegisterForm(registerId);
			personFormVo = personFormService.selectByRegisterId(registerId);
        }
        model.addAttribute("item", personRegisterVo);
		model.addAttribute("form", personFormVo);
        return "personRegister/registerForm";
    }

    /**
     * 点击人脸识别的人员登记，跳到新增页面
     *
     * @param model
     * @param step
     * @return
     */
    @RequestMapping("/faceTo")
    public String faceTo(Model model, Long registerId, String cardNo, String name, String faceUrl) {
        model.addAttribute("cardNo", cardNo);
        model.addAttribute("name", name);
        // 下载照片
        AttachDo attachDo = personRegisterService.downloadFaceImgToAttachFolder(faceUrl);
        model.addAttribute("attachDo", attachDo);
        return "personRegister/add";
    }

}
