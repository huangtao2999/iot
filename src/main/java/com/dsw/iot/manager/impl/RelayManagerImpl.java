package com.dsw.iot.manager.impl;

import com.dsw.iot.manager.RelayManager;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.vo.RelayCfgVo;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadCoilsRequest;
import net.wimpi.modbus.msg.ReadCoilsResponse;
import net.wimpi.modbus.msg.WriteCoilRequest;
import net.wimpi.modbus.net.TCPMasterConnection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetAddress;

/**
 * 继电器控制服务类
 *
 * @author huangt
 * @create 2018-02-28 9:05
 **/
@Service
public class RelayManagerImpl implements RelayManager {
    private static final Logger logger = Logger.getLogger(RelayManagerImpl.class);

    @Value("${relay.timeout}")
    private int timeout;

    //    @Override
    @Override
    public ActionResult<String> openV2(String ip, int port, int index) {
        ActionResult<String> result = new ActionResult<>();
        try {
            logger.info(String.format("继电器打开: (ip:%s,port:%s,路数:%s)", ip, port, index));
            this.writeDigitalOutput(ip, port, 254, index, 1);
            result.setSuccess(true);
            result.setContent("继电器打开成功");
        } catch (Exception e) {
            logger.error("继电器打开异常", e);
            result.setSuccess(false);
            result.setContent("继电器打开异常");
        }
        return result;
    }

    @Override
    public ActionResult<String> closeV2(String ip, int port, int index) {
        ActionResult<String> result = new ActionResult<>();
        try {
            //关闭大多数在打开之后马上执行，此时延时半秒执行 避免被拒绝
            Thread.sleep(500L);
            logger.info(String.format("继电器关闭: (ip:%s,port:%s,路数:%s)", ip, port, index));
            this.writeDigitalOutput(ip, port, 254, index, 0);
            result.setSuccess(true);
            result.setContent("继电器关闭成功");
        } catch (Exception e) {
            logger.error("继电器关闭异常", e);
            result.setSuccess(false);
            result.setContent("继电器关闭异常");
        }
        return result;
    }

    @Override
    public ActionResult<String> getStatus(String ip, int port, int index) {
        ActionResult<String> result = new ActionResult<>();
        try {
            logger.info(String.format("继电器状态： (ip:%s,port:%s,路数:%s)", ip, port, index));
            int checkResult = this.readDigitalOutput(ip, port, 254, index);
            result.setSuccess(true);
            result.setContent(checkResult + "");
        } catch (Exception e) {
            logger.error("继电器状态查询异常", e);
            result.setSuccess(false);
            result.setContent("继电器状态查询异常");
        }
        return result;
    }

    /**
     * 写入数据到真机的DO类型的寄存器上面
     *
     * @param ip
     * @param port
     * @param slaveId
     * @param address
     * @param value
     * @throws Exception
     */
    private void writeDigitalOutput(String ip, int port, int slaveId, int address, int value) throws Exception {

        InetAddress addr = InetAddress.getByName(ip);
        TCPMasterConnection connection = new TCPMasterConnection(addr);
        connection.setPort(port);
        connection.setTimeout(timeout);
        connection.connect();
        ModbusTCPTransaction trans = new ModbusTCPTransaction(connection);
        boolean val = true;
        if (value == 0) {
            val = false;
        }
        WriteCoilRequest req = new WriteCoilRequest(address, val);
        req.setUnitID(slaveId);
        trans.setRequest(req);
        trans.execute();
        connection.close();
    }

    private int readDigitalOutput(String ip, int port, int slaveId, int address) throws Exception {
        int data = 0;
        InetAddress addr = InetAddress.getByName(ip);
        TCPMasterConnection con = new TCPMasterConnection(addr);
        con.setPort(port);
        con.setTimeout(timeout);
        con.connect();
        ReadCoilsRequest req = new ReadCoilsRequest(address, 1);
        req.setUnitID(slaveId);
        ModbusTCPTransaction trans = new ModbusTCPTransaction(con);
        trans.setRequest(req);
        trans.execute();
        ReadCoilsResponse res = ((ReadCoilsResponse) trans.getResponse());
        if (res.getCoils().getBit(0)) {
            data = 1;
        }
        con.close();
        return data;
    }

    @Override
    public boolean open(String ip, int port, int index) {
        try {
            logger.info(String.format("继电器打开: (ip:%s,port:%s,路数:%s)", ip, port, index));
            this.writeDigitalOutput(ip, port, 254, index, 1);
        } catch (Exception e) {
            logger.error("继电器打开异常", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean close(String ip, int port, int index) {
        try {
            logger.info(String.format("继电器关闭: (ip:%s,port:%s,路数:%s)", ip, port, index));
            this.writeDigitalOutput(ip, port, 254, index, 0);
        } catch (Exception e) {
            logger.error("继电器关闭异常", e);
        }
        return true;
    }

    //报警器继电器配置
    @Value("${alarm.relay.ip}")
    private String alarmIp;
    @Value("${alarm.relay.port}")
    private int alarmPort;
    @Value("${alarm.relay.index}")
    private int alarmIndex;
    //亲情电话继电器配置
    @Value("${phone.relay.ip}")
    private String phoneIp;
    @Value("${phone.relay.port}")
    private int phonePort;
    @Value("${phone.relay.index}")
    private int phoneIndex;

    @Override
    public RelayCfgVo initCfg(String type) {
        RelayCfgVo relayCfgVo = new RelayCfgVo();
        if ("alarm".equals(type)) {
            relayCfgVo.setIp(alarmIp);
            relayCfgVo.setPort(alarmPort);
            relayCfgVo.setChannel(alarmIndex);
        } else {
            relayCfgVo.setIp(phoneIp);
            relayCfgVo.setPort(phonePort);
            relayCfgVo.setChannel(phoneIndex);
        }
        return relayCfgVo;
    }

}
