package com.dsw.iot.service;

import com.dsw.iot.dto.PersonRegisterRequest;
import com.dsw.iot.model.AttachDo;
import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.PersonListClickVo;
import com.dsw.iot.vo.PersonRegisterVo;

public interface PersonRegisterService {

    /**
     * 分页查询注册表主表
     *
     * @param param
     * @return
     */
    PageResult<PersonRegisterDo> queryPage(PersonRegisterRequest param);

    /**
     * 新增编辑人员基本信息
     *
     * @param personRegisterDo
     * @return
     */
    PersonRegisterVo savePerson(PersonRegisterRequest personRegisterRequest) throws BizException;

    /**
     * 编辑人员信息
     *
     * @param personRegisterDo
     * @return
     */
    PersonRegisterVo updatePerson(PersonRegisterRequest personRegisterRequest) throws BizException;

    /**
     * 通过主键批量删除
     */
    void deletePerson(String ids);

    /**
     * 通过主键查单条记录
     */
    PersonRegisterDo getPersonRegister(Long id);

    /**
     * 通过主键查找人员所有信息
     *
     * @param id
     * @return
     */
    PersonRegisterVo getPersonAllInfo(Long id);

    /**
     * 保存抓获信息和陪同人信息
     *
     * @param personRegisterRequest
     * @return
     * @throws BizException
     */
    PersonRegisterVo saveStepThree(PersonRegisterRequest personRegisterRequest) throws BizException;


    /**
     * 通过手环编号查询记录
     */
    PersonRegisterVo getPersonRegister(String braceletNo);

    /**
     * 更新签名领取人签名信息 id 人员主键id value 为签名信息
     */
    void updatePersonRegisterRsign(Long id, String value);

    /**
     * 主键，点击列表，查找人员基本信息和流程信息
     *
     * @param id
     * @return
     * @throws BizException
     */
    PersonListClickVo getPersonListClickVo(Long id) throws BizException;

    /**
     * 更新人员状态
     *
     * @param personRegisterRequest
     * @return
     */
    boolean updatePersonStatus(Long id, String status) throws BizException;

    /**
     * 临时出所人员返回办案区
     *
     * @param personRegisterRequest
     * @return
     */
    ActionResult<String> personBack(PersonRegisterRequest personRegisterRequest) throws BizException;

    /**
     * 人员台账接口
     *
     * @param id
     * @return
     */
    PersonRegisterVo getPersonRegisterForm(Long id);

    /**
     * 把人脸的图片，下载下来，再上传到附件表
     *
     * @param faceUrl
     * @return
     */
    AttachDo downloadFaceImgToAttachFolder(String faceUrl);

    /**
     * 自动分配等候室，并更新led主屏幕，更新等候室屏幕
     *
     * @param personRegisterRequest
     * @return
     * @throws BizException
     */
    ActionResult<String> autoSetWaitingRoom(Long id) throws BizException;

    /**
     * 通过主键查单条记录,和附件头像信息
     */
    PersonRegisterRequest getPersonRegisterInfo(Long id);
}
