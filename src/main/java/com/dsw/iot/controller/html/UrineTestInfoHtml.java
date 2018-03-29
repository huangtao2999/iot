package com.dsw.iot.controller.html;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsw.iot.dto.UrineTestInfoRequest;
import com.dsw.iot.model.AttachDo;
import com.dsw.iot.model.UrineTestInfoDo;
import com.dsw.iot.service.UrineTestInfoService;
import com.dsw.iot.vo.UrineTestInfoVo;

@Controller
@RequestMapping("/UrineTestInfoHtml")
public class UrineTestInfoHtml {

    @Autowired
    UrineTestInfoService urineTestInfoService;

    /**
     * 人员登记首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        return "urineTestInfo/index";
    }

    /**
     * 新增面
     *
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model) {
        return "urineTestInfo/add";
    }

    @RequestMapping("/addPart1")
    public String addPart1(Model model) {
        return "urineTestInfo/addPart1";
    }

    /**
     * 编辑页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model, HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        UrineTestInfoDo UrineTestDo = new UrineTestInfoDo();
        UrineTestInfoVo urineTestInfoVo = new UrineTestInfoVo();
        if (null != id) {
            urineTestInfoVo = urineTestInfoService.getUrineTestInfoById(Long.valueOf(id));
        }
        model.addAttribute("urineTestInfoRequest", urineTestInfoVo);
        return "urineTestInfo/edit";
    }

    /**
     * 打印页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/print")
    public String print(Model model, HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        UrineTestInfoVo urineTestInfoVo = new UrineTestInfoVo();
        UrineTestInfoRequest urineTestInfoRequest = new UrineTestInfoRequest();
        if (null != id) {
            urineTestInfoVo = urineTestInfoService.getUrineTestInfoById(Long.valueOf(id));
        }
        model.addAttribute("urineTestInfoVo", urineTestInfoVo);
        return "urineTestInfo/print";
    }

    /**
     * 查看尿检照片
     *
     * @param model
     * @return
     */
    @RequestMapping("/printImg")
    public String printImg(Model model, Long id) {
        List<AttachDo> attachDos = null;
		UrineTestInfoDo urineTestInfoDo = null;
        if (null != id) {
            attachDos = urineTestInfoService.getImgAttach(id);
			urineTestInfoDo = urineTestInfoService.getUrineTestInfoDoById(id);
        }
        model.addAttribute("attachDos", attachDos);
		model.addAttribute("urineTestInfoDo", urineTestInfoDo);
        return "urineTestInfo/printImg";
    }

    /**
     * 查看详情
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/detailDialog")
    public String detailDialog(Model model, Long id) {
//        UrineTestInfoDo UrineTestDo = new UrineTestInfoDo();
        UrineTestInfoVo urineTestInfoVo = new UrineTestInfoVo();
        if (null != id) {
            urineTestInfoVo = urineTestInfoService.getUrineTestInfoById(id);
        }
        model.addAttribute("item", urineTestInfoVo);
        return "urineTestInfo/detail";
    }
}
