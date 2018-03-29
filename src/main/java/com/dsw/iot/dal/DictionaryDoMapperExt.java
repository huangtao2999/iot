package com.dsw.iot.dal;

import java.util.List;

import com.dsw.iot.dal.base.DictionaryDoMapper;
import com.dsw.iot.dto.DictionaryRequest;
import com.dsw.iot.model.DictionaryDo;
import com.dsw.iot.vo.DictionaryTreeVo;

public interface DictionaryDoMapperExt extends DictionaryDoMapper {
	/**
	 * 查询字典id下的子节点
	 * 动态加载树使用
	 * @param param
	 * @return
	 */
	public List<DictionaryDo> findDictByPid(DictionaryRequest param);

	/**
	 * 通过父节点，查询下级树（自带判断下级节点是目录还是菜单isparent）
	 * 
	 * @param pid
	 * @return
	 */
	public List<DictionaryTreeVo> selectByPidWithIsParent(Long pid);
}