package com.dsw.iot.dto;

import java.util.Date;

import com.dsw.iot.util.BaseDto;

import lombok.Data;


@Data
public class SystemConfRequest extends BaseDto {
    
    /**
     * 查询ID
     * 
     */
    private Long id;
    
    
    /**
     * 查询主键名称
     * 
     */
    private String keyInfo;
    
    /**
     * 标题title
     * 
     */
    private String title;
    
    
    /**
     * 配置文件属性值
     * 
     */
    private String messageValue;
    
    /**
     * 说明
     * 
     */
    
    private String content;
    
    
    /**
     * 是否删除*
     *
     */
    private String isDeleted;

    /**
     * 创建时间*
     *
     */
    private Date createTime;

    /**
     * 创建人 *
     *
     */
    private String createUser;

    /**
     * 更新时间*
     *
     */
    private Date updateTime;

    /**
     * 更新人*
     *
     */
    private String updateUser;

    /**
     * 描述*
     *
     */
    private String remark;

}
