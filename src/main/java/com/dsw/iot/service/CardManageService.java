package com.dsw.iot.service;

import com.dsw.iot.dto.CardManageRequest;
import com.dsw.iot.model.CardManageDo;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;

public interface CardManageService {

    /**
     * 卡芯片管理分页查询
     *
     * @param param
     * @return
     */
    PageResult<CardManageDo> queryPage(CardManageRequest param);

    /**
     * 新增/编辑 卡芯片信息
     *
     * @param cardManageDo
     * @throws BizException
     */
    void saveCard(CardManageDo cardManageDo) throws BizException;

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteCard(String ids);

    /**
     * 激活卡，开始记录定位信息
     *
     * @param id
     * @return
     * @throws BizException
     */
    void activateCard(Long id) throws BizException;

    /**
     * 停止记录轨迹
     *
     * @param id
     * @return
     * @throws BizException
     */
    void deactivateCard(Long id) throws BizException;

    /**
     * 根据手环编号查询手环信息
     */
	CardManageDo getCarManageByCarNo(String cardId);
}
