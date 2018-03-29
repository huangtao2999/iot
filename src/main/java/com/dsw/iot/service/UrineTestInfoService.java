package com.dsw.iot.service;

import java.util.List;

import com.dsw.iot.dto.UrineTestInfoRequest;
import com.dsw.iot.model.AttachDo;
import com.dsw.iot.model.UrineTestInfoDo;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.UrineTestInfoVo;

public interface UrineTestInfoService {
    /**
     * luming
     * 分页查询尿检主表
     *
     * @param param
     * @return
     */
    PageResult<UrineTestInfoDo> queryPage(UrineTestInfoRequest param);

    /**
     * 新增编辑人员信息
     */
    void savePerson(UrineTestInfoRequest urineTestInfoRequest);

    void updateByPrimaryKeySelective(UrineTestInfoDo urineTestDo);

    /**
     * 通过主键批量删除
     */
    void deletePerson(String ids);

    /**
     * 通过主键查单条记录
     */
    UrineTestInfoVo getUrineTestInfoById(Long id);

    /**
     * 查询B瓶过去的相关人员
     */
    PageResult<UrineTestInfoDo> getOverDeadtimeB();

    /**
     * 更新B瓶过去的相关人员
     */
    int updateDeadtimeBStatus(UrineTestInfoRequest parms);

    List<AttachDo> getImgAttach(Long id);

	/**
	 * 根据主键id查询数据
	 * 
	 * @param id
	 * @return
	 */
	public UrineTestInfoDo getUrineTestInfoDoById(Long id);
}
