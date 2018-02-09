package com.dsw.iot.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.RoleDoMapperExt;
import com.dsw.iot.dal.RoleMenuDoMapperExt;
import com.dsw.iot.dto.RoleRequest;
import com.dsw.iot.model.RoleDo;
import com.dsw.iot.model.RoleDoExample;
import com.dsw.iot.model.RoleMenuDo;
import com.dsw.iot.model.RoleMenuDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.LogService;
import com.dsw.iot.service.RoleService;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.PageDto;
import com.dsw.iot.util.PageResult;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired(required = false)
    private RoleDoMapperExt roleDoMapperExt;
    @Autowired(required = false)
    private RoleMenuDoMapperExt roleMenuDoMapperExt;
    @Autowired
    private CurrentUserService currentUserService;
	@Autowired
	private LogService logService;
//    @Autowired
//    private PrivilegeInfo pvgInfo;

//    @Autowired
//    private TpRoleDoMapper tpRoleDoMapper;

    /**
     * 每5秒执行一次任务
     */
//    @Scheduled(cron="0/5 * * * * ?")
    @Override
    public void print() {
//        System.out.println(LocalDateTime.now());
//        TpRoleDoExample example = new TpRoleDoExample();
//        List<TpRoleDo> list = tpRoleDoMapper.selectByExample(example);
//        list.stream().forEach(item->{
//            System.out.println(item.getId() +":"+item.getRolename());
//        });
    }

    /**
     * 角色分页查询
     */
    @Override
    public PageResult<RoleDo> queryPage(RoleRequest request) {
        //定义分页返回集合
        PageResult<RoleDo> pageResult = new PageResult<>();
        //定义查询条件容器
        RoleDoExample example = new RoleDoExample();
        //添加条件
        RoleDoExample.Criteria criteria = example.createCriteria();
        //默认条件添加is_deleted="N"
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());//criteria.andIsDeletedEqualTo(CommConfig.NOT_DELETED);
        //角色名
        if (StringUtils.isNotEmpty(request.getRoleName())) {
            criteria.andRoleNameLike("%" + request.getRoleName() + "%");
        }
        //排序字段
        if (StringUtils.isBlank(request.getOrderByClause())) {
            example.setOrderByClause("sort asc, create_time desc");
        } else {
            example.setOrderByClause(request.getOrderByClause());
        }
        //分页查询总数
        int count = roleDoMapperExt.countByExample(example);
        //创建分页
        PageDto pageDto = new PageDto(request.getPage(), request.getLimit(), count);
        example.setPageDto(pageDto);
        //查询数据集合
        List<RoleDo> list = roleDoMapperExt.selectByExample(example);
        //返回list和分页
        pageResult.setData(list);
        pageResult.setCount(count);
        return pageResult;
    }

    /**
     * 角色编辑查找记录
     *
     * @param id
     */
    @Override
    @Transactional
    public RoleDo selectByPrimaryKey(Long id) {
        return roleDoMapperExt.selectByPrimaryKey(id);
    }

    /**
     * 新增/编辑
     */
    @Override
    @Transactional
	public void saveRole(HttpServletRequest request, RoleDo record) {
        if (record.getId() == null) {
            //新增
            DomainUtil.setCommonValueForCreate(record, currentUserService.getPvgInfo());
			roleDoMapperExt.insertSelective(record);
			// 写日志
			logService.insertLog(request, CommConfig.LOG_MODULE.ROLE.getModule(), CommConfig.LOG_TYPE.ADD.getType(),
					"新增了一个角色：" + record.getRoleName());
        } else {
            //编辑
			DomainUtil.setCommonValueForUpdate(record, currentUserService.getPvgInfo());
			roleDoMapperExt.updateByPrimaryKeySelective(record);
			// 写日志
			logService.insertLog(request, CommConfig.LOG_MODULE.ROLE.getModule(), CommConfig.LOG_TYPE.UPDATE.getType(),
					"编辑了一个角色：" + record.getRoleName());
        }

        //删除角色下的菜单
        Long roleId = Long.parseLong(record.getId() + "");
        RoleMenuDoExample exp = new RoleMenuDoExample();
        //添加默认条件is_deleted='N'
        RoleMenuDoExample.Criteria con = exp.createCriteria();
        con.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        if (roleId != 0) {
            //执行删除
            con.andRoleIdEqualTo(roleId);
            roleMenuDoMapperExt.deleteByExample(exp);
			// 写日志
			logService.insertLog(request, CommConfig.LOG_MODULE.ROLE.getModule(),
					CommConfig.LOG_TYPE.DELETE.getType(),
					"删除了【" + record.getRoleName() + "】下的所有菜单");
        }
        String menuIds[] = request.getParameter("menuIds").split(",");
        //添加角色新绑定的菜单
        if (menuIds.length > 0) {
            //执行插入
            for (int m = 0; m < menuIds.length; m++) {
                if (!"".equals(menuIds[m])) {
                    RoleMenuDo entity = new RoleMenuDo();
                    entity.setRoleId(roleId);
                    entity.setMenuId(Long.parseLong(menuIds[m]));
					DomainUtil.setCommonValueForCreate(entity, currentUserService.getPvgInfo());
                    roleMenuDoMapperExt.insertSelective(entity);
                }
            }
			// 写日志
			logService.insertLog(request, CommConfig.LOG_MODULE.ROLE.getModule(),
					CommConfig.LOG_TYPE.ADD.getType(), "给【" + record.getRoleName() + "】绑定了新的菜单");
        }
    }

    /**
     * 删除
     */
    @Override
    @Transactional
	public void delRole(RoleRequest param) {
        if (param.getId() != null) {
			roleDoMapperExt.deleteByPrimaryKey(param.getId());
        } else if (StringUtils.isNotEmpty(param.getIds())) {
            String ids[] = param.getIds().split(",");
            for (int j = 0; j < ids.length; j++) {
				roleDoMapperExt.deleteByPrimaryKey((long) Integer.parseInt(ids[j]));
            }
        }
    }

    /**
     * 查询所有的角色
     */
    @Override
    public List<RoleDo> selectAllRole() {
        RoleDoExample example = new RoleDoExample();
        //添加条件
        RoleDoExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        return roleDoMapperExt.selectByExample(example);
    }

    /**
     * 查询登陆人拥有的角色列表
     */
    @Override
    public List<RoleDo> selectRoleDoListByUserId(Long userId) {
        RoleRequest param = new RoleRequest();
        param.setUserId(userId);
        return roleDoMapperExt.selectRoleDoListByUserId(param);
    }


}
