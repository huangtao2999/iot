package com.dsw.iot;


import com.dsw.iot.model.AttachDo;
import com.dsw.iot.model.UrineTestInfoDo;
import com.dsw.iot.service.FileUploadService;
import com.dsw.iot.service.UrineTestInfoService;
import com.dsw.iot.util.BizException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class TestUrineTestInfo extends BaseTest {


    //    private PersonRegisterRequest personRegisterRequest;
    private UrineTestInfoDo urineTestInfoDo;
    private AttachDo attachDo;
    @Autowired
    private UrineTestInfoService urineTestInfoService;

    @Autowired
    private FileUploadService fileUploadService;

    @Before
    public void before() {
        urineTestInfoDo = new UrineTestInfoDo();
        urineTestInfoDo.setName("单元测试002");
        Date d = new Date();
        urineTestInfoDo.setBottleb("22");
        urineTestInfoDo.setBirthDate(d);
        urineTestInfoDo.setBottlea("11");
        urineTestInfoDo.setReportType("cs1");
        urineTestInfoDo.setCardNo("1001");
        urineTestInfoDo.setRegisterId((long) 1001);
        urineTestInfoDo.setSex("1");
        urineTestInfoDo.setCheckWay("p1");
        String base64 = null;//把File("D://文件名称.jpg")文件转换成base64
        attachDo = fileUploadService.uploadBase64(base64, "文件名称.jpg");
    }

    @Test
    public void register() throws BizException {
//    	urineTestInfoService.savePerson(urineTestInfoDo);
        //Long fileId = attachDo.getId();//文件ID
        //{cardPicId:fileId}
    }

    @After
    public void after() {

    }

}
