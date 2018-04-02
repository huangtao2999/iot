package com.dsw.iot.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.LockerDoMapperExt;
import com.dsw.iot.dto.LockerResquest;
import com.dsw.iot.manager.RelayManager;
import com.dsw.iot.model.LockerDo;
import com.dsw.iot.model.LockerDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.LockerService;
import com.dsw.iot.service.LogService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;

import dm.jdbc.util.StringUtil;

/**
 * 储物柜服务
 *
 * @author huangt
 * @create 2018-01-17 22:24
 **/
@Service
public class LockerServiceImpl implements LockerService {
    @Autowired(required = false)
    private LockerDoMapperExt lockerDoMapperExt;
    @Autowired
    private CurrentUserService currentUserService;
    @Autowired
    private RelayManager relayManager;
    @Autowired
	private LogService logService;

    @Override
    public PageResult<LockerDo> queryPage(LockerResquest request) {
        PageResult<LockerDo> pageResult = new PageResult<>();
        LockerDoExample example = new LockerDoExample();
        LockerDoExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        //添加条件
        if (StringUtils.isNotEmpty(request.getLockerNo())) {
            criteria.andLockerNoLike("%" + request.getLockerNo() + "%");
        }
        if (StringUtils.isNotEmpty(request.getUseStatus())) {
            criteria.andUseStatusEqualTo(request.getUseStatus());
        }
        if (StringUtils.isNotEmpty(request.getType())) {
            criteria.andTypeEqualTo(request.getType());
        }
        int count = lockerDoMapperExt.countByExample(example);
        PageDto pageDto = new PageDto(request.getPage(), request.getLimit(), count);
        example.setPageDto(pageDto);
        List<LockerDo> list = lockerDoMapperExt.selectByExample(example);
        pageResult.setData(list);
        pageResult.setCount(count);
        return pageResult;
    }

    /**
     * 保存
     */
    @Override
    @Transactional
    public void saveLocker(LockerDo lockerDo) throws BizException {
        if (StringUtils.isEmpty(lockerDo.getLockerNo())) {
            throw new BizException("储物柜编号为空");
        }
        if (null == lockerDo.getId()) {
            // 新增
            DomainUtil.setCommonValueForCreate(lockerDo, currentUserService.getPvgInfo());
            lockerDoMapperExt.insertSelective(lockerDo);
            // 写日志
    		logService.insertLog(CommConfig.LOG_MODULE.LOCKER.getModule(),CommConfig.LOG_TYPE.ADD.getType(),
    				currentUserService.getPvgInfo().getName() + "  新增了储物柜,编号："+lockerDo.getLockerNo());
        } else {
            // 编辑
            DomainUtil.setCommonValueForUpdate(lockerDo, currentUserService.getPvgInfo());
            lockerDoMapperExt.updateByPrimaryKeySelective(lockerDo);
            // 写日志
    		logService.insertLog(CommConfig.LOG_MODULE.LOCKER.getModule(),CommConfig.LOG_TYPE.UPDATE.getType(),
    				currentUserService.getPvgInfo().getName() + "  编辑了储物柜,编号："+lockerDo.getLockerNo());
        }
    }

    /**
     * 根据主键id查询数据
     */
    @Override
    public LockerDo getLocker(Long id) {
        return lockerDoMapperExt.selectByPrimaryKey(id);
    }

    /**
     * 根据主键id删除数据
     */
    @Override
    @Transactional
    public void deleteLocker(String ids) {
        if (!StringUtils.isEmpty(ids)) { // 删除多个id
            String[] idArr = ids.split(",");// 分解逗号拼接字符串
            for (String id : idArr) {
                if (!StringUtils.isEmpty(id)) {
                    LockerDo lockerDo = new LockerDo();
                    lockerDo.setId(Long.parseLong(id));
                    DomainUtil.setCommonValueForDelete(lockerDo, currentUserService.getPvgInfo());
                    lockerDoMapperExt.deleteByPrimaryKey(lockerDo);
                }
            }
        }
        // 写日志
		logService.insertLog(CommConfig.LOG_MODULE.LOCKER.getModule(),CommConfig.LOG_TYPE.ADD.getType(),
				currentUserService.getPvgInfo().getName() + "  删除了储物柜");
    }

    /**
     * 查取最大编号
     */
    @Override
    public ActionResult<String> getLockerNo(String type) {
        ActionResult<String> actionResult = new ActionResult<String>();
        String lockerNo = lockerDoMapperExt.selectLockerNo(type);
		if (StringUtil.isEmpty(lockerNo)) {
			actionResult.setContent("1");
		} else {
			actionResult.setContent((Integer.parseInt(lockerNo) + 1) + "");
		}
		// if (null != lockerNo) {
		// int num = Integer.valueOf(lockerNo) + 1;
		// actionResult.setContent(type + String.format("%04d", num)); //
		// 按4个位置左对齐，不足补0
		// } else {
		// actionResult.setContent(type + "0001"); // 默认从第一个开始
		// }
        return actionResult;
    }

