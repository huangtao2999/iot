package com.dsw.iot.controller.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.PersonRegisterRequest;
import com.dsw.iot.manager.SwytManager;
import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.service.PersonRegisterService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.PersonListClickVo;
import com.dsw.iot.vo.PersonRegisterVo;

@RestController
@RequestMapping("/PersonRegister")
public class PersonRegisterRpc {

    @Autowired
    PersonRegisterService personRegisterService;
    
    @Autowired
    SwytManager swytManager;

    /**
     * 分页查询人员信息
     *
     * @param personRegisterRequest
     * @return
     */
    @RequestMapping("/queryPage")
    public PageResult<PersonRegisterDo> queryPage(PersonRegisterRequest personRegisterRequest) {
        PageResult<PersonRegisterDo> pageResult = personRegisterService.queryPage(personRegisterRequest);
        return pageResult;
    }

    /**
     * 第一步，新增编辑人员信息，保存人员照片，身份证照片
     *
     * @param personRegisterRequest
     */
    @RequestMapping("/savePerson")
    public PersonRegisterVo savePerson(PersonRegisterRequest personRegisterRequest) throws BizException {
        return personRegisterService.savePerson(personRegisterRequest);
    }

    /**
     * 第二步，编辑人员入办案区信息，录入伤情表数据，物品表数据（前端拼接对象，这里进行插入和更新）
     *
     * @param personRegisterRequest
     */
    @RequestMapping("/updatePerson")
    public PersonRegisterVo updatePerson(@RequestBody PersonRegisterRequest personRegisterRequest) throws BizException {
        return personRegisterService.updatePerson(personRegisterRequest);
    }

    /**
     * 删除人员信息
     *
     * @param ids
     */
    @RequestMapping("/deletePerson")
    public void deletePerson(String ids) {
        personRegisterService.deletePerson(ids);
    }

    /**
     * 保存抓获信息和陪同人信息
     *
     * @param personRegisterRequest
     * @return
     * @throws BizException
     */
    @RequestMapping("/saveStepThree")
    public PersonRegisterVo saveStepThree(@RequestBody PersonRegisterRequest personRegisterRequest)
            throws BizException {
        return personRegisterService.saveStepThree(personRegisterRequest);
    }

    /**
     * 主键，查找人员基本信息，图片信息，抓获信息，伤情信息，物品信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/getPersonVo")
    public PersonRegisterVo getPersonVo(Long id) {
        return personRegisterService.getPersonAllInfo(id);
    }

    /**
     * 主键，点击列表，查找人员基本信息和流程信息
     *
     * @param id
     * @return
     * @throws BizException
     */
    @RequestMapping("/getPersonListClickVo")
    public PersonListClickVo getPersonListClickVo(Long id) throws BizException {
        return personRegisterService.getPersonListClickVo(id);
    }

    /**
     * 临时出所人员返回办案区，更新手环为激活状态
     *
     * @param personRegisterRequest
     */
    @RequestMapping("/personBack")
    public ActionResult<String> personBack(PersonRegisterRequest personRegisterRequest) throws BizException {
        return personRegisterService.personBack(personRegisterRequest);
    }

    /**
     * 自动分配等候室，并更新led主屏幕，更新等候室屏幕
     *
     * @param personRegisterRequest
     */
    @RequestMapping("/autoSetWaitingRoom")
    public ActionResult<String> autoSetWaitingRoom(Long id) throws BizException {
        return personRegisterService.autoSetWaitingRoom(id);
    }
    
    /**
     * 根据身份证号 获取常驻人员信息
     * @param cardNo
     * @param policeNo
     * @return
     * @throws BizException
     */
    @RequestMapping("/getIDCardInfo")
    public Object getPeopleByCardNo(String cardNo, String policeNo) throws BizException {
    	return swytManager.getPeopleByCardNo(cardNo, policeNo);
    }
}
