package com.dsw.iot.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.UrineTestInfoDoMapperExt;
import com.dsw.iot.dto.UrineTestInfoRequest;
import com.dsw.iot.model.AttachDo;
import com.dsw.iot.model.UrineTestInfoDo;
import com.dsw.iot.model.UrineTestInfoDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.DictionaryService;
import com.dsw.iot.service.FileUploadService;
import com.dsw.iot.service.LogService;
import com.dsw.iot.service.UrineTestInfoService;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.UrineTestInfoVo;

@Service
public class UrineTestInfoServiceImpl implements UrineTestInfoService {

    @Autowired(required = false)
    UrineTestInfoDoMapperExt urineTestInfoDoMapperExt;
    @Autowired
    CurrentUserService currentUserService;
    @Autowired
    LogService logService;
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    DictionaryService dictionaryService;
    @Value("${urine.dead.time}")
    private Integer bottlebDeadtime;

    @Override
    public PageResult<UrineTestInfoDo> queryPage(UrineTestInfoRequest param) {
        // 分页返回集合
        PageResult<UrineTestInfoDo> pageResult = new PageResult<>();
        // 分页
        UrineTestInfoDoExample example = new UrineTestInfoDoExample();
        UrineTestInfoDoExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        if (StringUtils.isNotEmpty(param.getName())) {
            criteria.andNameLike("%" + param.getName() + "%");
        }
        if (StringUtils.isNotEmpty(param.getBottleb())) {
            criteria.andBottlebLike("%" + param.getBottleb() + "%");
        }
        example.setOrderByClause(" update_time desc");
        int count = urineTestInfoDoMapperExt.countByExample(example);
        PageDto page = new PageDto(param.getPage(), param.getLimit(), count);
        //获得分页参数
        example.setPageDto(page);
        List<UrineTestInfoDo> list = urineTestInfoDoMapperExt.selectByExample(example);
        // 返回集合
        pageResult.setData(list);
        pageResult.setCount(count);
        return pageResult;
    }

