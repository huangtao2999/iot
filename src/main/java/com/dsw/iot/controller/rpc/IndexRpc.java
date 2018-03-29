package com.dsw.iot.controller.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.IndexChartRequest;
import com.dsw.iot.service.IndexService;
import com.dsw.iot.util.BizException;
import com.dsw.iot.vo.IndexChartVo;
import com.dsw.iot.vo.IndexDelayVo;

/**
 * 首页接口汇总
 *
 * @author zhang
 *
 */
@RestController
@RequestMapping("/Index")
public class IndexRpc {

	@Autowired
	IndexService indexService;

	/**
	 * 首页延期留置预警
	 *
	 * @return
	 */
	@RequestMapping("/getDelayAlarm")
	public List<IndexDelayVo> getDelayAlarm() {
		return indexService.getDalayList();
	}

	/**
	 * 首页延期留置预警，单个的查询
	 *
	 * @return
	 */
	@RequestMapping("/getDelayAlarmSingle")
	public List<IndexDelayVo> getDelayAlarmSingle(int delayHour) {
		return indexService.getDelayAlarmSingle(delayHour);
	}

	/**
	 * 最近7天入办案区人数（统计主表）
	 */
	@RequestMapping("/getSevenDayCount")
	public IndexChartVo getSevenDayCount(IndexChartRequest request) {
		return indexService.getSevenDayCount(request);
	}

	/**
	 * 到案方式日期区间统计[a,b)
	 *
	 * @param request
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getInTypeDateBetweenCount")
	public IndexChartVo getInTypeDateBetweenCount(IndexChartRequest request) throws BizException {
		return indexService.getInTypeDateBetweenCount(request);
	}

	/**
	 * 周到案方式统计
	 *
	 * @throws BizException
	 */
	@RequestMapping("/getInTypeWeekCount")
	public IndexChartVo getInTypeWeekCount(IndexChartRequest request) throws BizException {
		return indexService.getInTypeWeekCount(request);
	}

	/**
	 * 月到案方式统计
	 */
	@RequestMapping("/getInTypeMonthCount")
	public IndexChartVo getInTypeMonthCount(IndexChartRequest request) throws BizException {
		return indexService.getInTypeMonthCount(request);
	}

	/**
	 * 年到案方式统计
	 */
	@RequestMapping("/getInTypeYearCount")
	public IndexChartVo getInTypeYearCount(IndexChartRequest request) throws BizException {
		return indexService.getInTypeYearCount(request);
	}

	/**
	 * 人员类型区间统计 [a,b)
	 *
	 * @param request
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/getPersonTypeDateBetweenCount")
	public IndexChartVo getPersonTypeDateBetweenCount(IndexChartRequest request) throws BizException {
		return indexService.getPersonTypeDateBetweenCount(request);
	}

	/**
	 * 周人员类型person_type统计
	 *
	 * @throws BizException
	 */
	@RequestMapping("/getPersonTypeWeekCount")
	public IndexChartVo getPersonTypeWeekCount(IndexChartRequest request) throws BizException {
		return indexService.getPersonTypeWeekCount(request);
	}

	/**
	 * 月人员类型person_type统计
	 */
	@RequestMapping("/getPersonTypeMonthCount")
	public IndexChartVo getPersonTypeMonthCount(IndexChartRequest request) throws BizException {
		return indexService.getPersonTypeMonthCount(request);
	}

	/**
	 * 年人员类型person_type统计
	 */
	@RequestMapping("/getPersonTypeYearCount")
	public IndexChartVo getPersonTypeYearCount(IndexChartRequest request) throws BizException {
		return indexService.getPersonTypeYearCount(request);
	}
}
