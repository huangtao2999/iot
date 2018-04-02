package com.dsw.iot.controller.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.UrineTestInfoRequest;
import com.dsw.iot.model.UrineTestInfoDo;
import com.dsw.iot.service.DictionaryService;
import com.dsw.iot.service.PersonRegisterService;
import com.dsw.iot.service.UrineTestInfoService;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.PersonRegisterVo;

@RestController
@RequestMapping("/UrineTestInfo")
public class UrineTestInfoRpc {

    @Autowired
    UrineTestInfoService urineTestInfoService;
    @Autowired
    PersonRegisterService personRegisterService;
    @Autowired
    DictionaryService dictionaryService;

    /**
     * 分页查询人员信息
     *
     * @param UrineTestInfoRequest
     * @return
     */
    @RequestMapping("/queryPage")
    public PageResult<UrineTestInfoDo> queryPage(UrineTestInfoRequest urineTestInfoRequest) {
        PageResult<UrineTestInfoDo> pageResult = urineTestInfoService.queryPage(urineTestInfoRequest);
        return pageResult;
    }

    /**
     * 通过手环编号查询记录
     *
     * @param UrineTestInfoDo
     */
    @RequestMapping(value = "/PersonRegister", method = RequestMethod.POST)
    public PersonRegisterVo PersonRegister(String braceletNo) throws Exception {
        PersonRegisterVo pageResult = personRegisterService.getPersonRegister(braceletNo);
        return pageResult;
    }

    /**
	 * 新增编辑尿检信息
	 *
	 * @param urineTestInfoRequest
	 */
    @RequestMapping(value = "/savePerson", method = RequestMethod.POST)
    public void savePerson(UrineTestInfoRequest urineTestInfoRequest) throws Exception {
        urineTestInfoService.savePerson(urineTestInfoRequest);
    }


    /**
     * 删除人员信息
     *
     * @param ids
     */
    @RequestMapping("/deletePerson")
    public void deletePerson(String ids) {
        urineTestInfoService.deletePerson(ids);
    }


    /**
     * 查询B瓶过期数据
     *
     * @param UrineTestInfoRequest
     * @return
     */
    @RequestMapping("/getOverDeadtimeB")
    public PageResult<UrineTestInfoDo> getOverDeadtimeB() {
        PageResult<UrineTestInfoDo> pageResult = urineTestInfoService.getOverDeadtimeB();
        return pageResult;
    }

	/**
	 * 处理B瓶过期数据
	 *
	 */
    @RequestMapping("/updateDeadtimeBStatus")
    public int updateDeadtimeBStatus(UrineTestInfoRequest parms) {
        int pageResult = urineTestInfoService.updateDeadtimeBStatus(parms);
        return pageResult;
    }
}
