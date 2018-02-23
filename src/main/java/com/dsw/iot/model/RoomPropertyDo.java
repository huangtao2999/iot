package com.dsw.iot.model;

import java.util.Date;

public class RoomPropertyDo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.id
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.org
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String org;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.area
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String area;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.room_name
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String roomName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.room_no
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String roomNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.room_type
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String roomType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.room_max
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private Integer roomMax;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.room_status
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String roomStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.light_ip
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String lightIp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.light_port
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String lightPort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.light_index
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private Integer lightIndex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.light_status
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String lightStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.wind_ip
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String windIp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.wind_port
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String windPort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.wind_road
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private Integer windRoad;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.wind_status
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String windStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.wall_ip
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String wallIp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.wall_port
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String wallPort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.wall_road
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private Integer wallRoad;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.wall_status
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String wallStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.is_deleted
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.create_time
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.create_user
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.update_time
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.update_user
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_property.remark
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.id
     *
     * @return the value of room_property.id
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.id
     *
     * @param id the value for room_property.id
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.org
     *
     * @return the value of room_property.org
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getOrg() {
        return org;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.org
     *
     * @param org the value for room_property.org
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setOrg(String org) {
        this.org = org == null ? null : org.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.area
     *
     * @return the value of room_property.area
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getArea() {
        return area;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.area
     *
     * @param area the value for room_property.area
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.room_name
     *
     * @return the value of room_property.room_name
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.room_name
     *
     * @param roomName the value for room_property.room_name
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.room_no
     *
     * @return the value of room_property.room_no
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getRoomNo() {
        return roomNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.room_no
     *
     * @param roomNo the value for room_property.room_no
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo == null ? null : roomNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.room_type
     *
     * @return the value of room_property.room_type
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.room_type
     *
     * @param roomType the value for room_property.room_type
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType == null ? null : roomType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.room_max
     *
     * @return the value of room_property.room_max
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public Integer getRoomMax() {
        return roomMax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.room_max
     *
     * @param roomMax the value for room_property.room_max
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setRoomMax(Integer roomMax) {
        this.roomMax = roomMax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.room_status
     *
     * @return the value of room_property.room_status
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getRoomStatus() {
        return roomStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.room_status
     *
     * @param roomStatus the value for room_property.room_status
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus == null ? null : roomStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.light_ip
     *
     * @return the value of room_property.light_ip
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getLightIp() {
        return lightIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.light_ip
     *
     * @param lightIp the value for room_property.light_ip
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setLightIp(String lightIp) {
        this.lightIp = lightIp == null ? null : lightIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.light_port
     *
     * @return the value of room_property.light_port
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getLightPort() {
        return lightPort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.light_port
     *
     * @param lightPort the value for room_property.light_port
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setLightPort(String lightPort) {
        this.lightPort = lightPort == null ? null : lightPort.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.light_index
     *
     * @return the value of room_property.light_index
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public Integer getLightIndex() {
        return lightIndex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.light_index
     *
     * @param lightIndex the value for room_property.light_index
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setLightIndex(Integer lightIndex) {
        this.lightIndex = lightIndex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.light_status
     *
     * @return the value of room_property.light_status
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getLightStatus() {
        return lightStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.light_status
     *
     * @param lightStatus the value for room_property.light_status
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setLightStatus(String lightStatus) {
        this.lightStatus = lightStatus == null ? null : lightStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.wind_ip
     *
     * @return the value of room_property.wind_ip
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getWindIp() {
        return windIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.wind_ip
     *
     * @param windIp the value for room_property.wind_ip
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setWindIp(String windIp) {
        this.windIp = windIp == null ? null : windIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.wind_port
     *
     * @return the value of room_property.wind_port
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getWindPort() {
        return windPort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.wind_port
     *
     * @param windPort the value for room_property.wind_port
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setWindPort(String windPort) {
        this.windPort = windPort == null ? null : windPort.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.wind_road
     *
     * @return the value of room_property.wind_road
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public Integer getWindRoad() {
        return windRoad;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.wind_road
     *
     * @param windRoad the value for room_property.wind_road
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setWindRoad(Integer windRoad) {
        this.windRoad = windRoad;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.wind_status
     *
     * @return the value of room_property.wind_status
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getWindStatus() {
        return windStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.wind_status
     *
     * @param windStatus the value for room_property.wind_status
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setWindStatus(String windStatus) {
        this.windStatus = windStatus == null ? null : windStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.wall_ip
     *
     * @return the value of room_property.wall_ip
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getWallIp() {
        return wallIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.wall_ip
     *
     * @param wallIp the value for room_property.wall_ip
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setWallIp(String wallIp) {
        this.wallIp = wallIp == null ? null : wallIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.wall_port
     *
     * @return the value of room_property.wall_port
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getWallPort() {
        return wallPort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.wall_port
     *
     * @param wallPort the value for room_property.wall_port
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setWallPort(String wallPort) {
        this.wallPort = wallPort == null ? null : wallPort.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.wall_road
     *
     * @return the value of room_property.wall_road
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public Integer getWallRoad() {
        return wallRoad;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.wall_road
     *
     * @param wallRoad the value for room_property.wall_road
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setWallRoad(Integer wallRoad) {
        this.wallRoad = wallRoad;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.wall_status
     *
     * @return the value of room_property.wall_status
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getWallStatus() {
        return wallStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.wall_status
     *
     * @param wallStatus the value for room_property.wall_status
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setWallStatus(String wallStatus) {
        this.wallStatus = wallStatus == null ? null : wallStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.is_deleted
     *
     * @return the value of room_property.is_deleted
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.is_deleted
     *
     * @param isDeleted the value for room_property.is_deleted
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.create_time
     *
     * @return the value of room_property.create_time
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.create_time
     *
     * @param createTime the value for room_property.create_time
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.create_user
     *
     * @return the value of room_property.create_user
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.create_user
     *
     * @param createUser the value for room_property.create_user
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.update_time
     *
     * @return the value of room_property.update_time
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.update_time
     *
     * @param updateTime the value for room_property.update_time
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.update_user
     *
     * @return the value of room_property.update_user
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.update_user
     *
     * @param updateUser the value for room_property.update_user
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_property.remark
     *
     * @return the value of room_property.remark
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_property.remark
     *
     * @param remark the value for room_property.remark
     *
     * @mbggenerated Fri Feb 09 19:02:10 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}