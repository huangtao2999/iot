package com.dsw.iot.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.UserDoMapperExt;
import com.dsw.iot.dal.UserRoleDoMapperExt;
import com.dsw.iot.dto.UserRequest;
import com.dsw.iot.model.UserDo;
import com.dsw.iot.model.UserDoExample;
import com.dsw.iot.model.UserRoleDo;
import com.dsw.iot.model.UserRoleDoExample;
import com.dsw.iot.service.UserService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.MD5Util;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDoMapperExt userDoMapperExt;
    @Autowired(required = false)
    private UserRoleDoMapperExt userRoleDoMapperExt;

    /**
     * 分页查询数据
     */
    @Override
    public PageResult<UserDo> queryPage(UserRequest param) {
        //定义分页返回集合
        PageResult<UserDo> pageResult = new PageResult<>();
        //定义查询条件容器
        UserDoExample example = new UserDoExample();
        //新建查询标准
        UserDoExample.Criteria criteria = example.createCriteria();
        //添加默认条件is_deleted='N'
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        //获得查询参数，模糊查询人名，电话
//        String search = param.getSearch();
//        if (StringUtils.isNotEmpty(param.getSearch())) {
//            criteria.andRealNameLike("%" + search + "%");
//            criteria.andTelLike("%" + search + "%");
//            criteria.andPhoneLike("%" + search + "%");
//        }
//        criteria.andAccountNotEqualTo("admin");
//        example.setOrderByClause("create_time desc");
//        //分页
//        int count = userDoMapperExt.countByExample(example);
//        Page page = new Page(param.getPage(), param.getLimit(), count);
//        example.setPage(page);
//        List<UserDo> list = userDoMapperExt.selectByExample(example);
        //排序字段
        if (StringUtils.isBlank(param.getOrderByClause())) {
            param.setOrderByClause("create_time desc");
        }
        //下面用的自定义sql（为了满足模糊查询）
        int count = userDoMapperExt.countByParam(param);
        //定义分页
        PageDto page = new PageDto(param.getPage(), param.getLimit(), count);
        //获得分页参数
        param.setPager(page);
        //根据分页查询数据集合
        List<UserDo> list = userDoMapperExt.selectByParam(param);
        //返回list和分页
        pageResult.setData(list);
        pageResult.setCount(count);
        return pageResult;
    }

    /**
     * 通过主键查询数据
     */
    @Override
    public UserDo selectByPrimaryKey(Long id) {
        return userDoMapperExt.selectByPrimaryKey(id);
    }

    /**
     * 新增/编辑用户信息
     * 更新用户角色表
     */
    @Override
    @Transactional
    public int addEdit(HttpServletRequest request, UserDo record, UserDo loginUserDo) throws Exception {
        int i = 0;
        //获得勾选的角色集合
        String roleIds[] = request.getParameter("roleIds").split(",");
        //新增或更新用户表
        if (record.getId() == null) {
            //新增
            record.setCreateTime(new Date());
            record.setUpdateTime(new Date());
            record.setCreateUser(loginUserDo.getAccount());
            record.setUpdateUser(loginUserDo.getAccount());
            //默认密码为1
            record.setPassword(MD5Util.MD5("1"));
            i = userDoMapperExt.insertSelective(record);
        } else {
            //编辑
            if (!StringUtils.isBlank(record.getPassword())) {
                //密码不为空就更新密码，md5加密
                record.setPassword(MD5Util.MD5(record.getPassword()));
            }
            record.setUpdateTime(new Date());
            record.setUpdateUser(loginUserDo.getAccount());
            i = userDoMapperExt.updateByPrimaryKeySelective(record);
        }
        //更新用户角色表（先全部删除，再插入新的）
        UserRoleDoExample userRoleExp = new UserRoleDoExample();
        //添加默认条件is_deleted='N'
        UserRoleDoExample.Criteria con = userRoleExp.createCriteria();
        con.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        if (null != record) {
            //执行删除
            con.andUserIdEqualTo(record.getId());
            userRoleDoMapperExt.deleteByExample(userRoleExp);
        }
        if (roleIds.length > 0) {
            //执行插入
            for (int m = 0; m < roleIds.length; m++) {
                if (roleIds[m] != "") {
                    UserRoleDo UREntity = new UserRoleDo();
                    UREntity.setUserId(record.getId());
                    UREntity.setRoleId(Long.parseLong(roleIds[m]));
                    UREntity.setCreateUser(loginUserDo.getAccount());
                    UREntity.setUpdateUser(loginUserDo.getAccount());
                    UREntity.setCreateTime(new Date());
                    UREntity.setUpdateTime(new Date());
                    userRoleDoMapperExt.insertSelective(UREntity);
                }
            }
        }
        return i;
    }

    @Override
    @Transactional
    public int del(UserRequest param) {
        int i = 0;
        if (param.getId() != null) {
            i = userDoMapperExt.deleteByPrimaryKey(param.getId());
        } else if (param.getIds() != null) {
            String ids[] = param.getIds().split(",");
            for (int j = 0; j < ids.length; j++) {
                i = userDoMapperExt.deleteByPrimaryKey((long) Integer.parseInt(ids[j]));
            }
        }
        return i;
    }

    @Override
	public ActionResult<String> checkAccount(UserRequest param) {
		ActionResult<String> res = new ActionResult<>();
        UserDoExample exp = new UserDoExample();
        //添加默认条件is_deleted='N'
        UserDoExample.Criteria con = exp.createCriteria();
        con.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        con.andAccountEqualTo(param.getAccount());
        List<UserDo> list = userDoMapperExt.selectByExample(exp);
        //返回数据
        if (list.size() > 0) {
            res.setSuccess(false);
            res.setContent("已存在该登录账号，请更换账号或编辑该账号");
        } else {
            res.setSuccess(true);
            res.setContent("没有重复，该账号可使用");
        }
        return res;
    }

}
