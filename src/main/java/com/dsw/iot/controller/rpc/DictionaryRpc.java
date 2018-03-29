package com.dsw.iot.controller.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.DictionaryRequest;
import com.dsw.iot.model.DictionaryDo;
import com.dsw.iot.service.DictionaryService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.vo.DictionaryTreeVo;

@RestController
@RequestMapping("/Dictionary")
public class DictionaryRpc {

	@Autowired
	DictionaryService dictionaryService;

	/**
	 * 新增
	 *
	 * @param dictionaryDo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveDictionary")
	public int saveDictionary(DictionaryDo dictionaryDo)
			throws Exception {
		int i = dictionaryService.saveDictionary(dictionaryDo);
		return i;
	}

	/**
	 * 删除节点（级联删除以下节点）
	 *
	 * @param model
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@RequestMapping("/deleteTreeNodeCascade")
	public void deleteTreeNodeCascade(DictionaryRequest dictionaryRequest) throws BizException {
		dictionaryService.deleteTreeNodeCascade(dictionaryRequest);
	}

	/**
	 * 通过pid查询下一级树
	 *
	 * @param pid
	 * @return
	 */
	@RequestMapping("/queryByPid")
	public List<DictionaryTreeVo> queryByPid(Long pid) {
		return dictionaryService.selectDictionaryByPid(pid);
	}

	/**
	 * 刷新当前节点(id查记录，pid查有没有下级isParent)
	 *
	 * @param pid
	 * @return
	 */
	@RequestMapping("/queryNodeById")
	public DictionaryTreeVo queryNodeById(DictionaryRequest dictionaryRequest) {
		return dictionaryService.selectTreeByPrimaryKey(dictionaryRequest);
	}

	/**
	 * 通过type和code快速定位层级的树（传入几级就默认查几级，不传默认把子集都查出来）
	 *
	 * @param DictionaryRequest
	 * @return
	 */
	@RequestMapping("/queryByTypeAndCodeAndLay")
	public List<DictionaryTreeVo> queryByTypeAndCodeAndLay(DictionaryRequest dictionaryRequest) throws BizException {
		return dictionaryService.selectByTypeAndCodeAndLay(dictionaryRequest);
	}

	/**
	 * 通过code查询出combo下拉框（单层） 修改版
	 *
	 * @param DictionaryRequest
	 * @return
	 */
	@RequestMapping("/queryComboList")
	public List<DictionaryDo> queryComboList(DictionaryRequest dictionaryRequest) throws BizException {
		return dictionaryService.queryComboList(dictionaryRequest);
	}

	/**
	 * 通过type查询出所有下级，单层列表出来，供table里格式化字典用
	 *
	 * @param DictionaryRequest
	 * @return
	 */
	@RequestMapping("/queryTableListByType")
	public List<DictionaryDo> queryTableListByType(DictionaryRequest dictionaryRequest) throws BizException {
		return dictionaryService.queryTableListByType(dictionaryRequest);
	}

	/**
	 * 判断编码是否有重复（新增根节点时，code和type是一样的，新增子节点时，code和type不一样）
	 *
	 * @param code
	 * @return
	 */
	@RequestMapping("/checkDicCode")
	public ActionResult<String> checkDicCode(DictionaryRequest dictionaryRequest) {
		return dictionaryService.checkDicCode(dictionaryRequest);
	}

	/**
	 * 通过pid查询下一级树,自带判断是否为文件夹
	 *
	 * @param pid
	 * @return
	 */
	@RequestMapping("/queryByPidIsParent")
	public List<DictionaryTreeVo> queryByPidIsParent(Long pid) {
		return dictionaryService.selectDictionaryByPidIsParent(pid);
	}
}
