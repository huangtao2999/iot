package com.dsw.iot.controller.html;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsw.iot.service.RoomRecordService;
import com.dsw.iot.util.BizException;

@Controller
@RequestMapping("/FdblHtml")
public class FdblHtml {

	@Autowired
	private RoomRecordService roomRecordService;
	
	/**
     * 嫌疑人信息和笔录
     *
     * @param model
     * @param id
     * @return
     * @throws BizException 
     */
    @RequestMapping("/registerDetail")
    public String fbblDetail(Model model, HttpServletRequest request, HttpServletResponse response) {
    	
    	try {
    		model.addAttribute("item", roomRecordService.authRoom(request, response));
    	} catch (BizException e) {
    		model.addAttribute("message", e.getMessage());
    		return "index/error";
    	}
        return "personRegister/registerDetail";
    }
}
