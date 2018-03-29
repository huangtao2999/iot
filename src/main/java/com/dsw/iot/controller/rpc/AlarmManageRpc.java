package com.dsw.iot.controller.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.AlarmManageRequest;
import com.dsw.iot.model.AlarmManageDo;
import com.dsw.iot.service.AlarmManageService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;

import ch.qos.logback.core.joran.action.Action;

@RestController
@RequestMapping("/AlarmManage")
public class AlarmManageRpc {

	@Autowired
	AlarmManageService alarmManageService;

	/**
	 * 分页查询预警信息
	 *
	 * @param alarmManageRequest
	 * @return
	 */
	@RequestMapping("/queryPage")
	public PageResult<AlarmManageDo> queryPage(AlarmManageRequest alarmManageRequest) {
		PageResult<AlarmManageDo> pageResult = alarmManageService.queryPage(alarmManageRequest);
		return pageResult;
	}

	/**
	 * 通过id查询预警详情
	 *
	 * @param param
	 * @return
	 */
	@RequestMapping("/getAlarmManage")
	public ActionResult getAlarmManage(AlarmManageDo param) {
		ActionResult res = new ActionResult<>();
		AlarmManageDo alarmManageDo = alarmManageService.getAlarmManage(param.getId());
		res.setSuccess(true);
		res.setContent(alarmManageDo);
		return res;
	}

	/**
	 * 编辑预警信息
	 *
	 * @param alarmManageDo
	 */
	@RequestMapping("/saveAlarmManage")
	public void saveAlarmManage(AlarmManageDo alarmManageDo) {
		alarmManageService.saveAlarmManage(alarmManageDo);
	}

	/**
	 * 删除预警信息
	 */
	@RequestMapping("/deleteAlarmManage")
	public void deleteAlarmManage(String ids) throws BizException {
		alarmManageService.deleteAlarmManage(ids);
	}

	/**
	 * 关闭预警
	 */
	@RequestMapping("/closeAlarmManage")
	public ActionResult<String> closeAlarmManage() {
		ActionResult<String> result = new ActionResult<>();
		result = alarmManageService.closeAlarmManage();
		return result;
	}

	/**
	 * 打开预警
	 */
	@RequestMapping("/openAlarmManage")
	public ActionResult<String> openAlarmManage() {
		ActionResult<String> result = new ActionResult<>();
		result = alarmManageService.openAlarmManage();
		return result;
	}

	/**
	 * 
	 * @return int 0是关 1是开
	 */
	@RequestMapping("/checkAlarmManage")
	public ActionResult<String> checkAlarmManage() {
		ActionResult<String> result = new ActionResult<>();
		result = alarmManageService.checkAlarmManage();
		return result;
	}

}
