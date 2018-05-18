package com.dsw.iot.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.BurnRecordDoMapperExt;
import com.dsw.iot.dto.BurnRecordRequest;
import com.dsw.iot.model.BurnRecordDo;
import com.dsw.iot.model.BurnRecordDoExample;
import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.model.UserDo;
import com.dsw.iot.service.BurnRecordService;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.UserService;
import com.dsw.iot.util.DateUtil;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.HttpClientUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.BurnRecordVo;
import com.dsw.iot.vo.BurningStateVo;

@Service
public class BurnRecordServiceImpl implements BurnRecordService {

	private final Logger logger = LogManager.getLogger(BurnRecordServiceImpl.class);
	@Autowired(required = false)
	private BurnRecordDoMapperExt burnRecordDoMapperExt;
	@Autowired
	private CurrentUserService currentUserService;
	@Value("${hardwareUrl}")
	private String hardwareServerUrl;
	@Autowired
	private UserService userService;
	
	public PageResult<BurnRecordVo> queryPage(BurnRecordRequest burnRecordRequest) {
		PageResult<BurnRecordVo> pageResult = new PageResult<>();
		
		BurnRecordDoExample example = new BurnRecordDoExample();
		BurnRecordDoExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		if (StringUtils.isNoneBlank(burnRecordRequest.getPoliceNo())) {
			criteria.andPoliceNoEqualTo(burnRecordRequest.getPoliceNo());
		}
		if (StringUtils.isNoneBlank(burnRecordRequest.getRegisterName())) {
			criteria.andRegisterNameEqualTo(burnRecordRequest.getRegisterName());
		}
		if (StringUtils.isNoneBlank(burnRecordRequest.getPoliceName())) {
			criteria.andPoliceNameEqualTo(burnRecordRequest.getPoliceName());
		}
		if (burnRecordRequest.getBurnState() != 0) {
			criteria.andStateEqualTo(burnRecordRequest.getBurnState());
		}
		int count = burnRecordDoMapperExt.countByExample(example);
        PageDto pageDto = new PageDto(burnRecordRequest.getPage(), burnRecordRequest.getLimit(), count);
        example.setPageDto(pageDto);
        example.setOrderByClause("create_time desc");
        
        List<BurnRecordDo> list = burnRecordDoMapperExt.selectByExample(example);
        List<BurnRecordVo> vos = new ArrayList<>();
        for(BurnRecordDo record : list) {
        	BurnRecordVo vo = new BurnRecordVo();
        	vo.setId(record.getId());
        	vo.setBurnPath(record.getTargetBurnPath());
        	vo.setCreateTime(record.getCreateTime());
        	vo.setState(record.getState());
        	if (record.getPercent() != null) {
        		vo.setPercent(record.getPercent());	
        	}
        	describeState(vo);
        	vo.setPoliceNo(record.getPoliceNo());
        	vo.setPoliceName(record.getPoliceName());
        	vo.setRegisterId(record.getRegisterId());
        	vo.setRegisterName(record.getRegisterName());
        	vos.add(vo);
        }
        pageResult.setData(vos);
        pageResult.setCount(count);
        return pageResult;
	}
	
	private void describeState(BurnRecordVo vo) {
		switch (vo.getState()) {
		case 1: vo.setStateName("待处理"); break;
		case 2: vo.setStateName(String.format("下载中:%s%%", vo.getPercent())); break;
		case 3: vo.setStateName("刻录中"); break;
		case 4: vo.setStateName("下载失败"); break;
		case 5: vo.setStateName("刻录失败"); break;
		case 6: vo.setStateName("已完成"); break;
		case -1: vo.setStateName("处理失败"); break;
		default:
			break;
		}
	}
	
