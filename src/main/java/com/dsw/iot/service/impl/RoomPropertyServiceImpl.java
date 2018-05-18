package com.dsw.iot.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.RoomPropertyDoMapperExt;
import com.dsw.iot.dto.RoomPropertyRequest;
import com.dsw.iot.manager.LedManager;
import com.dsw.iot.manager.RelayManager;
import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.model.RoomPropertyDo;
import com.dsw.iot.model.RoomPropertyDoExample;
import com.dsw.iot.model.RoomRecordDo;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.LogService;
import com.dsw.iot.service.PersonRegisterService;
import com.dsw.iot.service.RoomPropertyService;
import com.dsw.iot.service.RoomRecordService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.DateUtil;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;
import com.dsw.iot.vo.RoomInquiryVo;
import com.dsw.iot.vo.RoomPropertyVo;

import dm.jdbc.util.StringUtil;

/**
 * 讯询问室服务
 **/
@Service
public class RoomPropertyServiceImpl implements RoomPropertyService {
    protected static final Logger logger = Logger.getLogger(RoomPropertyServiceImpl.class);
    @Autowired(required = false)
    private RoomPropertyDoMapperExt roomPropertyDoMapperExt;
    @Autowired
    private PersonRegisterService personRegisterService;
    @Autowired
    private RoomRecordService roomRecordService;
    @Autowired
    private CurrentUserService currentUserService;
    @Autowired
    private RelayManager relayManager;
    @Autowired
    private LedManager ledManager;
    @Autowired
    private LogService logService;

    @Value("${led.relay.ip}")
    private String ip;

    @Override
    public PageResult<RoomPropertyDo> queryPage(RoomPropertyRequest request) {
        PageResult<RoomPropertyDo> pageResult = new PageResult<>();
        RoomPropertyDoExample example = new RoomPropertyDoExample();
        RoomPropertyDoExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        // 添加条件
        //roomType为-1，则为查询除等候室外所有的房间
        if (StringUtils.isNotEmpty(request.getRoomType())) {
            if (request.getRoomType().equals("-1")) {
                criteria.andRoomTypeNotEqualTo("1");
            } else {
                criteria.andRoomTypeEqualTo(request.getRoomType());
            }
        }
        if (StringUtils.isNotEmpty(request.getRoomStatus())) {
            criteria.andRoomStatusEqualTo(request.getRoomStatus());
        }
        int count = roomPropertyDoMapperExt.countByExample(example);
        PageDto pageDto = new PageDto(request.getPage(), request.getLimit(), count);
        example.setPageDto(pageDto);
        List<RoomPropertyDo> list = roomPropertyDoMapperExt.selectByExample(example);
        pageResult.setData(list);
        pageResult.setCount(count);
        return pageResult;
    }

    /**
     * 分页查询等候室列表
     */
    @Override
    @Transactional
    public PageResult<RoomPropertyVo> queryPageWait(RoomPropertyRequest request) {
        PageResult<RoomPropertyVo> pageResult = new PageResult<>();
        RoomPropertyDoExample example = new RoomPropertyDoExample();
        RoomPropertyDoExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        // 添加条件
        criteria.andRoomTypeEqualTo("1");
        if (StringUtils.isNotEmpty(request.getRoomStatus())) {
            criteria.andRoomStatusEqualTo(request.getRoomStatus());
        }
        int count = roomPropertyDoMapperExt.countByExample(example);
        PageDto pageDto = new PageDto(request.getPage(), request.getLimit(), count);
        example.setPageDto(pageDto);
        List<RoomPropertyVo> list_vo = new ArrayList<>();
        List<RoomPropertyDo> list = roomPropertyDoMapperExt.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            RoomPropertyVo roomPropertyVo = null;
            for (RoomPropertyDo roomPropertyDo : list) {
                roomPropertyVo = new RoomPropertyVo();
                roomPropertyVo.setId(roomPropertyDo.getId());
                roomPropertyVo.setRoomName(roomPropertyDo.getRoomName());
                roomPropertyVo.setRoomNo(roomPropertyDo.getRoomNo());
                roomPropertyVo.setRoomMax(roomPropertyDo.getRoomMax());
                //查看关押人数
                List<PersonRegisterDo> list_count = roomRecordService.getPersonRegisterByrid(roomPropertyDo.getId(), "0");
                if (CollectionUtils.isNotEmpty(list_count)) {
                    roomPropertyVo.setHeldCount(list_count.size());
                    String sex = "";
                    //判断关押人员性别
                    for (PersonRegisterDo personRegisterDo : list_count) {
                        String sex_ = personRegisterDo.getSex();
                        if (sex_.equals("man")) {
                            if (!sex.contains("男")) {
                                sex += "男 ";
                            }
                        } else if (sex_.equals("woman")) {
                            if (!sex.contains("女")) {
                                sex += "女 ";
                            }
                        }

                    }
                    roomPropertyVo.setRoomSex(sex);
                } else {
                    roomPropertyVo.setHeldCount(0);
                    roomPropertyVo.setRoomSex("");
                }
                roomPropertyVo.setHeldCountAndRoomMax(roomPropertyVo.getHeldCount() + "," + roomPropertyVo.getRoomMax());
                list_vo.add(roomPropertyVo);
            }

        }

