package com.dsw.iot.controller.rpc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.RemindRequest;
import com.dsw.iot.model.RemindDo;
import com.dsw.iot.service.RemindService;
import com.dsw.iot.util.PageResult;

@RestController
@RequestMapping("/Remind")
public class RemindRpc {

	@Autowired
	RemindService remindService;

	/**
	 * 查询待办
	 *
	 * @param resquest
	 * @return
	 */
	@RequestMapping("/listRemind")
	public List<RemindDo> queryPage(Long roleId) {
		List<RemindDo> resultList = new ArrayList<RemindDo>();
		resultList = remindService.listRemind(roleId);
		return resultList;
	}

	/**
	 * 多个roleid，查询待办
	 *
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/listRemindByIds")
	public List<RemindDo> listRemindByIds(String roleIds) {
		return remindService.listRemindByIds(roleIds);
	}

	/**
	 * 分页查询代办任务
	 *
	 * @param AlarmManageRequest
	 * @return
	 */
	@RequestMapping("/queryPage")
	public PageResult<RemindDo> queryPage(RemindRequest remindRequest) {
		PageResult<RemindDo> pageResult = remindService.queryPage(remindRequest);
		return pageResult;
	}
	
	/**
	 * 根据id查询记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/getRemind")
	public RemindDo getRemind(String id) {
		System.out.println("id:"+id.replace(".html", ""));
		RemindDo remindDo = new RemindDo();
		remindDo = remindService.getRemind(Long.parseLong(id));
		System.out.println("taskIid:"+remindDo.getTaskId());
		return remindDo;
	}
}
