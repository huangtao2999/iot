package com.dsw.iot.service;

import java.util.List;

import com.dsw.iot.dto.IndexChartRequest;
import com.dsw.iot.util.BizException;
import com.dsw.iot.vo.IndexChartVo;
import com.dsw.iot.vo.IndexDelayVo;

public interface IndexService {

	/**
	 * 获得延期留置预警
	 *
	 * @return
	 */
	List<IndexDelayVo> getDalayList();

	/**
	 * 获得延期留置预警单个
	 *
	 * @return
	 */
	List<IndexDelayVo> getDelayAlarmSingle(int delayHour);

	/**
	 * 获得7天一周的入办案区人数统计
	 *
	 * @param request
	 * @return
	 */
	IndexChartVo getSevenDayCount(IndexChartRequest request);

	/**
	 * 日期区间统计[a,b)
	 *
	 * @param request
	 * @return
	 * @throws BizException
	 */
	IndexChartVo getInTypeDateBetweenCount(IndexChartRequest request) throws BizException;

	/**
	 * 周到案方式统计
	 *
	 * @param request
	 * @return
	 * @throws BizException
	 */
	IndexChartVo getInTypeWeekCount(IndexChartRequest request) throws BizException;

	/**
	 * 月到案方式统计
	 */
	IndexChartVo getInTypeMonthCount(IndexChartRequest request) throws BizException;

	/**
	 * 年到案方式统计
	 */
	IndexChartVo getInTypeYearCount(IndexChartRequest request) throws BizException;

	/**
	 * 人员类型日期区间统计[a,b)
	 *
	 * @param request
	 * @return
	 * @throws BizException
	 */
	IndexChartVo getPersonTypeDateBetweenCount(IndexChartRequest request) throws BizException;

	/**
	 * 周人员类型person_type统计
	 *
	 * @param request
	 * @return
	 * @throws BizException
	 */
	IndexChartVo getPersonTypeWeekCount(IndexChartRequest request) throws BizException;

	/**
	 * 月人员类型person_type统计
	 */
	IndexChartVo getPersonTypeMonthCount(IndexChartRequest request) throws BizException;

	/**
	 * 年人员类型person_type统计
	 */
	IndexChartVo getPersonTypeYearCount(IndexChartRequest request) throws BizException;

}
