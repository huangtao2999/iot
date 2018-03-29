package com.dsw.iot.dto;

import java.sql.Timestamp;
import java.util.Date;

import com.dsw.iot.util.BaseDto;

import lombok.Data;

@Data
public class RemindRequest extends BaseDto{
    
    /**
     * 用户所属的多个角色
     */
	private String roleIds = "";

	/**
	 * 标题
	 */
	private String title = "";
	
	/**
	 * 内容
	 */
	private String content = "";
	
	/**
	 * 创建人
	 */
	private String createUser = "";
	
	 /**
     * 创建时间
     */
    private Timestamp createTime;
    private Date createStartTime;//创建开始时间
    private Date createEndTime;//创建结束时间
    /**
     * 排序
     */
    private String orderByClause;
}
