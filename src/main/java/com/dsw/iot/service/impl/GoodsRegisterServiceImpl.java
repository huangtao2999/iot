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
import com.dsw.iot.dal.GoodsRegisterDoMapperExt;
import com.dsw.iot.dto.GoodsRegisterRequest;
import com.dsw.iot.model.GoodsRegisterDo;
import com.dsw.iot.model.GoodsRegisterDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.FileUploadService;
import com.dsw.iot.service.GoodsRegisterService;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;

/**
 * 物品信息表
 * @author zc
 *
 */
@Service
public class GoodsRegisterServiceImpl implements GoodsRegisterService {

	@Autowired
	GoodsRegisterDoMapperExt goodsRegisterDoMapperExt;
	@Autowired
	CurrentUserService currentUserService;
	@Autowired
	FileUploadService fileUploadService;

	/**
	 * 分页查询物品信息主表
	 */
	@Override
	public PageResult<GoodsRegisterDo> queryPage(GoodsRegisterRequest param) {
		// 分页返回集合
		PageResult<GoodsRegisterDo> pageResult = new PageResult<>();
		// 查询条件的容器
		GoodsRegisterDoExample example = new GoodsRegisterDoExample();
		// 新建查询条件
		GoodsRegisterDoExample.Criteria criteria = example.createCriteria();
		// 添加条件
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		// 人员id
		if(param.getRegisterId()!=null) {
			criteria.andRegisterIdEqualTo(param.getRegisterId());
		}
		// 出所审核表id
		if(param.getOutId()!=null) {
			criteria.andOutIdEqualTo(param.getOutId());
		}
		//状态
		if(param.getStatus()!=null) {
			criteria.andStatusEqualTo(param.getStatus());
		}
		// 分页
		int count = goodsRegisterDoMapperExt.countByExample(example);
		PageDto pageDto = new PageDto(param.getPage(), param.getLimit(), count);
		example.setPageDto(pageDto);
		List<GoodsRegisterDo> list = goodsRegisterDoMapperExt.selectByExample(example);
		// 返回集合
		pageResult.setData(list);
		pageResult.setCount(count);
		return pageResult;
	}

	/**
	 * 分页查询物品信息主表
	 */
	@Override
	public PageResult<GoodsRegisterRequest> queryPhotoPage(GoodsRegisterRequest param) {
		// 分页返回集合
		PageResult<GoodsRegisterRequest> pageResult = new PageResult<>();
		// 查询条件的容器
		GoodsRegisterDoExample example = new GoodsRegisterDoExample();
		// 新建查询条件
		GoodsRegisterDoExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		// 人员id
		if (param.getRegisterId() == null) {
			return pageResult;
		}
		// 出所审核表id
		if (param.getOutId() != null) {
			criteria.andOutIdEqualTo(param.getOutId());
		}
		criteria.andRegisterIdEqualTo(param.getRegisterId());
		// 分页
		int count = goodsRegisterDoMapperExt.countByExample(example);
		PageDto pageDto = new PageDto(param.getPage(), param.getLimit(), count);
		example.setPageDto(pageDto);
		List<GoodsRegisterRequest> resList = getGoodsInfoByRid(param.getRegisterId());
		// 返回集合
		pageResult.setData(resList);
		pageResult.setCount(count);
		return pageResult;
	}

