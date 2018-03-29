package com.dsw.iot;

import com.dsw.iot.dto.GoodsRegisterRequest;
import com.dsw.iot.dto.InjuryRegisterRequest;
import com.dsw.iot.dto.PersonRegisterRequest;
import com.dsw.iot.manager.LedManager;
import com.dsw.iot.manager.RelayManager;
import com.dsw.iot.model.*;
import com.dsw.iot.service.*;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BASE64Util;
import com.dsw.iot.util.BizException;
import com.dsw.iot.vo.DelayConfirmVo;
import com.dsw.iot.vo.PersonRegisterVo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试整套流程
 *
 * @author zhang
 */
public class TestAllMerged extends BaseTest {

    // 人员信息
    private PersonRegisterRequest personRegisterRequest;
    private PersonRegisterDo personRegisterDo;
    private PersonRegisterVo personRegisterVo;

    // 储物柜
    private LockerDo lockerDo;
    // 伤情信息
    private InjuryRegisterDo injuryRegisterDo;
    // 物品信息
    private GoodsRegisterDo goodsRegisterDo;
    // 抓获信息
    private CatchInfoDo catchInfoDo;
    // 陪同人信息
    private PersonRelatedDo relatedDo;
    private List<PersonRelatedDo> relatedDos;
    // 卡芯片管理
    private CardManageDo cardManageDo;
    // 文件上传管理
    private AttachDo attachDo;
    // 电子围栏预警
    private AlarmManageDo alarmManageDo;

    // 延期留置
    private DelayConfirmDo delayConfirmDo;
    private DelayConfirmVo delayConfirmVo;

    // 家属通知
    private RelatedPhoneConfirmDo relatedPhoneConfirmDo;
    // 尿检
    private UrineTestInfoDo urineTestInfoDo;

    // 出办案区审批
    private OutConfirmDo outConfirmDo;
    // private OutConfirmVo outConfirmVo;

    @Autowired
    private PersonRegisterService personRegisterService;
    @Autowired
    private CardManageService cardManageService;
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private InjuryRegisterService injuryRegisterService;
    @Autowired
    private GoodsRegisterService goodsRegisterService;
    @Autowired
    private LockerService lockerService;
    @Autowired
    private RoomPropertyService roomPropertyService;
    @Autowired
    private RoomRecordService roomRecordService;
    @Autowired
    private LedManager ledManager;
    @Autowired
    private RelayManager relayManager;
    @Autowired
    private AlarmManageService alarmManageService;
    @Autowired
    private DelayConfirmService delayConfirmService;
    @Autowired
    private RemindService remindService;
    @Autowired
    private RelatedPhoneConfirmService relatedPhoneConfirmService;
    @Autowired
    private UrineTestInfoService urineTestInfoService;
    @Autowired
    private OutConfirmService outConfirmService;
    @Autowired
    private OutFetchService outFetchService;

    /**
     * 准备参数
     */
    @Before
    public void before() {
        // 人员
        personRegisterRequest = new PersonRegisterRequest();
        personRegisterRequest.setName("陈辉");
        personRegisterRequest.setCardNo("422320111129292923");
        personRegisterRequest.setCardType("1");
        personRegisterRequest.setExtraName("辉辉");
        personRegisterRequest.setSex("man");
        personRegisterRequest.setBirthDate(new Date());
        personRegisterRequest.setEducationLevel("1");
        personRegisterRequest.setPolitical("1");
        personRegisterRequest.setPersonType("1");
        personRegisterRequest.setInType("1");
        personRegisterRequest.setCaseType("1");
        personRegisterRequest.setSpecialGroup("3");
        personRegisterRequest.setPeopleType("1");
//        personRegisterRequest.setInTime(new Date());
        personRegisterRequest.setInReason("1");

    }

