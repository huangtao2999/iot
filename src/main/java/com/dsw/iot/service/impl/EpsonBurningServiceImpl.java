package com.dsw.iot.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.dsw.iot.dto.BurnRequest;
import com.dsw.iot.manager.FdblManager;
import com.dsw.iot.model.BurnRecordDo;
import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.service.BurnRecordService;
import com.dsw.iot.service.DictionaryService;
import com.dsw.iot.service.EpsonBurningService;
import com.dsw.iot.service.PersonRegisterService;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.DateUtil;
import com.dsw.iot.util.HttpClientUtil;
import com.dsw.iot.vo.BlContentVo;

@Service
public class EpsonBurningServiceImpl implements EpsonBurningService {

	private final Logger logger = LogManager.getLogger(EpsonBurningServiceImpl.class);
	@Autowired
	private FdblManager fdblManager;
	@Autowired
	private PersonRegisterService personRegisterService;
	@Value("${hardwareUrl}")
	private String burningServerUrl;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private BurnRecordService burnRecordService;
	@Autowired
	private Environment environment;
	
	@Override
	public void sendBurningRequest(long registerId) throws BizException {
		synchronized (String.valueOf(registerId).intern()) {
			if (burnRecordService.hasBurningTaskToday(registerId)) {
				throw new BizException("该登记人有刻录任务正在进行，请完成后再试。");
			} 
			BlContentVo contentVo = fdblManager.getBlContentToPdfPath(registerId);
			
			BurnRequest request = new BurnRequest();
			setCaptureConfig(request, contentVo.getRoomNo());
			PersonRegisterDo personRegister = personRegisterService.getPersonRegister(registerId);
			
			BurnRecordDo record = burnRecordService.createAndStore(personRegister);
			try{
				HttpClientUtil.postBody(String.format("%s%s", burningServerUrl, "/BurnBiLuDiscRpc/burnDisc.json"), setBlInfo(contentVo, request, personRegister));
			} catch (Exception e) {
				burnRecordService.updateRecordFailed(record);
				logger.error(String.format("请求硬件服务失败:%s, %s", burningServerUrl, e.getMessage()));
				throw new BizException("刻录失败");
			}
		}
	}
	
	private BurnRequest setBlInfo(BlContentVo contentVo, BurnRequest request, PersonRegisterDo personRegister) {
		request.setCaseName(dictionaryService.queryByTypeAndCode("PERSON_REGISTER_MODEL", personRegister.getInReason()).getName());
		request.setName(personRegister.getName());
		request.setDownloadUrl(contentVo.getFilePath());
		request.setEndTime(DateUtil.formatDate(contentVo.getJssj()));
		request.setStartTime(DateUtil.formatDate(contentVo.getKssj()));
		request.setPersonRegisterId(contentVo.getRyId());
		return request;
	}
	
	private void setCaptureConfig(BurnRequest request, String roomNo) {
		request.setNvrIp(environment.getRequiredProperty(String.format("%s.capture.ip", roomNo)));
		request.setNvrChannel(Long.parseLong(environment.getRequiredProperty(String.format("%s.capture.channel", roomNo))));
		request.setNvrPort(Integer.parseInt(environment.getRequiredProperty(String.format("%s.capture.port", roomNo))));
		request.setUserName(environment.getRequiredProperty(String.format("%s.capture.username", roomNo)));
		request.setPassword(environment.getRequiredProperty(String.format("%s.capture.password", roomNo)));
	}
	
	@Override
	public String getBurningState(long registerId) {
		return HttpClientUtil.get(String.format("%s%s", burningServerUrl, "/BurnBiLuDiscRpc/getState.json?personRegisterId=" + registerId));
	}

}
