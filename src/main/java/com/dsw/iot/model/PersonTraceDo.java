package com.dsw.iot.model;

import java.util.Date;

public class PersonTraceDo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_trace.id
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_trace.register_id
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private Long registerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_trace.person_name
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String personName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_trace.content
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_trace.is_deleted
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_trace.create_time
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_trace.create_user
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_trace.update_time
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_trace.update_user
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_trace.remark
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_trace.id
     *
     * @return the value of person_trace.id
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_trace.id
     *
     * @param id the value for person_trace.id
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_trace.register_id
     *
     * @return the value of person_trace.register_id
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public Long getRegisterId() {
        return registerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_trace.register_id
     *
     * @param registerId the value for person_trace.register_id
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_trace.person_name
     *
     * @return the value of person_trace.person_name
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_trace.person_name
     *
     * @param personName the value for person_trace.person_name
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_trace.content
     *
     * @return the value of person_trace.content
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_trace.content
     *
     * @param content the value for person_trace.content
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_trace.is_deleted
     *
     * @return the value of person_trace.is_deleted
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_trace.is_deleted
     *
     * @param isDeleted the value for person_trace.is_deleted
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_trace.create_time
     *
     * @return the value of person_trace.create_time
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_trace.create_time
     *
     * @param createTime the value for person_trace.create_time
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_trace.create_user
     *
     * @return the value of person_trace.create_user
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_trace.create_user
     *
     * @param createUser the value for person_trace.create_user
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_trace.update_time
     *
     * @return the value of person_trace.update_time
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_trace.update_time
     *
     * @param updateTime the value for person_trace.update_time
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_trace.update_user
     *
     * @return the value of person_trace.update_user
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_trace.update_user
     *
     * @param updateUser the value for person_trace.update_user
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_trace.remark
     *
     * @return the value of person_trace.remark
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_trace.remark
     *
     * @param remark the value for person_trace.remark
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}