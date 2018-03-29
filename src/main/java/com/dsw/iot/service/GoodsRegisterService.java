package com.dsw.iot.service;

import java.util.List;

import com.dsw.iot.dto.GoodsRegisterRequest;
import com.dsw.iot.model.GoodsRegisterDo;
import com.dsw.iot.util.PageResult;

/**
 * 物品信息展示
 *
 * @author zc
 *
 */
public interface GoodsRegisterService {

	/**
	 * 分页查询物品信息主表
	 *
	 * @param param
	 * @return
	 */
	PageResult<GoodsRegisterDo> queryPage(GoodsRegisterRequest param);

	/**
	 * 新增编辑物品信息表
	 */
	void saveGoodsRegister(GoodsRegisterDo goodsRegisterDo);

	/**
	 * 通过主键批量删除
	 */
	void deleteGoodsRegister(String ids);

	/**
	 * 通过主键查单条记录
	 */
	GoodsRegisterDo getGoodsRegister(Long id);

	/**
	 * 查询犯人的物品信息
	 */
	List<GoodsRegisterDo> getGoodsRegisterByrid(Long registerId);

	/**
	 * 人员登记时登记物品信息
	 *
	 * @param goodsInfo
	 * @return
	 */
	List<GoodsRegisterRequest> saveGoodsInfo(List<GoodsRegisterRequest> goodsInfo, Long registerId);

	/**
	 * 通过人员id查询物品信息，包括图片附件
	 *
	 * @param registerId
	 * @return
	 */
	List<GoodsRegisterRequest> getGoodsInfoByRid(Long registerId);

	/**
	 * 批量更新物品状态
	 * ids为物品主键id集合,status为更新后的物品状态
	 */
	void updateGoodsStatus(String ids,String status);

	/**
	 * 根据出所申请id查询物品列表
	 * @param outId
	 * @return
	 */
	List<GoodsRegisterRequest> getGoodsRegisterByOutId(Long outId);

	/**
	 * 批量更新物品状态
	 * ids为物品主键id集合,status为更新后的物品状态,outId为出所申请id
	 */
	void updateGoodsStatus(String ids,String status,Long outId);

	/**
	 * 分页查询带图片的分页列表， 前端插件规范是分页的格式
	 *
	 * @param param
	 * @return
	 */
	PageResult<GoodsRegisterRequest> queryPhotoPage(GoodsRegisterRequest param);

}
