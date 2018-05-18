package com.dsw.iot.dto;

import java.util.Date;
import java.util.List;

public class BurnRequest {
	
	private long personRegisterId;
	private List<String> downloadUrl;
	private String userName;
	private String password;
	private String nvrIp;
	private int nvrPort;
	private long nvrChannel;
	private Date startTime;
	private Date endTime;
 	private String name;
	private String caseName;
	
	public long getPersonRegisterId() {
		return personRegisterId;
	}
	public void setPersonRegisterId(long personRegisterId) {
		this.personRegisterId = personRegisterId;
	}
	public List<String> getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(List<String> downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNvrIp() {
		return nvrIp;
	}
	public void setNvrIp(String nvrIp) {
		this.nvrIp = nvrIp;
	}
	public int getNvrPort() {
		return nvrPort;
	}
	public void setNvrPort(int nvrPort) {
		this.nvrPort = nvrPort;
	}
	public long getNvrChannel() {
		return nvrChannel;
	}
	public void setNvrChannel(long nvrChannel) {
		this.nvrChannel = nvrChannel;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	
	
}