	@Scheduled(cron = "0 */1 * * * ?")
	public void updateBurnState() {
		BurnRecordDoExample example = new BurnRecordDoExample();
		BurnRecordDoExample.Criteria criteria = example.createCriteria();
		criteria.andCreateTimeGreaterThan(DateUtil.getStartTimeOfToday());
		criteria.andStateIn(TASK_STATE.PROCESSING);
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		int total = burnRecordDoMapperExt.countByExample(example);
		int pageNum = 10;
		int startPage = 1;
		int times = total > pageNum ?  (total % pageNum > 0 ? total/pageNum + 1 : total/pageNum) : 1;
		
		PageDto pageDto = new PageDto(startPage, pageNum, total);
        example.setPageDto(pageDto);
        example.setOrderByClause("create_time desc");
		for(; startPage <= times; startPage ++) {
			pageDto.setCurrentPage(startPage);
			List<BurnRecordDo> records =  burnRecordDoMapperExt.selectByExample(example);
			for (BurnRecordDo record : records) {
				updateStateAndBurnPath(record, requestState(record.getRegisterId()));
			}
		}
        
	}
	
	private void updateStateAndBurnPath(BurnRecordDo record, BurningStateVo state) {
		if (state != null) {
			record.setState(state.getState());
			if (StringUtils.isNotEmpty(state.getBurnPath())) {
				record.setTargetBurnPath(state.getBurnPath());
			}
			if (state.getPercent() > 0) {
				record.setPercent(state.getPercent());
			}
			DomainUtil.setCommonValueForUpdate(record, currentUserService.getPvgInfo());
			burnRecordDoMapperExt.updateByPrimaryKey(record);
		}
	}
	
	private BurningStateVo requestState(long personRegisterId) {
		try {
			String result = HttpClientUtil.get(String.format("%s%s%d", hardwareServerUrl, "/BurnBiLuDiscRpc/getState.json?personRegisterId=", personRegisterId));
			JSONObject jsonResult = JSON.parseObject(result);
			if (jsonResult.getBoolean("success")) {
				return JSON.parseObject(jsonResult.getString("content"), BurningStateVo.class);
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error(String.format("request registerId %d burn state error: %s", personRegisterId, e.getMessage()));
			return null;
		}
	}
	
	@Override
	public BurnRecordDo createAndStore(PersonRegisterDo personRegisterDo) {
		BurnRecordDo burnRecordDo = new BurnRecordDo();
		burnRecordDo.setRegisterId(personRegisterDo.getId());
		burnRecordDo.setRegisterName(personRegisterDo.getName());
		if (StringUtils.isNoneEmpty(personRegisterDo.getHostPoliceNo())) {
			burnRecordDo.setPoliceNo(personRegisterDo.getHostPoliceNo());
		} else {
			burnRecordDo.setPoliceNo(personRegisterDo.getHostPoliceName());
		}
		if (StringUtils.isNoneEmpty(burnRecordDo.getPoliceNo())) {
			List<UserDo> users = userService.queryByAccount(burnRecordDo.getPoliceNo());
			if (CollectionUtils.isNotEmpty(users)) {
				burnRecordDo.setPoliceName(users.get(0).getRealName());
			}
		}
		burnRecordDo.setState(TASK_STATE.INITIAL.getValue());
		DomainUtil.setCommonValueForCreate(burnRecordDo, currentUserService.getPvgInfo());
		burnRecordDoMapperExt.insert(burnRecordDo);
		return burnRecordDo;
	}
	
	static enum TASK_STATE {
		INITIAL(1), DOWNLOADING(2), BURNING(3), DOWNLOAD_FAILED(4), BURNING_FAILED(5), DONE(6), FAILED(-1); 
		
		TASK_STATE(int value) {
			this.value = value;
		}
		
		private int value;
		
		public int getValue() {
			return this.value;
		}
		
		public static List<Integer> PROCESSING = Arrays.asList(INITIAL.getValue(), DOWNLOADING.getValue(), BURNING.getValue());
	}

	@Override
	public boolean hasBurningTaskToday(long registerId) {
		BurnRecordDoExample example = new BurnRecordDoExample();
		BurnRecordDoExample.Criteria criteria = example.createCriteria();
		criteria.andCreateTimeGreaterThan(DateUtil.getStartTimeOfToday());
		criteria.andRegisterIdEqualTo(registerId);
		criteria.andStateIn(TASK_STATE.PROCESSING);
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		return burnRecordDoMapperExt.countByExample(example) > 0;
	}

	@Override
	public void updateRecordFailed(BurnRecordDo record) {
		record.setState(TASK_STATE.FAILED.getValue());
		DomainUtil.setCommonValueForUpdate(record, currentUserService.getPvgInfo());
		burnRecordDoMapperExt.updateByPrimaryKey(record);
	}
}
