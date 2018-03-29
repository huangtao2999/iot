package com.dsw.iot.vo;

import java.util.Date;

public class DelayConfirmVo {
   
    private Long id;

    private Long registerId;

    private String applyName;
    
    private String applyReason;
    
    private Date applyTime;
    
    private String delayHour;
   
    private String auditContent;
    
    private String auditUser;
    
    private Date auditTime;
   
    private String status;

    private String dept;
    
    private String isDeleted;

    private Date createTime;
    
    private String createUser;
   
    private Date updateTime;

    private String updateUser;
    
    private String remark;
    
    private Long roleId;

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
   
    public Long getRegisterId() {
        return registerId;
    }
    
    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }
    
    public String getApplyName() {
        return applyName;
    }
    
    public void setApplyName(String applyName) {
        this.applyName = applyName == null ? null : applyName.trim();
    }

    public String getApplyReason() {
        return applyReason;
    }
    
    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason == null ? null : applyReason.trim();
    }
   
    public Date getApplyTime() {
        return applyTime;
    }
    
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
    
    public String getDelayHour() {
        return delayHour;
    }
   
    public void setDelayHour(String delayHour) {
        this.delayHour = delayHour == null ? null : delayHour.trim();
    }
    
    public String getAuditContent() {
        return auditContent;
    }
   
    public void setAuditContent(String auditContent) {
        this.auditContent = auditContent == null ? null : auditContent.trim();
    }
    
    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser == null ? null : auditUser.trim();
    }
    
    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
    
    public String getStatus() {
        return status;
    }
   
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
   
    public String getDept() {
        return dept;
    }
   
    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }
   
    public String getIsDeleted() {
        return isDeleted;
    }
    
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }
    
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
   
    public String getCreateUser() {
        return createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getUpdateUser() {
        return updateUser;
    }
    
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
    
}