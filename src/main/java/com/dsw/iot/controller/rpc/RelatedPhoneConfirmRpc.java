package com.dsw.iot.controller.rpc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.AlarmManageRequest;
import com.dsw.iot.dto.MenuRequest;
import com.dsw.iot.dto.RelatedPhoneConfirmRequest;
import com.dsw.iot.model.AlarmManageDo;
import com.dsw.iot.model.RelatedPhoneConfirmDo;
import com.dsw.iot.service.RelatedPhoneConfirmService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.RelatedPhoneConfirmVo;

@RestController
@RequestMapping("/RelatedPhoneConfirm")
public class RelatedPhoneConfirmRpc {

	@Autowired
	RelatedPhoneConfirmService relatedPhoneConfirmService;
	
	/**
	 * 分页查询家属通知信息
	 *
	 * @param RelatedPhoneConfirmRequest
	 * @return
	 */
	@RequestMapping("/queryPage")
	public PageResult<RelatedPhoneConfirmVo> queryPage(RelatedPhoneConfirmRequest relatedPhoneConfirmRequest) {
		PageResult<RelatedPhoneConfirmVo> pageResult = relatedPhoneConfirmService.queryPage(relatedPhoneConfirmRequest);
		return pageResult;
	}

	/**
	 * 通过id查询家属通知详情
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getRelatedPhoneConfirm")
	public ActionResult getRelatedPhoneConfirm(RelatedPhoneConfirmRequest param){
		ActionResult res = new ActionResult<>();
		RelatedPhoneConfirmDo relatedPhoneConfirmDo = relatedPhoneConfirmService.getRelatedPhoneConfirm(param.getId());
		res.setSuccess(true);
		res.setContent(relatedPhoneConfirmDo);
		return res;
	}


	/**
	 * 家属通知审核
	 *
	 * @param relatedPhoneConfirmDo
	 */
	@RequestMapping(value = "/saveRelatedPhoneConfirm", method = RequestMethod.POST)
	public void saveRelatedPhoneConfirm(RelatedPhoneConfirmDo relatedPhoneConfirmDo,HttpServletRequest request) {
		relatedPhoneConfirmService.saveRelatedPhoneConfirm(relatedPhoneConfirmDo,request);
	}

}
