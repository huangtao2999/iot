package com.dsw.iot.service.impl;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.MenuDoMapperExt;
import com.dsw.iot.dto.MenuRequest;
import com.dsw.iot.model.MenuDo;
import com.dsw.iot.model.MenuDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.MenuSerivce;
import com.dsw.iot.util.BizException;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.TreeUtil;
import com.dsw.iot.vo.MenuTreeVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 查找当前用户的菜单
     */
    @Override
    public List<MenuDo> findMenuByUserId(Long userId) {
        MenuRequest param = new MenuRequest();
        param.setUserId(userId);
        return menuDoMapperExt.findMenuByUserId(param);
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
    public int saveMenu(MenuDo menuDo) {
        int i = 0;
        if (StringUtils.isBlank(menuDo.getIsValid())) {
            //默认有效
            menuDo.setIsValid("1");
        }
        if (menuDo.getId() == null) {
            //新增
            DomainUtil.setCommonValueForCreate(menuDo, currentUserService.getPvgInfo());
            i = menuDoMapperExt.insertSelective(menuDo);
        } else {
            //编辑
            DomainUtil.setCommonValueForUpdate(menuDo, currentUserService.getPvgInfo());
            i = menuDoMapperExt.updateByPrimaryKeySelective(menuDo);
        }
        return i;
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
                i = menuDoMapperExt.deleteByPrimaryKey(menuDo.getId());
                if (i == 0) {
                    throw new BizException("删除失败");
                }
            }
        }

        return i;
    }
}
