package com.dsw.iot;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dsw.iot.model.RelatedPhoneConfirmDo;
import com.dsw.iot.service.RelatedPhoneConfirmService;
import com.dsw.iot.util.BizException;

public class TestRelatedPhoneConfirm extends BaseTest{

	private RelatedPhoneConfirmDo relatedPhoneConfirmDo;
	
	@Autowired
	private RelatedPhoneConfirmService relatedPhoneConfirmService;
	
	@Before
    public void before() {
		// 家属通知
		relatedPhoneConfirmDo = new RelatedPhoneConfirmDo();
		relatedPhoneConfirmDo.setApplyName("张三");
		relatedPhoneConfirmDo.setApplyReason("fsgm");
		relatedPhoneConfirmDo.setCallName("wangwu");
		relatedPhoneConfirmDo.setStatus("3");
		relatedPhoneConfirmDo.setPoliceName("1234567890");
		relatedPhoneConfirmDo.setTalkTime("20");
		relatedPhoneConfirmDo.setTel("12312312312");
		relatedPhoneConfirmDo.setRegisterId(Long.parseLong("3"));
		relatedPhoneConfirmDo.setRemark("1234567890");
		relatedPhoneConfirmDo.setCreateTime(new Date());
		relatedPhoneConfirmDo.setCreateUser("13");
    }

    @Test
    public void RelatedPhoneConfirm() throws BizException {
		stepOne();
		stepTwo();
    }

	

	/**
	 * 第二步
	 *
	 * @throws BizException
	 */
	private void stepTwo() throws BizException {
		System.err.println("家属通知第二步----开始");
		relatedPhoneConfirmDo.setUpdateUser("王五1");
		relatedPhoneConfirmDo.setUpdateTime(new Date());
		relatedPhoneConfirmDo.setAuditContent("b");
		relatedPhoneConfirmDo.setAuditUser("张三2");
		relatedPhoneConfirmDo.setAuditTime(new Date());
		relatedPhoneConfirmDo.setStatus("2");
		//relatedPhoneConfirmService.saveRelatedPhoneConfirm(relatedPhoneConfirmDo);
		System.out.println("家属通知第二步----结束        申请人员：" + relatedPhoneConfirmDo.getApplyName() + " id:" + relatedPhoneConfirmDo.getId());
		System.err.println("家属通知第二步----结束");
	}

	/**
	 * 第一步
	 *
	 * @throws BizException
	 */
	public void stepOne() throws BizException {
		System.err.println("家属通知第一步----开始");
		//relatedPhoneConfirmService.saveRelatedPhoneConfirm(relatedPhoneConfirmDo);
		System.err.println(
				"家属通知第一步----结束        申请人员：" + relatedPhoneConfirmDo.getApplyName() + " id:" + relatedPhoneConfirmDo.getId());
	}

    @After
    public void after() {
        Assert.assertNotNull("执行失败", relatedPhoneConfirmDo.getId());
    }

}
