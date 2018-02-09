package com.dsw.iot.vo;

import java.util.Date;
import java.util.List;

import com.dsw.iot.util.BaseModel;

import lombok.Data;

/**
 * 菜单目录树
 */
@Data
public class MenuTreeVo extends BaseModel{
	/**
	 * 菜单表所有字段
	 */
    private Long id;
    private Long pid;
    private String text;
    private String action;
    private String icon;
    private Integer sort;
    private String isValid;
    private String isDeleted;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;

    /**
     * 树节点名（可以通过ztree配置，识别text；但是由id和pid生成的树必须是name）
     */
    private String name;
    /**
     * 树是否打开(true,false)
     */
    private String open;
    /**
     * 节点的子集
     */
    private List<MenuTreeVo> children;
}
