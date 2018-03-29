package com.dsw.iot.dto;

import java.util.Date;
import java.util.List;

import com.dsw.iot.model.CatchInfoDo;
import com.dsw.iot.model.PersonRelatedDo;
import com.dsw.iot.util.BaseDto;

import lombok.Data;

@Data
public class PersonRegisterRequest extends BaseDto{
    /**
     * id
     */
    private Long id;

    /**
     * 证件号
     */
    private String cardNo;

    /**
     * 证件类型
     */
    private String cardType;

    /**
     * 姓名
     */
    private String name;

    /**
     * 别名
     */
    private String extraName;

    /**
     * 绰号
     */
    private String nickName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 曾用名
     */
    private String oldName;

    /**
     * 出生日期（年-月-日）
     */
    private Date birthDate;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 户籍地区划
     */
    private String domicileArea;

    /**
     * 现住地区划
     */
    private String livingArea;

    /**
     * 户籍地
     */
    private String domicilePlace;

    /**
     * 现住地
     */
    private String livingPlace;

    /**
     * 文化程度
     */
    private String educationLevel;

    /**
     * 婚姻状况（字典：1-未婚；2-已婚；3-再婚等）
     */
    private String marriageType;

    /**
     * 宗教信仰
     */
    private String relief;

    /**
     * 兵役状况
     */
    private String minitary;

    /**
     * 身高
     */
    private String height;

    /**
     * 人员标签
     */
    private String personLable;

    /**
     * 工作单位
     */
    private String workUnit;

    /**
     * 职业类别
     */
    private String professionType;

    /**
     * 国籍
     */
    private String nationality;

    /**
     * 民族
     */
    private String minority;

    /**
     * 政治面貌
     */
    private String political;

    /**
     * 身份类别（字典：1-普通人员；2-共产党员；3-人大代表等）
     */
    private String personType;

    /**
     * 身份不明（0-明确；1-不明）
     */
    private String unknownPerson;

    /**
     * 指纹编号
     */
    private String fingerNo;

    /**
     * DNA编号
     */
    private String dnaNo;

    /**
     * 到案方式（字典：1-自首；2-举报等）
     */
    private String inType;

    /**
     * 抓获地点（带入地点）
     */
    private String handingPlace;

    /**
     * 案件类型（字典）
     */
    private String caseType;

    /**
     * 特殊群体（字典：1-孕妇；2-残疾人；3-未成年人；4-聋哑人等）
     */
    private String specialGroup;

    /**
     * 人员类型（字典：1-嫌疑人；2-受害人；3-证人等）
     */
    private String peopleType;

    /**
     * 入办案区时间
     */
    private Date inTime;
    private Date inStartTime;
    private Date inEndTime;

    /**
     * 入办案区原因（字典：1-抢劫；2-盗窃等）
     */
    private String inReason;

    /**
     * 办案区域（办案区机构字典）
     */
    private String handlingArea;

    /**
     * 主办民警编号
     */
    private String hostPoliceNo;

    /**
     * 主办民警姓名
     */
    private String hostPoliceName;

    /**
     * 主办民警胸牌编码（芯片编码）【定位用】
     */
    private String hostPoliceCardNo;

    /**
     * 协办民警编号
     */
    private String joinPoliceNo;

    /**
     * 协办民警姓名
     */
    private String joinPoliceName;

    /**
     * 协办民警胸牌编码（芯片编码）【定位用】
     */
    private String joinPoliceCardNo;

    /**
     * 警情编号
     */
    private String alarmNumber;

    /**
     * 文书编号
     */
    private String wordNumber;

    /**
     * 自述有无疾病、伤情及受伤原因
     */
    private String medicalHistory;

    /**
     * 检查发现体表特殊特伤情及处理情况
     */
    private String checkBodyResult;

    /**
     * 嫌疑人手环编号（芯片编码）【定位用】
     */
    private String braceletNo;

    /**
     * 采集项目（字典：1-指纹；2-血液；3-尿液；10-其他)
     */
    private String collectProject;

    /**
     * 是否核查比对（字典：0-否；1-是）
     */
    private String doCheck;

    /**
     * 物品保管柜编号
     */
    private String lockerNo;

    /**
     * 物品保管柜id
     */
    private Long lockerId;

    /**
     * 是否同案预警（字典：0-否；1-是）
     */
    private String isWarned;

    /**
     * 被检查人签字
     */
    private String examineSign;

    /**
     * 检查民警签字
     */
    private String policeSign;

    /**
     * 检查民警2签字
     */
    private String police2Sign;

    /**
     * 见证人签字
     */
    private String witnessSign;

    /**
     * 随身财物管理员签字
     */
    private String lockerAdminSign;

    /**
     * 领取人签字（可能是代领的）
     */
    private String receiverSign;

    /**
     * 延期留置时限，默认8小时（8,12,24,48)
     */
    private String delayHour;

    /**
     * 是否延期关押（字典：0-否；1-是）
     */
    private String isDelay;

    /**
     * 出办案区时间
     */
    private Date outTime;
    private Date outStartTime;
    private Date outEndTime;

    /**
     * 出办案区原因
     */
    private String outReason;

    /**
     * 人员出入办案区状态（字典：1-在所；2-已出所）
     */
    private String personStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 描述
     */
    private String remark;

	/**
	 * 排序参数
	 */
	private String orderByClause;

	/**
	 * 抓获信息
	 */
	private CatchInfoDo catchInfo;
	/**
	 * 陪同人信息
	 */
	private List<PersonRelatedDo> relatedInfo;

	//各种附件接收参数，存放附件的id
	/**
	 * 人员照片，要绑定的附件ids，类型：PERSON_REGISTER_PERSON_IMGS
	 */
	private String personImgIds;
	/**
	 * 更新身份证附件所属信息，要绑定的附件ids，类型：PERSON_REGISTER_CARD_IMGS
	 */
	private String cardImgIds;
	/**
	 * 插入伤情表数据，更新附件所属信息
	 */
	private List<InjuryRegisterRequest> injuryInfo;
	/**
	 * 插入物品表数据，更新附件所属信息
	 */
	private List<GoodsRegisterRequest> goodsInfo;
}
