package com.dsw.iot.model;

import java.util.Date;

public class RoomAreaDo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_area.id
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_area.room_name
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String roomName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_area.beginX
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private Long beginX;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_area.endX
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private Long endX;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_area.beginY
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private Long beginY;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_area.endY
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private Long endY;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_area.beginZ
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private Long beginZ;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_area.endZ
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private Long endZ;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_area.status
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_area.is_deleted
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_area.create_time
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_area.create_user
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_area.update_time
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_area.update_user
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_area.remark
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_area.id
     *
     * @return the value of room_area.id
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_area.id
     *
     * @param id the value for room_area.id
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_area.room_name
     *
     * @return the value of room_area.room_name
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_area.room_name
     *
     * @param roomName the value for room_area.room_name
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_area.beginX
     *
     * @return the value of room_area.beginX
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public Long getBeginX() {
        return beginX;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_area.beginX
     *
     * @param beginX the value for room_area.beginX
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setBeginX(Long beginX) {
        this.beginX = beginX;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_area.endX
     *
     * @return the value of room_area.endX
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public Long getEndX() {
        return endX;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_area.endX
     *
     * @param endX the value for room_area.endX
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setEndX(Long endX) {
        this.endX = endX;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_area.beginY
     *
     * @return the value of room_area.beginY
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public Long getBeginY() {
        return beginY;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_area.beginY
     *
     * @param beginY the value for room_area.beginY
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setBeginY(Long beginY) {
        this.beginY = beginY;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_area.endY
     *
     * @return the value of room_area.endY
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public Long getEndY() {
        return endY;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_area.endY
     *
     * @param endY the value for room_area.endY
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setEndY(Long endY) {
        this.endY = endY;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_area.beginZ
     *
     * @return the value of room_area.beginZ
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public Long getBeginZ() {
        return beginZ;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_area.beginZ
     *
     * @param beginZ the value for room_area.beginZ
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setBeginZ(Long beginZ) {
        this.beginZ = beginZ;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_area.endZ
     *
     * @return the value of room_area.endZ
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public Long getEndZ() {
        return endZ;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_area.endZ
     *
     * @param endZ the value for room_area.endZ
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setEndZ(Long endZ) {
        this.endZ = endZ;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_area.status
     *
     * @return the value of room_area.status
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_area.status
     *
     * @param status the value for room_area.status
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_area.is_deleted
     *
     * @return the value of room_area.is_deleted
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_area.is_deleted
     *
     * @param isDeleted the value for room_area.is_deleted
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_area.create_time
     *
     * @return the value of room_area.create_time
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_area.create_time
     *
     * @param createTime the value for room_area.create_time
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_area.create_user
     *
     * @return the value of room_area.create_user
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_area.create_user
     *
     * @param createUser the value for room_area.create_user
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_area.update_time
     *
     * @return the value of room_area.update_time
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_area.update_time
     *
     * @param updateTime the value for room_area.update_time
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_area.update_user
     *
     * @return the value of room_area.update_user
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_area.update_user
     *
     * @param updateUser the value for room_area.update_user
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_area.remark
     *
     * @return the value of room_area.remark
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_area.remark
     *
     * @param remark the value for room_area.remark
     *
     * @mbggenerated Thu Mar 01 11:47:52 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}