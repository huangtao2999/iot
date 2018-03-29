package com.dsw.iot;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dsw.iot.model.OutConfirmDo;
import com.dsw.iot.service.OutConfirmService;
import com.dsw.iot.util.BizException;

public class TestOutConfirm extends BaseTest {

    private OutConfirmDo outConfirmDo;
	// private OutConfirmVo outConfirmVo;

    @Autowired
    private OutConfirmService outConfirmService;

    @Before
    public void before() {
        // 人员出所
		// outConfirmVo = new OutConfirmVo();
		// outConfirmVo.setApplyName("张三");
		// outConfirmVo.setApplyReason("fsgm");
		// outConfirmVo.setApplyType("1");
		// ;
		// outConfirmVo.setStatus("3");
		// outConfirmVo.setEscortPolice("23567");
		// outConfirmVo.setIsEscort("1");
		// outConfirmVo.setIsHistory("1");
		// outConfirmVo.setIsReturn("1");
		// outConfirmVo.setOutType("1");
		// outConfirmVo.setOutTime(new Date());
		// outConfirmVo.setRegisterId(Long.parseLong("3"));
		// outConfirmVo.setRemark("1234567890");
		// outConfirmVo.setCreateTime(new Date());
		// outConfirmVo.setCreateUser("13");
		// outConfirmVo.setRoleId(Long.parseLong("1"));
    }

    @Test
    public void outConfirm() throws BizException {
        stepOne();
        stepTwo();
    }


    /**
     * 第二步
     *
     * @throws BizException
     */
    private void stepTwo() throws BizException {
        System.err.println("人员出所第二步----开始");
        outConfirmDo.setUpdateUser("王五1");
        outConfirmDo.setUpdateTime(new Date());
        outConfirmDo.setAuditContent("b");
        outConfirmDo.setAuditUser("张三2");
        outConfirmDo.setAuditTime(new Date());
        outConfirmDo.setStatus("2");
//		outConfirmService.checkOutConfirm(outConfirmDo);
        System.out.println("人员出所第二步----结束        申请人员：" + outConfirmDo.getApplyName() + " id:" + outConfirmDo.getId());
        System.err.println("人员出所第二步----结束");
    }

    /**
     * 第一步
     *
     * @throws BizException
     */
    public void stepOne() throws BizException {
        System.err.println("人员出所第一步----开始");
        //outConfirmDo = outConfirmService.saveOutConfirm(outConfirmVo);
        System.err.println(
                "人员出所第一步----结束        申请人员：" + outConfirmDo.getApplyName() + " id:" + outConfirmDo.getId());
    }

    @After
    public void after() {
        Assert.assertNotNull("执行失败", outConfirmDo.getId());
    }

}
