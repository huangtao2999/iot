package com.dsw.iot.service;

import java.util.List;

import com.dsw.iot.dto.DictionaryRequest;
import com.dsw.iot.model.DictionaryDo;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.vo.DictionaryTreeVo;

public interface DictionaryService {

    /**
     * 单条查询
     * @param id
     * @return
     */
    DictionaryDo selectByPrimaryKey(Long id);
    /**
     * 查询所有字典
     * @param example
     * @return
     */
    List<DictionaryTreeVo> selectAllDictionary();

	/**
	 * 通过pid查询下级节点
	 *
	 * @param example
	 * @return
	 */
	List<DictionaryTreeVo> selectDictionaryByPid(Long pid);

	/**
	 * 新增/更新
	 *
	 * @param request
	 * @param dictionaryDo
	 * @param loginUserDo
	 * @return
	 */
	int saveDictionary(DictionaryDo dictionaryDo);

	/**
	 * 单条查询，返回树集合
	 *
	 * @param id
	 * @return
	 */
	DictionaryTreeVo selectTreeByPrimaryKey(DictionaryRequest dictionaryRequest);

	/**
	 * 级联删除节点及节点下的树
	 *
	 * @param dictionaryRequest
	 * @throws BizException
	 */
	void deleteTreeNodeCascade(DictionaryRequest dictionaryRequest) throws BizException;

	/**
	 * 通过type和code快速定位层级的树（传入几级就默认查几级，不传默认把子集都查出来）
	 *
	 * @param dictionaryRequest
	 * @return
	 */
	List<DictionaryTreeVo> selectByTypeAndCodeAndLay(DictionaryRequest dictionaryRequest) throws BizException;

	/**
	 * 通过code查询出combo下拉框（单层）
	 *
	 * @param dictionaryRequest
	 * @return
	 */
	List<DictionaryDo> queryComboList(DictionaryRequest dictionaryRequest) throws BizException;

	/**
	 * 通过type查询出所有下级，单层列表出来，供table里格式化字典用
	 *
	 * @param dictionaryRequest
	 * @return
	 */
	List<DictionaryDo> queryTableListByType(DictionaryRequest dictionaryRequest) throws BizException;

	/**
	 * 判断编码是否有重复（新增根节点时，code和type是一样的，新增子节点时，code和type不一样）
	 *
	 * @param dictionaryRequest
	 * @return
	 */
	ActionResult<String> checkDicCode(DictionaryRequest dictionaryRequest);

	/**
	 * 通过pid查询下级节点
	 *
	 * @param example
	 * @return
	 */
	List<DictionaryTreeVo> selectDictionaryByPidIsParent(Long pid);
}
