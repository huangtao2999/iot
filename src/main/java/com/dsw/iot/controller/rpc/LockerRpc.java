package com.dsw.iot.controller.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.LockerResquest;
import com.dsw.iot.model.LockerDo;
import com.dsw.iot.service.LockerService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;

/**
 * 储物柜rpc
 *
 * @author huangt
 * @create 2018-01-18 8:40
 **/
@RestController
@RequestMapping("/Locker")
public class LockerRpc {
    @Autowired
    private LockerService lockerService;

    @RequestMapping("/queryPage")
    public PageResult<LockerDo> queryPage(LockerResquest resquest) {
        PageResult<LockerDo> pageResult = lockerService.queryPage(resquest);
        return pageResult;
    }

    @RequestMapping("addOrUpdate")
    public ActionResult<String> addOrUpdate(LockerDo lockerDo) throws BizException {
        return lockerService.addOrUpdate(lockerDo);
    }

	@RequestMapping("deleteLocker")
	public ActionResult<String> deleteLocker(String ids) throws BizException {
		return lockerService.deleteLocker(ids);
	}

    @RequestMapping("getLockerDo")
    public LockerDo getLockerDo(Long id) {
        LockerDo lockerDo = new LockerDo();
        return lockerDo;
    }

	@RequestMapping("selectLockerNo")
	public ActionResult<String> test12(LockerResquest resquest, String type) {
		return lockerService.selectLockerNo(type);
	}
}