        pageResult.setData(list_vo);
        pageResult.setCount(count);
        return pageResult;
    }

    /**
     * 分页查询询问室列表
     */
    @Override
    public PageResult<RoomInquiryVo> queryPageInquiry(RoomPropertyRequest request) {
        PageResult<RoomInquiryVo> pageResult = new PageResult<>();
        RoomPropertyDoExample example = new RoomPropertyDoExample();
        RoomPropertyDoExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        // 添加条件
        List<String> typeList = new ArrayList<String>();
        typeList.add("2");//询问室
        typeList.add("3");//讯问室
        typeList.add("4");//特殊讯问室
        typeList.add("5");//未成年人询问室
        criteria.andRoomTypeIn(typeList);
        if (StringUtils.isNotEmpty(request.getRoomStatus())) {
            criteria.andRoomStatusEqualTo(request.getRoomStatus());
        }
        int count = roomPropertyDoMapperExt.countByExample(example);
        PageDto pageDto = new PageDto(request.getPage(), request.getLimit(), count);
        example.setPageDto(pageDto);
        List<RoomInquiryVo> list_vo = new ArrayList<>();
        List<RoomPropertyDo> list = roomPropertyDoMapperExt.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            RoomInquiryVo roomInquiryVo = null;
            for (RoomPropertyDo roomPropertyDo : list) {
                roomInquiryVo = new RoomInquiryVo();
                roomInquiryVo.setId(roomPropertyDo.getId());
                roomInquiryVo.setRoomName(roomPropertyDo.getRoomName());
                roomInquiryVo.setRoomNo(roomPropertyDo.getRoomNo());
                roomInquiryVo.setRoomStatus(roomPropertyDo.getRoomStatus());
                //查看关押人数
                List<PersonRegisterDo> list_count = roomRecordService.getPersonRegisterByrid(roomPropertyDo.getId(), "0");
                if (CollectionUtils.isNotEmpty(list_count)) {
                    //取最后一位
                    PersonRegisterDo personRegisterDo = list_count.get(list_count.size() - 1);
                    if (personRegisterDo != null) {
                        roomInquiryVo.setLockPersonId(personRegisterDo.getId());
                        roomInquiryVo.setLockPersonName(personRegisterDo.getName());
                    }
                } else {
                    roomInquiryVo.setLockPersonId(null);
                    roomInquiryVo.setLockPersonName("");
                }
                list_vo.add(roomInquiryVo);
            }
        }

        pageResult.setData(list_vo);
        pageResult.setCount(count);
        return pageResult;
    }

    @Override
    public void saveRoomProperty(RoomPropertyDo roomPropertyDo) throws BizException {
        if (null == roomPropertyDo.getId()) {
            validate(roomPropertyDo, "add");
            // 新增
            DomainUtil.setCommonValueForCreate(roomPropertyDo, currentUserService.getPvgInfo());
            roomPropertyDoMapperExt.insertSelective(roomPropertyDo);

            // 写日志
            logService.insertLog(CommConfig.LOG_MODULE.ROOM.getModule(), CommConfig.LOG_TYPE.ADD.getType(),
                    currentUserService.getPvgInfo().getName() + "  新增了一个房间信息：" + roomPropertyDo.getRoomName());
        } else {
            validate(roomPropertyDo, "update");
            // 编辑
            DomainUtil.setCommonValueForUpdate(roomPropertyDo, currentUserService.getPvgInfo());
            roomPropertyDoMapperExt.updateByPrimaryKeySelective(roomPropertyDo);

            // 写日志
            logService.insertLog(CommConfig.LOG_MODULE.ROOM.getModule(), CommConfig.LOG_TYPE.UPDATE.getType(),
                    currentUserService.getPvgInfo().getName() + "  编辑了一个房间信息：" + roomPropertyDo.getRoomName());
        }
    }

    private void validate(RoomPropertyDo roomPropertyDo, String option) throws BizException {
        if ("add".equals(option)) {
            if (StringUtils.isEmpty(roomPropertyDo.getArea())) {
                throw new BizException("单位不能为空");
            }
            if (StringUtils.isEmpty(roomPropertyDo.getRoomName())) {
                throw new BizException("房间名称不能为空");
            }
            if (StringUtils.isEmpty(roomPropertyDo.getRoomNo())) {
                throw new BizException("房间编号不能为空");
            }
        } else {
            // TODO
        }
    }

    @Override
    public RoomPropertyDo getRoomProperty(Long id) {
        return roomPropertyDoMapperExt.selectByPrimaryKey(id);
    }

    @Override
    public void deleteRoomProperty(String ids) {
        if (!StringUtils.isEmpty(ids)) { // 删除多个id
            String[] idArr = ids.split(",");// 分解逗号拼接字符串
            for (String id : idArr) {
                if (!StringUtils.isEmpty(id)) {
                    RoomPropertyDo roomPropertyDo = new RoomPropertyDo();
                    roomPropertyDo.setId(Long.parseLong(id));
                    DomainUtil.setCommonValueForDelete(roomPropertyDo, currentUserService.getPvgInfo());
                    roomPropertyDoMapperExt.deleteByPrimaryKey(roomPropertyDo);
                }
            }
        }
        // 写日志
        logService.insertLog(CommConfig.LOG_MODULE.ROOM.getModule(), CommConfig.LOG_TYPE.DELETE.getType(),
                currentUserService.getPvgInfo().getName() + "  删除了房间数据");
    }


    /**
     * 讯/询问室列表
     * roomtype 房间类型 为空，查询所有 -1则代表查询除等候室之外的所有房间
     * roomstatus 房间状态 为空，则代表查询所有
     */
    @Override
    public List<RoomPropertyDo> getRoomPropertylist(String roomType, String roomStatus) {
        RoomPropertyDoExample example = new RoomPropertyDoExample();
        RoomPropertyDoExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        // 添加条件
        if (StringUtil.isNotEmpty(roomType)) {
            if (roomType.equals("-1")) {
                criteria.andRoomTypeNotEqualTo("1");//1代表等候室
            } else {
                criteria.andRoomTypeEqualTo(roomType);
            }
        }
        if (StringUtils.isNotEmpty(roomStatus)) {
            criteria.andRoomStatusEqualTo(roomStatus);
        }
        List<RoomPropertyDo> list = roomPropertyDoMapperExt.selectByExample(example);
        return list;
    }

    /**
     * 讯/询问室分配,手动分配
     * registerId 注册人员id
     * roomId 房间id
     */
    @Override
    @Transactional
    public ActionResult<String> getRoomDistribution(Long registerId, Long roomId) {
        ActionResult<String> res = new ActionResult<>();
        //房间
        RoomPropertyDo roomPropertyDo = roomPropertyDoMapperExt.selectByPrimaryKey(roomId);
        if (roomPropertyDo != null) {
            String roomStatus = roomPropertyDo.getRoomStatus();
            //判断房间是否是空间或占用
            if (roomStatus.equals("0")) {//空闲
                //创建人员--房间关联表
//                RoomRecordDo roomRecordDo = new RoomRecordDo();
//                roomRecordDo.setRegisterId(registerId);
//                roomRecordDo.setRoomId(roomId);
//                try {
//                    roomRecordService.saveRoomRecord(roomRecordDo);
//                } catch (BizException e) {
//                    logger.error("保存异常", e);
//                }

                //将房间状态改为占用
                updateRoomStatus(roomId, "1");
                //保存人员-房间记录表
                getRoomWaitDistribution(registerId, roomId);
                relayManager.openV2(roomPropertyDo.getLightIp(), Integer.parseInt(roomPropertyDo.getLightPort()), roomPropertyDo.getLightIndex());
                relayManager.openV2(roomPropertyDo.getWindIp(), Integer.parseInt(roomPropertyDo.getWindPort()), roomPropertyDo.getWindRoad());
                res.setSuccess(true);
                res.setContent("房间分配成功");

                // 写日志
                logService.insertLog(CommConfig.LOG_MODULE.ROOM.getModule(), CommConfig.LOG_TYPE.UPDATE.getType(),
                        currentUserService.getPvgInfo().getName() + "  手动分配了" + roomPropertyDo.getRoomName());
            } else {
                res.setSuccess(false);
                res.setContent("房间状态为占用,不可再分配");
            }
        } else {
            res.setSuccess(false);
            res.setContent("房间不存在");
        }
        return res;
    }


    /**
     * 等候室分派 ,自动分配 registerId 注册人员id
     */
    @Override
    @Transactional
    public ActionResult<String> getRoomWaitAutoDistribution(Long registerId) {
        ActionResult<String> res = new ActionResult<String>();
        //当前注册人员
        PersonRegisterDo personRegisterDo = personRegisterService.getPersonRegister(registerId);
        String dis_roomId = "";//分配的房间id
        if (personRegisterDo != null) {
            String sex = personRegisterDo.getSex();//性别
            String alarmNumber = personRegisterDo.getAlarmNumber();//警情编号
            String isWarned = personRegisterDo.getIsWarned();//同案预警 0否 1是
            if (isWarned.equals("0")) {// 非同案预警，分配时只区别性别
                //获取所有的等候室
                List<RoomPropertyDo> list = getRoomPropertylist("1", "");
                if (CollectionUtils.isNotEmpty(list)) {
                    for (RoomPropertyDo roomPropertyDo : list) {
                        Long roomId = roomPropertyDo.getId();//房间id
                        int roomMax = roomPropertyDo.getRoomMax();

                        boolean rule = true;// true代表满足规则
                        // 获取等候室所有关押人员信息
                        List<PersonRegisterDo> list_person = roomRecordService.getPersonRegisterByrid(roomId, "0");
                        if (CollectionUtils.isNotEmpty(list_person)) {
                            for (PersonRegisterDo personRegisterDo_ : list_person) {
                                String sex_ = personRegisterDo_.getSex();// 性别
                                if (!sex_.equals(sex)) {
                                    rule = false;
                                    break;
                                }
                            }
                        }

                        if (rule) {
                            // 目前房间关押人员
                            int count = roomRecordService.getPersonCount(roomId);
                            if (roomMax > count) {
                                // 直接分配
                                getRoomWaitDistribution(registerId, roomId);
                                dis_roomId = String.valueOf(roomId);
                                break;
                            }
                        }

                    }
                }

                if (StringUtil.isEmpty(dis_roomId)) {
                    res.setSuccess(false);
                    res.setContent("系统未找到合适等候室");
                } else {
                    res.setSuccess(true);
                    res.setContent(dis_roomId);
                }

            } else {// 同案预警，区别性别和警情编号
                //获取所有的等候室
                List<RoomPropertyDo> list = getRoomPropertylist("1", "");
                if (CollectionUtils.isNotEmpty(list)) {
                    for (RoomPropertyDo roomPropertyDo : list) {
                        Long roomId = roomPropertyDo.getId();//房间id
                        int roomMax = roomPropertyDo.getRoomMax();
                        boolean rule = true;//true代表满足规则
                        //获取等候室所有关押人员信息
                        List<PersonRegisterDo> list_person = roomRecordService.getPersonRegisterByrid(roomId, "0");
                        if (CollectionUtils.isNotEmpty(list_person)) {
                            for (PersonRegisterDo personRegisterDo_ : list_person) {
                                String sex_ = personRegisterDo_.getSex();// 性别
                                String alarmNumber_ = personRegisterDo_.getAlarmNumber();// 警情编号
                                if (!"".equals(alarmNumber)) {
                                    if (!sex_.equals(sex) || alarmNumber.equals(alarmNumber_)) {
                                        rule = false;
                                        break;
                                    }
                                } else {
                                    if (!sex_.equals(sex)) {
                                        rule = false;
                                        break;
                                    }
                                }
                            }
                        }

                        if (rule) {
                            //目前房间关押人员
                            int count = roomRecordService.getPersonCount(roomId);
                            if (roomMax > count) {
                                //直接分配
                                dis_roomId = String.valueOf(roomId);
                                getRoomWaitDistribution(registerId, roomId);
                                break;
                            }
                        }
                    }

                    if (StringUtil.isEmpty(dis_roomId)) {
                        res.setSuccess(false);
                        res.setContent("系统未找到合适等候室");
                    } else {
                        res.setSuccess(true);
                        res.setContent(dis_roomId);
                    }
                }
            }
        }

        return res;
    }


    /**
     * 等候室分派 ，直接分配，不考虑预警规则
     * registerId 注册人员id
     * roomId 房间id
     */
    @Override
    @Transactional
    public void getRoomWaitDistribution(Long registerId, Long roomId) {
        //判断该人员是否存在
        List<PersonRegisterDo> list = roomRecordService.getPersonRegisterByprid(registerId, "0");
        if (!CollectionUtils.isEmpty(list)) {
            //该人员已在房间里,将该人员在房间记录设为历史记录，再进行分配
            List<RoomPropertyDo> roomPropertyByprid = roomRecordService
                    .getRoomPropertyByprid(registerId, "0");// 获取之前待过的房间list
            roomRecordService.updateRoomRecordHistory(registerId);
            if (CollectionUtils.isNotEmpty(roomPropertyByprid)) {
                updateRoomStatusForInquiryRoom(roomPropertyByprid, roomId);// 将改变之前待过的房间状态（针对讯、询问室）
                updateLedAfterDistribute(roomPropertyByprid.get(0).getId());// 之前待过的房间，跟新LED展示
            }
        }
        //进行分配
        RoomRecordDo roomRecordDo = new RoomRecordDo();
        roomRecordDo.setRegisterId(registerId);
        roomRecordDo.setRoomId(roomId);
        try {
            roomRecordService.saveRoomRecord(roomRecordDo);
            RoomPropertyDo roomPropertyDo = roomPropertyDoMapperExt.selectByPrimaryKey(roomId);
            // 写日志
            logService.insertLog(CommConfig.LOG_MODULE.ROOM.getModule(), CommConfig.LOG_TYPE.UPDATE.getType(),
                    currentUserService.getPvgInfo().getName() + "  分配了等候室：" + roomPropertyDo.getRoomName());
            distributeRoomLedShow(registerId, roomId);// 新分配的房间跟新LED展示
            corridorLedShow(registerId, roomId); // 走廊展示屏显示最新消息
        } catch (BizException e) {
            // TODO Auto-generated catch block
            logger.error("保存异常", e);
        }
    }

    /**
     * 修改房间状态 roomId 房间id roomStatus 房间状态 0空闲 1占用
     */
    @Override
    public void updateRoomStatus(Long roomId, String roomStatus) {
        RoomPropertyDo roomPropertyDo = getRoomProperty(roomId);
        if (roomPropertyDo != null) {
            roomPropertyDo.setRoomStatus(roomStatus);
            try {
                saveRoomProperty(roomPropertyDo);
                // 写日志
                logService.insertLog(CommConfig.LOG_MODULE.ROOM.getModule(), CommConfig.LOG_TYPE.UPDATE.getType(),
                        currentUserService.getPvgInfo().getName() + "  修改了房间使用状态");
            } catch (BizException e) {
                // TODO Auto-generated catch block
                logger.error("保存异常", e);
            }
        }

    }

    /**
     * type=1 打开房间电源，type=2 打开换气扇 ，type=3打开只能墙体
     */
    @Override
    public ActionResult<String> openEvent(Long id, String type) {
        ActionResult<String> actionResult = new ActionResult<String>();
        RoomPropertyDo roomPropertyDo = this.getRoomProperty(id);
        if (!StringUtils.isEmpty(type)) {// 判断type是否为空
            if (type.equals("1")) {// 打开房间电源
                if (StringUtil.isEmpty(roomPropertyDo.getLightIp()) || StringUtil.isEmpty(roomPropertyDo.getLightPort()) || null == roomPropertyDo.getLightIndex()) {
                    actionResult.setContent("房间电源ip、port、road未填写完整");
                    actionResult.setSuccess(false);
                    return actionResult;
                }
                if ("1".equals(roomPropertyDo.getLightStatus())) {//状态为"1",代表状态为"开"
                    if (relayManager.close(roomPropertyDo.getLightIp(), Integer.parseInt(roomPropertyDo.getLightPort()),// 关灯
                            roomPropertyDo.getLightIndex())) {
                        RoomPropertyDo record = new RoomPropertyDo();
                        record.setId(roomPropertyDo.getId());
                        record.setLightStatus("0");// 关灯成功，跟新状态为"关"
                        DomainUtil.setCommonValueForUpdate(record, currentUserService.getPvgInfo());
                        roomPropertyDoMapperExt.updateByPrimaryKeySelective(record);
                        actionResult.setContent("关闭房间电源成功");

                        // 写日志
                        logService.insertLog(CommConfig.LOG_MODULE.ROOM.getModule(),
                                CommConfig.LOG_TYPE.UPDATE.getType(),
                                currentUserService.getPvgInfo().getName() + "  关闭房间电源成功");
                        return actionResult;
                    }
                } else if ("0".equals(roomPropertyDo.getLightStatus())) {//状态为"0",代表状态为"关"
                    if (relayManager.open(roomPropertyDo.getLightIp(), Integer.parseInt(roomPropertyDo.getLightPort()),// 开灯
                            roomPropertyDo.getLightIndex())) {
                        RoomPropertyDo record = new RoomPropertyDo();
                        record.setId(roomPropertyDo.getId());
                        record.setLightStatus("1");// 开灯成功，跟新状态为"开"
                        DomainUtil.setCommonValueForUpdate(record, currentUserService.getPvgInfo());
                        roomPropertyDoMapperExt.updateByPrimaryKeySelective(record);
                        actionResult.setContent("打开房间电源成功");

                        // 写日志
                        logService.insertLog(CommConfig.LOG_MODULE.ROOM.getModule(),
                                CommConfig.LOG_TYPE.UPDATE.getType(),
                                currentUserService.getPvgInfo().getName() + "  打开房间电源成功");
                        return actionResult;
                    }
                }
            } else if (type.equals("2")) {// 打开换气扇
                if (StringUtil.isEmpty(roomPropertyDo.getWindIp()) || StringUtil.isEmpty(roomPropertyDo.getWindPort()) || null == roomPropertyDo.getWindRoad()) {
                    actionResult.setContent("换气扇ip、port、road未填写完整");
                    actionResult.setSuccess(false);
                    return actionResult;
                }
                if ("1".equals(roomPropertyDo.getWindStatus())) {//状态为"1",代表状态为"开"
                    if (relayManager.close(roomPropertyDo.getWindIp(), Integer.parseInt(roomPropertyDo.getWindPort()),// 关换气扇
                            roomPropertyDo.getWindRoad())) {
                        RoomPropertyDo record = new RoomPropertyDo();
                        record.setId(roomPropertyDo.getId());
                        record.setWindStatus("0");// 关换气扇成功，跟新状态为"关"
                        DomainUtil.setCommonValueForUpdate(record, currentUserService.getPvgInfo());
                        roomPropertyDoMapperExt.updateByPrimaryKeySelective(record);
                        actionResult.setContent("关换气扇成功");

                        // 写日志
                        logService.insertLog(CommConfig.LOG_MODULE.ROOM.getModule(),
                                CommConfig.LOG_TYPE.UPDATE.getType(),
                                currentUserService.getPvgInfo().getName() + "  关换气扇成功");
                        return actionResult;
                    }
                } else if ("0".equals(roomPropertyDo.getWindStatus())) {//状态为"0",代表状态为"关"
                    if (relayManager.open(roomPropertyDo.getWindIp(), Integer.parseInt(roomPropertyDo.getWindPort()),// 开换气扇
                            roomPropertyDo.getWindRoad())) {
                        RoomPropertyDo record = new RoomPropertyDo();
                        record.setId(roomPropertyDo.getId());
                        record.setWindStatus("1");// 开换气扇成功，跟新状态为"开"
                        DomainUtil.setCommonValueForUpdate(record, currentUserService.getPvgInfo());
                        roomPropertyDoMapperExt.updateByPrimaryKeySelective(record);
                        actionResult.setContent("开换气扇成功");

                        // 写日志
                        logService.insertLog(CommConfig.LOG_MODULE.ROOM.getModule(),
                                CommConfig.LOG_TYPE.UPDATE.getType(),
                                currentUserService.getPvgInfo().getName() + "  开换气扇成功");
                        return actionResult;
                    }
                }
            } else if (type.equals("3")) {// 打开智能墙体
                if (StringUtil.isEmpty(roomPropertyDo.getWallIp()) || StringUtil.isEmpty(roomPropertyDo.getWallPort()) || null == roomPropertyDo.getWallRoad()) {
                    actionResult.setContent("智能墙体ip、port、road未填写完整");
                    actionResult.setSuccess(false);
                    return actionResult;
                }
                relayManager.openV2(roomPropertyDo.getWallIp(), Integer.parseInt(roomPropertyDo.getWallPort()),// 打开智能墙体
                        roomPropertyDo.getWallRoad());
                relayManager.closeV2(roomPropertyDo.getWallIp(), Integer.parseInt(roomPropertyDo.getWallPort()), roomPropertyDo.getWallRoad());//关闭
                actionResult.setContent("打开智能墙体成功");
                // 写日志
                logService.insertLog(CommConfig.LOG_MODULE.ROOM.getModule(), CommConfig.LOG_TYPE.UPDATE.getType(),
                        currentUserService.getPvgInfo().getName() + "  打开智能墙体成功");
                return actionResult;
            }
        }
        actionResult.setSuccess(false);// 操作失败
        actionResult.setContent("操作失败");
        return actionResult;
    }

    /**
     * LED屏展示 讯、询问室
     *
     * @param registerId
     */
    public void distributeRoomLedShow(Long registerId, Long roomId) {
        String title = " ";
        String content = " ";
        RoomPropertyDo roomPropertyDo = roomPropertyDoMapperExt.selectByPrimaryKey(roomId);
        String roomType = roomPropertyDo.getRoomType();
        if (roomType.equals("1")) {// 等候室
            distributeWaitRoomShow(roomId);//等候室可能有多人，展示方式特殊
            return;
        } else {
            title = roomPropertyDo.getRoomName();
        }
        PersonRegisterDo personRegister = personRegisterService.getPersonRegister(registerId);
        content = "嫌疑人：" + personRegister.getName() + "  主办民警：" + personRegister.getHostPoliceName() + "   开始时间: " + DateUtil.formatFull(new Date(), DateUtil.FORMAT_FULL);
        ledManager.showContent(roomPropertyDo.getLedIp(), title, content);
    }

    /**
     * LED屏展示等候室
     */
    @Override
    public void distributeWaitRoomShow(Long roomId) {
        String title = " ";
        String content = " ";
        RoomPropertyDo roomPropertyDo = roomPropertyDoMapperExt.selectByPrimaryKey(roomId);
        title = roomPropertyDo.getRoomName();
        List<PersonRegisterDo> list = roomRecordService.getPersonRegisterByrid(roomId, "0");// 获取所有在此房间的人（非历史状态）
        if (CollectionUtils.isNotEmpty(list)) {
            for (PersonRegisterDo obj : list) {
                content += obj.getName() + " ";
            }
            content += "   ";// 循环展示避免首尾连接
            ledManager.showContent(roomPropertyDo.getLedIp(), title, content);
        } else {
            ledManager.showContent(roomPropertyDo.getLedIp(), title, CommConfig.LED_SHOW_FREE);
        }

    }

    /**
     * 走廊LED展示
     */
    @Override
    public String corridorLedShow(Long registerId, Long roomId) {
        String content = " ";
        RoomPropertyDo roomPropertyDo = roomPropertyDoMapperExt.selectByPrimaryKey(roomId);
        PersonRegisterDo personRegister = personRegisterService.getPersonRegister(registerId);
        content = personRegister.getName() + "  " + roomPropertyDo.getRoomName() + "   ";
        //现场环境没有配置主屏LED  先注释掉  TODO；huangt
//        ledManager.showContent(ip, roomPropertyDo.getRoomName(), content);
        return content;
    }

    /**
     * 之前待过的房间，跟新LED展示（离开房间之后去掉这个人的名字展示）
     */
    public void updateLedAfterDistribute(Long roomId) {
        RoomPropertyDo roomPropertyDo = roomPropertyDoMapperExt.selectByPrimaryKey(roomId);
        String roomType = roomPropertyDo.getRoomType();
        if (roomType.equals("1")) {//如果为等候室
            distributeWaitRoomShow(roomId);
            return;
        } else {                    //如果为讯、询问室
            ledManager.showContent(roomPropertyDo.getLedIp(), roomPropertyDo.getRoomName(), CommConfig.LED_SHOW_FREE);// 则展示人员信息为空（讯、询问室只审问一人）
        }
    }

    /**
     * 将讯、询问室状态置为空闲（这是改变之前房间的状态，现在房间不变）
     */
    public void updateRoomStatusForInquiryRoom(List<RoomPropertyDo> list, Long roomId) {
        for (RoomPropertyDo roomPropertyDo : list) {
            if ((!"1".equals(roomPropertyDo.getRoomType())) && (roomId != roomPropertyDo.getId())) {// 当房间类型不是等候室的时候
                RoomPropertyDo record = new RoomPropertyDo();
                record.setId(roomPropertyDo.getId());
                record.setRoomStatus("0");// 将房间类型置为“空闲”
                DomainUtil.setCommonValueForUpdate(record, currentUserService.getPvgInfo());
                roomPropertyDoMapperExt.updateByPrimaryKeySelective(record);
            }
        }
    }

    /**
     * 清空房间（清除嫌疑人，作历史状态）
     */
    @Override
    public void releaseRoom(Long roomId) {
        List<RoomRecordDo> list = roomRecordService.getRoomRecordByrid(roomId, "0");//找到所有在该房间的非历史状态记录数据
        if (CollectionUtils.isNotEmpty(list)) {
            for (RoomRecordDo roomRecordDo : list) {
                roomRecordService.updateRoomRecordHistory(roomRecordDo.getRoomId(), roomRecordDo.getRegisterId());//将该房间所有在关人员置为历史状态
            }
        }
        RoomPropertyDo record = new RoomPropertyDo();
        record.setId(roomId);
        record.setRoomStatus("0");// 空闲状态
        DomainUtil.setCommonValueForUpdate(record, currentUserService.getPvgInfo());
        roomPropertyDoMapperExt.updateByPrimaryKeySelective(record);
        RoomPropertyDo roomPropertyDo = roomPropertyDoMapperExt.selectByPrimaryKey(roomId);

        // 写日志
        logService.insertLog(CommConfig.LOG_MODULE.ROOM.getModule(), CommConfig.LOG_TYPE.UPDATE.getType(),
                currentUserService.getPvgInfo().getName() + "  释放了房间" + roomPropertyDo.getRoomName());
        try {
            if (!CommConfig.ROOM_TYPE.WAIT_ROOM.getType().equals(roomPropertyDo.getRoomType())) {
                relayManager.closeV2(roomPropertyDo.getLightIp(), Integer.parseInt(roomPropertyDo.getLightPort()), roomPropertyDo.getLightIndex());//关灯
                relayManager.closeV2(roomPropertyDo.getWindIp(), Integer.parseInt(roomPropertyDo.getWindPort()), roomPropertyDo.getWindRoad());//关风扇
                RoomPropertyDo roomInfo = new RoomPropertyDo();
                roomInfo.setId(roomPropertyDo.getId());
                roomInfo.setLightStatus("0");// 关灯后，跟新状态为"关"
                roomInfo.setWindStatus("0");//关换气扇后，跟新状态为"关"
                DomainUtil.setCommonValueForUpdate(roomInfo, currentUserService.getPvgInfo());
                roomPropertyDoMapperExt.updateByPrimaryKeySelective(roomInfo);
            }
            ledManager.showContent(roomPropertyDo.getLedIp(), roomPropertyDo.getRoomName(), CommConfig.LED_SHOW_FREE);
        } catch (Exception e) {
            logger.warn("释放房间设备控制异常", e);
        }

    }

    /**
     * 正式临时出办案区时释放房间
     *
     * @param registerId
     */
    @Override
    public void outReleaseRoomByRid(Long registerId) {
        if (null != registerId) {
            // 获取现在呆在哪个房间
            List<RoomPropertyDo> list = roomRecordService.getRoomPropertyByprid(registerId, "0");
            // 把人员呆过的房间置为历史
            roomRecordService.updateRoomRecordHistory(registerId);
            if (CollectionUtils.isNotEmpty(list)) {
                updateRoomStatusForOut(list, list.get(0).getId());// 将改变之前待过的房间状态（针对讯、询问室、等候室）
                updateLedAfterDistribute(list.get(0).getId());// 之前待过的房间，跟新LED展示
            }
        }
    }

    /**
     * 将讯、询问室状态置为空闲，等候室的记录更新为
     */
    public void updateRoomStatusForOut(List<RoomPropertyDo> list, Long roomId) {
        for (RoomPropertyDo roomPropertyDo : list) {
            if ((!"1".equals(roomPropertyDo.getRoomType()))) {// 当房间类型不是等候室的时候
                RoomPropertyDo record = new RoomPropertyDo();
                record.setId(roomPropertyDo.getId());
                record.setRoomStatus("0");// 将房间类型置为“空闲”
                DomainUtil.setCommonValueForUpdate(record, currentUserService.getPvgInfo());
                roomPropertyDoMapperExt.updateByPrimaryKeySelective(record);
            }
        }
    }

    @Override
    public RoomPropertyDo getRoomPropertyByRoomNo(String roomNo) throws BizException {
        RoomPropertyDoExample example = new RoomPropertyDoExample();
        RoomPropertyDoExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        criteria.andRoomNoEqualTo(roomNo);

        List<RoomPropertyDo> roomProperties = roomPropertyDoMapperExt.selectByExample(example);

        if (CollectionUtils.isEmpty(roomProperties)) {
            throw new BizException(String.format("未查询到房间号:%s", roomNo));
        }
        return roomProperties.get(0);
    }

	/**
	 * 分配等候室，检查是否符合分配等候室的规则，返回结果
	 */
	@Override
	public ActionResult<String> checkRoomWaitDistribution(Long registerId, Long roomId) {
		ActionResult<String> result = new ActionResult<>();
		result.setSuccess(true);
		PersonRegisterDo personRegisterDo = personRegisterService.getPersonRegister(registerId); // 登记信息

		if (personRegisterDo != null) {
			String sex = personRegisterDo.getSex();// 性别
			String alarmNumber = personRegisterDo.getAlarmNumber();// 警情编号

			/* 性别规则检查 */
			List<PersonRegisterDo> list_person = roomRecordService.getPersonRegisterByrid(roomId, "0"); // 获取等候室所有关押人员信息
			if (CollectionUtils.isNotEmpty(list_person)) {
				for (PersonRegisterDo personRegisterDo_ : list_person) {
					String sex_ = personRegisterDo_.getSex();// 性别
					if (!sex_.equals(sex)) {
						result.setSuccess(false);
						result.setContent("性别不同！不能分配到同一个等候室！");
						return result;
					}
				}
			}

			/* 满员检查 */
			// int roomMax = roomPropertyDo.getRoomMax(); // 房间的最大人数
//			int count = roomRecordService.getPersonCount(roomId);
//			if (roomMax <= count) {
//				result.setSuccess(false);
//				result.setContent("该等候室已满！请选择其他等候室");
//				return result;
//			}

			/* 同案检查 */
			if (StringUtils.isNotBlank(alarmNumber)) {// 警情编号不为空，进行同案预警
				if (CollectionUtils.isNotEmpty(list_person)) {
					for (PersonRegisterDo personRegisterDo_ : list_person) {
						String alarmNumber_ = personRegisterDo_.getAlarmNumber();// 警情编号
						if (!"".equals(alarmNumber)) {
							if (alarmNumber.equals(alarmNumber_)) {
								result.setSuccess(false);
								result.setContent("警情编号相同！同一案件的人不能分配到同一个等候室！");
								return result;
							}
						}
					}
				}

			}
		}
		return result;
	}
}
