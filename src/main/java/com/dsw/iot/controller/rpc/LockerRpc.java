package com.dsw.iot.controller.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsw.iot.dto.LockerResquest;
import com.dsw.iot.manager.RelayManager;
import com.dsw.iot.model.LockerDo;
import com.dsw.iot.service.LockerService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.PageResult;

/**
 * 储物柜rpc
 *
 * @author huangt
 * @create 2018-01-18 8:40
 **/
@RestController
@RequestMapping("/Locker")
public class LockerRpc {
    @Autowired
    private LockerService lockerService;

    @Autowired
    private RelayManager relayManager;

    /**
     * 分页查询
     *
     * @param resquest
     * @return
     */
    @RequestMapping("/queryPage")
    public PageResult<LockerDo> queryPage(LockerResquest resquest) {
        PageResult<LockerDo> pageResult = lockerService.queryPage(resquest);
        return pageResult;
    }

    /**
     * 保存储物柜信息
     *
     * @param lockerDo
     * @return
     * @throws BizException
     */
    @RequestMapping("/saveLocker")
    public void saveLocker(LockerDo lockerDo) throws BizException {
        lockerService.saveLocker(lockerDo);
    }

    /**
     * 删除储物柜信息
     *
     * @param ids
     * @return
     * @throws BizException
     */
    @RequestMapping("/deleteLocker")
    public void deleteLocker(String ids) throws BizException {
        lockerService.deleteLocker(ids);
    }

    /**
     * 获得储物柜编号（用于保存）
     *
     * @param resquest
     * @param type
     * @return
     */
    @RequestMapping("/getLockerNo")
    public ActionResult<String> getLockerNo(LockerResquest resquest, String type) {
        return lockerService.getLockerNo(type);
    }

    /**
     * 出入所柜门控制(type="in"入所;type="out"出所 )
     *
     * @param type
     * @return
     */
    @RequestMapping("/openLocker")
    public ActionResult<String> openLocker(Long id, String type) {
        return lockerService.openLocker(id, type);
    }

    /**
     * 根据柜子类型，获得空闲柜子
     *
     * @param type
     * @return
     * @throws BizException
     */
    @RequestMapping("/getFreeLocker")
    public LockerDo getFreeLockerByType(String type) throws BizException {
        return lockerService.getFreeLockerByType(type);
    }

    /**
     * 开柜关柜操作
     *
     * @param type
     * @return
     * @throws BizException
     */
    @RequestMapping("/openLockerByid")
    public ActionResult<String> openLockerByid(Long id, String type) throws BizException {
        return lockerService.openLocker(id, type);
    }
}