    /**
     * 人员登记第三步
     *
     * @throws BizException
     */
    private void stepThree() throws BizException {
        System.err.println("=========start==========人员登记第三步----开始");
        // 抓获信息
        catchInfoDo = new CatchInfoDo();
        catchInfoDo.setCatchTime(new Date());
        catchInfoDo.setHappenedPlace("光谷广场");
        // 人员信息
        relatedDos = new ArrayList();
        relatedDo = new PersonRelatedDo();
        relatedDo.setType("1");
        relatedDo.setName("张三");
        relatedDo.setSex("man");
        relatedDos.add(relatedDo);

        relatedDo = new PersonRelatedDo();
        relatedDo.setType("2");
        relatedDo.setName("小红");
        relatedDo.setSex("woman");
        relatedDos.add(relatedDo);

        personRegisterRequest = new PersonRegisterRequest();
        personRegisterRequest.setCatchInfo(catchInfoDo);
        personRegisterRequest.setRelatedInfo(relatedDos);
        personRegisterVo = personRegisterService.saveStepThree(personRegisterRequest);
        System.out.println("抓获信息id：" + personRegisterVo.getCatchInfo().getId());
        System.out.println("陪同人员第一条信息id：" + personRegisterVo.getRelatedInfo().get(0).getId());
        System.err.println("=========start==========人员登记第三步----结束");
    }

    /**
     * 人员登记第二步，入办案区信息
     *
     * @throws BizException
     */
    private void stepTwo() throws BizException {
        System.err.println("=========start==========人员登记第二步----开始");
        personRegisterRequest.setHostPoliceName("黄岛");
        personRegisterRequest.setHostPoliceCardNo("110001");
        personRegisterRequest.setJoinPoliceName("协办民警");
        personRegisterRequest.setJoinPoliceCardNo("110002");
        // 手环编号
        personRegisterRequest.setBraceletNo("110100");
        // 是否检查
        personRegisterRequest.setDoCheck("1");
        personRegisterRequest.setIsWarned("1");
        // 延期
        personRegisterRequest.setDelayHour("8");
        personRegisterRequest.setIsDelay("0");
        personRegisterRequest.setInType("1");// 到案方式

        System.out.println("分配储物柜中......");
        lockerDo = getFreeLocker(2);// M类型
        System.out.println("分配储物柜完成，id:" + lockerDo.getId() + " 编号：" + lockerDo.getLockerNo());
        openLocker(lockerDo, 1);
        System.out.println("开柜完成");
        openLocker(lockerDo, 0);
        System.out.println("关柜完成");

        List<InjuryRegisterRequest> injuryList = getInjuryDataList();// 登记伤情信息
        injuryRegisterService.saveInjuryInfo(injuryList, personRegisterRequest.getId());

        List<GoodsRegisterRequest> goodsList = getGoodsDataList();// 登记物品信息
        goodsRegisterService.saveGoodsInfo(goodsList, personRegisterRequest.getId());

        // 更新人员信息
        personRegisterVo = personRegisterService.updatePerson(personRegisterRequest);
        System.out.println("嫌疑人：" + personRegisterVo.getPersonInfo().getName());
        System.out.println("主办民警：" + personRegisterVo.getPersonInfo().getHostPoliceName());
        System.out.println("手环编号：" + personRegisterVo.getPersonInfo().getBraceletNo());
        //更新储物柜为占用
        lockerDo.setUseStatus("1");
        lockerService.saveLocker(lockerDo);

        System.out.println("手环激活中......");
//        cardManageService.activateCard(personRegisterRequest.getBraceletNo());
        System.out.println("手环已激活，编号：" + personRegisterRequest.getBraceletNo());

        System.out.println("分配等候室.......");
        ActionResult<String> roomActionResult = roomPropertyService.getRoomWaitAutoDistribution(personRegisterVo.getPersonInfo().getId());
        System.out.println("等候室：" + roomActionResult.getContent());

        System.out.println("更新led........");
        System.out.println(roomActionResult.getContent());
        RoomPropertyDo roomPropertyDo = roomPropertyService
                .getRoomProperty(Long.parseLong(roomActionResult.getContent()));

        List<PersonRegisterDo> roomPerList = roomRecordService.getPersonRegisterByrid(
                Long.parseLong(roomActionResult.getContent()),
                personRegisterVo.getPersonInfo().getId(), "0");

        System.out.println("title=" + roomPropertyDo.getRoomName() + "; name=" + roomPerList.get(0).getName());
        ledUpdate(roomPropertyDo.getRoomName(), roomPerList.get(0).getName());
        System.out.println("led已更新");

        System.err.println("=========start==========人员登记第二步----结束");
    }

