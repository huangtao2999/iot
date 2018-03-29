package com.dsw.iot;

import com.dsw.iot.manager.RelayManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 继电器单元测试
 *
 * @author huangt
 * @create 2018-02-28 9:30
 **/
public class TestRelayManager extends BaseTest {

    @Autowired
    private RelayManager relayManager;

    @Test
    public void openAndClose() {
        String ip = "192.168.1.200";
        int port = 10000;
        int index = 3;
//        relayManager.open(ip, port, index);
        relayManager.close(ip, port, index);
    }
}