    @Override
    public void savePerson(UrineTestInfoRequest urineTestInfoRequest) {
        UrineTestInfoDo urineTestDo = new UrineTestInfoDo();
        BeanUtils.copyProperties(urineTestInfoRequest, urineTestDo);
        if (urineTestDo.getId() == null) {
            try {
                //计算b瓶保管到期时间
                Calendar now = Calendar.getInstance();
                now.setTime(urineTestDo.getCheckDate());
                now.add(now.DATE, bottlebDeadtime);
                Date bottle_deadtimeB = now.getTime();
                urineTestDo.setBottleDeadtimeB(bottle_deadtimeB);
            } catch (Exception e) {
                e.printStackTrace();
            }
            DomainUtil.setCommonValueForCreate(urineTestDo, currentUserService.getPvgInfo());
            urineTestInfoDoMapperExt.insertSelective(urineTestDo);

			// 写日志
			logService.insertLog(CommConfig.LOG_MODULE.URINE_TEST.getModule(), CommConfig.LOG_TYPE.ADD.getType(),
					currentUserService.getPvgInfo().getName() + "  新增了人员尿检信息：" + urineTestDo.getName());

            //保存被检测人
            fileUploadService.updateAttach(urineTestInfoRequest.getIdentified(), urineTestDo.getId(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_IDENTIFIED.getType(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_IDENTIFIED.getName());
            fileUploadService.updateAttach(urineTestInfoRequest.getCognizant1(), urineTestDo.getId(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_COGNIZANT_1.getType(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_COGNIZANT_1.getName());
            //现场检测报告
            if (urineTestDo.getReportType().equals("2") || urineTestDo.getReportType().equals("3")) {
                //检测人2  办案单位负责人
                fileUploadService.updateAttach(urineTestInfoRequest.getCognizant2(), urineTestDo.getId(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_COGNIZANT_2.getType(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_COGNIZANT_2.getName());
                fileUploadService.updateAttach(urineTestInfoRequest.getOrgManager(), urineTestDo.getId(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_ORG_MANAGER.getType(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_ORG_MANAGER.getName());
            }
            //保存拍照附件
            fileUploadService.updateAttach(urineTestInfoRequest.getPhotoFileIds(), urineTestDo.getId(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_IMG.getType(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_IMG.getName());
        } else {
            //保存被检测人
            if (urineTestInfoRequest.getIdentified() != null) {
                fileUploadService.updateAttach(urineTestInfoRequest.getIdentified(), urineTestDo.getId(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_IDENTIFIED.getType(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_IDENTIFIED.getName());
            }
            if (urineTestInfoRequest.getCognizant1() != null) {
                fileUploadService.updateAttach(urineTestInfoRequest.getCognizant1(), urineTestDo.getId(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_COGNIZANT_1.getType(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_COGNIZANT_1.getName());
            }
            //现场检测报告
            if (urineTestDo.getReportType().equals("2") || urineTestDo.getReportType().equals("3")) {
                //检测人2  办案单位负责人
                if (urineTestInfoRequest.getCognizant2() != null) {
                    fileUploadService.updateAttach(urineTestInfoRequest.getCognizant2(), urineTestDo.getId(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_COGNIZANT_2.getType(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_COGNIZANT_2.getName());
                }
                if (urineTestInfoRequest.getOrgManager() != null) {
                    fileUploadService.updateAttach(urineTestInfoRequest.getOrgManager(), urineTestDo.getId(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_ORG_MANAGER.getType(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_ORG_MANAGER.getName());
                }
            }
            //保存拍照附件
            fileUploadService.updateAttach(urineTestInfoRequest.getPhotoFileIds(), urineTestDo.getId(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_IMG.getType(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_IMG.getName());
            DomainUtil.setCommonValueForUpdate(urineTestDo, currentUserService.getPvgInfo());
            urineTestInfoDoMapperExt.updateByPrimaryKey(urineTestDo);

			// 写日志
			logService.insertLog(CommConfig.LOG_MODULE.URINE_TEST.getModule(), CommConfig.LOG_TYPE.ADD.getType(),
					currentUserService.getPvgInfo().getName() + "  编辑了人员尿检信息：" + urineTestDo.getName());
        }
    }

    @Override
    public void deletePerson(String ids) {
        // TODO Auto-generated method stub

    }

    @Override
    public UrineTestInfoVo getUrineTestInfoById(Long id) {
        UrineTestInfoVo urineTestInfoVo = new UrineTestInfoVo();
        UrineTestInfoDo urineTestInfoDo = urineTestInfoDoMapperExt.selectByPrimaryKey(id);
        BeanUtils.copyProperties(urineTestInfoDo, urineTestInfoVo);
        //查询签字附件
        String identified = fileUploadService.getAttachIds(urineTestInfoDo.getId(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_IDENTIFIED.getType());
        urineTestInfoVo.setIdentified(identified);
        String cognizant1 = fileUploadService.getAttachIds(urineTestInfoDo.getId(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_COGNIZANT_1.getType());
        urineTestInfoVo.setCognizant1(cognizant1);
        //现场检测报告
        if (urineTestInfoVo.getReportType().equals("2") || urineTestInfoVo.getReportType().equals("3")) {
            //检测人2  办案单位负责人
            String orgManage = fileUploadService.getAttachIds(urineTestInfoDo.getId(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_ORG_MANAGER.getType());
            urineTestInfoVo.setOrgManager(orgManage);
            String cognizant2 = fileUploadService.getAttachIds(urineTestInfoDo.getId(), CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_COGNIZANT_2.getType());
            urineTestInfoVo.setCognizant2(cognizant2);
        }
        return urineTestInfoVo;
    }

    @Override
    public PageResult<UrineTestInfoDo> getOverDeadtimeB() {
        PageResult<UrineTestInfoDo> pageResult = new PageResult<>();
        // TODO Auto-generated method stub
        List<UrineTestInfoDo> list = urineTestInfoDoMapperExt.getOverDeadtimeB();
        pageResult.setData(list);
        pageResult.setCount(list.size());

		// 写日志
		logService.insertLog(CommConfig.LOG_MODULE.URINE_TEST.getModule(), CommConfig.LOG_TYPE.QUERY.getType(),
				currentUserService.getPvgInfo().getName() + "  查询了尿检 B 瓶超期数据");
        return pageResult;
    }

    @Override
    public int updateDeadtimeBStatus(UrineTestInfoRequest parms) {
        int result = urineTestInfoDoMapperExt.updateDeadtimeBStatus(parms);
		// 写日志
		logService.insertLog(CommConfig.LOG_MODULE.URINE_TEST.getModule(), CommConfig.LOG_TYPE.ADD.getType(),
				currentUserService.getPvgInfo().getName() + "  处理了过期的 B 瓶数据");
        return result;
    }

    @Override
    public List<AttachDo> getImgAttach(Long id) {
        return fileUploadService.listAttach(id, CommConfig.ATTACH_TYPE.URINE_TEST_INFO_SIGN_IMG.getType());
    }

    @Override
    public void updateByPrimaryKeySelective(UrineTestInfoDo urineTestDo) {
        urineTestInfoDoMapperExt.updateByPrimaryKeySelective(urineTestDo);
    }

	/**
	 * 根据主键id查询数据
	 */
	@Override
	public UrineTestInfoDo getUrineTestInfoDoById(Long id) {
		if (null != id) {
			return urineTestInfoDoMapperExt.selectByPrimaryKey(id);
		} else {
			return null;
		}
	}
}
