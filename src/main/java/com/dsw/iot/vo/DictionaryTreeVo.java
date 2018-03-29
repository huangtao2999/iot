package com.dsw.iot.vo;

import java.util.Date;
import java.util.List;

import com.dsw.iot.util.BaseModel;

import lombok.Data;
/**
 * 字典目录
 */
@Data
public class DictionaryTreeVo extends BaseModel{
    /**
     * 字典表字段
     */
    private Long id;
    private Long pid;
    private String code;
    private String type;
    private String name;
    private Long sort;
    private String isSystem;
    private String remark;
    private String isDeleted;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;
    /**
     * 树是否打开(true,false)
     */
    private Boolean open;
    private Boolean isParent;
    /**
     * 节点的子集
     */
    private List<DictionaryTreeVo> children;
}
