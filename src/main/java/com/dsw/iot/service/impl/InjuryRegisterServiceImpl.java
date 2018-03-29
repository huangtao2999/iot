package com.dsw.iot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.InjuryRegisterDoMapperExt;
import com.dsw.iot.dto.InjuryRegisterRequest;
import com.dsw.iot.model.InjuryRegisterDo;
import com.dsw.iot.model.InjuryRegisterDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.FileUploadService;
import com.dsw.iot.service.InjuryRegisterService;
import com.dsw.iot.util.DomainUtil;

@Service
public class InjuryRegisterServiceImpl implements InjuryRegisterService {

	@Autowired
	InjuryRegisterDoMapperExt injuryRegisterDoMapperExt;
	@Autowired
	CurrentUserService currentUserService;
	@Autowired
	FileUploadService fileUploadService;

	/**
	 * 保存伤情信息
	 */
	@Override
	@Transactional
	public List<InjuryRegisterRequest> saveInjuryInfo(List<InjuryRegisterRequest> injuryRegisterList, Long registerId) {
		List<InjuryRegisterRequest> resList = new ArrayList<>();
		String removeIds = "";
		if (CollectionUtils.isNotEmpty(injuryRegisterList)) {
			removeIds = injuryRegisterList.get(0).getRemoveIds();
			for (InjuryRegisterRequest iRequest : injuryRegisterList) {
				InjuryRegisterDo iDo = new InjuryRegisterDo();
				BeanUtils.copyProperties(iRequest, iDo);
				if (null == iDo.getId()) {
					// 新增
					iDo.setRegisterId(registerId);
					DomainUtil.setCommonValueForCreate(iDo, currentUserService.getPvgInfo());
					injuryRegisterDoMapperExt.insertSelective(iDo);
				} else {
					// 编辑
					DomainUtil.setCommonValueForUpdate(iDo, currentUserService.getPvgInfo());
					injuryRegisterDoMapperExt.updateByPrimaryKeySelective(iDo);
				}
				// 更新附件
				fileUploadService.updateAttach(iRequest.getSourceIds(), iDo.getId(),
						CommConfig.ATTACH_TYPE.INJURY_REGISTER_IMGS.getType(),
						CommConfig.ATTACH_TYPE.INJURY_REGISTER_IMGS.getName());

				// 返回
				BeanUtils.copyProperties(injuryRegisterDoMapperExt.selectByPrimaryKey(iDo.getId()), iRequest);
				resList.add(iRequest);// 添加更新后的信息，返回前端
			}
		}
		// 删除已经删除整行的记录
		if (StringUtils.isNotBlank(removeIds)) {
			String[] removeId = removeIds.split(",");
			for (String id : removeId) {
				if (StringUtils.isNotBlank(id)) {
					InjuryRegisterDo injuryRegisterDo = new InjuryRegisterDo();
					injuryRegisterDo.setId(Long.parseLong(id));
					// 删除伤情表记录
					injuryRegisterDoMapperExt.deleteByPrimaryKey(injuryRegisterDo);
				}
			}
		}

		return resList;
	}

	/**
	 * 查询人员的伤情信息
	 */
	@Override
	public List<InjuryRegisterRequest> getInjuryInfoByRid(Long registerId) {
		List<InjuryRegisterRequest> resList = new ArrayList<>();
		// 拼接条件
		InjuryRegisterDoExample example = new InjuryRegisterDoExample();
		InjuryRegisterDoExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		criteria.andRegisterIdEqualTo(registerId);
		List<InjuryRegisterDo> list = injuryRegisterDoMapperExt.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {
			for (InjuryRegisterDo injuryRegisterDo : list) {
				String sourceIds = "";
				InjuryRegisterRequest iRequest = new InjuryRegisterRequest();
				BeanUtils.copyProperties(injuryRegisterDo, iRequest);
				// 查询附件，拼接sourceIds
				sourceIds = fileUploadService.getAttachIds(iRequest.getId(),
						CommConfig.ATTACH_TYPE.INJURY_REGISTER_IMGS.getType());
				iRequest.setSourceIds(sourceIds);
				resList.add(iRequest);
			}
		}
		return resList;
	}

}
