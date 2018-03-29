package com.dsw.iot;

import com.dsw.iot.manager.LedManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * led单元测试
 *
 * @author huangt
 * @create 2018-02-28 9:30
 **/
public class TestLedManager extends BaseTest {

    @Autowired
    private LedManager ledManager;

    @Test
    public void showContent() {
        String ip = "192.168.1.29";
        String title = "询问室";
        String content = "犯罪嫌疑人:测试LED连接";
        ledManager.showContent(ip, title, content);
    }
}
