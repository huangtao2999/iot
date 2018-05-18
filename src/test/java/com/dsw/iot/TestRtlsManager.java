package com.dsw.iot;

import com.dsw.iot.manager.RtlsManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 智物达标签修改单元测试
 *
 * @author huangt
 * @create 2018-02-28 9:30
 **/
public class TestRtlsManager extends BaseTest {

    @Autowired
    private RtlsManager rtlsManager;

    @Test
    public void bindTag() {
        rtlsManager.bindTag("DECA010010003418", "李四", 1);
    }

    @Test
    public void unBindTag() {
        rtlsManager.unBindTag("DECA010010003418");
    }
}
