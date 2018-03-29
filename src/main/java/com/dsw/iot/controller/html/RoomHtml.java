package com.dsw.iot.controller.html;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsw.iot.model.LockerDo;
import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.model.RoomPropertyDo;
import com.dsw.iot.service.LockerService;
import com.dsw.iot.service.PersonRegisterService;
import com.dsw.iot.service.RoomPropertyService;
import com.dsw.iot.service.RoomRecordService;

/**
 * 询讯问室管理页面控制器
 *
 * @author zc
 * 
 **/
@Controller
@RequestMapping("/RoomHtml")
public class RoomHtml {

    @Autowired
    private RoomPropertyService roomPropertyService;
    @Autowired
    private PersonRegisterService personRegisterService;
    @Autowired
    private RoomRecordService roomRecordService;

    @RequestMapping("/index")
    public String index(Model model) {
//        List<TpLockerDo> lockers = lockerService.queryLocker();
//        model.addAttribute("lockers", lockers);
        return "room/index";
    }


    // 新增页面
    @RequestMapping("/add")
    public String add() {
        return "room/add";
    }

    // 查看页面
    @RequestMapping("/detail")
    public String detail(Model model, Long id) {
        RoomPropertyDo roomPropertyDo = roomPropertyService.getRoomProperty(id);
        model.addAttribute("roomPropertyDo", roomPropertyDo);
        return "room/detail";
    }

    // 编辑页面
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
    	RoomPropertyDo roomPropertyDo = roomPropertyService.getRoomProperty(id);
        model.addAttribute("roomPropertyDo", roomPropertyDo);
        return "room/edit";
    }
    
    /*
     *房间关押人员 
     *roomId 房间id
     */
    @RequestMapping("/record")
    public String record(Model model, Long roomId) {
    	  model.addAttribute("roomId", roomId);
        return "room/record";
    }
    
    /*
     *等候室分配 
     *registerId 人员id
     */
    @RequestMapping("/roomwaitassign")
    public String roomwaitassign(Model model, Long registerId) {
    	PersonRegisterDo personRegisterDo=personRegisterService.getPersonRegister(registerId);
    	model.addAttribute("personRegisterDo", personRegisterDo);
        //所在的等候室
    	List<RoomPropertyDo> list=roomRecordService.getRoomPropertyByprid(registerId, "0");
    	if (CollectionUtils.isNotEmpty(list)) {
    		//如果有多条记录，取第一条记录
    		RoomPropertyDo roomPropertyDo=list.get(0);
    		model.addAttribute("roomName", roomPropertyDo.getRoomName());
    		model.addAttribute("roomId", roomPropertyDo.getId());
    	}else {
    		model.addAttribute("roomName", "");
    		model.addAttribute("roomId", "");
    	}
        return "room/roomwaitassign";
    }
    
    /*
     *询问室分配 
     *registerId 人员id
     */
    @RequestMapping("/roominquiryassign")
    public String roominquiryassign(Model model, Long registerId) {
    	PersonRegisterDo personRegisterDo=personRegisterService.getPersonRegister(registerId);
        model.addAttribute("personRegisterDo", personRegisterDo);
        //所在的等候室
    	List<RoomPropertyDo> list=roomRecordService.getRoomPropertyByprid(registerId, "0");
    	if (CollectionUtils.isNotEmpty(list)) {
    		//如果有多条记录，取第一条记录
    		RoomPropertyDo roomPropertyDo=list.get(0);
    		model.addAttribute("roomName", roomPropertyDo.getRoomName());
    		model.addAttribute("roomId", roomPropertyDo.getId());
    	}else {
    		model.addAttribute("roomName", "");
    		model.addAttribute("roomId", "");
    	}
        return "room/roominquiryassign";
    }
    
}
