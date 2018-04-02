package com.dsw.iot.service.impl;

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
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.FileUploadService;
import com.dsw.iot.service.LogService;
import com.dsw.iot.service.UserService;
import com.dsw.iot.util.ActionResult;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.MD5Util;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDoMapperExt userDoMapperExt;
    @Autowired(required = false)
    private UserRoleDoMapperExt userRoleDoMapperExt;
    @Autowired
    private CurrentUserService currentUserService;
    @Autowired
    private FileUploadService fileUploadService;
	@Autowired
	private LogService logService;

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
    public UserDo getUser(Long id) {
        return userDoMapperExt.selectByPrimaryKey(id);
    }

    /**
     * 新增/编辑用户信息
     * 更新用户角色表
     */
    @Override
    @Transactional
    public void saveUser(HttpServletRequest request, UserDo userDo) throws Exception {
        //获得勾选的角色集合
        String roleIds[] = null;
        if (StringUtils.isNotEmpty(request.getParameter("roleIds"))) {
            roleIds = request.getParameter("roleIds").split(",");
        }
        //新增或更新用户表
        if (userDo.getId() == null) {
            //新增
            DomainUtil.setCommonValueForCreate(userDo, currentUserService.getPvgInfo());
            //默认密码为1
            userDo.setPassword(MD5Util.MD5("1"));
            userDoMapperExt.insertSelective(userDo);
            // 写日志
    		logService.insertLog(CommConfig.LOG_MODULE.USER.getModule(),CommConfig.LOG_TYPE.ADD.getType(),
    				currentUserService.getPvgInfo().getName() + "  新增了用户："+userDo.getRealName());
        } else {
            //编辑
            if (!StringUtils.isBlank(userDo.getPassword())) {
                //密码不为空就更新密码，md5加密
                userDo.setPassword(MD5Util.MD5(userDo.getPassword()));
            }
            DomainUtil.setCommonValueForUpdate(userDo, currentUserService.getPvgInfo());
            userDoMapperExt.updateByPrimaryKeySelective(userDo);
            // 写日志
    		logService.insertLog(CommConfig.LOG_MODULE.USER.getModule(),CommConfig.LOG_TYPE.UPDATE.getType(),
    				currentUserService.getPvgInfo().getName() + "  编辑了用户："+userDo.getRealName());
        }
        // 更新头像信息
        String headImgId = userDo.getHeadImg();
        fileUploadService.updateAttach(headImgId, userDo.getId(), CommConfig.ATTACH_TYPE.USER_HEAD_IMG.getType(),
                CommConfig.ATTACH_TYPE.USER_HEAD_IMG.getName());

        //更新用户角色表（先全部删除，再插入新的）
        if (null != userDo) {
            // 执行删除用户绑定的角色
            userRoleDoMapperExt.deleteByUserId(userDo.getId());
        }
        if (null != roleIds && roleIds.length > 0) {
            //执行插入
            for (int m = 0; m < roleIds.length; m++) {
                if (roleIds[m] != "") {
                    UserRoleDo UREntity = new UserRoleDo();
                    UREntity.setUserId(userDo.getId());
                    UREntity.setRoleId(Long.parseLong(roleIds[m]));
                    DomainUtil.setCommonValueForCreate(UREntity, currentUserService.getPvgInfo());
                    userRoleDoMapperExt.insertSelective(UREntity);
                }
            }
        }
    }

    /**
     * 删除用户
     *
     * @param param
     */
    @Override
    @Transactional
    public void removeUser(UserRequest param) {
        // 先删除用户绑定的角色
        if (null != param.getId()) {
            // 执行删除用户绑定的角色
            userRoleDoMapperExt.deleteByUserId(param.getId());
            UserDo userDo = new UserDo();
            userDo.setId(param.getId());
            DomainUtil.setCommonValueForDelete(userDo, currentUserService.getPvgInfo());
            userDoMapperExt.deleteByPrimaryKey(userDo);
            // 写日志
    		logService.insertLog(CommConfig.LOG_MODULE.USER.getModule(),CommConfig.LOG_TYPE.DELETE.getType(),
    				currentUserService.getPvgInfo().getName() + "  删除了用户："+userDo.getRealName());
        } else if (param.getIds() != null) {
            String ids[] = param.getIds().split(",");
            for (int j = 0; j < ids.length; j++) {
                // 执行删除用户绑定的角色
                userRoleDoMapperExt.deleteByUserId(Long.parseLong(ids[j]));
                UserDo userDo = new UserDo();
                userDo.setId(Long.parseLong(ids[j]));
                DomainUtil.setCommonValueForDelete(userDo, currentUserService.getPvgInfo());
                userDoMapperExt.deleteByPrimaryKey(userDo);
            }
            // 写日志
    		logService.insertLog(CommConfig.LOG_MODULE.USER.getModule(),CommConfig.LOG_TYPE.DELETE.getType(),
    				currentUserService.getPvgInfo().getName() + "  删除了用户");
        }
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

    /**
     * 更新用户密码
     *
     * @throws BizException
     */
    @Override
    public void updatePwd(UserRequest userRequest) throws BizException {
        String oldPwd = userRequest.getOldPwd();
        String newPwd = userRequest.getNewPwd();
        String newPwdConfirm = userRequest.getNewPwdConfirm();
        if (StringUtils.isNotBlank(oldPwd)) {
            if (StringUtils.isNotBlank(newPwd) && StringUtils.isNotBlank(newPwdConfirm)) {
                if (newPwd.equals(newPwdConfirm)) {
                    UserDo userDo = userDoMapperExt.selectByPrimaryKey(currentUserService.getPvgInfo().getUserId());
                    String pwd = userDo.getPassword();
                    if (MD5Util.MD5(oldPwd).equals(pwd)) {
                        // 更新密码
                        UserDo userDo2 = new UserDo();
                        userDo2.setId(userDo.getId());
                        userDo2.setPassword(MD5Util.MD5(newPwd));
                        userDoMapperExt.updateByPrimaryKeySelective(userDo2);
                        // 写日志
                		logService.insertLog(CommConfig.LOG_MODULE.USER.getModule(),CommConfig.LOG_TYPE.UPDATE.getType(),
                				currentUserService.getPvgInfo().getName() + "  更改了用户密码");
                    } else {
                        throw new BizException("原密码输入错误");
                    }
                } else {
                    throw new BizException("新密码两次输入不一致，请重新输入");
                }
            } else {
                throw new BizException("请输入新密码");
            }
        } else {
            throw new BizException("请输入原密码");
        }
    }

}
