/*
Navicat MySQL Data Transfer

Source Server         : dsw-112.74.203.20
Source Server Version : 50540
Source Host           : 112.74.203.20:3306
Source Database       : guankong

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2018-04-11 14:46:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for alarm_manage
-- ----------------------------
DROP TABLE IF EXISTS `alarm_manage`;
CREATE TABLE `alarm_manage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alarm_type` varchar(30) DEFAULT NULL COMMENT '预警类型（字典：1-电子围栏类型；2-尿检样本逾期；3-人员留置逾期）',
  `alarm_level` varchar(20) DEFAULT NULL COMMENT '预警等级（字典：1-一级；2-二级；3-三级；4-四级等）',
  `alarm_time` timestamp NULL DEFAULT NULL COMMENT '预警时间',
  `content` varchar(150) DEFAULT NULL COMMENT '预警内容',
  `dept` varchar(50) DEFAULT NULL COMMENT '单位',
  `register_id` int(11) DEFAULT NULL COMMENT '触发人员注册表主键（非嫌疑人填本次嫌疑人的注册id）',
  `tag_euid` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `active_person` varchar(50) DEFAULT NULL COMMENT '触发人员',
  `card_id` varchar(30) DEFAULT NULL COMMENT '触发人手环/胸牌MAC编码（芯片读取码）',
  `handle_person` varchar(30) DEFAULT NULL COMMENT '处理人',
  `handler_no` varchar(50) DEFAULT NULL COMMENT '处理人编号',
  `handle_method` varchar(20) DEFAULT NULL COMMENT '处理方式，电子围栏警报用（1-处理方式一；2-处理方式二；3-处理方式三；4-处理方式四）',
  `handle_content` varchar(200) DEFAULT NULL COMMENT '处理内容',
  `status` varchar(10) DEFAULT NULL COMMENT '状态（字典：1-预警中；2-已处理）',
  `is_deleted` varchar(50) DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8 COMMENT='预警管理（预警管理中记录人员留置逾期，尿检样本逾期，电子围栏类型等）';

-- ----------------------------
-- Table structure for attach
-- ----------------------------
DROP TABLE IF EXISTS `attach`;
CREATE TABLE `attach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) DEFAULT NULL COMMENT '业务id',
  `task_belong` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '所属业务',
  `file_name` varchar(150) COLLATE utf8_bin DEFAULT NULL COMMENT '文件名',
  `path` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '文件路径',
  `size` int(12) DEFAULT NULL COMMENT '文件大小（单位为KB）',
  `remark` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `status` varchar(10) COLLATE utf8_bin DEFAULT '0' COMMENT '状态（0-暂存；1-已存）',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1894 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='附件表';

-- ----------------------------
-- Table structure for card_locate
-- ----------------------------
DROP TABLE IF EXISTS `card_locate`;
CREATE TABLE `card_locate` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `card_id` varchar(30) DEFAULT NULL COMMENT '卡芯片编码id',
  `register_id` int(11) DEFAULT NULL COMMENT '人员注册id（register_id）',
  `tag_id` int(11) DEFAULT NULL COMMENT '定位标签id',
  `tag_euid` varchar(30) DEFAULT NULL COMMENT '定位标签euid',
  `localX` int(11) DEFAULT NULL COMMENT '坐标X值(像素)',
  `localY` int(11) DEFAULT NULL COMMENT '坐标Y值(像素)',
  `localZ` int(11) DEFAULT NULL COMMENT '坐标Z值(像素)',
  `addTime` timestamp NULL DEFAULT NULL COMMENT '位置生成时间(毫秒)',
  `tagAlias` varchar(50) DEFAULT NULL COMMENT 'Tag绑定的用户名',
  `tagGender` varchar(10) DEFAULT NULL COMMENT '表示TAG绑定用户的性别',
  `is_history` varchar(10) DEFAULT '0' COMMENT '是否历史记录，默认否（字典：0-否；1-是）【否：实时记录，用于同步显示定位信息；是：历史记录，用于重现历史轨迹，人员出办案区后，将相关记录更新为1】',
  `is_deleted` varchar(50) DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=17286 DEFAULT CHARSET=utf8 COMMENT='卡定位日志记录（激活的卡，定位信息会同步插入到该表，通过该表重现轨迹）';

-- ----------------------------
-- Table structure for card_manage
-- ----------------------------
DROP TABLE IF EXISTS `card_manage`;
CREATE TABLE `card_manage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_id` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '手环/胸牌MAC编码（芯片读取码）',
  `card_no` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '手环/胸牌编号（标签号）',
  `tag_id` int(11) DEFAULT NULL COMMENT '定位标签id',
  `tag_euid` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '定位标签euid',
  `type` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '设备类型：1-胸牌-民警；2-手环-嫌疑人',
  `is_activate` varchar(10) COLLATE utf8_bin DEFAULT '0' COMMENT '是否被激活,激活则记录轨迹,默认0未激活,手环被回收时重置为0,1-激活',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='手环胸牌管理';

-- ----------------------------
-- Table structure for catch_info
-- ----------------------------
DROP TABLE IF EXISTS `catch_info`;
CREATE TABLE `catch_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `register_id` int(11) DEFAULT NULL COMMENT '注册表id',
  `happened_place` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '案发地',
  `security_level` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '保密级别',
  `suspect_property` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '嫌疑属性',
  `catch_time` timestamp NULL DEFAULT NULL COMMENT '抓获日期',
  `catch_place` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '抓获地点',
  `catch_person` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '抓获人',
  `catch_method` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '抓获方式',
  `catch_unit` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '抓获单位',
  `catch_unit_out` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '局外抓获单位',
  `reality` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '现实状况',
  `healthy` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '健康状况（字典：1-健康；2-良好；3-差，，，或根据警综数据定义）',
  `is_check_drug` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '是否吸毒检测（根据这个自动打勾要检测的项目）',
  `doubt_proof` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '可疑依据',
  `motivation` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '作案原因',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='抓获信息表（对接警综或手动输入）';

-- ----------------------------
-- Table structure for delay_confirm
-- ----------------------------
DROP TABLE IF EXISTS `delay_confirm`;
CREATE TABLE `delay_confirm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `register_id` int(11) DEFAULT NULL COMMENT '人员id',
  `apply_name` varchar(30) DEFAULT NULL COMMENT '人员姓名（显示列表用）',
  `apply_reason` varchar(50) DEFAULT NULL COMMENT '申请原因',
  `apply_time` timestamp NULL DEFAULT NULL COMMENT '申请时间',
  `delay_hour` varchar(10) DEFAULT NULL COMMENT '延期时间（字典：小时8、12、24、48）',
  `audit_content` varchar(50) DEFAULT NULL COMMENT '审核内容',
  `audit_user` varchar(150) DEFAULT NULL COMMENT '审核人',
  `audit_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(50) DEFAULT NULL COMMENT '审核状态（字典：3-待审核；2-审核不通过；1-审核通过）',
  `dept` varchar(50) DEFAULT NULL COMMENT '办案区',
  `is_deleted` varchar(50) DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='延期留置审核';

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT '父id',
  `code` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '编码',
  `type` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '字典类型',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `is_system` varchar(10) COLLATE utf8_bin DEFAULT '0' COMMENT '是否系统字典（1-是；0-否）系统字典不可更改',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `remark` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=846 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字典，树';

-- ----------------------------
-- Table structure for face_base
-- ----------------------------
DROP TABLE IF EXISTS `face_base`;
CREATE TABLE `face_base` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `catch_id` int(11) DEFAULT NULL COMMENT '抓拍库关联id',
  `face_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '底库人脸编号',
  `group_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '底库编号',
  `score` double DEFAULT NULL COMMENT '相似度',
  `threshold` double DEFAULT NULL COMMENT '报警阀值',
  `card_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
  `name` varchar(80) COLLATE utf8_bin DEFAULT NULL COMMENT '人员姓名',
  `role_id` int(2) DEFAULT NULL COMMENT '报警规则编号：1-6级报警',
  `alert_type` int(2) DEFAULT NULL COMMENT '报警类型：1-黑名单报警；0-陌生人报警',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='人脸库（附表）';

-- ----------------------------
-- Table structure for face_catch
-- ----------------------------
DROP TABLE IF EXISTS `face_catch`;
CREATE TABLE `face_catch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topic` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '主题名称',
  `code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '业务编码',
  `capture_id` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '抓拍编号',
  `camera_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '相机编号',
  `time` timestamp NULL DEFAULT NULL COMMENT '报警时间',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='抓拍库（主表）';

-- ----------------------------
-- Table structure for goods_register
-- ----------------------------
DROP TABLE IF EXISTS `goods_register`;
CREATE TABLE `goods_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `register_id` int(11) DEFAULT NULL COMMENT '人员id（以后对接物证系统用）',
  `out_id` int(11) DEFAULT NULL COMMENT '出所审核表id',
  `goods_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '物品名称',
  `goods_num` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '物品数量（同类物品，数量多时用）',
  `goods_desc` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '物品描述',
  `status` varchar(10) COLLATE utf8_bin DEFAULT '0' COMMENT '物品状态（字典：0-待绑定（登记了物品后还没有绑定注册表）；1-已登记（入办案区登记所有物品）；2-已扣押（出办案区选择物品扣押）；3-已归还）',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='物品登记表';

-- ----------------------------
-- Table structure for injury_register
-- ----------------------------
DROP TABLE IF EXISTS `injury_register`;
CREATE TABLE `injury_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `register_id` int(11) DEFAULT NULL COMMENT '人员id',
  `part` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '受伤部位',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='伤情登记表';

-- ----------------------------
-- Table structure for locker
-- ----------------------------
DROP TABLE IF EXISTS `locker`;
CREATE TABLE `locker` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '类型（1-小；2-中；3-大）',
  `locker_no` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '储物柜编号',
  `status` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '状态（1-正常；2-损坏）',
  `use_status` varchar(20) COLLATE utf8_bin DEFAULT '0' COMMENT '使用状态（1-使用中；0-空闲）',
  `in_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '入所-ip',
  `in_port` int(8) DEFAULT NULL COMMENT '入所-端口',
  `in_road` int(8) DEFAULT NULL COMMENT '入所-路数',
  `in_status` varchar(20) COLLATE utf8_bin DEFAULT '0' COMMENT '入所-继电器状态(1-开；0-关）',
  `out_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '出所-电源ip',
  `out_port` int(8) DEFAULT NULL COMMENT '出所-端口',
  `out_road` int(8) DEFAULT NULL COMMENT '出所-通道',
  `out_status` varchar(20) COLLATE utf8_bin DEFAULT '0' COMMENT '出所-开关状态(1-开；0-关）',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='储物柜表';

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '操作模块（对应哪个页面，可在常量中定义）',
  `type` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '操作类型(对应常量：增删改查自定义等)',
  `content` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '操作内容',
  `url` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '操作url',
  `param` varchar(5000) COLLATE utf8_bin DEFAULT NULL COMMENT '参数',
  `ip` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'ip',
  `browser` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '浏览器',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=437 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='日志表';

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '父id',
  `text` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '菜单名称',
  `action` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '跳转路径',
  `icon` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `is_valid` varchar(10) COLLATE utf8_bin DEFAULT '1' COMMENT '0-无效；1-有效（默认1）',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜单表';

-- ----------------------------
-- Table structure for org
-- ----------------------------
DROP TABLE IF EXISTS `org`;
CREATE TABLE `org` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT '父id',
  `code` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '编码',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `is_valid` varchar(10) COLLATE utf8_bin DEFAULT '1' COMMENT '是否有效',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='组织机构层级表';

-- ----------------------------
-- Table structure for out_confirm
-- ----------------------------
DROP TABLE IF EXISTS `out_confirm`;
CREATE TABLE `out_confirm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `register_id` int(11) DEFAULT NULL COMMENT '人员id',
  `apply_name` varchar(30) DEFAULT NULL COMMENT '申请人姓名（显示列表用）',
  `apply_type` varchar(50) DEFAULT NULL COMMENT '申请类型',
  `apply_reason` varchar(50) DEFAULT NULL COMMENT '申请原因',
  `apply_time` timestamp NULL DEFAULT NULL COMMENT '申请时间',
  `is_return` varchar(10) DEFAULT NULL COMMENT '物品是否全部归还（字典：0-否；1-是）',
  `audit_content` varchar(500) DEFAULT NULL COMMENT '审核内容',
  `audit_user` varchar(50) DEFAULT NULL COMMENT '审核人',
  `audit_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(10) DEFAULT '3' COMMENT '审核状态（字典：3-待审核；2-审核不通过；1-审核通过）',
  `is_escort` varchar(10) DEFAULT NULL COMMENT '是否押送（字典：0-否；1-是）',
  `escort_police` varchar(50) DEFAULT NULL COMMENT '押送民警',
  `remind_id` varchar(50) DEFAULT NULL COMMENT '提醒id',
  `out_type` varchar(10) DEFAULT NULL COMMENT '出所类型（字典：1-正式出所；2-临时出所）',
  `is_history` varchar(10) DEFAULT NULL COMMENT '是否是以往审核记录，（字典：0-已出所；1-未出所）',
  `out_time` timestamp NULL DEFAULT NULL COMMENT '出办案区时间',
  `back_time` timestamp NULL DEFAULT NULL COMMENT '归办案区时间',
  `hold_goods` varchar(255) DEFAULT NULL COMMENT '扣押物品（存放要扣押的物品id，逗号拼接）',
  `is_deleted` varchar(50) DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=197 DEFAULT CHARSET=utf8 COMMENT='人员出所审核';

-- ----------------------------
-- Table structure for person_form
-- ----------------------------
DROP TABLE IF EXISTS `person_form`;
CREATE TABLE `person_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `register_id` int(11) DEFAULT NULL COMMENT '注册表id',
  `form_no` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '台账编号',
  `org` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '填表单位',
  `card_no` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '证件号',
  `card_type` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '证件类型（1-居民身份证；2-驾照；3-护照；4-军官证等）',
  `name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `sex` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `birth_date` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '出生日期（年-月-日）',
  `phone` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话',
  `domicile_place` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '户籍地',
  `living_place` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '现住地',
  `in_type` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '到案方式（字典：1-自首；2-举报等）',
  `in_time` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '入办案区时间',
  `in_reason` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '入办案区原因（字典：1-抢劫；2-盗窃等）',
  `alarm_number` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '警情编号',
  `word_number` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '文书编号',
  `medical_history` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '自述有无疾病、伤情及受伤原因',
  `check_body_result` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '检查发现体表特殊特伤情及处理情况',
  `collect_project` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '采集项目（字典：1-指纹；2-血液；3-尿液；10-其他)',
  `do_info_collect` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '是否信息采集',
  `do_info_inbase` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '是否信息入库',
  `do_radio_sync` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '是否同步录音录像',
  `do_check` varchar(5) COLLATE utf8_bin DEFAULT NULL COMMENT '是否核查比对',
  `disk_no` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '光盘编号',
  `receive_card_no` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '领取人身份证号',
  `receive_date` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '领取时间',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='人员台账表';

-- ----------------------------
-- Table structure for person_register
-- ----------------------------
DROP TABLE IF EXISTS `person_register`;
CREATE TABLE `person_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_no` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '证件号',
  `card_type` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '证件类型（1-居民身份证；2-驾照；3-护照；4-军官证等）',
  `name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `extra_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '别名',
  `nick_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '绰号',
  `sex` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `old_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '曾用名',
  `birth_date` date DEFAULT NULL COMMENT '出生日期（年-月-日）',
  `phone` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话',
  `domicile_area` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '户籍地区划',
  `living_area` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '现住地区划',
  `domicile_place` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '户籍地',
  `living_place` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '现住地',
  `education_level` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '文化程度（1-小学；2-初中；3-高中等）',
  `marriage_type` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '婚姻状况（字典：1-未婚；2-已婚；3-再婚等）',
  `relief` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '宗教信仰',
  `minitary` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '兵役状况（字典）',
  `height` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '身高',
  `person_lable` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '人员标签',
  `work_unit` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '工作单位',
  `profession_type` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '职业类别',
  `nationality` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '国籍',
  `minority` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '民族',
  `political` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '政治面貌',
  `person_type` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '身份类别（字典：1-普通人员；2-共产党员；3-人大代表等）',
  `unknown_person` varchar(10) COLLATE utf8_bin DEFAULT '0' COMMENT '身份不明（0-明确；1-不明）',
  `finger_no` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '指纹编号',
  `DNA_no` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'DNA编号',
  `in_type` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '到案方式（字典：1-自首；2-举报等）',
  `handing_place` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '抓获地点（带入地点）',
  `case_type` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '案件类型（字典）',
  `special_group` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '特殊群体（字典：1-孕妇；2-残疾人；3-未成年人；4-聋哑人等）',
  `people_type` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '人员类型（字典：1-嫌疑人；2-受害人；3-证人等）',
  `in_time` timestamp NULL DEFAULT NULL COMMENT '入办案区时间',
  `in_reason` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '入办案区原因（字典：1-抢劫；2-盗窃等）',
  `handling_area` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '办案区域（办案区机构字典）',
  `host_police_no` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '主办民警编号',
  `host_police_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '主办民警姓名',
  `host_police_card_no` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '主办民警胸牌编码（芯片编码）【定位用】',
  `join_police_no` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '协办民警编号',
  `join_police_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '协办民警姓名',
  `join_police_card_no` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '协办民警胸牌编码（芯片编码）【定位用】',
  `alarm_number` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '警情编号',
  `word_number` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '文书编号',
  `medical_history` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '自述有无疾病、伤情及受伤原因',
  `check_body_result` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '检查发现体表特殊特伤情及处理情况',
  `bracelet_no` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '嫌疑人手环编号（芯片编码）【定位用】',
  `collect_project` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '采集项目（字典：1-指纹；2-血液；3-尿液；10-其他)',
  `do_check` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '是否核查比对（字典：0-否；1-是）',
  `locker_no` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '物品保管柜编号',
  `locker_id` int(11) DEFAULT NULL COMMENT '物品保管柜id',
  `is_warned` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '是否同案预警（字典：0-否；1-是）',
  `examine_sign` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '被检查人签字',
  `police_sign` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '检查民警签字',
  `police2_sign` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '检查民警2签字',
  `witness_sign` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '见证人签字',
  `locker_admin_sign` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '随身财物管理员签字',
  `receiver_sign` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '领取人签字（可能是代领的）',
  `delay_hour` varchar(10) COLLATE utf8_bin DEFAULT '8' COMMENT '延期留置时限，默认8小时（8,12,24,48)',
  `is_delay` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '是否延期关押（字典：0-否；1-是）',
  `out_time` timestamp NULL DEFAULT NULL COMMENT '出办案区时间',
  `out_reason` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '出办案区原因',
  `person_status` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '人员出入办案区状态（字典：1-在所；2-已出所）',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='人员注册信息（主表，通过这个关联属性表【person_property】和信息采集表【person_collect】)';

-- ----------------------------
-- Table structure for person_related
-- ----------------------------
DROP TABLE IF EXISTS `person_related`;
CREATE TABLE `person_related` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `register_id` int(11) DEFAULT NULL COMMENT '注册表id',
  `card_no` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '证件号',
  `card_type` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '证件类型（1-居民身份证；2-驾照；3-护照；4-军官证等）',
  `name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `sex` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `phone` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话',
  `relationship` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '关系',
  `language` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '语种（翻译人专用）',
  `type` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '陪同人类型（字典：1-监护人；2-翻译人；3-社区人员；4-协同办案民警等）',
  `chip_no` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '卡编号，芯片的编号（填写陪同人信息的时候绑定卡，记录轨迹）',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='陪同人表';

-- ----------------------------
-- Table structure for person_trace
-- ----------------------------
DROP TABLE IF EXISTS `person_trace`;
CREATE TABLE `person_trace` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `register_id` int(11) DEFAULT NULL COMMENT '人员登记表主键',
  `person_name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '人员名称',
  `tag_id` int(11) DEFAULT NULL COMMENT '手环设备id',
  `tag_euid` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag euid',
  `content` varchar(150) COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=293 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='人员活动台账：记录嫌疑人等在登记过程中 何时 何地 信息。';

-- ----------------------------
-- Table structure for related_phone_confirm
-- ----------------------------
DROP TABLE IF EXISTS `related_phone_confirm`;
CREATE TABLE `related_phone_confirm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `register_id` int(11) DEFAULT NULL COMMENT '人员注册id',
  `police_name` varchar(20) DEFAULT NULL COMMENT '主办民警',
  `call_name` varchar(20) DEFAULT NULL COMMENT '联系人',
  `apply_name` varchar(30) DEFAULT NULL COMMENT '人员姓名（显示列表用）',
  `apply_reason` varchar(200) DEFAULT NULL COMMENT '申请原因',
  `apply_time` timestamp NULL DEFAULT NULL COMMENT '申请时间',
  `talk_time` varchar(20) DEFAULT NULL COMMENT '通话时长',
  `tel` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `audit_user` varchar(20) DEFAULT NULL COMMENT '审批人',
  `audit_time` timestamp NULL DEFAULT NULL COMMENT '审批时间',
  `audit_content` varchar(200) DEFAULT NULL COMMENT '审批备注',
  `status` varchar(10) DEFAULT NULL COMMENT '审批状态（1-通过；2-不通过；3-待审批）',
  `is_deleted` varchar(50) DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='亲情电话申请';

-- ----------------------------
-- Table structure for remind
-- ----------------------------
DROP TABLE IF EXISTS `remind`;
CREATE TABLE `remind` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `content` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  `path` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '跳转路径',
  `user_id` int(11) DEFAULT NULL COMMENT '处理人id',
  `role_id` int(11) DEFAULT NULL COMMENT '处理人角色id【与用户id没有优先级，sql用或者查询】',
  `org` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '处理人机构',
  `status` varchar(10) COLLATE utf8_bin DEFAULT '0' COMMENT '状态（0-未读；1-已读）',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `task_id` int(11) DEFAULT NULL COMMENT '业务id',
  `task_belong` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '待办业务类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='代办表（可以发送到角色，具体的人）';

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `is_super` int(2) NOT NULL DEFAULT '0' COMMENT '是否超级角色（0-否；1-是）默认0',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=286 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色菜单表';

-- ----------------------------
-- Table structure for room_area
-- ----------------------------
DROP TABLE IF EXISTS `room_area`;
CREATE TABLE `room_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '房间名',
  `beginX` int(11) DEFAULT NULL COMMENT 'x轴开始像素',
  `endX` int(11) DEFAULT NULL COMMENT 'x轴结束像素',
  `beginY` int(11) DEFAULT NULL COMMENT 'y轴开始像素',
  `endY` int(11) DEFAULT NULL COMMENT 'y轴结束像素',
  `beginZ` int(11) DEFAULT NULL COMMENT 'z轴开始像素',
  `endZ` int(11) DEFAULT NULL COMMENT 'z轴结束像素',
  `status` varchar(10) COLLATE utf8_bin DEFAULT '1' COMMENT '状态（字典：0-不可用；1-可用）',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='房间区域配置（留存，估计显示页面的时候会用到）';

-- ----------------------------
-- Table structure for room_property
-- ----------------------------
DROP TABLE IF EXISTS `room_property`;
CREATE TABLE `room_property` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org` varchar(50) DEFAULT NULL COMMENT '单位',
  `area` varchar(50) DEFAULT NULL COMMENT '办案区',
  `room_name` varchar(100) DEFAULT NULL COMMENT '房间名称',
  `room_no` varchar(50) DEFAULT NULL COMMENT '房间编号',
  `room_type` varchar(10) DEFAULT NULL COMMENT '房间类型（字典：1-等候室；2-询问时；3-讯问室；4-特殊讯问室；5-未成年人询问室）',
  `room_max` int(8) DEFAULT '1' COMMENT '房间容纳人数',
  `room_status` varchar(10) DEFAULT '0' COMMENT '房间状态（字典：1-占用；0-空闲）',
  `light_ip` varchar(50) DEFAULT NULL COMMENT '灯ip',
  `light_port` varchar(10) DEFAULT NULL COMMENT '灯端口',
  `light_index` int(8) DEFAULT NULL COMMENT '灯路数',
  `light_status` varchar(50) DEFAULT '0' COMMENT '灯状态（字典：0-关；1-开）',
  `wind_ip` varchar(50) DEFAULT NULL COMMENT '风扇ip',
  `wind_port` varchar(10) DEFAULT NULL COMMENT '风扇端口',
  `wind_road` int(8) DEFAULT NULL COMMENT '风扇路数',
  `wind_status` varchar(50) DEFAULT '0' COMMENT '风扇状态（字典：0-关；1-开）',
  `wall_ip` varchar(50) DEFAULT NULL COMMENT '智能墙体',
  `wall_port` varchar(10) DEFAULT NULL COMMENT '智能墙体端口',
  `wall_road` int(8) DEFAULT NULL COMMENT '智能墙体路数',
  `wall_status` varchar(50) DEFAULT '0' COMMENT '智能墙体状态（字典：0-关；1-开）',
  `led_ip` varchar(50) DEFAULT NULL COMMENT 'ledip',
  `led_port` varchar(10) DEFAULT NULL COMMENT 'led端口',
  `led_road` int(8) DEFAULT NULL COMMENT 'led路数',
  `led_status` varchar(50) DEFAULT NULL COMMENT 'led状态（字典：0-关；1-开）',
  `is_deleted` varchar(50) DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='讯询问室、等候室配置表';

-- ----------------------------
-- Table structure for room_record
-- ----------------------------
DROP TABLE IF EXISTS `room_record`;
CREATE TABLE `room_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) DEFAULT NULL COMMENT '讯询问室、等候室id',
  `register_id` int(11) DEFAULT NULL COMMENT '人员id（对应register表里的id）',
  `is_deleted` varchar(50) DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) DEFAULT NULL COMMENT '描述',
  `is_history` varchar(10) DEFAULT '0' COMMENT '是否历史',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=427 DEFAULT CHARSET=utf8 COMMENT='讯询问室、等候室使用记录';

-- ----------------------------
-- Table structure for system_config
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '主键',
  `message_value` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '属性值集合',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '内容说明',
  `status` varchar(10) DEFAULT 'N' COMMENT '状态（0-暂存；1-已存）',
  `is_deleted` varchar(50) CHARACTER SET utf8 DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for tp_blrecord
-- ----------------------------
DROP TABLE IF EXISTS `tp_blrecord`;
CREATE TABLE `tp_blrecord` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '笔录编号',
  `gmt_create` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `ry_id` int(12) DEFAULT NULL COMMENT '办案区人员表对应的自增id',
  `whdd` varchar(55) DEFAULT NULL COMMENT '问话地点',
  `card_id` varchar(30) DEFAULT NULL COMMENT '人员证件号，为了和笔录系统传输数据，一般是存身份证号码',
  `aj_id` varchar(10) DEFAULT NULL COMMENT '所关联的案件id',
  `ajbh` varchar(30) DEFAULT NULL COMMENT '所关联的案件编号',
  `jqbh` varchar(30) DEFAULT NULL COMMENT '所关联的警情编号',
  `afsj` varchar(15) DEFAULT NULL COMMENT '案发时间',
  `ajmc` varchar(100) DEFAULT NULL COMMENT '案件名称',
  `xwcs` varchar(3) DEFAULT NULL COMMENT '笔录次数',
  `kssj` varchar(16) DEFAULT NULL COMMENT '开始时间（yyyyMMddHHmmss）',
  `jssj` varchar(16) DEFAULT NULL COMMENT '结束时间（yyyyMMddHHmmss）',
  `qzry1jh` varchar(10) DEFAULT NULL COMMENT '主办民警警号',
  `qzry1xm` varchar(15) DEFAULT NULL COMMENT '主办民警姓名',
  `qzry1dwdm` varchar(10) DEFAULT NULL COMMENT '主办民警单位代码',
  `qzrydw` varchar(25) DEFAULT NULL COMMENT '主办民警单位名称',
  `qzry2jh` varchar(10) DEFAULT NULL COMMENT '协办民警警号',
  `qzry3xm` varchar(15) DEFAULT NULL COMMENT '协办民警姓名',
  `qzry3dw` varchar(10) DEFAULT NULL COMMENT '协办民警单位代码',
  `bldxid` varchar(20) DEFAULT NULL COMMENT '嫌疑人人员编号',
  `bldxxm` varchar(15) DEFAULT NULL COMMENT '嫌疑人姓名',
  `bldxwxhm` varchar(30) DEFAULT NULL COMMENT '嫌疑人微信号',
  `bllx` varchar(15) DEFAULT NULL COMMENT '笔录类型',
  `jcdxmc` varchar(15) DEFAULT NULL COMMENT '辨认对象姓名',
  `qtr` varchar(50) DEFAULT NULL COMMENT '其他在场人员',
  `xm` varchar(15) DEFAULT NULL COMMENT '见证人姓名',
  `xb` varchar(5) DEFAULT NULL COMMENT '见证人性别',
  `zjlx` varchar(10) DEFAULT NULL COMMENT '见证人证件类型',
  `zjhm` varchar(30) DEFAULT NULL COMMENT '见证人证件号码',
  `lxdh` varchar(11) DEFAULT NULL COMMENT '见证人联系电话',
  `symd` varchar(255) DEFAULT NULL COMMENT '事由和目的',
  `xgsj` varchar(16) DEFAULT NULL COMMENT '最后更新时间（yyyyMMddHHmmss）',
  `pdf_file` varchar(1) DEFAULT NULL,
  `is_video` varchar(10) DEFAULT NULL COMMENT '是否同步录音录像（字典：0-否；1-是）',
  `cd_code` varchar(50) DEFAULT NULL COMMENT '光盘编号',
  `is_deleted` varchar(50) DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='笔录';

-- ----------------------------
-- Table structure for urine_test_info
-- ----------------------------
DROP TABLE IF EXISTS `urine_test_info`;
CREATE TABLE `urine_test_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `register_id` int(11) DEFAULT NULL COMMENT '人员注册表id（关联拿到被检测人的基本信息）',
  `report_type` varchar(20) DEFAULT NULL COMMENT '报告书类别（字典：1-吸毒检测报告；2-吸毒成瘾认定书；3-吸毒成瘾严重认定书等）',
  `jianzi` varchar(50) DEFAULT NULL COMMENT '检字',
  `report_no` varchar(50) DEFAULT NULL COMMENT '报告书编号（生成打印文件的时候直接用这个拿这张表的数据）',
  `check_date` date DEFAULT NULL COMMENT '检测时间',
  `check_way` varchar(10) DEFAULT NULL COMMENT '检测方式（字典：1-配置方法一；2-配置方法二；3-配置方法三；4-配置方法四等）',
  `check_place` varchar(100) DEFAULT NULL COMMENT '检测地点',
  `check_result` varchar(500) DEFAULT NULL COMMENT '检测结果',
  `name` varchar(50) DEFAULT NULL COMMENT '被检测人姓名',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别（放中文，用于打印报告书）',
  `birth_date` date DEFAULT NULL COMMENT '出生日期',
  `card_no` varchar(50) DEFAULT NULL COMMENT '证件号码',
  `living_place` varchar(200) DEFAULT NULL COMMENT '现住地',
  `work_unit` varchar(50) DEFAULT NULL COMMENT '工作单位',
  `find_out` varchar(200) DEFAULT NULL COMMENT '现查明',
  `proof` varchar(50) DEFAULT NULL COMMENT '勾选证据（字典，打勾的那些，逗号分隔）',
  `officer` varchar(50) DEFAULT NULL COMMENT '办案区负责人',
  `check_person` varchar(50) DEFAULT NULL COMMENT '检测人、认定人员',
  `check_person2` varchar(50) DEFAULT NULL COMMENT '检测人2、认定人员2',
  `bottleB` varchar(50) DEFAULT NULL COMMENT 'b瓶编号',
  `bottle_deadtimeB` date DEFAULT NULL COMMENT 'b瓶保管到期时间',
  `bottleA` varchar(50) DEFAULT NULL COMMENT 'a瓶编号',
  `bottle_deadtimeA` date DEFAULT NULL COMMENT 'a瓶保管到期时间',
  `is_dealed` varchar(10) DEFAULT 'N' COMMENT '是否已超期处理（Y-已处理；N-未处理）',
  `is_deleted` varchar(50) DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=173 DEFAULT CHARSET=utf8 COMMENT='尿检信息';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '1' COMMENT '密码',
  `idcard` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
  `real_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '真实姓名',
  `org` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '组织机构',
  `dept` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '部门',
  `tel` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '办公电话',
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '手机',
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `head_img` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `status` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '1' COMMENT '状态：1-激活；0-锁定',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `is_deleted` varchar(50) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色表';
DROP TRIGGER IF EXISTS `alarm`;
DELIMITER ;;
CREATE TRIGGER `alarm` BEFORE INSERT ON `alarm_manage` FOR EACH ROW BEGIN     
    set NEW.register_id = (select id from person_register where bracelet_no = (SELECT card_id from card_manage where tag_euid = NEW.tag_euid));
    set NEW.active_person =  (select name from person_register where bracelet_no = (SELECT card_id from card_manage where tag_euid = NEW.tag_euid));
    set NEW.card_id = (select card_id from card_manage where tag_euid = NEW.tag_euid);
    set NEW.alarm_time =  NOW();
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `person_name`;
DELIMITER ;;
CREATE TRIGGER `person_name` BEFORE INSERT ON `person_trace` FOR EACH ROW BEGIN     
    set NEW.register_id = (select id from person_register where bracelet_no = (SELECT card_id from card_manage where tag_euid = new.tag_euid));
    set NEW.person_name =  (select name from person_register where bracelet_no = (SELECT card_id from card_manage where tag_euid = new.tag_euid));
END
;;
DELIMITER ;
