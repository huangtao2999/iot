package com.dsw.iot.controller.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.OutConfirmRequest;
import com.dsw.iot.model.OutConfirmDo;
import com.dsw.iot.service.OutConfirmService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;

@RestController
@RequestMapping("/OutConfirm")
public class OutConfirmRpc {

	@Autowired
	OutConfirmService outConfirmService;

	/**
	 * 分页查询家属通知信息
	 *
	 * @param RelatedPhoneConfirmRequest
	 * @return
	 */
	@RequestMapping("/queryPage")
	public PageResult<OutConfirmDo> queryPage(OutConfirmRequest outConfirmRequest) {
		PageResult<OutConfirmDo> pageResult = outConfirmService.queryPage(outConfirmRequest);
		return pageResult;
	}

	/**
	 * 通过id查询家属通知详情
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getOutConfirm")
	public ActionResult getOutConfirm(OutConfirmRequest param){
		ActionResult res = new ActionResult<>();
		OutConfirmDo outConfirmDo = outConfirmService.getOutConfirm(param.getId());
		res.setSuccess(true);
		res.setContent(outConfirmDo);
		return res;
	}


	/**
	 * 人员出所审核
	 *
	 * @param outConfirmVo
	 * @throws BizException
	 */
	@RequestMapping(value = "/saveOutConfirm", method = RequestMethod.POST)
	public void saveOutConfirm(OutConfirmRequest outConfirmRequest) throws BizException {
		outConfirmService.saveOutConfirm(outConfirmRequest);
	}

	/**
	 * 通过人员id和状态获得人员出所审批单集合--人员出所取物
	 *
	 * @param param
	 * @return
	 */
	@RequestMapping("/getOutConfirmByRid")
	public List<OutConfirmDo> getOutConfirmByRid(Long registerId, String status, String isHistory) {
		return outConfirmService.getOutConfirmByRid(registerId, status, isHistory);
	}
}
