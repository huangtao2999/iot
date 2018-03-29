package com.dsw.iot.model;

import java.util.Date;

public class RemindDo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remind.id
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remind.title
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remind.content
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remind.path
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    private String path;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remind.user_id
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remind.role_id
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    private Long roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remind.org
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    private String org;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remind.status
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remind.is_deleted
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remind.create_time
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remind.create_user
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remind.update_time
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remind.update_user
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remind.remark
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remind.task_id
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    private Long taskId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remind.task_belong
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    private String taskBelong;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remind.id
     *
     * @return the value of remind.id
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remind.id
     *
     * @param id the value for remind.id
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remind.title
     *
     * @return the value of remind.title
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remind.title
     *
     * @param title the value for remind.title
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remind.content
     *
     * @return the value of remind.content
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remind.content
     *
     * @param content the value for remind.content
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remind.path
     *
     * @return the value of remind.path
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public String getPath() {
        return path;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remind.path
     *
     * @param path the value for remind.path
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remind.user_id
     *
     * @return the value of remind.user_id
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remind.user_id
     *
     * @param userId the value for remind.user_id
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remind.role_id
     *
     * @return the value of remind.role_id
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remind.role_id
     *
     * @param roleId the value for remind.role_id
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remind.org
     *
     * @return the value of remind.org
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public String getOrg() {
        return org;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remind.org
     *
     * @param org the value for remind.org
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public void setOrg(String org) {
        this.org = org == null ? null : org.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remind.status
     *
     * @return the value of remind.status
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remind.status
     *
     * @param status the value for remind.status
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remind.is_deleted
     *
     * @return the value of remind.is_deleted
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remind.is_deleted
     *
     * @param isDeleted the value for remind.is_deleted
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remind.create_time
     *
     * @return the value of remind.create_time
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remind.create_time
     *
     * @param createTime the value for remind.create_time
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remind.create_user
     *
     * @return the value of remind.create_user
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remind.create_user
     *
     * @param createUser the value for remind.create_user
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remind.update_time
     *
     * @return the value of remind.update_time
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remind.update_time
     *
     * @param updateTime the value for remind.update_time
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remind.update_user
     *
     * @return the value of remind.update_user
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remind.update_user
     *
     * @param updateUser the value for remind.update_user
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remind.remark
     *
     * @return the value of remind.remark
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remind.remark
     *
     * @param remark the value for remind.remark
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remind.task_id
     *
     * @return the value of remind.task_id
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remind.task_id
     *
     * @param taskId the value for remind.task_id
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remind.task_belong
     *
     * @return the value of remind.task_belong
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public String getTaskBelong() {
        return taskBelong;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remind.task_belong
     *
     * @param taskBelong the value for remind.task_belong
     *
     * @mbggenerated Sat Mar 03 15:12:19 CST 2018
     */
    public void setTaskBelong(String taskBelong) {
        this.taskBelong = taskBelong == null ? null : taskBelong.trim();
    }
}