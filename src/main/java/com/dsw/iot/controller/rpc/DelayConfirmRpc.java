package com.dsw.iot.controller.rpc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.DelayConfirmRequest;
import com.dsw.iot.model.DelayConfirmDo;
import com.dsw.iot.service.DelayConfirmService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.DelayConfirmVo;

@RestController
@RequestMapping("/DelayConfirm")
public class DelayConfirmRpc {
	@Autowired
	DelayConfirmService delayConfirmService;
	
	/**
	 * 分页查询延期置留信息
	 *
	 * @param RelatedPhoneConfirmRequest
	 * @return
	 */
	@RequestMapping("/queryPage")
	public PageResult<DelayConfirmDo> queryPage(DelayConfirmRequest delayConfirmRequest) {
		PageResult<DelayConfirmDo> pageResult = delayConfirmService.queryPage(delayConfirmRequest);
		return pageResult;
	}

	/**
	 * 通过id查询延期置留详情
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getDelayConfirmDoById")
	public ActionResult getDelayConfirmDoById(DelayConfirmRequest param){
		ActionResult res = new ActionResult<>();
		DelayConfirmDo delayConfirmDo = delayConfirmService.getDelayConfirm(param.getId());
		res.setSuccess(true);
		res.setContent(delayConfirmDo);
		return res;
	}


	/**
	 * 延期置留保存
	 *
	 * @param delayConfirmVo
	 */
	@RequestMapping(value = "/saveDelayConfirm", method = RequestMethod.POST)
	public void saveDelayConfirm(DelayConfirmVo delayConfirmVo,HttpServletRequest request) {
		delayConfirmService.saveDelayConfirm(delayConfirmVo,request);
	}

	/**
	 * 延期置留审核
	 *
	 * @param delayConfirmDo
	 */
	@RequestMapping("/checkDelayConfirm")
	public void checkDelayConfirm(DelayConfirmDo delayConfirmDo) {
		delayConfirmService.checkDelayConfirm(delayConfirmDo);
	}
}
