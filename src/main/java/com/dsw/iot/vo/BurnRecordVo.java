package com.dsw.iot.vo;

import java.util.Date;
import lombok.Data;

@Data
public class BurnRecordVo {
	
	private long id;
	private Long registerId;
	private String registerName;
	private String policeNo;
	private String policeName;
	private int percent;
	private int state;
	private String stateName;
	private String burnPath;
	private Date createTime;
}	
