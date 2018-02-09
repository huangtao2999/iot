package com.dsw.iot.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.LockerDoMapperExt;
import com.dsw.iot.dto.LockerResquest;
import com.dsw.iot.model.LockerDo;
import com.dsw.iot.model.LockerDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.LockerService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;

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

    @Override
    public ActionResult<String> addOrUpdate(LockerDo lockerDo) throws BizException {
        ActionResult<String> actionResult = null;
        if (null == lockerDo.getId()) {
            validate(lockerDo, "add");
            // 新增
            DomainUtil.setCommonValueForCreate(lockerDo, currentUserService.getPvgInfo());
            lockerDoMapperExt.insertSelective(lockerDo);
        } else {
            validate(lockerDo, "update");
            // 编辑
            DomainUtil.setCommonValueForUpdate(lockerDo, currentUserService.getPvgInfo());
            lockerDoMapperExt.updateByPrimaryKeySelective(lockerDo);
        }
        return actionResult;
    }

    private void validate(LockerDo lockerDo, String option) throws BizException {
        if ("add".equals(option)) {
            if (StringUtils.isEmpty(lockerDo.getLockerNo())) {
                throw new BizException("编号为空");
            }
        } else {
            //TODO
        }
    }

    /**
     * 根据主键id查询数据
     */
    @Override
    public LockerDo selectByPrimaryKey(Long id) {
        if (null == id) {
            return new LockerDo();
        } else {
            return lockerDoMapperExt.selectByPrimaryKey(id);
        }
    }

    /**
     * 根据主键id删除数据
     */
    @Override
	public ActionResult<String> deleteLocker(String ids) {
        ActionResult<String> actionResult = null;
		if (!StringUtils.isEmpty(ids)) { // 删除多个id
            String[] idArr = ids.split(",");// 分解逗号拼接字符串
			for (String id : idArr) {
				if (!StringUtils.isEmpty(id))
                    lockerDoMapperExt.deleteByPrimaryKey((long) Integer
							.parseInt(id));
            }
        }
        return actionResult;
    }

    /**
     * 查取最大编号
     */
    @Override
    public ActionResult<String> selectLockerNo(String type) {
        ActionResult<String> actionResult = new ActionResult<String>();
        String lockerNo = lockerDoMapperExt.selectLockerNo(type);
        if (null != lockerNo) {
            int num = Integer.valueOf(lockerNo) + 1;
            actionResult.setContent(String.format("%04d", num));
        }
        return actionResult;
    }
}
