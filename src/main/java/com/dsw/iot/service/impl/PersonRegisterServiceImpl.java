package com.dsw.iot.service.impl;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.PersonRegisterDoMapperExt;
import com.dsw.iot.dal.RoomRecordDoMapperExt;
import com.dsw.iot.dto.DictionaryRequest;
import com.dsw.iot.dto.GoodsRegisterRequest;
import com.dsw.iot.dto.InjuryRegisterRequest;
import com.dsw.iot.dto.PersonRegisterRequest;
import com.dsw.iot.manager.LedManager;
import com.dsw.iot.model.*;
import com.dsw.iot.service.*;
import com.dsw.iot.util.*;
import com.dsw.iot.vo.PersonListBaseShowVo;
import com.dsw.iot.vo.PersonListClickVo;
import com.dsw.iot.vo.PersonRegisterVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PersonRegisterServiceImpl implements PersonRegisterService {
    protected static final Logger logger = Logger.getLogger(PersonRegisterServiceImpl.class);
    @Autowired(required = false)
    PersonRegisterDoMapperExt personRegisterDoMapperExt;
    @Autowired
    CurrentUserService currentUserService;
    @Autowired
    LogService logService;
    @Autowired
    CatchInfoService catchInfoService;
    @Autowired
    PersonRelatedService personRelatedService;
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    InjuryRegisterService injuryRegisterService;
    @Autowired
    GoodsRegisterService goodsRegisterService;
    @Autowired
    DictionaryService dictionaryService;
    @Autowired
    RoomRecordService roomRecordService;
    @Autowired(required = false)
    RoomRecordDoMapperExt roomRecordDoMapperExt;
    @Autowired
    OutConfirmService outConfirmService;
    @Autowired
    CardManageService cardManageService;
    @Autowired
    RoomPropertyService roomPropertyService;
    @Autowired
    private LedManager ledManager;
    @Autowired
    private PersonTraceService personTraceService;

    @Value("${capture.file.path}")
    private String tempPath;

    /**
     * 分页查询注册表主表
     */
    @Override
    public PageResult<PersonRegisterDo> queryPage(PersonRegisterRequest param) {
        // 分页返回集合
        PageResult<PersonRegisterDo> pageResult = new PageResult<>();
        // 查询条件的容器
        PersonRegisterDoExample example = new PersonRegisterDoExample();
        // 新建查询条件
        PersonRegisterDoExample.Criteria criteria = example.createCriteria();
        // 添加条件
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        // 入办案区时间区间
        if (null != param.getInStartTime()) {
            criteria.andInTimeGreaterThanOrEqualTo(param.getInStartTime());
        }
        if (null != param.getInEndTime()) {
            criteria.andInTimeLessThanOrEqualTo(param.getInEndTime());
        }
        // 出办案区时间区间
        if (null != param.getOutStartTime()) {
            criteria.andOutTimeGreaterThanOrEqualTo(param.getOutStartTime());
        }
        if (null != param.getOutEndTime()) {
            criteria.andOutTimeLessThanOrEqualTo(param.getOutEndTime());
        }
        // 入办案区原因
        if (StringUtils.isNoneBlank(param.getInReason())) {
            criteria.andInReasonLike("%" + param.getInReason() + "%");
        }
        // 出办案区原因
        if (StringUtils.isNoneBlank(param.getOutReason())) {
            criteria.andOutReasonLike("%" + param.getOutReason() + "%");
        }
        // 姓名
        if (StringUtils.isNotBlank(param.getName())) {
            criteria.andNameLike("%" + param.getName() + "%");
        }
        // 单位
        if (StringUtils.isNotBlank(param.getWorkUnit())) {
            criteria.andWorkUnitLike("%" + param.getWorkUnit() + "%");
        }
        // 办案区位置
        if (StringUtils.isNotBlank(param.getHandlingArea())) {
            criteria.andHandlingAreaEqualTo(param.getHandlingArea());
        }
        // 人员状态
        if (StringUtils.isNotBlank(param.getPersonStatus())) {
            criteria.andPersonStatusEqualTo(param.getPersonStatus());
        }
        // 性别
        if (StringUtils.isNotBlank(param.getSex())) {
            criteria.andSexEqualTo(param.getSex());
        }
        //证件类型
        if (StringUtils.isNotEmpty(param.getCardType())) {
            criteria.andCardTypeEqualTo(param.getCardType());
        }
        //证件号码
        if (StringUtils.isNotEmpty(param.getCardNo())) {
            criteria.andCardNoLike("%" + param.getCardNo() + "%");
        }
        // 排序
        if (StringUtils.isBlank(param.getOrderByClause())) {
            example.setOrderByClause("update_time desc,create_time desc");
        } else {
            example.setOrderByClause(param.getOrderByClause());
        }
        // 分页
        int count = personRegisterDoMapperExt.countByExample(example);
        PageDto pageDto = new PageDto(param.getPage(), param.getLimit(), count);
        example.setPageDto(pageDto);
        List<PersonRegisterDo> list = personRegisterDoMapperExt.selectByExample(example);
        // 返回集合
        pageResult.setData(list);
        pageResult.setCount(count);
        return pageResult;
    }

    /**
     * 第一步，新增编辑人员信息 返回实体到前台，有图片信息所以通过VO传回页面
     */
    @Override
    @Transactional
    public PersonRegisterVo savePerson(PersonRegisterRequest personRegisterRequest) throws BizException {
        PersonRegisterDo personRegisterDo = new PersonRegisterDo();// sql操作实体
        BeanUtils.copyProperties(personRegisterRequest, personRegisterDo);
        if (null == personRegisterDo.getId()) {
            // 新增校验，确保某些字段不能为空
            validateAdd(personRegisterDo);
            personRegisterDo.setInTime(new Date());// 入区时间确定
            // 插入新数据
            DomainUtil.setCommonValueForCreate(personRegisterDo, currentUserService.getPvgInfo());
            personRegisterDoMapperExt.insertSelective(personRegisterDo);
        } else {
            // 同样是停留在第一阶段，再次保存
            // 编辑
            DomainUtil.setCommonValueForUpdate(personRegisterDo, currentUserService.getPvgInfo());
            personRegisterDoMapperExt.updateByPrimaryKeySelective(personRegisterDo);
        }
        // 更新附件信息
        fileUploadService.updateAttach(personRegisterRequest.getPersonImgIds(), personRegisterDo.getId(),
                CommConfig.ATTACH_TYPE.PERSON_REGISTER_PERSON_IMGS.getType(),
                CommConfig.ATTACH_TYPE.PERSON_REGISTER_PERSON_IMGS.getName());// 人员照片
        fileUploadService.updateAttach(personRegisterRequest.getCardImgIds(), personRegisterDo.getId(),
                CommConfig.ATTACH_TYPE.PERSON_REGISTER_CARD_IMGS.getType(),
                CommConfig.ATTACH_TYPE.PERSON_REGISTER_CARD_IMGS.getName());// 身份证
        // 返回实体对象
        PersonRegisterVo personRegisterVo = new PersonRegisterVo();
        BeanUtils.copyProperties(personRegisterDoMapperExt.selectByPrimaryKey(personRegisterDo.getId()),
                personRegisterRequest);
        personRegisterVo.setPersonInfo(personRegisterRequest);
        return personRegisterVo;
    }

    /**
     * 校验人员登记入库--新增校验
     *
     * @param personRegisterDo
     * @throws BizException
     */
    private void validateAdd(PersonRegisterDo personRegisterDo) throws BizException {
        personRegisterDo.setIsDelay("0"); // 是否延期关押（默认否，8小时）
        personRegisterDo.setDelayHour("8"); // 默认8小时
        personRegisterDo.setInTime(new Date()); // 入办案区时间
        // 身份明确就判断不能为空的字段
        if (StringUtils.isBlank(personRegisterDo.getUnknownPerson())
                || "0".equals(personRegisterDo.getUnknownPerson())) {
            if (StringUtils.isEmpty(personRegisterDo.getCardNo())) {
                throw new BizException("证件号码不能为空!");
            }
            if (StringUtils.isEmpty(personRegisterDo.getCardType())) {
                throw new BizException("证件类型不能为空!");
            }
            if (StringUtils.isEmpty(personRegisterDo.getName())) {
                throw new BizException("姓名不能为空");
            }
            if (null == personRegisterDo.getBirthDate()) {
                throw new BizException("出生日期不能为空");
            }
        }

        // 判断新增的人员，证件号，是否有已经在办案区的，有就不能新增
        PersonRegisterDoExample example = new PersonRegisterDoExample();
        PersonRegisterDoExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        criteria.andCardNoEqualTo(personRegisterDo.getCardNo());
        if (StringUtils.isNotBlank(personRegisterDo.getCardType())) {
            criteria.andCardTypeEqualTo(personRegisterDo.getCardType());
        } else {
            criteria.andCardTypeEqualTo("1");
        }
        criteria.andPersonStatusNotEqualTo("2");// 人员状态非出办案区
        List<PersonRegisterDo> list = personRegisterDoMapperExt.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            throw new BizException("该人员还在办案区内，不能重复新增，请核实");
        }

    }

    /**
     * 第二步，编辑人员入办案区信息，录入伤情表数据，物品表数据（前端拼接对象，这里进行插入和更新）
     */
    @Override
    @Transactional
    public PersonRegisterVo updatePerson(PersonRegisterRequest personRegisterRequest) throws BizException {
        PersonRegisterDo personRegisterDo = personRegisterDoMapperExt.selectByPrimaryKey(personRegisterRequest.getId());
        String status = personRegisterDo.getPersonStatus();
        //手环需要判断是否有在所人员   和  在card_manager中 存在 才能保保存  否则抛异常  huangto TODO;
        String oldBraceletNo = personRegisterDo.getBraceletNo();
        String newBraceletNo = personRegisterRequest.getBraceletNo();
        if ((StringUtils.isEmpty(oldBraceletNo) && StringUtils.isNotEmpty(personRegisterRequest.getBraceletNo()))
                || (StringUtils.isNotEmpty(oldBraceletNo) && !oldBraceletNo.equals(personRegisterRequest.getBraceletNo()))) {
            CardManageDo newCardManageDo = cardManageService.getCarManageByCarNo(newBraceletNo);
            if (newCardManageDo == null) {
                throw new BizException("该手环不存在!");
            }
            PersonRegisterVo personRegisterVo = getPersonRegister(newBraceletNo);
            if (null != personRegisterVo) {
                throw new BizException("该手环已被使用!");
            }
            //手环编号，手环ID  紊乱 huangt  TODO；
            cardManageService.activateCard(newCardManageDo.getId());
            //更新手环的时候 旧手环需要停用
            if (!StringUtils.isEmpty(oldBraceletNo)) {
                CardManageDo oldCardManageDo = cardManageService.getCarManageByCarNo(oldBraceletNo);
                cardManageService.deactivateCard(oldCardManageDo.getId());
            }
        }
        //更新人员数据
        BeanUtils.copyProperties(personRegisterRequest, personRegisterDo);
        // 判断人员状态，不用更新
        if (StringUtils.isNotBlank(status)) {
            personRegisterDo.setPersonStatus(status);
        }
        DomainUtil.setCommonValueForUpdate(personRegisterDo, currentUserService.getPvgInfo());
        personRegisterDoMapperExt.updateByPrimaryKeySelective(personRegisterDo);
        //更新伤情数据
        List<InjuryRegisterRequest> injuryList = injuryRegisterService
                .saveInjuryInfo(personRegisterRequest.getInjuryInfo(), personRegisterRequest.getId());
        //更新物品数据
        List<GoodsRegisterRequest> goodsList = goodsRegisterService
                .saveGoodsInfo(personRegisterRequest.getGoodsInfo(), personRegisterRequest.getId());
        // 返回实体对象
        PersonRegisterVo personRegisterVo = new PersonRegisterVo();
        BeanUtils.copyProperties(personRegisterDoMapperExt.selectByPrimaryKey(personRegisterDo.getId()),
                personRegisterRequest);
        personRegisterVo.setPersonInfo(personRegisterRequest);
        personRegisterVo.setInjuryInfo(injuryList);
        personRegisterVo.setGoodsInfo(goodsList);
        return personRegisterVo;
    }

    /**
     * 删除（更改is_deleted状态）
     *
     * @param ids
     */
    @Override
    @Transactional
    public void deletePerson(String ids) {
        PersonRegisterDo personRegisterDo = new PersonRegisterDo();
        if (StringUtils.isNotBlank(ids)) {
            String[] idsArray = ids.split(",");
            for (String id : idsArray) {
                if (StringUtils.isNotBlank(id)) {
                    personRegisterDo.setId(Long.parseLong(id));
                    DomainUtil.setCommonValueForDelete(personRegisterDo, currentUserService.getPvgInfo());
                    personRegisterDoMapperExt.deleteByPrimaryKey(personRegisterDo);
                }
            }
        }
    }

    /**
     * 通过主键查单条记录
     */
    @Override
    public PersonRegisterDo getPersonRegister(Long id) {
        return personRegisterDoMapperExt.selectByPrimaryKey(id);
    }

    /**
     * 通过主键查单条记录，同时查询附件信息，以及伤情信息和物品信息
     */
    @Override
    public PersonRegisterVo getPersonAllInfo(Long id) {
        PersonRegisterVo personRegisterVo = new PersonRegisterVo();//总的返回参数
        //查询人员
        personRegisterVo.setPersonInfo(getPersonInfo(personRegisterDoMapperExt.selectByPrimaryKey(id)));
        // 抓获信息
        personRegisterVo.setCatchInfo(catchInfoService.getCatchInfoByRid(id));
        // 查询伤情
        personRegisterVo.setInjuryInfo(injuryRegisterService.getInjuryInfoByRid(id));
        // 查询物品
        personRegisterVo.setGoodsInfo(goodsRegisterService.getGoodsInfoByRid(id));
        // 查询陪同人信息
        personRegisterVo.setRelatedInfo(personRelatedService.getRelatedListByRid(id));

        return personRegisterVo;
    }

    /**
     * 通过手环编号查询记录
     */
    @Override
    public PersonRegisterVo getPersonRegister(String braceletNo) {
        PersonRegisterVo personRegisterVo = null;// 总的返回参数
        // 查询条件的容器
        PersonRegisterDoExample example = new PersonRegisterDoExample();
        // 新建查询条件
        PersonRegisterDoExample.Criteria criteria = example.createCriteria();
        // 添加条件
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        criteria.andBraceletNoEqualTo(braceletNo);
        List<String> statusList = new ArrayList<>();
        statusList.add("1");
        statusList.add("2");
        statusList.add("3");
        criteria.andPersonStatusIn(statusList);// 在办案区的1-在办案区；2-申请出办案区；3-待出办案区；4-临时出办案区；5-正式出办案区；
        example.setOrderByClause("create_time desc, update_time desc");
        List<PersonRegisterDo> list = personRegisterDoMapperExt.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            personRegisterVo = new PersonRegisterVo();
            Long id = list.get(0).getId();
            // 查询人员
            personRegisterVo.setPersonInfo(getPersonInfo(list.get(0)));
            // 查询伤情
            personRegisterVo.setInjuryInfo(injuryRegisterService.getInjuryInfoByRid(id));
            // 查询物品
            personRegisterVo.setGoodsInfo(goodsRegisterService.getGoodsInfoByRid(id));
        }
        return personRegisterVo;
    }

    /**
     * 更新签名信息
     */
    @Override
    public void updatePersonRegisterRsign(Long id, String value) {
        PersonRegisterDo personRegisterDo = new PersonRegisterDo();
        personRegisterDo.setId(id);
        personRegisterDo.setReceiverSign(value);
        personRegisterDoMapperExt.updateByPrimaryKeySelective(personRegisterDo);

    }


    /**
     * 保存抓获信息和陪同人信息
     *
     * @throws BizException
     */
    @Override
    @Transactional
    public PersonRegisterVo saveStepThree(PersonRegisterRequest personRegisterRequest)
            throws BizException {
        PersonRegisterVo personRegisterVo = new PersonRegisterVo(); // 返回集合
        List<PersonRelatedDo> resRelatedList = new ArrayList<>();// 陪同人信息
        Long registerId = personRegisterRequest.getId(); // 人员id
        // 保存抓获信息
        CatchInfoDo catchInfo = personRegisterRequest.getCatchInfo();
        if (null != catchInfo) {
            catchInfo.setRegisterId(registerId); // 绑定该人员
            catchInfoService.saveCatchInfo(catchInfo);
        }
        // 保存陪同人信息
        List<PersonRelatedDo> relatedInfo = personRegisterRequest.getRelatedInfo();
        if (CollectionUtils.isNotEmpty(relatedInfo)) {
            for (PersonRelatedDo personRelatedDo : relatedInfo) {
                validateRelated(personRelatedDo);
                personRelatedDo.setRegisterId(registerId); // 绑定该人员
                personRelatedService.saveRelated(personRelatedDo);
                resRelatedList.add(personRelatedDo);
            }
        }
        // 返回信息
        personRegisterVo.setPersonInfo(
                getPersonInfo(personRegisterDoMapperExt.selectByPrimaryKey(personRegisterRequest.getId())));
        personRegisterVo.setCatchInfo(catchInfo);
        personRegisterVo.setRelatedInfo(resRelatedList);
        return personRegisterVo;
    }

    /**
     * 校验陪同人
     *
     * @param personRelatedDo
     * @throws BizException
     */
    public void validateRelated(PersonRelatedDo personRelatedDo) throws BizException {
        if (StringUtils.isBlank(personRelatedDo.getType())) {
            throw new BizException("陪同人类型不能为空");
        }
    }

    /**
     * 获得人员信息，写成公共的来调用
     *
     * @param personRegisterDo
     */
    private PersonRegisterRequest getPersonInfo(PersonRegisterDo personRegisterDo) {
        PersonRegisterRequest personInfo = new PersonRegisterRequest();
        Long id = personRegisterDo.getId();
        BeanUtils.copyProperties(personRegisterDo, personInfo);
        personInfo.setPersonImgIds(
                fileUploadService.getAttachIds(id, CommConfig.ATTACH_TYPE.PERSON_REGISTER_PERSON_IMGS.getType()));
        personInfo.setCardImgIds(
                fileUploadService.getAttachIds(id, CommConfig.ATTACH_TYPE.PERSON_REGISTER_CARD_IMGS.getType()));
        return personInfo;
    }

    /**
     * 主键，点击列表，查找人员基本信息和流程信息
     *
     * @throws BizException
     */
    @Override
    public PersonListClickVo getPersonListClickVo(Long id) throws BizException {
        PersonListClickVo res = new PersonListClickVo();// 总的返回参数
        if (null == id) {
            return null;
        }
        // 查询人员
        PersonRegisterDo personRegisterDo = personRegisterDoMapperExt.selectByPrimaryKey(id);
        PersonRegisterRequest pRequest = getPersonInfo(personRegisterDo);

        // 赋值
        res.setId(pRequest.getId());// 主键id
        res.setPersonStatus(pRequest.getPersonStatus());
        res.setPhotoUrl(pRequest.getPersonImgIds());// 图片的id

        // 右侧详情信息
        res.setShowInfo(getShowInfo(pRequest));
        // 获得可点击按钮
        res.setBtns(getActiveBtns(pRequest));
        // 获得流程时间
        res.setDoTask(getDoTask(pRequest));

        return res;
    }

    // 获得右侧详情信息
    private List<PersonListBaseShowVo> getShowInfo(PersonRegisterRequest pRequest) throws BizException {
        List<PersonListBaseShowVo> showInfo = new ArrayList<>();

        PersonListBaseShowVo pShow = new PersonListBaseShowVo();
        pShow.setName("姓名");
        pShow.setValue(pRequest.getName());
        showInfo.add(pShow);

        pShow = new PersonListBaseShowVo();
        pShow.setName("性别");
        pShow.setValue("man".equals(pRequest.getSex()) ? "男" : "女");
        showInfo.add(pShow);

        pShow = new PersonListBaseShowVo();
        pShow.setName("籍贯");
        pShow.setValue(pRequest.getDomicileArea());//户籍地区划
        showInfo.add(pShow);

        pShow = new PersonListBaseShowVo();
        pShow.setName("延期时间");
        pShow.setValue(pRequest.getDelayHour() + "小时");
        showInfo.add(pShow);

        pShow = new PersonListBaseShowVo();
        pShow.setName("手环编号");
        pShow.setValue(pRequest.getBraceletNo());
        showInfo.add(pShow);

        pShow = new PersonListBaseShowVo();
        pShow.setName("人员类型");
        pShow.setValue("");
        DictionaryRequest dictionaryRequest2 = new DictionaryRequest();
        dictionaryRequest2.setCode("PEOPLE_TYPE");
        List<DictionaryDo> dictionaryDos = dictionaryService.queryComboList(dictionaryRequest2);
        if (CollectionUtils.isNotEmpty(dictionaryDos)) {
            for (DictionaryDo dictionaryDo : dictionaryDos) {
                if (dictionaryDo.getCode().equals(pRequest.getPeopleType())) {
                    pShow.setValue(dictionaryDo.getName());
                    break;
                }
            }
        }
        showInfo.add(pShow);

        if (null != pRequest.getInTime()) {
            pShow = new PersonListBaseShowVo();
            pShow.setName("入所时间");
            SimpleDateFormat sf = new SimpleDateFormat(DateUtil.FORMAT_FULL);
            pShow.setValue(sf.format(pRequest.getInTime()));
            showInfo.add(pShow);
        }

        pShow = new PersonListBaseShowVo();
        pShow.setName("入所原因");
        pShow.setValue("");
        dictionaryRequest2 = new DictionaryRequest();
        dictionaryRequest2.setCode("PER_IN_REASON");
        dictionaryDos = dictionaryService.queryComboList(dictionaryRequest2);
        if (CollectionUtils.isNotEmpty(dictionaryDos)) {
            for (DictionaryDo dictionaryDo : dictionaryDos) {
                if (dictionaryDo.getCode().equals(pRequest.getInReason())) {
                    pShow.setValue(dictionaryDo.getName());
                    break;
                }
            }
        }
        showInfo.add(pShow);

        return showInfo;
    }

    /**
     * 获得可操作的按钮列表
     * <p>
     * delayButton【延期申请】、outConfirmButton【出所申请】、personInfoButton【人员登记表】、
     * phoneButton【电话使用】、personTraceButton【人员轨迹】、diskButton【光盘补录】、
     * drugButton【毒品信息】、urineTestButton【尿检信息】
     *
     * @param pRequest
     * @return
     */
    private String[] getActiveBtns(PersonRegisterRequest pRequest) {
        String personStatus = pRequest.getPersonStatus();// 1-在办案区；2-申请出办案区；3-待出办案区；4-临时出办案区；5-正式出办案区；
        // "delayButton", "outConfirmButton", "personInfoButton", "phoneButton",
        // "personTraceButton", "diskButton", "drugButton", "urineTestButton"
        String[] btns1 = {"delayButton", "outConfirmButton", "phoneButton", "personTraceButton", "diskButton",
                "drugButton", "urineTestButton"};// 1-在办案区
        String[] btns2 = {"delayButton", "phoneButton", "personTraceButton", "diskButton", "drugButton",
                "urineTestButton"};// 2-申请出办案区
        String[] btns3 = {"delayButton", "phoneButton", "personTraceButton", "diskButton", "drugButton",
                "urineTestButton"};// 3-待出办案区
        String[] btns4 = {"delayButton", "phoneButton", "personTraceButton", "diskButton", "drugButton",
                "urineTestButton"};// 4-临时出办案区
        String[] btns5 = {"personInfoButton", "phoneButton", "personInfoButton", "personTraceButton", "diskButton"};// 5-正式出办案区
        // 在办案区的才可以点
        if ("1".equals(personStatus)) {// 1-在办案区
            return btns1;
        } else if ("2".equals(personStatus)) {// 2-申请出办案区
            return btns2;
        } else if ("3".equals(personStatus)) {// 3-待出办案区
            return btns3;
        } else if ("4".equals(personStatus)) {// 4-临时出办案区
            return btns4;
        } else if ("5".equals(personStatus)) {// 5-正式出办案区
            return btns5;
        } else {
            return null;
        }
    }

    /**
     * 获得流程图时间
     *
     * @param pRequest
     * @return
     * @throws BizException
     */
    private List<PersonListBaseShowVo> getDoTask(PersonRegisterRequest pRequest) throws BizException {
        List<PersonListBaseShowVo> res = new ArrayList<>();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String roomFlag = "";

        // 入办案区，进来的人员都可以分配
        PersonListBaseShowVo pShow = new PersonListBaseShowVo();
        pShow.setName("01");
        pShow.setValue(f.format(pRequest.getInTime()));
        pShow.setCanClick(true);
        res.add(pShow);


        // 获得现在在哪个房间
        RoomRecordDoExample example = new RoomRecordDoExample();
        RoomRecordDoExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        criteria.andRegisterIdEqualTo(pRequest.getId());
        criteria.andIsHistoryEqualTo(CommConfig.IS_HISTORY.NO.getName());// 获取不是历史的
        example.setOrderByClause("create_time desc");
        List<RoomRecordDo> list = roomRecordDoMapperExt.selectByExample(example);

        // 获得历史到过哪些房间
        RoomRecordDoExample example2 = new RoomRecordDoExample();
        RoomRecordDoExample.Criteria criteria2 = example2.createCriteria();
        criteria2.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        criteria2.andRegisterIdEqualTo(pRequest.getId());
        criteria2.andIsHistoryEqualTo(CommConfig.IS_HISTORY.YES.getName());// 获取是历史的
        example2.setOrderByClause("create_time desc");
        List<RoomRecordDo> list_his = roomRecordDoMapperExt.selectByExample(example2);

        if (CollectionUtils.isNotEmpty(list)) {
            // 查询第一条记录的房间类型
            RoomPropertyDo roomPropertyDo = roomPropertyService.getRoomProperty(list.get(0).getRoomId());
            if (roomPropertyDo.getRoomType().equals(CommConfig.ROOM_TYPE.WAIT_ROOM.getType())) {
                // 等候室分配完毕
                pShow = new PersonListBaseShowVo();
                pShow.setName("02");
                pShow.setValue(f.format(list.get(0).getCreateTime()));
                pShow.setCanClick(true);
                pShow.setRoomId(list.get(0).getRoomId());
                pShow.setRoomName(roomPropertyDo.getRoomName());
                res.add(pShow);

                roomFlag = "2";// 其他类型房间标志
            } else {
                // 是其他房间
                pShow = new PersonListBaseShowVo();
                pShow.setName("03");
                pShow.setValue(f.format(list.get(0).getCreateTime()));
                pShow.setCanClick(true);
                pShow.setRoomId(list.get(0).getId());
                pShow.setRoomName(roomPropertyDo.getRoomName());
                res.add(pShow);

                roomFlag = "1";// 等候室标志
            }
        }
        // 绑定去过的历史房间
        if (CollectionUtils.isNotEmpty(list_his)) {
            for (RoomRecordDo recordDo : list_his) {
                RoomPropertyDo roomPropertyDo = roomPropertyService.getRoomProperty(recordDo.getRoomId());
                if (roomPropertyDo.getRoomType().equals(roomFlag)) {
                    pShow = new PersonListBaseShowVo();
                    pShow.setName(roomFlag == "1" ? "02" : "03");
                    pShow.setValue(f.format(recordDo.getCreateTime()));
                    pShow.setCanClick(false);
                    pShow.setRoomId(recordDo.getId());
                    pShow.setRoomName(roomPropertyDo.getRoomName());
                    res.add(pShow);
                    break;
                }
            }
        }


        // 出所，填了出所申请并且通过了，可以点出所取物
        List<OutConfirmDo> outConfirmList = outConfirmService.getOutConfirmByRid(pRequest.getId(), "1");
        if (CollectionUtils.isNotEmpty(outConfirmList)) {
            pShow = new PersonListBaseShowVo();
            pShow.setName("04");
            pShow.setCanClick(true);
            pShow.setValue(f.format(outConfirmList.get(0).getCreateTime()));
            res.add(pShow);
        }
        return res;
    }

    /**
     * 更新人员状态，主表
     *
     * @throws BizException
     */
    @Override
    @Transactional
    public boolean updatePersonStatus(Long id, String status) throws BizException {
        boolean flag = false;
        PersonRegisterDo personRegisterDo = new PersonRegisterDo();
        if (null == id || StringUtils.isBlank(status)) {
            throw new BizException("参数错误");
        } else {
            personRegisterDo.setId(id);
            personRegisterDo.setPersonStatus(status);
            // 编辑
            DomainUtil.setCommonValueForUpdate(personRegisterDo, currentUserService.getPvgInfo());
            int i = personRegisterDoMapperExt.updateByPrimaryKeySelective(personRegisterDo);
            if (i > 0) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 临时出所人员返回办案区，更新手环为激活状态
     *
     * @param personRegisterRequest
     * @return
     * @throws BizException
     */
    @Override
    @Transactional
    public ActionResult<String> personBack(PersonRegisterRequest personRegisterRequest) throws BizException {
        ActionResult<String> result = new ActionResult<>();
        if (null == personRegisterRequest.getId()) {
            throw new BizException("参数错误，未找到人员信息，请刷新重试");
        } else {
            Long registerId = personRegisterRequest.getId();
            PersonRegisterDo personRegisterDo = personRegisterDoMapperExt
                    .selectByPrimaryKey(registerId);
            // 只有临时出办案区才能确认回办案区，正式出的和还在的就抛异常
            // 1-在办案区；2-申请出办案区；3-待出办案区；4-临时出办案区；5-正式出办案区；
            if (!"4".equals(personRegisterDo.getPersonStatus())) {
                throw new BizException("只能操作临时出办案区的人员");
            }
            // 更新状态为在办案区
            personRegisterDo.setPersonStatus("1");
            DomainUtil.setCommonValueForUpdate(personRegisterDo, currentUserService.getPvgInfo());
            personRegisterDoMapperExt.updateByPrimaryKeySelective(personRegisterDo);
            // 更新手环为激活状态
            CardManageDo cardManageDo = cardManageService.getCarManageByCarNo(personRegisterDo.getBraceletNo());
            cardManageService.activateCard(cardManageDo.getId());
            // 更新审批表单里的返回时间为当前时间
            List<OutConfirmDo> oList = outConfirmService.getOutConfirmByRid(registerId, "1", "1");
            if (CollectionUtils.isNotEmpty(oList)) {
                outConfirmService.updateConfirmBackTime(oList.get(0).getId());
            }
        }
        result.setSuccess(true);
        result.setContent("人员状态更新完毕，请完善民警胸牌信息");
        return result;
    }

    /**
     * 人员台账接口
     */
    @Override
    public PersonRegisterVo getPersonRegisterForm(Long id) {
        PersonRegisterVo personRegisterVo = new PersonRegisterVo();// 总的返回参数
        // 查询人员
        personRegisterVo.setPersonInfo(getPersonInfo(personRegisterDoMapperExt.selectByPrimaryKey(id)));
        // 抓获信息
        personRegisterVo.setCatchInfo(catchInfoService.getCatchInfoByRid(id));
        // 查询伤情
        personRegisterVo.setInjuryInfo(injuryRegisterService.getInjuryInfoByRid(id));
        // 查询物品
        personRegisterVo.setGoodsInfo(goodsRegisterService.getGoodsInfoByRid(id));
        // 查询陪同人信息
        personRegisterVo.setRelatedInfo(personRelatedService.getRelatedListByRid(id));

        // 查询通过了的审批记录
        personRegisterVo.setOutInfo(outConfirmService.getOutConfirmByRid(id, "1"));

        // 查询人员轨迹
        personRegisterVo.setTraceInfo(personTraceService.queryList(id));

        return personRegisterVo;
    }

    /**
     * 把人脸的图片，下载下来，再上传到附件表
     */
    @Override
    public AttachDo downloadFaceImgToAttachFolder(String faceUrl) {
        AttachDo attachDo = null;
        try {
            String fileName = FileUtils.getFileName(faceUrl);
            String outFile = tempPath + "/" + fileName;
            // 下载图片
            HttpClientUtil.downloadByUrl(outFile, faceUrl);
            // 上传图片
            attachDo = fileUploadService.uploadFaceImg(outFile);
        } catch (Exception e) {
            logger.error("身份证照片下载失败!", e);
        }
        return attachDo;
    }

    /**
     * 自动分配等候室，并更新led主屏幕，更新等候室屏幕
     *
     * @throws BizException
     */
    @Override
    public ActionResult<String> autoSetWaitingRoom(Long id) throws BizException {
        ActionResult<String> result = new ActionResult<>();
        if (null == id) {
            throw new BizException("参数错误，未找到人员");
        }
        result = roomPropertyService.getRoomWaitAutoDistribution(id);
        if (result.isSuccess()) {
            if (StringUtils.isNotBlank(result.getContent())) {
                // 分配成功了等候室，更新led显示
                Long roomId = Long.parseLong(result.getContent());
                roomPropertyService.distributeWaitRoomShow(roomId);// 更新等候室led
                String content = roomPropertyService.corridorLedShow(id, roomId);// 更新走廊led
                result.setSuccess(true);
                result.setContent(content);
            }
        }
        return result;
    }

    /**
     * 获得用户基本信息，包括头像信息
     *
     * @param id
     * @return
     */
    @Override
    public PersonRegisterRequest getPersonRegisterInfo(Long id) {
        PersonRegisterRequest personInfo = new PersonRegisterRequest();
        if (null != id) {
            PersonRegisterDo personRegisterDo = personRegisterDoMapperExt.selectByPrimaryKey(id);
            BeanUtils.copyProperties(personRegisterDo, personInfo);
            personInfo.setPersonImgIds(
                    fileUploadService.getAttachIds(id, CommConfig.ATTACH_TYPE.PERSON_REGISTER_PERSON_IMGS.getType()));
            personInfo.setCardImgIds(
                    fileUploadService.getAttachIds(id, CommConfig.ATTACH_TYPE.PERSON_REGISTER_CARD_IMGS.getType()));
        }
        return personInfo;
    }
}
