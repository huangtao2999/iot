package com.dsw.iot;

import com.dsw.iot.dto.PersonRegisterRequest;
import com.dsw.iot.model.AttachDo;
import com.dsw.iot.model.CatchInfoDo;
import com.dsw.iot.model.PersonRegisterDo;
import com.dsw.iot.model.PersonRelatedDo;
import com.dsw.iot.service.FileUploadService;
import com.dsw.iot.service.PersonRegisterService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BASE64Util;
import com.dsw.iot.util.BizException;
import com.dsw.iot.vo.PersonRegisterVo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 人员登记单元测试
 *
 * @author huangt
 * @create 2018-03-01 19:33
 **/
public class TestPersonRegister extends BaseTest {

    private PersonRegisterRequest personRegisterRequest;
    private PersonRegisterDo personRegisterDo;
    private PersonRegisterVo personRegisterVo;
    private CatchInfoDo catchInfoDo;
    private PersonRelatedDo relatedDo;
    private List<PersonRelatedDo> relatedDos;
    private ActionResult<PersonRegisterVo> actionResult;
    private AttachDo attachDo;

    @Autowired
    private PersonRegisterService personRegisterService;
    @Autowired
    private FileUploadService fileUploadService;

    @Before
    public void before() {
        // 人员
        personRegisterRequest = new PersonRegisterRequest();
        personRegisterRequest.setName("测试002");
        personRegisterRequest.setCardNo("422320111129292923");
        personRegisterRequest.setCardType("1");
        personRegisterRequest.setExtraName("002测试");
        personRegisterRequest.setSex("man");
        personRegisterRequest.setBirthDate(new Date());
        personRegisterRequest.setEducationLevel("1");
        personRegisterRequest.setPolitical("1");
        personRegisterRequest.setPersonType("1");
        personRegisterRequest.setInType("1");
        personRegisterRequest.setCaseType("1");
        personRegisterRequest.setSpecialGroup("3");
        personRegisterRequest.setPeopleType("1");
//		personRegisterRequest.setInTime(new Date());
        personRegisterRequest.setInReason("1");

        personRegisterRequest.setHostPoliceName("主办民警");
        personRegisterRequest.setHostPoliceCardNo("110001");
        personRegisterRequest.setJoinPoliceName("协办民警");
        personRegisterRequest.setJoinPoliceCardNo("110002");
        // 手环编号
        personRegisterRequest.setBraceletNo("110100");
        // 是否检查
        personRegisterRequest.setDoCheck("1");
        personRegisterRequest.setIsWarned("1");
        // 储物柜编号
        personRegisterRequest.setLockerId((long) 4);
        personRegisterRequest.setLockerNo("S0001");
        // 延期
        personRegisterRequest.setDelayHour("8");
        personRegisterRequest.setIsDelay("0");

    }

    @Test
    public void register() throws BizException {
        // stepOne();
        // stepTwo();
        // stepThree();
        // 文件上传
        String path = "E:\\Java-Work\\whcskj\\95-上传测试文件\\2.JPG";
        String base64 = BASE64Util.getBase64ByPath(path);
        attachDo = fileUploadService.uploadBase64(base64, "test.jpg");
    }

    /**
     * 第三步
     *
     * @throws BizException
     */
    private void stepThree() throws BizException {
        System.err.println("人员登记第三步----开始");
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

        personRegisterRequest.setCatchInfo(catchInfoDo);
        personRegisterRequest.setRelatedInfo(relatedDos);
        personRegisterVo = personRegisterService.saveStepThree(personRegisterRequest);
        System.err.println("人员登记第三步----结束");
    }

    /**
     * 第二步
     *
     * @throws BizException
     */
    private void stepTwo() throws BizException {
        System.err.println("人员登记第二步----开始");
        personRegisterRequest.setInType("1");
        personRegisterService.updatePerson(personRegisterRequest);
        System.err.println("人员登记第二步----结束");
    }

    /**
     * 第一步
     *
     * @throws BizException
     */
    public void stepOne() throws BizException {
        System.err.println("人员登记第一步----开始");
        personRegisterService.savePerson(personRegisterRequest);
        System.err.println(
                "人员登记第一步----结束         人员：" + personRegisterRequest.getName() + "     id:"
                        + personRegisterRequest.getId());
    }

    @After
    public void after() {
        // Assert.assertNotNull("执行失败", personRegisterDo.getId());
        // Assert.assertNotNull("执行失败", actionResult.getContent());
    }
}
