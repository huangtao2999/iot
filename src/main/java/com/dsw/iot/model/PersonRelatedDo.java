package com.dsw.iot.model;

import java.util.Date;

public class PersonRelatedDo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_related.id
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_related.register_id
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private Long registerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_related.card_no
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String cardNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_related.card_type
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String cardType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_related.name
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_related.sex
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_related.phone
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_related.relationship
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String relationship;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_related.language
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String language;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_related.type
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_related.chip_no
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String chipNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_related.is_deleted
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_related.create_time
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_related.create_user
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_related.update_time
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_related.update_user
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person_related.remark
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_related.id
     *
     * @return the value of person_related.id
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_related.id
     *
     * @param id the value for person_related.id
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_related.register_id
     *
     * @return the value of person_related.register_id
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public Long getRegisterId() {
        return registerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_related.register_id
     *
     * @param registerId the value for person_related.register_id
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_related.card_no
     *
     * @return the value of person_related.card_no
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_related.card_no
     *
     * @param cardNo the value for person_related.card_no
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_related.card_type
     *
     * @return the value of person_related.card_type
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_related.card_type
     *
     * @param cardType the value for person_related.card_type
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_related.name
     *
     * @return the value of person_related.name
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_related.name
     *
     * @param name the value for person_related.name
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_related.sex
     *
     * @return the value of person_related.sex
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_related.sex
     *
     * @param sex the value for person_related.sex
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_related.phone
     *
     * @return the value of person_related.phone
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_related.phone
     *
     * @param phone the value for person_related.phone
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_related.relationship
     *
     * @return the value of person_related.relationship
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_related.relationship
     *
     * @param relationship the value for person_related.relationship
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship == null ? null : relationship.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_related.language
     *
     * @return the value of person_related.language
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getLanguage() {
        return language;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_related.language
     *
     * @param language the value for person_related.language
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_related.type
     *
     * @return the value of person_related.type
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_related.type
     *
     * @param type the value for person_related.type
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_related.chip_no
     *
     * @return the value of person_related.chip_no
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getChipNo() {
        return chipNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_related.chip_no
     *
     * @param chipNo the value for person_related.chip_no
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setChipNo(String chipNo) {
        this.chipNo = chipNo == null ? null : chipNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_related.is_deleted
     *
     * @return the value of person_related.is_deleted
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_related.is_deleted
     *
     * @param isDeleted the value for person_related.is_deleted
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_related.create_time
     *
     * @return the value of person_related.create_time
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_related.create_time
     *
     * @param createTime the value for person_related.create_time
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_related.create_user
     *
     * @return the value of person_related.create_user
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_related.create_user
     *
     * @param createUser the value for person_related.create_user
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_related.update_time
     *
     * @return the value of person_related.update_time
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_related.update_time
     *
     * @param updateTime the value for person_related.update_time
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_related.update_user
     *
     * @return the value of person_related.update_user
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_related.update_user
     *
     * @param updateUser the value for person_related.update_user
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person_related.remark
     *
     * @return the value of person_related.remark
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person_related.remark
     *
     * @param remark the value for person_related.remark
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}