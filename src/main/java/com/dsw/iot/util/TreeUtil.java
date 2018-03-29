package com.dsw.iot.util;

import java.util.ArrayList;
import java.util.List;

import com.dsw.iot.model.DictionaryDo;
import com.dsw.iot.model.MenuDo;
import com.dsw.iot.vo.DictionaryTreeVo;
import com.dsw.iot.vo.MenuTreeVo;

public class TreeUtil {

	/**
	 * 通过id，获取这个节点以及下级节点数据，单层返回（删除用）
	 * @param list
	 * @param rootId
	 * @return
	 */
	public static List<MenuDo> getMenuListById(List<MenuDo> list, Long rootId) {
		List<MenuDo> rootList = new ArrayList<>();
		List<MenuDo> resList = new ArrayList<>();
		for(MenuDo map : list){
			if (map.getId().longValue() == rootId.longValue()) {
				rootList.add(map);
			}
		}
		resList.addAll(rootList);
		for (MenuDo map : rootList) {
			addChildMenuNode(map, list, resList);
		}
		return resList;
	}

	/**
	 * 通过id，找到子集，单层拼接
	 *
	 * @param root
	 * @param list
	 * @param resList
	 */
	private static void addChildMenuNode(MenuDo root, List<MenuDo> list, List<MenuDo> resList) {
		List<MenuDo> childList = new ArrayList<MenuDo>();
		for (MenuDo map : list) {
			if (root.getId().longValue() == map.getPid().longValue()) {
				childList.add(map);
			}
		}
		if (childList.size() > 0) {
			resList.addAll(childList);
			for (MenuDo map : childList) {
				addChildMenuNode(map, list, resList);
			}
		}
	}

	/**
	 * 创建菜单目录树形结构
	 *
	 * @param list
	 * @param nameField
	 * @return
	 */
	public static List<MenuTreeVo> createMenuTree(List<MenuTreeVo> list, Long rootId) {
		List<MenuTreeVo> rootList = getRootNote(list, rootId);
		for (MenuTreeVo map : list) {
			addChildNote(map, list);
		}
		return rootList;
	}

	/**
	 * 添加菜单目录子节点
	 *
	 * @param root
	 * @param list
	 * @param nameField
	 */
	private static void addChildNote(MenuTreeVo root, List<MenuTreeVo> list){
		List<MenuTreeVo> childList = new ArrayList<MenuTreeVo>();
		for (MenuTreeVo map : list) {
			if (root.getId().longValue() == map.getPid().longValue()) {
				map.setName(map.getText());
				childList.add(map);
			}
		}
		if(childList.size() > 0){
			root.setChildren(childList);
			for (MenuTreeVo map : childList) {
				addChildNote(map, list);
			}
		}
	}

	/**
	 * 获取菜单目录根节点
	 *
	 * @param list
	 * @return
	 */
	private static List<MenuTreeVo> getRootNote(List<MenuTreeVo> list, Long rootId) {
		List<MenuTreeVo> rootList = new ArrayList<MenuTreeVo>();
		for (MenuTreeVo map : list) {
			if (map.getPid().longValue() == rootId.longValue()) {
				map.setName(map.getText());
				rootList.add(map);
			}
		}
		return rootList;
	}

	/////////////////////////////////////////////////////////////////////////////////
	/**
	 * 创建数据字典树
	 *
	 * @param list
	 * @return
	 */
	public static List<DictionaryTreeVo> createDictionaryTree(List<DictionaryTreeVo> list) {
		List<DictionaryTreeVo> rootList = getRootDictionary(list);
		for (DictionaryTreeVo map : list) {
			addDictionaryChildNote(map, list);
		}
		return rootList;
		// return filterData(rootList);
	}

	/**
	 * 获取数据字典根节点
	 * @param list
	 * @return
	 */
	private static List<DictionaryTreeVo> getRootDictionary(List<DictionaryTreeVo> list) {
		List<DictionaryTreeVo> rootList = new ArrayList<DictionaryTreeVo>();
		for (DictionaryTreeVo map : list) {
			if (map.getPid().longValue() == 0 || null == map.getPid()) {
				rootList.add(map);
			}
		}
		return rootList;
	}