	/**
	 * 新增编辑物品展示信息
	 */
	@Override
	@Transactional
	public void saveGoodsRegister(GoodsRegisterDo goodsRegisterDo) {
		if (null == goodsRegisterDo.getId()) {
			// 新增
			DomainUtil.setCommonValueForCreate(goodsRegisterDo, currentUserService.getPvgInfo());
			goodsRegisterDoMapperExt.insertSelective(goodsRegisterDo);
		} else {
			// 编辑
			DomainUtil.setCommonValueForUpdate(goodsRegisterDo, currentUserService.getPvgInfo());
			goodsRegisterDoMapperExt.updateByPrimaryKeySelective(goodsRegisterDo);
		}

	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void deleteGoodsRegister(String ids) {
		if (!StringUtils.isEmpty(ids)) { // 删除多个id
			String[] idArr = ids.split(",");// 分解逗号拼接字符串
			for (String id : idArr) {
				if (!StringUtils.isEmpty(id)) {
					GoodsRegisterDo goodsRegisterDo = new GoodsRegisterDo();
					goodsRegisterDo.setId(Long.parseLong(id));
					DomainUtil.setCommonValueForDelete(goodsRegisterDo, currentUserService.getPvgInfo());
					goodsRegisterDoMapperExt.deleteByPrimaryKey(goodsRegisterDo);
				}
			}
		}
	}


	@Override
	public GoodsRegisterDo getGoodsRegister(Long id) {
		return goodsRegisterDoMapperExt.selectByPrimaryKey(id);
	}

	/**
	 * 根据注册用户id获取物品信息列表
	 */
	@Override
	public List<GoodsRegisterDo> getGoodsRegisterByrid(Long registerId) {
		GoodsRegisterDoExample example = new GoodsRegisterDoExample();
		GoodsRegisterDoExample.Criteria criteria = example.createCriteria();
		criteria.andRegisterIdEqualTo(registerId);
		// 添加条件
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		return goodsRegisterDoMapperExt.selectByExample(example);
	}

	/**
	 * 人员登记时登记物品信息
	 */
	@Override
	@Transactional
	public List<GoodsRegisterRequest> saveGoodsInfo(List<GoodsRegisterRequest> goodsInfo, Long registerId) {
		List<GoodsRegisterRequest> resList = new ArrayList<>();
		String removeIds = "";
		if (CollectionUtils.isNotEmpty(goodsInfo)) {
			removeIds = goodsInfo.get(0).getRemoveIds();
			for (GoodsRegisterRequest gRequest : goodsInfo) {
				GoodsRegisterDo goodsRegisterDo = new GoodsRegisterDo();
				BeanUtils.copyProperties(gRequest, goodsRegisterDo);
				if (null == goodsRegisterDo.getId()) {
					// 新增
					goodsRegisterDo.setRegisterId(registerId);
					DomainUtil.setCommonValueForCreate(goodsRegisterDo, currentUserService.getPvgInfo());
					goodsRegisterDoMapperExt.insertSelective(goodsRegisterDo);
				} else {
					// 编辑
					DomainUtil.setCommonValueForUpdate(goodsRegisterDo, currentUserService.getPvgInfo());
					goodsRegisterDoMapperExt.updateByPrimaryKeySelective(goodsRegisterDo);
				}
				// 更新附件
				fileUploadService.updateAttach(gRequest.getSourceIds(), goodsRegisterDo.getId(),
						CommConfig.ATTACH_TYPE.GOODS_REGISTER_IMGS.getType(),
						CommConfig.ATTACH_TYPE.GOODS_REGISTER_IMGS.getName());
				// 返回
				BeanUtils.copyProperties(goodsRegisterDoMapperExt.selectByPrimaryKey(goodsRegisterDo.getId()),
						gRequest);
				resList.add(gRequest);
			}
		}
		// 删除已经删除整行的记录
		if (StringUtils.isNotBlank(removeIds)) {
			String[] removeId = removeIds.split(",");
			for (String id : removeId) {
				if (StringUtils.isNotBlank(id)) {
					GoodsRegisterDo goodsRegisterDo = new GoodsRegisterDo();
					goodsRegisterDo.setId(Long.parseLong(id));
					// 删除伤情表记录
					goodsRegisterDoMapperExt.deleteByPrimaryKey(goodsRegisterDo);
				}
			}
		}

		return resList;
	}

	/**
	 * 根据注册用户id获取物品信息列表
	 */
	@Override
	public List<GoodsRegisterRequest> getGoodsInfoByRid(Long registerId) {
		List<GoodsRegisterRequest> resList = new ArrayList<>();
		GoodsRegisterDoExample example = new GoodsRegisterDoExample();
		GoodsRegisterDoExample.Criteria criteria = example.createCriteria();
		// 添加条件
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		criteria.andRegisterIdEqualTo(registerId);
		List<GoodsRegisterDo> list = goodsRegisterDoMapperExt.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {
			for (GoodsRegisterDo gDo : list) {
				String sourceIds = "";
				String receiveIds = "";
				GoodsRegisterRequest gRequest = new GoodsRegisterRequest();
				BeanUtils.copyProperties(gDo, gRequest);
				// 查询附件，拼接sourceIds
				sourceIds = fileUploadService.getAttachIds(gDo.getId(),
						CommConfig.ATTACH_TYPE.GOODS_REGISTER_IMGS.getType());
				receiveIds = fileUploadService.getAttachIds(gDo.getId(),
						CommConfig.ATTACH_TYPE.GOODS_REGISTER_IMGS_OUT.getType());
				gRequest.setSourceIds(sourceIds);
				gRequest.setReceiveIds(receiveIds);
				resList.add(gRequest);
			}
		}
		return resList;
	}


	/**
	 * 批量更新物品状态
	 * ids为物品主键id集合,status为更新后的物品状态
	 */
	@Override
	@Transactional
	public void updateGoodsStatus(String ids,String status) {
		if (!StringUtils.isEmpty(ids)) { // 删除多个id
			String[] idArr = ids.split(",");// 分解逗号拼接字符串
			for (String id : idArr) {
				if (!StringUtils.isEmpty(id)) {
					GoodsRegisterDo goodsRegisterDo = new GoodsRegisterDo();
					goodsRegisterDo.setId(Long.parseLong(id));
					goodsRegisterDo.setStatus(status);
					saveGoodsRegister(goodsRegisterDo);
				}
			}
		}
	}

	/**
	 * 批量更新物品状态
	 * ids为物品主键id集合,status为更新后的物品状态,outId为出所申请id
	 */
	@Override
	@Transactional
	public void updateGoodsStatus(String ids,String status,Long outId) {
		if (StringUtils.isNotBlank(status)) {
			if (!StringUtils.isEmpty(ids)) { // 需要更新的多个物品id
				String[] idArr = ids.split(",");// 分解逗号拼接字符串
				for (String id : idArr) {
					if (!StringUtils.isEmpty(id)) {
						GoodsRegisterDo goodsRegisterDo = new GoodsRegisterDo();
						goodsRegisterDo = getGoodsRegister(Long.parseLong(id));
						goodsRegisterDo.setOutId(outId);
						goodsRegisterDo.setStatus(status);
						saveGoodsRegister(goodsRegisterDo);
					}
				}
			}
		}

	}

	/**
	 * 根据出所申请id查询物品列表
	 */
	@Override
	public List<GoodsRegisterRequest> getGoodsRegisterByOutId(Long outId) {
		List<GoodsRegisterRequest> resList = new ArrayList<>();
		GoodsRegisterDoExample example = new GoodsRegisterDoExample();
		GoodsRegisterDoExample.Criteria criteria = example.createCriteria();
		criteria.andOutIdEqualTo(outId);
		// 添加条件
		criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		List<GoodsRegisterDo> list = goodsRegisterDoMapperExt.selectByExample(example);
		// 照物品图片
		if (CollectionUtils.isNotEmpty(list)) {
			for (GoodsRegisterDo gDo : list) {
				String sourceIds = "";
				GoodsRegisterRequest gRequest = new GoodsRegisterRequest();
				BeanUtils.copyProperties(gDo, gRequest);
				// 查询附件，拼接sourceIds
				sourceIds = fileUploadService.getAttachIds(gDo.getId(),
						CommConfig.ATTACH_TYPE.GOODS_REGISTER_IMGS.getType());
				gRequest.setSourceIds(sourceIds);
				resList.add(gRequest);
			}
		}
		return resList;
	}

}
