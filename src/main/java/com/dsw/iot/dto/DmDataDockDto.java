package com.dsw.iot.dto;

import lombok.Data;

/**
 * 达梦数据查询人员信息响应1
 * @author guox
 *
 */
@Data
public class DmDataDockDto {
	
	/**
	 * 返回数据
	 */
	private Entity entity;
	
	/**
	 * 返回信息
	 */
	private String msgstr;
	
	/**
	 * 其它结果
	 */
	private Object otherResult;
	
	/**
	 * 状态码
	 */
	private String statusCode;
	
	/**
	 * 是否成功
	 */
	private boolean success;
	
	@Data
	public class Entity{
		
		private DmDataPopulationDto population;
		
	/*	private PopulationWorkObj populationWorkObj;
		
		@Data
		public class PopulationWorkObj{
			private String bDSJ;      
			private String bDYY;      
			private String bJYW;      
			private String bS;      
			private String bZ;      
			private String cXDW;      
			private String cXRQ;      
			private String cXRXM;      
			private String cXRZH;      
			private String cXYY;      
			private String cYZK;      
			private String dABH;      
			private String dAYW;      
			private String dJDW;      
			private String dJDWMC;      
			private String dJRXM;      
			private String dJRZH;      
			private String dJSJ;      
			private String dNA;      
			private String gLDW;      
			private String gZDXBH;      
			private String lLHFSJ;      
			private String rYBH;      
			private String rYLB;      
			private String sALB;      
			private String sJLY;      
			private String sSYJ;      
			private String xGDW;      
			private String xGDWMC;      
			private String xGRXM;      
			private String xGRZH;      
			private String xGSJ;      
			private String zBCL;      
			private String zPYW;      
			private String zTRY;      
			private String zTRYBH;      
			private String zWBM;      
			private String zWYW;
		}
		*/
	}

}