    /**
     * 人员登记第一步
     *
     * @throws BizException
     */
    public void stepOne() throws BizException {
        System.err.println("=========start==========人员登记第一步----开始");

        attachDo = upload("E:\\Java-Work\\whcskj\\95-上传测试文件\\2.JPG", "person.jpg", "上传人员照片完成：");
        personRegisterRequest.setPersonImgIds(attachDo.getId() + "");

        attachDo = upload("E:\\Java-Work\\whcskj\\95-上传测试文件\\3.jpg", "idcard.jpg", "上传身份证照片完成：");
        personRegisterRequest.setCardImgIds(attachDo.getId() + "");

        personRegisterVo = personRegisterService.savePerson(personRegisterRequest);
        System.out.println("人员姓名：" + personRegisterVo.getPersonInfo().getName());
        System.out.println("人员id:" + personRegisterVo.getPersonInfo().getId());
        System.err.println("=========start==========人员登记第一步----结束");
    }

    /**
     * 主类
     *
     * @throws BizException
     */
    @Test
    public void register() throws BizException {
        // 人员登记
        // stepOne();// 基本信息
        // stepTwo();// 入办案区信息
        // stepThree();// 抓获信息，陪同人信息

        // alarmTest((long) 3);// 电子围栏预警 + 预警处理 param:人员id
        // delayTest((long) 3);// 延期留置审批 + 待办提醒
        // relatedPhoneTest((long) 3);// 家属通知（无审批）
        // urineTest((long) 3);// 尿检信息录入

        // disXWRoom((long) 3, (long) 28);// 分配讯问室

        // outConfirmTest((long) 3);// 出办案区申请（正式） //出办案区申请（临时）审批
        // outFetch((long) 3, "110100");// 出所取物

        // 更新定位信息为历史
        // 更新人员表，状态未已出所，
        // personRegisterService.
    }

    // !!!!!!!!!人员出办案区取物，未更新人员表信息，
    public void outFetch(Long registerId, String cardId) {
        System.err.println("出办案区取物----开始");
        //查询人员信息
        personRegisterVo = personRegisterService.getPersonRegister(cardId);

        //查询物品信息
        List<GoodsRegisterRequest> goodsList = goodsRegisterService
                .getGoodsInfoByRid(registerId);
        System.out.println("物品数量：" + goodsList.size());

        // 扣押物品

        // 返还物品

        // outFetchService.outArea(registerId, cardId);//
        // 更新手环信息，更新定位历史记录，更新人员信息为已出所
        System.err.println("出办案区取物----结束");
    }

    // 出办案区申请
    public void outConfirmTest(Long registerId) {
        // outConfirmVo = new OutConfirmVo();
        // outConfirmVo.setRegisterId(registerId);
        // outConfirmVo.setApplyName("张三");
        // outConfirmVo.setApplyReason("fsgm");
        // outConfirmVo.setApplyType("1");
        // outConfirmVo.setStatus("3");
        // outConfirmVo.setEscortPolice("23567");
        // outConfirmVo.setIsEscort("1");
        // outConfirmVo.setIsHistory("1");
        // outConfirmVo.setIsReturn("1");
        // outConfirmVo.setOutType("1");// 正式出所
        // outConfirmVo.setOutTime(new Date());
        // outConfirmVo.setRemark("1234567890");
        // outConfirmVo.setCreateTime(new Date());
        // outConfirmVo.setCreateUser("13");
        // outConfirmVo.setRoleId(Long.parseLong("1"));

        System.err.println("出办案区申请----开始");
        // outConfirmDo = outConfirmService.saveOutConfirm(outConfirmVo);
        System.out.println("申请人员：" + outConfirmDo.getApplyName());

        outConfirmDo.setUpdateUser("王五1");
        outConfirmDo.setUpdateTime(new Date());
        outConfirmDo.setAuditContent("b");
        outConfirmDo.setAuditUser("张三2");
        outConfirmDo.setAuditTime(new Date());
        outConfirmDo.setStatus("2");
        // outConfirmService.checkOutConfirm(outConfirmDo);

        System.err.println("出办案区申请----处理完成：" + outConfirmDo.getStatus());
    }

    // 分配讯问室！！！！！！有问题
    public void disXWRoom(Long registerId, Long roomId) {
        System.err.println("分配讯问室----开始");
        ActionResult<String> roomActionResult = roomPropertyService.getRoomDistribution(registerId, roomId);
        System.err.println("讯问室：" + roomActionResult.getContent());

        RoomPropertyDo roomPropertyDo = roomPropertyService
                .getRoomProperty(roomId);

        List<PersonRegisterDo> roomPerList = roomRecordService.getPersonRegisterByrid(
                roomId, registerId, "0");

        System.out.println("title=" + roomPropertyDo.getRoomName() + "; name=" + roomPerList.get(0).getName());
        ledUpdate(roomPropertyDo.getRoomName(), roomPerList.get(0).getName());
    }

