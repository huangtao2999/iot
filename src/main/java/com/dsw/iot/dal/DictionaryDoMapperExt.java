package com.dsw.iot.dal;

import java.util.List;

import com.dsw.iot.dal.base.DictionaryDoMapper;
import com.dsw.iot.dto.DictionaryRequest;
import com.dsw.iot.model.DictionaryDo;

public interface DictionaryDoMapperExt extends DictionaryDoMapper {
	/**
	 * 查询字典id下的子节点
	 * 动态加载树使用
	 * @param param
	 * @return
	 */
	public List<DictionaryDo> findDictByPid(DictionaryRequest param);
}