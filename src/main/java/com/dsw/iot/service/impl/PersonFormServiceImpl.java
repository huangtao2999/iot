package com.dsw.iot.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.PersonFormDoMapperExt;
import com.dsw.iot.dto.PersonFormRequest;
import com.dsw.iot.model.PersonFormDo;
import com.dsw.iot.model.PersonFormDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.FileUploadService;
import com.dsw.iot.service.PersonFormService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.vo.PersonFormVo;

@Service
public class PersonFormServiceImpl implements PersonFormService {
    @Autowired
    PersonFormDoMapperExt personFormDoMapperExt;
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    CurrentUserService currentUserService;

    /**
     * 通过人员id查找人员登记表
     *
     * @param registerId
     * @return
     */
    @Override
    public PersonFormVo selectByRegisterId(Long registerId) {
        PersonFormDo formDo = new PersonFormDo();
        PersonFormVo formVo = new PersonFormVo();
        if (null != registerId) {
            PersonFormDoExample example = new PersonFormDoExample();
            PersonFormDoExample.Criteria criteria = example.createCriteria();
            criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
            criteria.andRegisterIdEqualTo(registerId);
            example.setOrderByClause("create_time desc");
            List<PersonFormDo> list = personFormDoMapperExt.selectByExample(example);
            if (CollectionUtils.isNotEmpty(list)) {
                formDo = list.get(0);
                BeanUtils.copyProperties(formDo, formVo);
            }
            //查找附件信息
            formVo.setFormHostPoliceSign(fileUploadService.getAttachIds(formVo.getId(), CommConfig.ATTACH_TYPE.FORM_HOST_POLICE_SIGN.getType()));
            formVo.setFormAdminSign(
                    fileUploadService.getAttachIds(formVo.getId(), CommConfig.ATTACH_TYPE.FORM_ADMIN_SIGN.getType()));
            formVo.setFormCheckPoliceSign(fileUploadService.getAttachIds(formVo.getId(),
                    CommConfig.ATTACH_TYPE.FORM_CHECK_POLICE_SIGN.getType()));
            formVo.setFormWitnessSign(
                    fileUploadService.getAttachIds(formVo.getId(), CommConfig.ATTACH_TYPE.FORM_WITNESS_SIGN.getType()));
            formVo.setFormSuspectSign(
                    fileUploadService.getAttachIds(formVo.getId(), CommConfig.ATTACH_TYPE.FORM_SUSPECT_SIGN.getType()));
            formVo.setFormGoodsHostPoliceSign(fileUploadService.getAttachIds(formVo.getId(),
                    CommConfig.ATTACH_TYPE.FORM_GOODS_HOST_POLICE_SIGN.getType()));
            formVo.setFormGoodsAdminSign(fileUploadService.getAttachIds(formVo.getId(),
                    CommConfig.ATTACH_TYPE.FORM_GOODS_ADMIN_SIGN.getType()));
            formVo.setFormGoodsSuspectSign(fileUploadService.getAttachIds(formVo.getId(),
                    CommConfig.ATTACH_TYPE.FORM_GOODS_SUSPECT_SIGN.getType()));
            formVo.setFormReceiveSign(
                    fileUploadService.getAttachIds(formVo.getId(), CommConfig.ATTACH_TYPE.FORM_RECEIVE_SIGN.getType()));
            formVo.setFormGoodsHoldAdminSign(fileUploadService.getAttachIds(formVo.getId(),
                    CommConfig.ATTACH_TYPE.FORM_GOODS_HOLD_ADMIN_SIGN.getType()));
        }
        return formVo;
    }

    /**
     * 保存人员登记表
     *
     * @param personFormRequest
     * @return
     * @throws BizException
     */
    @Override
    public ActionResult<String> savePersonForm(PersonFormRequest personFormRequest) throws BizException {
        ActionResult<String> result = new ActionResult<>();
        PersonFormDo formDo = new PersonFormDo();
        if (null == personFormRequest.getId()) {
            BeanUtils.copyProperties(personFormRequest, formDo);
            DomainUtil.setCommonValueForCreate(formDo, currentUserService.getPvgInfo());
            personFormDoMapperExt.insertSelective(formDo);
            Long id = formDo.getId();
            fileUploadService.updateAttach(personFormRequest.getFormHostPoliceSign(), id, CommConfig.ATTACH_TYPE.FORM_HOST_POLICE_SIGN.getType(), CommConfig.ATTACH_TYPE.FORM_HOST_POLICE_SIGN.getName());
            fileUploadService.updateAttach(personFormRequest.getFormAdminSign(), id, CommConfig.ATTACH_TYPE.FORM_ADMIN_SIGN.getType(), CommConfig.ATTACH_TYPE.FORM_ADMIN_SIGN.getName());
            fileUploadService.updateAttach(personFormRequest.getFormCheckPoliceSign(), id, CommConfig.ATTACH_TYPE.FORM_CHECK_POLICE_SIGN.getType(), CommConfig.ATTACH_TYPE.FORM_CHECK_POLICE_SIGN.getName());
            fileUploadService.updateAttach(personFormRequest.getFormGoodsAdminSign(), id, CommConfig.ATTACH_TYPE.FORM_GOODS_ADMIN_SIGN.getType(), CommConfig.ATTACH_TYPE.FORM_GOODS_ADMIN_SIGN.getName());
            fileUploadService.updateAttach(personFormRequest.getFormGoodsHoldAdminSign(), id, CommConfig.ATTACH_TYPE.FORM_GOODS_HOLD_ADMIN_SIGN.getType(), CommConfig.ATTACH_TYPE.FORM_GOODS_HOLD_ADMIN_SIGN.getName());
            fileUploadService.updateAttach(personFormRequest.getFormGoodsSuspectSign(), id, CommConfig.ATTACH_TYPE.FORM_GOODS_SUSPECT_SIGN.getType(), CommConfig.ATTACH_TYPE.FORM_GOODS_SUSPECT_SIGN.getName());
            fileUploadService.updateAttach(personFormRequest.getFormGoodsHostPoliceSign(), id, CommConfig.ATTACH_TYPE.FORM_GOODS_HOST_POLICE_SIGN.getType(), CommConfig.ATTACH_TYPE.FORM_GOODS_HOST_POLICE_SIGN.getName());
            fileUploadService.updateAttach(personFormRequest.getFormReceiveSign(), id, CommConfig.ATTACH_TYPE.FORM_RECEIVE_SIGN.getType(), CommConfig.ATTACH_TYPE.FORM_RECEIVE_SIGN.getName());
            fileUploadService.updateAttach(personFormRequest.getFormWitnessSign(), id, CommConfig.ATTACH_TYPE.FORM_WITNESS_SIGN.getType(), CommConfig.ATTACH_TYPE.FORM_WITNESS_SIGN.getName());
            fileUploadService.updateAttach(personFormRequest.getFormSuspectSign(), id, CommConfig.ATTACH_TYPE.FORM_SUSPECT_SIGN.getType(), CommConfig.ATTACH_TYPE.FORM_SUSPECT_SIGN.getName());
            
        } else {
            throw new BizException("签名确认不可重复编辑");
        }
        result.setSuccess(true);
        result.setContent("保存完成");
        return result;
    }

}