    // 尿检信息
    public void urineTest(Long registerId) {
        urineTestInfoDo = new UrineTestInfoDo();
        urineTestInfoDo.setRegisterId(registerId);// 人员id
        urineTestInfoDo.setName("单元测试002");
        Date d = new Date();
        urineTestInfoDo.setBottleb("22");
        urineTestInfoDo.setBirthDate(d);
        urineTestInfoDo.setBottlea("11");
        urineTestInfoDo.setReportType("cs1");
        urineTestInfoDo.setCardNo("110001");
        urineTestInfoDo.setSex("1");
        urineTestInfoDo.setCheckWay("p1");

        System.err.println("尿检信息----开始");
//		urineTestInfoService.savePerson(urineTestInfoDo);
        System.err.println("尿检信息----结束，被检测人：" + urineTestInfoDo.getName());
    }

    /**
     * 家属通知
     */
    public void relatedPhoneTest(Long registerId) {
        relatedPhoneConfirmDo = new RelatedPhoneConfirmDo();
        relatedPhoneConfirmDo.setRegisterId(registerId);// 人员id
        relatedPhoneConfirmDo.setApplyName("张三");
        relatedPhoneConfirmDo.setApplyReason("fsgm");
        relatedPhoneConfirmDo.setCallName("wangwu");
        relatedPhoneConfirmDo.setStatus("3");
        relatedPhoneConfirmDo.setPoliceName("1234567890");
        relatedPhoneConfirmDo.setTalkTime("20");
        relatedPhoneConfirmDo.setTel("12312312312");
        relatedPhoneConfirmDo.setRemark("1234567890");
        relatedPhoneConfirmDo.setCreateTime(new Date());
        relatedPhoneConfirmDo.setCreateUser("13");

        System.err.println("家属通知----开始");
        //relatedPhoneConfirmService.saveRelatedPhoneConfirm(relatedPhoneConfirmDo);
        System.out.println("申请人员：" + relatedPhoneConfirmDo.getApplyName());

        relatedPhoneConfirmDo.setUpdateUser("王五1");
        relatedPhoneConfirmDo.setUpdateTime(new Date());
        relatedPhoneConfirmDo.setAuditContent("b");
        relatedPhoneConfirmDo.setAuditUser("张三2");
        relatedPhoneConfirmDo.setAuditTime(new Date());
        relatedPhoneConfirmDo.setStatus("2");
        //relatedPhoneConfirmService.saveRelatedPhoneConfirm(relatedPhoneConfirmDo);
        System.out.println("审批人员：" + relatedPhoneConfirmDo.getAuditUser());
        System.err.println("家属通知----结束");
    }

    /**
     * 延期留置审批
     *
     * @param registerId
     */
    public void delayTest(Long registerId) {
        delayConfirmVo = new DelayConfirmVo();
        delayConfirmVo.setRegisterId(registerId);// 人员id
        delayConfirmVo.setApplyName("张三");
        delayConfirmVo.setApplyReason("fsgm");
        delayConfirmVo.setApplyTime(new Date());
        delayConfirmVo.setStatus("3");
        delayConfirmVo.setRemark("1234567890");
        delayConfirmVo.setCreateTime(new Date());
        delayConfirmVo.setCreateUser("13");
        delayConfirmVo.setRoleId(Long.parseLong("1"));
        delayConfirmVo.setDelayHour("12");// 留置时间

        System.err.println("延期留置----开始");

        // delayConfirmDo =
        // delayConfirmService.saveDelayConfirm(delayConfirmVo);
        System.out.println("申请人：" + delayConfirmDo.getApplyName() + "; 申请时间：" + delayConfirmDo.getDelayHour());

        // 查询待办
        List<RemindDo> resultList = remindService.listRemind(Long.valueOf("1"));
        System.out.println("待办任务：" + resultList.get(0).getContent());

        System.out.println("开始审批延期留置.......");

        delayConfirmDo.setUpdateUser("王五1");
        delayConfirmDo.setUpdateTime(new Date());
        delayConfirmDo.setAuditContent("b");
        delayConfirmDo.setAuditUser("张三2");
        delayConfirmDo.setAuditTime(new Date());
        delayConfirmDo.setStatus("1");// 3-待审核；2-审核不通过；1-审核通过
        delayConfirmService.checkDelayConfirm(delayConfirmDo);

        System.err.println("延期留置审批完成----审批结果：" + delayConfirmDo.getStatus());
    }

