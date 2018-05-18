package com.dsw.iot;

import com.dsw.iot.model.AlarmManageDo;
import com.dsw.iot.service.AlarmManageService;
import com.dsw.iot.util.BizException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class TestAlarmManage extends BaseTest{

	private AlarmManageDo alarmManageDo;
	
	@Autowired
	private AlarmManageService alarmManageService;
	
	@Before
    public void before() {
		// 预警信息
		alarmManageDo = new AlarmManageDo();
		alarmManageDo.setActivePerson("张三1");
		alarmManageDo.setAlarmType("2");
		alarmManageDo.setAlarmLevel("1");
		alarmManageDo.setAlarmTime(new Date());
		alarmManageDo.setStatus("2");
		alarmManageDo.setCardId("1234567890");
		alarmManageDo.setContent("a");
		alarmManageDo.setDept("hn");
		alarmManageDo.setRegisterId(Long.parseLong("3"));
		alarmManageDo.setRemark("1234567890");
		
    }

    @Test
    public void alarmManage() throws BizException {
		stepOne();
		stepTwo();
    }

	

	/**
	 * 第二步
	 *
	 * @throws BizException
	 */
	private void stepTwo() throws BizException {
		System.err.println("预警审批第二步----开始");
		alarmManageDo.setUpdateUser("王五1");
		alarmManageDo.setHandleContent("b");
		alarmManageDo.setHandleMethod("1");
		alarmManageDo.setHandlePerson("张三2");
		alarmManageDo.setHandlerNo("121212");
		alarmManageDo.setStatus("2");
		alarmManageService.saveAlarmManage(alarmManageDo);
		System.out.println("预警审批第二步----结束        触发 人员：" + alarmManageDo.getActivePerson() + "       id:" + alarmManageDo.getId());
		System.err.println("预警审批第二步----结束");
	}

	/**
	 * 第一步
	 *
	 * @throws BizException
	 */
	public void stepOne() throws BizException {
		System.err.println("预警审批第一步----开始");
		alarmManageService.saveAlarmManage(alarmManageDo);
		System.err.println(
				"预警审批第一步----结束        触发 人员：" + alarmManageDo.getActivePerson() + "       id:" + alarmManageDo.getId());
	}

    @After
    public void after() {
        Assert.assertNotNull("执行失败", alarmManageDo.getId());
    }

}
