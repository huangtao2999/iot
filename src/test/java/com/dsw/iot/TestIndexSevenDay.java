package com.dsw.iot;

import java.util.Date;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dsw.iot.dto.IndexChartRequest;
import com.dsw.iot.service.IndexService;
import com.dsw.iot.util.BizException;

/**
 * 首页统计7天人员数量测试
 *
 * @author zhang
 *
 */
public class TestIndexSevenDay extends BaseTest {

	IndexChartRequest indexChartRequest;

    @Autowired
	IndexService indexService;

    @Test
	public void main() throws BizException {
		indexChartRequest = new IndexChartRequest();
		indexChartRequest.setCountDate(new Date());
		indexService.getSevenDayCount(indexChartRequest);
    }

    @After
    public void after() {
    }
}