	/**
	 * 添加数据字典子节点【页面使用】（下拉框、下拉框树等）
	 *
	 * @param root
	 * @param list
	 * @param nameField
	 */
	private static void addDictionaryChildNote(DictionaryTreeVo root, List<DictionaryTreeVo> list) {
		List<DictionaryTreeVo> childList = new ArrayList<DictionaryTreeVo>();
		for (DictionaryTreeVo map : list) {
			if (root.getId().longValue() == map.getPid().longValue()) {
				childList.add(map);
			}
		}
		if (childList.size() > 0) {
			root.setChildren(childList);
			for (DictionaryTreeVo map : childList) {
				addDictionaryChildNote(map, list);
			}
		}
	}

	/**
	 * 通过code查询出入层级的树（传入几级就默认查几级，不传默认把子集都查出来）
	 *
	 * @param dictionaryTreeVos
	 * @param code
	 * @param lay
	 * @return
	 */
	public static List<DictionaryTreeVo> getDictionaryByCodeAndLay(List<DictionaryTreeVo> dictionaryTreeVos,
			String code, int lay) {
		List<DictionaryTreeVo> rootList = new ArrayList<DictionaryTreeVo>();
		int m = 0;
		for (DictionaryTreeVo map : dictionaryTreeVos) {
			if (map.getCode().equals(code)) {
				rootList.add(map);
				addChildNoteByCodeAndLay(map, dictionaryTreeVos, lay, m);
			}
		}
		return rootList;
	}

	/**
	 * 添加某个节点的子节点
	 *
	 * @param root
	 * @param list
	 * @param nameField
	 */
	private static void addChildNoteByCodeAndLay(DictionaryTreeVo root, List<DictionaryTreeVo> list, int lay, int m) {
		List<DictionaryTreeVo> childList = new ArrayList<DictionaryTreeVo>();
		for (DictionaryTreeVo map : list) {
			if (root.getId().longValue() == map.getPid().longValue()) {
				childList.add(map);
			}
		}
		// 没有定义查询几级，查询所有
		if (lay == 0) {
			if (childList.size() > 0) {
				root.setChildren(childList);
				for (DictionaryTreeVo map : childList) {
					addDictionaryChildNote(map, list);
				}
			}
		} else {
			m++;
			if (m <= lay) {
				if (childList.size() > 0) {
					root.setChildren(childList);
					for (DictionaryTreeVo map : childList) {
						addDictionaryChildNote(map, list);
					}
				}
			}
		}
	}
	// /**
	// * 过滤数据
	// *
	// * @param list
	// * @return
	// */
	// @SuppressWarnings("unchecked")
	// private static List<DictionaryTreeVo> filterData(List<DictionaryTreeVo>
	// list) {
	// for (int i = 0; i < list.size(); i++) {
	// DictionaryTreeVo map = list.get(i);
	// DictionaryTreeVo dicMap = new DictionaryTreeVo();
	// dicMap.put("code", map.get("code"));
	// dicMap.put("text", map.get("text"));
	// if (map.get("child") != null) {
	// dicMap.put("child", filterData((List<Map<String, Object>>)
	// map.get("child")));
	// }
	// list.set(i, dicMap);
	// }
	// return list;
	// }


	/**
	 * 获取某个节点id下的所有数据，平级展示
	 *
	 * @param list
	 * @return
	 */
	public static List<DictionaryDo> getListByRootId(List<DictionaryDo> list, Long rootId) {
		List<DictionaryDo> rootList = new ArrayList<DictionaryDo>();
		List<DictionaryDo> resList = new ArrayList<DictionaryDo>();
		for (DictionaryDo map : list) {
			if (rootId.longValue() == map.getId().longValue()) {
				rootList.add(map);
			}
		}
		resList.addAll(rootList);
		for (DictionaryDo map : rootList) {
			addDictionaryChildNote(map, list, resList);
		}
		return resList;
	}

	/**
	 * 添加数据字典子节点【页面使用】（下拉框、下拉框树等）
	 *
	 * @param root
	 * @param list
	 * @param nameField
	 */
	private static void addDictionaryChildNote(DictionaryDo root, List<DictionaryDo> list, List<DictionaryDo> resList) {
		List<DictionaryDo> childList = new ArrayList<DictionaryDo>();
		for (DictionaryDo map : list) {
			if (root.getId().longValue() == map.getPid().longValue()) {
				childList.add(map);
			}
		}
		if (childList.size() > 0) {
			resList.addAll(childList);
			for (DictionaryDo map : childList) {
				addDictionaryChildNote(map, list, resList);
			}
		}
	}
}
