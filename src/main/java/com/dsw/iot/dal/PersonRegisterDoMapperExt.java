package com.dsw.iot.dal;

import java.util.List;

import com.dsw.iot.dal.base.PersonRegisterDoMapper;
import com.dsw.iot.dto.IndexChartRequest;
import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.vo.IndexChartVo;

public interface PersonRegisterDoMapperExt extends PersonRegisterDoMapper {

	/**
	 * 查询首页延期留置超期人员
	 *
	 * @return
	 */
	List<PersonRegisterDo> selectDelayAlarmList();

	/**
	 * 获得单个延期留置预警
	 *
	 * @param delayHour
	 * @return
	 */
	List<PersonRegisterDo> selectDelayAlarmSingle(int delayHour);

	/**
	 * 查询一周办案区人数统计
	 */
	List<IndexChartVo> selectOneWeekCount(IndexChartRequest request);

	/**
	 * 日期区间到案方式统计
	 */
	List<IndexChartVo> selectInTypeDateBetweenCount(IndexChartRequest request);

	/**
	 * 周到案方式统计
	 */
	List<IndexChartVo> selectInTypeWeekCount(IndexChartRequest request);

	/**
	 * 月到案方式统计
	 */
	List<IndexChartVo> selectInTypeMonthCount(IndexChartRequest request);

	/**
	 * 年到案方式统计
	 */
	List<IndexChartVo> selectInTypeYearCount(IndexChartRequest request);

	/**
	 * 日期区间人员类型统计
	 */
	List<IndexChartVo> selectPersonTypeDateBetweenCount(IndexChartRequest request);

	/**
	 * 周人员类型person_type统计
	 */
	List<IndexChartVo> selectPersonTypeWeekCount(IndexChartRequest request);

	/**
	 * 月人员类型person_type统计
	 */
	List<IndexChartVo> selectPersonTypeMonthCount(IndexChartRequest request);

	/**
	 * 年人员类型person_type统计
	 */
	List<IndexChartVo> selectPersonTypeYearCount(IndexChartRequest request);
}