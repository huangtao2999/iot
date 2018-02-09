package com.dsw.iot.service;

import java.util.List;

import com.dsw.iot.model.MenuDo;
import com.dsw.iot.util.BizException;
import com.dsw.iot.vo.MenuTreeVo;


/**
 * 菜单服务
 */
public interface MenuSerivce {
	/**
	 * 通过用户id，查出所有的菜单
	 * @param userId
	 * @return
	 */
    public List<MenuDo> findMenuByUserId(Long userId);
    /**
     * 查找所有的菜单
     * @param userId
     * @return
     */
    public List<MenuDo> findAllMenu();
    /**
     * 通过角色查找菜单
     * @param roleId
     * @return
     */
    public List<MenuDo> findMenuByRoleId(Long roleId);
    /**
     * 根据id查记录
     * @param id
     * @return
     */
    MenuDo selectByPrimaryKey(Long id);
    /**
     * 查找所有的菜单
     * 格式化成树
     * @param userId
     * @return
     */
    public List<MenuTreeVo> findAllMenuToTree();
    /**
     * 新增/编辑
     * @param request
     * @param entity
     * @param loginUserDo
     * @return
     */
	public int saveMenu(MenuDo menuDo);

	/**
	 * 级联删除菜单
	 *
	 * @param param
	 * @return
	 * @throws BizException
	 */
	int deleteMenuNodeCascade(Long id) throws BizException;
}