    /**
     * 根据柜子类型，获得空闲柜子
     *
     * @throws BizException
     */
    @Override
    public LockerDo getFreeLockerByType(String type) throws BizException {
        LockerDoExample example = new LockerDoExample();
        LockerDoExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());

        criteria.andTypeEqualTo(type);
        criteria.andUseStatusEqualTo("0");

        List<LockerDo> list = lockerDoMapperExt.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        } else {
            throw new BizException("该类型柜子都在使用中，请更换其他类型或不使用储物柜");
        }
    }

    /**
     * 出入所柜门控制(type="in"入所;type="out"出所 )
     */
    @Override
    public ActionResult<String> openLocker(Long id, String type) {
        ActionResult<String> actionResult = new ActionResult<String>();
        LockerDo locker = this.getLocker(id);
        if (!StringUtils.isEmpty(type)) {// 判断type是否为空
            if (type.equals("in")) {
                if (StringUtil.isEmpty(locker.getInIp()) || locker.getInPort() == null || locker.getInRoad() == null) {
                    actionResult.setContent("入所ip、port、road未填写完成");
                    return actionResult;
                }
                relayManager.openV2(locker.getInIp(), locker.getInPort(), locker.getInRoad());
                relayManager.closeV2(locker.getInIp(), locker.getInPort(), locker.getInRoad());//关闭
                // 写日志
        		logService.insertLog(CommConfig.LOG_MODULE.LOCKER.getModule(),CommConfig.LOG_TYPE.ADD.getType(),
        				currentUserService.getPvgInfo().getName() + "  打开了入所柜门,编号："+locker.getLockerNo());
                actionResult.setContent("入所开柜成功");
                return actionResult;
            } else if (type.equals("out")) {
                if (StringUtil.isEmpty(locker.getOutIp()) || locker.getOutPort() == null || locker.getOutRoad() == null) {
                    actionResult.setContent("出所ip、port、road未填写完成");
                    return actionResult;
                }
                relayManager.openV2(locker.getOutIp(), locker.getOutPort(), locker.getOutRoad());
                relayManager.closeV2(locker.getOutIp(), locker.getOutPort(), locker.getOutRoad());//关闭
                // 写日志
        		logService.insertLog(CommConfig.LOG_MODULE.LOCKER.getModule(),CommConfig.LOG_TYPE.ADD.getType(),
        				currentUserService.getPvgInfo().getName() + "  打开了出所柜门,编号："+locker.getLockerNo());
                actionResult.setContent("出所开柜成功");
                return actionResult;
            }
        }
        actionResult.setSuccess(false);// 开柜失败
        actionResult.setContent("开柜失败");
        return actionResult;
    }


    /**
     * 开柜关柜操作
     */
    @Override
    public ActionResult<String> openLockerByid(Long id) throws BizException {
        ActionResult<String> result = new ActionResult<String>();
        LockerDo locker = this.getLocker(id);

        if (StringUtil.isEmpty(locker.getInIp()) || locker.getInPort() == null || locker.getInRoad() == null) {
            throw new BizException("参数错误");
        }
        result = relayManager.openV2(locker.getInIp(), locker.getInPort(), locker.getInRoad());// 打开
        if (result.isSuccess()) {
            relayManager.close(locker.getInIp(), locker.getInPort(), locker.getInRoad());// 关闭
            // 写日志
    		logService.insertLog(CommConfig.LOG_MODULE.LOCKER.getModule(),CommConfig.LOG_TYPE.ADD.getType(),
    				currentUserService.getPvgInfo().getName() + "  打开入所柜门,编号："+locker.getLockerNo());
        } else {
            throw new BizException(result.getContent());
        }
        result.setSuccess(true);
        result.setContent("入所开柜成功");
        return result;
    }

	/**
	 * 更新储物柜状态，空闲/占用
	 */
	@Override
	@Transactional
	public void updateLockerStatus(Long lockerId, String status) {
		if (null == lockerId || StringUtils.isBlank(status)) {
			return;
		}
		// 编辑
		LockerDo lockerDo = new LockerDo();
		lockerDo.setId(lockerId);
		lockerDo.setUseStatus(status);// 1-使用中；0-空闲
		DomainUtil.setCommonValueForUpdate(lockerDo, currentUserService.getPvgInfo());
		lockerDoMapperExt.updateByPrimaryKeySelective(lockerDo);
	}

}
