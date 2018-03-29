package com.dsw.iot;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dsw.iot.model.DelayConfirmDo;
import com.dsw.iot.service.DelayConfirmService;
import com.dsw.iot.util.BizException;
import com.dsw.iot.vo.DelayConfirmVo;

public class TestDelayConfirm extends BaseTest{

	private DelayConfirmDo delayConfirmDo;
	private DelayConfirmVo delayConfirmVo;
	
	@Autowired
	private DelayConfirmService delayConfirmService;
	
	@Before
    public void before() {
		// 延期置留
		delayConfirmVo = new DelayConfirmVo();
		delayConfirmVo.setApplyName("张三");
		delayConfirmVo.setApplyReason("fsgm");
		delayConfirmVo.setApplyTime(new Date());
		delayConfirmVo.setStatus("3");
		delayConfirmVo.setRegisterId(Long.parseLong("3"));
		delayConfirmVo.setRemark("1234567890");
		delayConfirmVo.setCreateTime(new Date());
		delayConfirmVo.setCreateUser("13");
		delayConfirmVo.setRoleId(Long.parseLong("1"));
    }

    @Test
    public void DelayConfirm() throws BizException {
		stepOne();
		stepTwo();
    }

	

	/**
	 * 第二步
	 *
	 * @throws BizException
	 */
	private void stepTwo() throws BizException {
		System.err.println("延期置留第二步----开始");
		delayConfirmDo.setUpdateUser("王五1");
		delayConfirmDo.setUpdateTime(new Date());
		delayConfirmDo.setAuditContent("b");
		delayConfirmDo.setAuditUser("张三2");
		delayConfirmDo.setAuditTime(new Date());
		delayConfirmDo.setStatus("2");
		delayConfirmService.checkDelayConfirm(delayConfirmDo);
		System.out.println("延期置留第二步----结束        申请人员：" + delayConfirmDo.getApplyName() + " id:" + delayConfirmDo.getId());
		System.err.println("延期置留第二步----结束");
	}

	/**
	 * 第一步
	 *
	 * @throws BizException
	 */
	public void stepOne() throws BizException {
		System.err.println("延期置留第一步----开始");
		//delayConfirmDo = delayConfirmService.saveDelayConfirm(delayConfirmVo);
		System.err.println(
				"延期置留第一步----结束        申请人员：" + delayConfirmDo.getApplyName() + " id:" + delayConfirmDo.getId());
	}

    @After
    public void after() {
        Assert.assertNotNull("执行失败", delayConfirmDo.getId());
    }

}
