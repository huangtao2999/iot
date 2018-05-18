CREATE TABLE `burn_record` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `register_id` int(11) DEFAULT NULL COMMENT '人员信息id',
   `register_name` varchar(128) DEFAULT NULL,
   `police_no` varchar(64) DEFAULT NULL COMMENT '审讯警员编号',
   `police_name` varchar(128) DEFAULT NULL,
   `target_burn_path` varchar(256) DEFAULT NULL COMMENT '刻录文件地址',
   `state` int(2) DEFAULT '0' COMMENT '1初始 2下载中 3刻录中 4下载失败 5刻录失败 6完成',
   `percent` int(3) DEFAULT '0' COMMENT '进度百分比',
   `is_deleted` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'N' COMMENT '是否删除（逻辑删除，查询时系统自动拼sql）',
   `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建人',
   `create_user` varchar(64) DEFAULT NULL,
   `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
   `update_user` varchar(64) DEFAULT NULL COMMENT '更新人',
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='光盘刻录记录'