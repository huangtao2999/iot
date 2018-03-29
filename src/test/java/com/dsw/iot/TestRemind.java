package com.dsw.iot;


import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dsw.iot.model.RemindDo;
import com.dsw.iot.service.RemindService;
import com.dsw.iot.util.BizException;

public class TestRemind extends BaseTest{

	@Autowired
	private RemindService remindService;
	
	@Test
    public void remind() throws BizException {
		stepOne();
    }
	
	public void stepOne() throws BizException {
		System.out.println("代办查询  start-----------------");
		List<RemindDo> resultList = remindService.listRemind(Long.valueOf("1"));
		System.out.println("查询结果条数："+resultList.size());
		RemindDo info = null;
		for (int i = 0 ; i < resultList.size() ; i++) {
			info = (RemindDo) resultList.get(i);
			System.out.println(info.getTaskBelong() +":"+ info.getPath() +":"+ info.getTaskId());
		}
		System.out.println("代办查询  end-----------------");
	}

}
