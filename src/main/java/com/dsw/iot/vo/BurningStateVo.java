package com.dsw.iot.vo;

import java.util.Date;

public class BurningStateVo {
	
	private long personRegisterId;
	private int state;
	private int percent;
	private Date updateTime;
	private String burnPath;
	
	public BurningStateVo(){}
	
	public BurningStateVo(long personRegisterId) {
		this.personRegisterId = personRegisterId;
		updateTime = new Date();
	}
	
	public long getPersonRegisterId() {
		return personRegisterId;
	}
	public void setPersonRegisterId(long personRegisterId) {
		this.personRegisterId = personRegisterId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getBurnPath() {
		return burnPath;
	}
	public void setBurnPath(String burnPath) {
		this.burnPath = burnPath;
	}
	
	
}