    /**
     * 电子围栏预警测试
     *
     * @param registerId
     */
    public void alarmTest(Long registerId) {
        // 预警信息
        alarmManageDo = new AlarmManageDo();
        alarmManageDo.setRegisterId(registerId);// 人员id
        alarmManageDo.setActivePerson("张三1");
        alarmManageDo.setAlarmType("2");
        alarmManageDo.setAlarmLevel("1");
        alarmManageDo.setAlarmTime(new Date());
        alarmManageDo.setStatus("2");
        alarmManageDo.setCardId("1234567890");
        alarmManageDo.setContent("a");
        alarmManageDo.setDept("hn");
        alarmManageDo.setRemark("1234567890");

        System.err.println("电子围栏预警----开始");
        alarmManageService.saveAlarmManage(alarmManageDo);

        System.out.println("触发 人员：" + alarmManageDo.getActivePerson());
        System.out.println("开始处理围栏预警.......");

        alarmManageDo.setUpdateUser("王五1");
        alarmManageDo.setHandleContent("b");
        alarmManageDo.setHandleMethod("1");
        alarmManageDo.setHandlePerson("张三2");
        alarmManageDo.setHandlerNo("121212");
        alarmManageDo.setStatus("2");
        alarmManageService.saveAlarmManage(alarmManageDo);

        System.err.println("电子围栏预警处理完毕----处理人：" + alarmManageDo.getHandlePerson());
    }

    // 开柜关柜
    public void openLocker(LockerDo lockerDo, int openOrClose) {
        String ip = lockerDo.getInIp();
        int port = lockerDo.getInPort();
        int road = lockerDo.getInRoad();
        if (openOrClose == 1) {
            relayManager.open(ip, port, road);
        } else {
            relayManager.close(ip, port, road);
        }
    }

    // led修改
    private void ledUpdate(String title, String content) {
        String ip = "192.168.1.29";
        ledManager.showContent(ip, title, content);
    }

    // 获得空闲储物柜
    private LockerDo getFreeLocker(int type) throws BizException {
        return lockerService.getFreeLockerByType(type + "");
    }

    // 上传图片
    public AttachDo upload(String path, String fileName, String msg) {
        String base64 = BASE64Util.getBase64ByPath(path);
        attachDo = fileUploadService.uploadBase64(base64, fileName);
        System.out.println(msg + attachDo.getFileName() + attachDo.getId());
        return attachDo;
    }

    // 获得伤情数据集合
    public List<InjuryRegisterRequest> getInjuryDataList() {
        List<InjuryRegisterRequest> list = new ArrayList<>();
        InjuryRegisterRequest iRequest = new InjuryRegisterRequest();
        iRequest.setPart("头部");
        iRequest.setRemark("撞电线杆了");

        String ids = "";
        attachDo = upload("E:\\Java-Work\\whcskj\\95-上传测试文件\\1.JPG", "injury1.jpg", "上传头部伤情1完成：");
        ids = attachDo.getId() + "";
        attachDo = upload("E:\\Java-Work\\whcskj\\95-上传测试文件\\3.jpg", "injury2.jpg", "上传头部伤情2完成：");
        ids += "," + attachDo.getId();
        iRequest.setSourceIds(ids);

        list.add(iRequest);
        return list;
    }

    // 获得物品数据集合
    private List<GoodsRegisterRequest> getGoodsDataList() {
        List<GoodsRegisterRequest> list = new ArrayList<>();
        GoodsRegisterRequest gRequest = new GoodsRegisterRequest();
        gRequest.setGoodsName("钱包");
        gRequest.setGoodsNum("1");
        gRequest.setGoodsDesc("里面有100块，还有身份证，驾驶证");

        String ids = "";
        attachDo = upload("E:\\Java-Work\\whcskj\\95-上传测试文件\\1.JPG", "goods1.jpg", "上传物品照片1完成：");
        ids = attachDo.getId() + "";
        attachDo = upload("E:\\Java-Work\\whcskj\\95-上传测试文件\\3.jpg", "goods2.jpg", "上传物品照片2完成：");
        ids += "," + attachDo.getId();
        gRequest.setSourceIds(ids);

        list.add(gRequest);
        return list;
    }
}
