package com.dsw.iot.constant;

public class BusinessBizConfig {

	/**
	 * 待办业务类型
	 * @author liujingmei
	 *
	 */
	public static final class RemindTaskBelong {
		public final static String OUT_CONFIRM = "1";    //人员出所审核
		public final static String DELAY_CONFIRM = "2";  //延期置留审核
		
		public static final String getName(String type){
			String name = "";
			switch (type) {
			case OUT_CONFIRM:
				name = "人员出所";
				break;
			case DELAY_CONFIRM:
				name = "延期置留";
				break;
			}
			return name;
		}
		
		public static final String getPath(String type){
			String path = "";
			switch (type) {
			case OUT_CONFIRM:
				path = "/OutConfirmHtml/edit.html";
				break;
			case DELAY_CONFIRM:
				path = "/DelayConfirmHtml/edit.html";
				break;
			}
			return path;
		}
	}
	
	/**
	 * 人员出所类型（字典：1-正式出所；2-临时出所）
	 * @author liujingmei
	 *
	 */
	public static final class OutConfirmType {
		public final static String FORMAL_OUT = "1";   //正式出所
		public final static String TEMPORARY_OUT = "2";//临时出所
		
		public static final String getName(String type){
			String name = "";
			switch (type) {
			case FORMAL_OUT:
				name = "正式出所";
				break;
			case TEMPORARY_OUT:
				name = "临时出所";
				break;
			}
			return name;
		}
	}
	
	/**
	 * 延期留置时限
	 * @author liujingmei
	 *
	 */
	public static final class DelayHour {
		public final static String EIGHT = "8";   		//8小时
		public final static String TWELVE = "12";		//12小时
		public final static String TWENTY_FOUR = "24";  //24小时
		public final static String FORTY_EIGHT = "48";	//48小时
		
		public static final String getName(String type){
			String name = "";
			switch (type) {
			case EIGHT:
				name = "8小时";
				break;
			case TWELVE:
				name = "12小时";
				break;
			case TWENTY_FOUR:
				name = "24小时";
				break;
			case FORTY_EIGHT:
				name = "48小时";
				break;	
			}
			return name;
		}
	}
	
	/**
	 * 审核状态（3-待审核；2-审核不通过；1-审核通过）
	 * @author liujingmei
	 *
	 */
	public static final class AuditStatus {
		public final static String PASS = "1";  	//审核通过
		public final static String REFUSE = "2";   //审核不通过
		public final static String WAIT = "3";  	//待审核
		
		public static final String getName(String status){
			String name = "";
			switch (status) {
			case PASS:
				name = "审核通过";
				break;
			case REFUSE:
				name = "审核不通过";
				break;
			case WAIT:
				name = "待审核";
				break;	
			}
			return name;
		}
		
	}
	
	/**
	 * 待办状态（0-未处理；1-已处理）
	 * @author liujingmei
	 *
	 */
	public static final class RemindStatus {
		public final static String NOTPROCESSED = "0";   //未处理
		public final static String PROCESSED = "1";  	 //已处理
		
		public static final String getName(String status){
			String name = "";
			switch (status) {
			case NOTPROCESSED:
				name = "未处理";
				break;
			case PROCESSED:
				name = "已处理";
				break;
			}
			return name;
		}
		
	}
	
	/**
	 * 物品状态（字典：0-待绑定（登记了物品后还没有绑定注册表）；
	 * 1-已登记（入办案区登记所有物品）；
	 * 2-已扣押（出办案区选择物品扣押）；
	 * 3-已归还）
	 * @author liujingmei
	 *
	 */
	public static final class GoodStatus {
		public final static String TOBEBOUND = "0";		//待绑定
		public final static String REGISTERED = "1";	//已登记
		public final static String SEIZED = "2";		//已扣押
		public final static String RETURNED = "3";		//已归还
		
		public static final String getName(String status){
			String name = "";
			switch (status) {
			case TOBEBOUND:
				name = "待绑定";
				break;
			case REGISTERED:
				name = "已登记";
				break;
			case SEIZED:
				name = "已扣押";
				break;
			case RETURNED:
				name = "已归还";
				break;
			}
			return name;
		}
		
	}
	
	public static final class YesOrNo {
		public final static String YES = "1";	//是
		public final static String NO = "0";	//否
		
		public static final String getName(String status){
			String name = "";
			switch (status) {
			case YES:
				name = "是";
				break;
			case NO:
				name = "否";
				break;
			}
			return name;
		}
		
	}
	
}
