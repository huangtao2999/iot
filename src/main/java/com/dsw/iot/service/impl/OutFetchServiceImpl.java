package com.dsw.iot.service.impl;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dto.GoodsRegisterRequest;
import com.dsw.iot.dto.OutClickConfirmRequest;
import com.dsw.iot.manager.RtlsManager;
import com.dsw.iot.model.CardManageDo;
import com.dsw.iot.model.LockerDo;
import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.service.*;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 出所取物
 *
 * @author zc
 */
@Service
public class OutFetchServiceImpl implements OutFetchService {

    @Autowired
    PersonRegisterService personRegisterService;
    @Autowired
    CardManageService cardManageService;
    @Autowired
    CardLocateService cardLocateService;
    @Autowired
    LockerService lockerService;
    @Autowired
    GoodsRegisterService goodsRegisterService;
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    OutConfirmService outConfirmService;
    @Autowired
    private LogService logService;
    @Autowired
    private CurrentUserService currentUserService;
    @Autowired
    private RoomPropertyService roomPropertyService;
    @Autowired
    private RtlsManager rtlsManager;

    /*
     * 储物柜控制
     * lockerId 储物柜主键id
     */
    @Override
    public void getLockerOpen(Long lockerId) {
        LockerDo lockerDo = lockerService.getLocker(lockerId);
        if (lockerDo != null) {
            // TODO 控制储物柜打开
        }
    }


    /**
     * 签名拍照
     * registerId 注册人员主键id
     */
    @Override
    public void signConfirm(Long registerId) {
        // TODO 签名拍照
        //更新人员信息
        personRegisterService.updatePersonRegisterRsign(registerId, "1");

    }

    /**
     * 临时出办案区 registerId 注册人员主键id cardId 卡芯片编码id
     *
     * @throws BizException
     */
    @Override
    public ActionResult<String> outTempArea(Long registerId, String cardId, Long outId) throws BizException {
        ActionResult<String> res = new ActionResult<>();
        String content = "";
        PersonRegisterDo personRegisterDo = personRegisterService.getPersonRegister(registerId);
        if (personRegisterDo != null) {
            //出办案区
            //解除手环状态
            try {
                CardManageDo cardManageDo = cardManageService.getCarManageByCarNo(cardId);
                cardManageService.deactivateCard(cardManageDo.getId());
                content += "手环停用完成。";
            } catch (BizException e) {
                e.printStackTrace();
                content += e.getMessage();
            }
            //更新主表人员状态为临时出所中
            personRegisterService.updatePersonStatus(registerId, "4");// 1-在办案区；2-申请出办案区；3-待出办案区；4-临时出办案区；5-正式出办案区；
            // 更新审批单为历史，更新out_time为当前时间
            outConfirmService.updateConfirmToHistory(outId);
            // 释放人员所在房间
            roomPropertyService.outReleaseRoomByRid(registerId);
            //更新定位表历史记录
            cardLocateService.updateToHistory(registerId, cardId);
            res.setSuccess(true);
            res.setContent("临时出办案区成功。" + content);

            // 写日志
            logService.insertLog(CommConfig.LOG_MODULE.OUT_FETCH.getModule(), CommConfig.LOG_TYPE.UPDATE.getType(),
                    currentUserService.getPvgInfo().getName() + "  临时出办案区了人员：" + personRegisterDo.getName());
        } else {
            res.setSuccess(false);
            res.setContent("用户不存在");
        }
        return res;

    }


    /**
     * 确认出办案区 registerId 注册人员id
     *
     * @throws BizException
     */
    @Override
    public ActionResult<String> outArea(Long registerId, String cardId, Long outId) throws BizException {
        ActionResult<String> res = new ActionResult<>();
        String content = "";
        PersonRegisterDo personRegisterDo = personRegisterService.getPersonRegister(registerId);
        if (personRegisterDo != null) {
            Long lockerId = personRegisterDo.getLockerId();// 获得储物柜id
            //解除手环状态
            CardManageDo cardManageDo = cardManageService.getCarManageByCarNo(cardId);
            if (null == cardManageDo) {
                throw new BizException("手环不存在,手环停用失败!");
            }
            cardManageService.deactivateCard(cardManageDo.getId());
            content += "手环停用完成。";
            // 更新主表人员状态为已出所
            personRegisterService.updatePersonStatus(registerId, "5");
            // 更新储物柜为空闲
            if (null != lockerId) {
                lockerService.updateLockerStatus(lockerId, CommConfig.BUSY_FREE_STATUS.FREE.getName());
            }
            // 更新审批单为历史
            outConfirmService.updateConfirmToHistory(outId);
            // 释放人员所在房间
            roomPropertyService.outReleaseRoomByRid(registerId);
            //更新定位表历史记录
            cardLocateService.updateToHistory(registerId, cardId);
            // 更新人员信息,确认签名【没用】
            // personRegisterService.updatePersonRegisterRsign(registerId, "1");
            res.setSuccess(true);
            res.setContent("确认出办案区成功。" + content);

            if (null != cardManageDo) {
                rtlsManager.unBindTag(cardManageDo.getTagEuid());
            }
            // 写日志
            logService.insertLog(CommConfig.LOG_MODULE.OUT_FETCH.getModule(), CommConfig.LOG_TYPE.UPDATE.getType(),
                    currentUserService.getPvgInfo().getName() + "  正式出办案区了人员：" + personRegisterDo.getName());
        } else {
            res.setSuccess(false);
            res.setContent("用户不存在");
        }
        return res;
    }


    /**
     * 页面点击签名确认
     */
    @Override
    @Transactional
    public ActionResult<String> clickConfirm(OutClickConfirmRequest clickConfirmRequest) {
        ActionResult<String> result = new ActionResult<>();
        String content = "";
        // 更新不扣押的物品为返还状态
        List<GoodsRegisterRequest> inList = clickConfirmRequest.getInGoodsInfo();
        if (CollectionUtils.isNotEmpty(inList)) {
            for (GoodsRegisterRequest gRequest : inList) {
                goodsRegisterService.updateGoodsStatus(gRequest.getId() + "", "3");// 更新为归还
            }
            content += "物品已归还；";

            // 写日志
            logService.insertLog(CommConfig.LOG_MODULE.PERSON_REGISTER.getModule(),
                    CommConfig.LOG_TYPE.UPDATE.getType(), currentUserService.getPvgInfo().getName() + "  返还了人员的物品：");
        }
        // 更新上传的出办案区物品照片信息
        List<GoodsRegisterRequest> outList = clickConfirmRequest.getOutGoodsInfo();
        if (CollectionUtils.isNotEmpty(outList)) {
            for (GoodsRegisterRequest gRequest : outList) {
                // 更新图片
                fileUploadService.updateAttach(gRequest.getSourceIds(), gRequest.getId(),
                        CommConfig.ATTACH_TYPE.GOODS_REGISTER_IMGS_OUT.getType(),
                        CommConfig.ATTACH_TYPE.GOODS_REGISTER_IMGS_OUT.getName());
            }
            content += "物品拍照保存成功；";
        }
        content += "物品核实完成。";
        result.setSuccess(true);
        result.setContent(content);
        return result;
    }

}
