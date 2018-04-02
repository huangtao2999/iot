package com.dsw.iot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.MenuDoMapperExt;
import com.dsw.iot.dto.MenuRequest;
import com.dsw.iot.model.MenuDo;
import com.dsw.iot.model.MenuDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.FileUploadService;
import com.dsw.iot.service.LogService;
import com.dsw.iot.service.MenuSerivce;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.TreeUtil;
import com.dsw.iot.vo.MenuTreeVo;

/**
 * 菜单服务类
 *
 * @author huangt
 * @create 2018-01-17 11:18
 **/
@Service
public class MenuServiceImpl implements MenuSerivce {
    @Autowired(required = false)
    private MenuDoMapperExt menuDoMapperExt;
    @Autowired
    CurrentUserService currentUserService;
	@Autowired
	private FileUploadService fileUploadService;
	@Autowired
	private LogService logService;

    /**
	 * 查找当前用户的菜单，平级返回，没有树形结构
	 */
    @Override
    public List<MenuDo> findMenuByUserId(Long userId) {
        MenuRequest param = new MenuRequest();
        param.setUserId(userId);
        return menuDoMapperExt.findMenuByUserId(param);
    }

	/**
	 * 通过用户id，查出菜单树返回
	 */
	@Override
	public List<MenuTreeVo> findMenuTreeByUserId() {
		Long userId = currentUserService.getPvgInfo().getUserId();
		MenuRequest param = new MenuRequest();
		param.setUserId(userId);
		// 查询所有的菜单
		List<MenuDo> list = menuDoMapperExt.findMenuByUserId(param);
		// 写日志
		logService.insertLog(CommConfig.LOG_MODULE.MENU.getModule(),CommConfig.LOG_TYPE.QUERY.getType(),
				currentUserService.getPvgInfo().getName() + "  通过用户ID查出了菜单树");
		// 定义菜单树实体
		List<MenuTreeVo> menuTreeVos = new ArrayList<MenuTreeVo>();
		// 把数据给菜单树
		for (int m = 0; m < list.size(); m++) {
			MenuTreeVo menu = new MenuTreeVo();
			menu.setMenuIcon(list.get(m).getIcon());
			BeanUtils.copyProperties(list.get(m), menu);
			menuTreeVos.add(menu);
		}
		// 拼接成children的树返回给前台的ztree插件
		if (CollectionUtils.isNotEmpty(menuTreeVos)) {
			// 格式化树
			menuTreeVos = TreeUtil.createMenuTree(menuTreeVos, (long) 0);
		}
		return menuTreeVos;
	}

    /**
     * 查找所有的菜单
     */
    @Override
    public List<MenuDo> findAllMenu() {
        //定义查询条件容器
        MenuDoExample example = new MenuDoExample();
        //新建查询标准
        MenuDoExample.Criteria criteria = example.createCriteria();
        //添加默认条件is_deleted='N'
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		example.setOrderByClause("sort asc");
        List<MenuDo> list = menuDoMapperExt.selectByExample(example);
        return list;
    }

    /**
     * 通过角色找菜单
     */
    @Override
    public List<MenuDo> findMenuByRoleId(Long roleId) {
        MenuRequest param = new MenuRequest();
        param.setRoleId(roleId);
        return menuDoMapperExt.findMenuByRoleId(param);
    }

    /**
     * 通过主键id获得一条记录
     */
    @Override
    public MenuDo selectByPrimaryKey(Long id) {
    	// 写日志
		logService.insertLog(CommConfig.LOG_MODULE.MENU.getModule(),CommConfig.LOG_TYPE.QUERY.getType(),
				currentUserService.getPvgInfo().getName() + "  通过ID查询了菜单表的一条记录");
        return menuDoMapperExt.selectByPrimaryKey(id);
    }

    /**
     * 查找所有的菜单
     * 格式化成树
     */
    @Override
    public List<MenuTreeVo> findAllMenuToTree() {
        //定义查询条件容器
        MenuDoExample example = new MenuDoExample();
        //新建查询标准
        MenuDoExample.Criteria criteria = example.createCriteria();
        //添加默认条件is_deleted='N'
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
		example.setOrderByClause("sort asc");
        //查询所有的菜单
        List<MenuDo> list = menuDoMapperExt.selectByExample(example);
        //定义菜单树实体
        List<MenuTreeVo> menuTreeVos = new ArrayList<MenuTreeVo>();
        //把数据给菜单树
        for (int m = 0; m < list.size(); m++) {
            MenuTreeVo menu = new MenuTreeVo();
            BeanUtils.copyProperties(list.get(m), menu);
            menuTreeVos.add(menu);
        }
        //拼接成children的树返回给前台的ztree插件
        if (CollectionUtils.isNotEmpty(menuTreeVos)) {
            //格式化树
            menuTreeVos = TreeUtil.createMenuTree(menuTreeVos, (long) 0);
        }
        return menuTreeVos;
    }

    /**
     * 新增/编辑
     */
    @Override
	@Transactional
	public void saveMenu(MenuDo menuDo) {
        if (StringUtils.isBlank(menuDo.getIsValid())) {
            //默认有效
            menuDo.setIsValid("1");
        }
        if (menuDo.getId() == null) {
            //新增
            DomainUtil.setCommonValueForCreate(menuDo, currentUserService.getPvgInfo());
			menuDoMapperExt.insertSelective(menuDo);
			// 写日志
    		logService.insertLog(CommConfig.LOG_MODULE.MENU.getModule(),CommConfig.LOG_TYPE.ADD.getType(),
    				currentUserService.getPvgInfo().getName() + "  新增了菜单："+menuDo.getText());
        } else {
            //编辑
            DomainUtil.setCommonValueForUpdate(menuDo, currentUserService.getPvgInfo());
			menuDoMapperExt.updateByPrimaryKeySelective(menuDo);
			// 写日志
    		logService.insertLog(CommConfig.LOG_MODULE.MENU.getModule(),CommConfig.LOG_TYPE.UPDATE.getType(),
    				currentUserService.getPvgInfo().getName() + "  更改了菜单："+menuDo.getText());
        }
		// 更新头像信息
		String menuIconIds = menuDo.getIcon();
		fileUploadService.updateAttach(menuIconIds, menuDo.getId(), CommConfig.ATTACH_TYPE.MENU_ICON.getType(),
				CommConfig.ATTACH_TYPE.MENU_ICON.getName());

    }

    /**
     * 级联删除
     *
     * @throws BizException
     */
    @Override
    public int deleteMenuNodeCascade(Long id) throws BizException {
        int i = 0;
        // 定义查询条件容器
        MenuDoExample example = new MenuDoExample();
        // 新建查询标准
        MenuDoExample.Criteria criteria = example.createCriteria();
        // 添加默认条件is_deleted='N'
        criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
        List<MenuDo> menuDos = menuDoMapperExt.selectByExample(example);
        // 找出id下所有的节点
        if (CollectionUtils.isNotEmpty(menuDos)) {
            List<MenuDo> menuDos2 = TreeUtil.getMenuListById(menuDos, id);
            for (int m = 0; m < menuDos2.size(); m++) {
                MenuDo menuDo = menuDos2.get(m);
				i = menuDoMapperExt.deleteByPrimaryKeyReal(menuDo.getId());
                if (i == 0) {
                    throw new BizException("删除失败");
                }
            }
        }
        // 写日志
		logService.insertLog(CommConfig.LOG_MODULE.MENU.getModule(),CommConfig.LOG_TYPE.DELETE.getType(),
				currentUserService.getPvgInfo().getName() + "  删除了菜单");
        return i;
    }
}
