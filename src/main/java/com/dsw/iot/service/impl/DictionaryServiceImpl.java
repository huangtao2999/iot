package com.dsw.iot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.DictionaryDoMapperExt;
import com.dsw.iot.dto.DictionaryRequest;
import com.dsw.iot.model.DictionaryDo;
import com.dsw.iot.model.DictionaryDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.DictionaryService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.TreeUtil;
import com.dsw.iot.vo.DictionaryTreeVo;

@Service
public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	DictionaryDoMapperExt dictionaryDoMapperExt;
	@Autowired
	CurrentUserService currentUserService;

	/**
	 * 通过主键查询
	 */
	@Override
	public DictionaryDo selectByPrimaryKey(Long id) {
		return dictionaryDoMapperExt.selectByPrimaryKey(id);
	}

	/**
	 * 查询所有字典，拼接成children树
	 */
	@Override
	public List<DictionaryTreeVo> selectAllDictionary() {
		//定义参数
		DictionaryDoExample example = new DictionaryDoExample();
		DictionaryDoExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		example.setOrderByClause("sort asc, id asc");
		//查询
		List<DictionaryDo> list = dictionaryDoMapperExt.selectByExample(example);
		//新建树集合
		List<DictionaryTreeVo> dictionaryTreeVos = new ArrayList<DictionaryTreeVo>();
		//放到树的集合中
		for (int m = 0; m < list.size(); m++) {
        	DictionaryTreeVo entity = new DictionaryTreeVo();
        	BeanUtils.copyProperties(list.get(m), entity);
        	dictionaryTreeVos.add(entity);
        }
        //拼接树
		if (CollectionUtils.isNotEmpty(dictionaryTreeVos)) {
			dictionaryTreeVos = TreeUtil.createDictionaryTree(dictionaryTreeVos);
		}
		return dictionaryTreeVos;
	}

	/**
	 * 通过pid查询下级节点
	 */
	@Override
	public List<DictionaryTreeVo> selectDictionaryByPid(Long pid) {
		DictionaryDoExample dictionaryDoExample = new DictionaryDoExample();
		DictionaryDoExample.Criteria criteria = dictionaryDoExample.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		criteria.andPidEqualTo(pid);
		dictionaryDoExample.setOrderByClause("sort asc, id asc");
		// 定义树集合
		List<DictionaryTreeVo> dictionaryTreeVos = new ArrayList<>();
		// 获得返回数据（只有一层）
		List<DictionaryDo> dictionaryDos = dictionaryDoMapperExt.selectByExample(dictionaryDoExample);
		// 转换到树集合中
		if (CollectionUtils.isNotEmpty(dictionaryDos)) {
			for (int m = 0; m < dictionaryDos.size(); m++) {
				DictionaryTreeVo dictionaryTreeVo = new DictionaryTreeVo();
				BeanUtils.copyProperties(dictionaryDos.get(m), dictionaryTreeVo);
				dictionaryTreeVo.setIsParent(true);
				dictionaryTreeVos.add(dictionaryTreeVo);
			}
		}
		return dictionaryTreeVos;
	}

	/**
	 * 新增/更新
	 */
	@Override
	@Transactional
	public int saveDictionary(DictionaryDo dictionaryDo) {
		int i = 0;
		if (null == dictionaryDo.getId()) {
			// 新增
			DomainUtil.setCommonValueForCreate(dictionaryDo, currentUserService.getPvgInfo());
			i = dictionaryDoMapperExt.insertSelective(dictionaryDo);
		} else {
			// 编辑
			DomainUtil.setCommonValueForUpdate(dictionaryDo, currentUserService.getPvgInfo());
			i = dictionaryDoMapperExt.updateByPrimaryKeySelective(dictionaryDo);
		}
		return i;
	}

	/**
	 * 单条查询，返回树集合
	 */
	@Override
	public DictionaryTreeVo selectTreeByPrimaryKey(DictionaryRequest dictionaryRequest) {
		// 查询当前节点的记录
		DictionaryDo dictionaryDo = dictionaryDoMapperExt.selectByPrimaryKey(dictionaryRequest.getId());
		DictionaryTreeVo dictionaryTreeVo = new DictionaryTreeVo();
		BeanUtils.copyProperties(dictionaryDo, dictionaryTreeVo);

		// 查询是否有下级
		DictionaryDoExample dictionaryDoExample = new DictionaryDoExample();
		DictionaryDoExample.Criteria criteria = dictionaryDoExample.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		criteria.andPidEqualTo(dictionaryRequest.getPid());
		dictionaryDoExample.setOrderByClause("sort asc, id asc");
		// 获得是否有下级
		List<DictionaryDo> dictionaryDos = dictionaryDoMapperExt.selectByExample(dictionaryDoExample);
		if (CollectionUtils.isNotEmpty(dictionaryDos)) {
			dictionaryTreeVo.setIsParent(true);
		} else {
			dictionaryTreeVo.setIsParent(false);
		}

		return dictionaryTreeVo;
	}

	/**
	 * 级联删除节点及节点下的树
	 *
	 * @throws BizException
	 */
	@Override
	public void deleteTreeNodeCascade(DictionaryRequest dictionaryRequest) throws BizException {
		// 定义参数
		DictionaryDoExample example = new DictionaryDoExample();
		DictionaryDoExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		example.setOrderByClause("sort asc, id asc");
		// 查询所有树
		List<DictionaryDo> list = dictionaryDoMapperExt.selectByExample(example);
		// 找出id下所有的节点
		if (CollectionUtils.isNotEmpty(list)) {
			List<DictionaryDo> dictionaryDos = TreeUtil.getListByRootId(list, dictionaryRequest.getId());
			for (int m = 0; m < dictionaryDos.size(); m++) {
				DictionaryDo dictionaryDo = dictionaryDos.get(m);
				int i = dictionaryDoMapperExt.deleteByPrimaryKey(dictionaryDo.getId());
				if (i == 0) {
					throw new BizException("删除失败");
				}
			}
		}

	}

	/**
	 * 通过type和code快速定位层级的树（传入几级就默认查几级，不传默认把子集都查出来）
	 */
	@Override
	public List<DictionaryTreeVo> selectByTypeAndCodeAndLay(DictionaryRequest dictionaryRequest) throws BizException {
		// 返回参数
		List<DictionaryTreeVo> dictionaryTreeVos = new ArrayList<>();
		String code = dictionaryRequest.getCode();// 要查询的编码
		String type = dictionaryRequest.getType();// 要查询的字典类型
		int lay = dictionaryRequest.getLay();
		if (StringUtils.isBlank(code)) {
			throw new BizException("没有字典编码");
		}
		if (StringUtils.isBlank(type)) {
			throw new BizException("没有字典类型");
		}
		// 查询所有的记录，然后拼接的时候找到code相同的，返回该节点下的数据
		DictionaryDoExample dictionaryDoExample = new DictionaryDoExample();
		DictionaryDoExample.Criteria criteria = dictionaryDoExample.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		criteria.andTypeEqualTo(type);// 加类型
		dictionaryDoExample.setOrderByClause("sort asc, id asc");
		// 查找所有记录集合
		List<DictionaryDo> dictionaryDos = dictionaryDoMapperExt.selectByExample(dictionaryDoExample);
		// 转换到树集合中
		if (CollectionUtils.isNotEmpty(dictionaryDos)) {
			for (int m = 0; m < dictionaryDos.size(); m++) {
				DictionaryTreeVo dictionaryTreeVo = new DictionaryTreeVo();
				BeanUtils.copyProperties(dictionaryDos.get(m), dictionaryTreeVo);
				dictionaryTreeVos.add(dictionaryTreeVo);
			}
		}
		if (CollectionUtils.isNotEmpty(dictionaryTreeVos)) {
			// 找到code下的树
			dictionaryTreeVos = TreeUtil.getDictionaryByCodeAndLay(dictionaryTreeVos, code, lay);
		}
		return dictionaryTreeVos;
	}

	/**
	 * 通过code查询出combo下拉框（单层）
	 */
	@Override
	public List<DictionaryDo> queryComboList(DictionaryRequest dictionaryRequest) throws BizException {
		// 返回参数
		List<DictionaryDo> DictionaryDo = new ArrayList<>();
		// 要查询的编码
		String code = dictionaryRequest.getCode();
		if (StringUtils.isBlank(code)) {
			return DictionaryDo;
		}
		DictionaryDoExample dictionaryDoExample = new DictionaryDoExample();
		DictionaryDoExample.Criteria criteria = dictionaryDoExample.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		criteria.andCodeEqualTo(code);
		List<DictionaryDo> dictionaryDos = dictionaryDoMapperExt.selectByExample(dictionaryDoExample);
		if (dictionaryDos.size() > 1) {
			// 通过code查出来多条，抛出异常
			throw new BizException(code + "在字典表里不是唯一的，无法初始化下拉框");
		}
		Long id = dictionaryDos.get(0).getId();// 获得id，下面查找pid为这个的，，子节点
		DictionaryDoExample dictionaryDoExample2 = new DictionaryDoExample();
		DictionaryDoExample.Criteria criteria2 = dictionaryDoExample2.createCriteria();
		criteria2.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		criteria2.andPidEqualTo(id);// 找到子集
		DictionaryDo = dictionaryDoMapperExt.selectByExample(dictionaryDoExample2);
		return DictionaryDo;
	}

	/**
	 * 通过type查询出所有下级，单层列表出来，供table里格式化字典用
	 *
	 * @param dictionaryRequest
	 * @return
	 * @throws BizException
	 */
	@Override
	public List<DictionaryDo> queryTableListByType(DictionaryRequest dictionaryRequest) throws BizException {
		// 返回参数
		List<DictionaryDo> DictionaryDo = new ArrayList<>();
		String type = dictionaryRequest.getType();// 要查询的字典类型
		if (StringUtils.isBlank(type)) {
			return DictionaryDo;
		}
		// 查询所有的记录，然后拼接的时候找到code相同的，返回该节点下的数据
		DictionaryDoExample dictionaryDoExample = new DictionaryDoExample();
		DictionaryDoExample.Criteria criteria = dictionaryDoExample.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		criteria.andTypeEqualTo(type);// 加类型
		dictionaryDoExample.setOrderByClause("sort asc, id asc");
		// 查找所有记录集合
		List<DictionaryDo> dictionaryDos = dictionaryDoMapperExt.selectByExample(dictionaryDoExample);
		return dictionaryDos;
	}

	/**
	 * 判断编码是否有重复
	 */
	@Override
	public ActionResult<String> checkDicCode(DictionaryRequest dictionaryRequest) {
		ActionResult<String> actionResult = new ActionResult<>();
		DictionaryDoExample dictionaryDoExample = new DictionaryDoExample();
		DictionaryDoExample.Criteria dCriteria = dictionaryDoExample.createCriteria();
		dCriteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		if (StringUtils.isBlank(dictionaryRequest.getType())) {
			// 新增的根节点，type为空，默认code等于type
			dictionaryRequest.setType(dictionaryRequest.getCode());
		}
		dCriteria.andCodeEqualTo(dictionaryRequest.getCode());
		dCriteria.andTypeEqualTo(dictionaryRequest.getType());
		List<DictionaryDo> dictionaryDos = dictionaryDoMapperExt.selectByExample(dictionaryDoExample);
		if (CollectionUtils.isNotEmpty(dictionaryDos)) {
			actionResult.setSuccess(false);
			actionResult.setContent("编码重复，请规范书写字典编码");
		} else {
			actionResult.setSuccess(true);
			actionResult.setContent("编码可用");
		}
		return actionResult;
	}

	// /**
	// * 通过type查询出comboTree下拉框
	// */
	// @Override
	// public List<DictionaryTreeVo> queryTreeList(DictionaryRequest
	// dictionaryRequest) {
	// // 返回参数
	// List<DictionaryTreeVo> dictionaryTreeVos = new ArrayList<>();
	// // 要查询的编码
	// String code = dictionaryRequest.getCode();
	// if (StringUtils.isBlank(code)) {
	// return dictionaryTreeVos;
	// }
	// int lay = dictionaryRequest.getLay();
	// // 查询所有的记录，然后拼接的时候找到code相同的，返回该节点下的数据
	// DictionaryDoExample dictionaryDoExample = new DictionaryDoExample();
	// DictionaryDoExample.Criteria criteria =
	// dictionaryDoExample.createCriteria();
	// criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
	// dictionaryDoExample.setOrderByClause("sort asc, id asc");
	// List<DictionaryDo> dictionaryDos =
	// dictionaryDoMapperExt.selectByExample(dictionaryDoExample);
	// // 转换到树集合中
	// if (CollectionUtils.isNotEmpty(dictionaryDos)) {
	// for (int m = 0; m < dictionaryDos.size(); m++) {
	// DictionaryTreeVo dictionaryTreeVo = new DictionaryTreeVo();
	// BeanUtils.copyProperties(dictionaryDos.get(m), dictionaryTreeVo);
	// dictionaryTreeVos.add(dictionaryTreeVo);
	// }
	// }
	// if (CollectionUtils.isNotEmpty(dictionaryTreeVos)) {
	// // 找到code下的树
	// dictionaryTreeVos = TreeUtil.getDictionaryByCodeAndLay(dictionaryTreeVos,
	// code, lay);
	// }
	// return dictionaryTreeVos;
	// }

}
