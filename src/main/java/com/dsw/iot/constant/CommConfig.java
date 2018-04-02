package com.dsw.iot.constant;

public interface CommConfig {
    //系统目录分隔符
    public static final String SEPARATOR = System.getProperty("file.separator");
    //cookie存在客户端的key
    public static final String GUANKONG_USER = "GUANKONG_USER";
    //cookie 生命周期
    public static final int COOKIE_AGE = 8 * 60 * 60;
    //guankong系统加密key
    public static final String GUANGKONG_KEY = "3c56602233254dd4be6db5d9b7f9a23a";
    //is_deleted N 常量
    public static final String NOT_DELETED = "N";

    /**
     * is_deleted 状态枚举
     *
     * @author zhang
     */
    enum DELETED {
        YES("Y"), NO("N");
        String name;

        private DELETED(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    enum IS_HISTORY {
        YES("1"), NO("0");
        String name;

        private IS_HISTORY(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * log日志类型常量枚举
     *
     * @author ZhangCB
     */
    enum LOG_TYPE {
        /**
         * 查询
         */
        QUERY("查询", "1"),
        /**
         * 新增
         */
        ADD("新增", "2"),
        /**
         * 更新
         */
        UPDATE("更新", "3"),
        /**
         * 删除
         */
        DELETE("删除", "4"),
        /**
         * 登录
         */
        LOGIN("登录", "5"),
        /**
         * 登出
         */
        LAYOUT("登出", "6");
        // 成员变量
        String name;
        String type;

        // 构造方法
        private LOG_TYPE(String name, String type) {
            this.name = name;
            this.type = type;
        }

        // get set 方法
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    /**
     * log操作模块常量枚举
     *
     * @author ZhangCB
     */
    enum LOG_MODULE {
        /**
         * 角色模块，角色表
         */
        ROLE("角色管理", "1"),
        /**
         * 操作了角色菜单表role_menu
         */
		USER("用户管理", "2"),

		/**
		 * 登录登出
		 */
		LOGIN_LOGOUT("登录登出", "3"),

		/**
		 * 菜单管理
		 */
		MENU("菜单管理", "4"),
		/**
		 * 字典管理
		 */
		DICTIONARY("字典管理", "5"),
		/**
		 * 讯询问室管理
		 */
		ROOM("讯询问室管理", "6"),
		/**
		 * 储物柜管理
		 */
		LOCKER("储物柜管理", "7"),

		/**
		 * 登记管理
		 */
		PERSON_REGISTER("登记管理", "8"),
		/**
		 * 家属通知
		 */
		PERSON_RELATED("家属通知", "9"),
		/**
		 * 尿检管理
		 */
		URINE_TEST("尿检管理", "10"),
		/**
		 * 预警管理
		 */
		ALARM("预警管理", "11"),
		/**
		 * 延期审核
		 */
		DELAY_CONFIRM("延期审核", "12"),
		/**
		 * 出所审核
		 */
		OUT_CONFIRM("出所审核", "13"),
		/**
		 * 出所取物
		 */
		OUT_FETCH("出所取物", "14"),
		/**
		 * 其他
		 */
		ELSE("其他", "15");

        // 成员变量
        String name;
        String module;

        // 构造方法
        private LOG_MODULE(String name, String module) {
            this.name = name;
            this.module = module;
        }

        // get set 方法
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }

    }

    /**
     * 附件类型枚举
     *
     * @author ZhangCB
     */
    enum ATTACH_TYPE {
        /**
         * 【系统管理-用户管理】用户头像
         */
        USER_HEAD_IMG("【系统管理-用户管理】用户头像", "USER_HEAD_IMG"),
        /**
         * 【系统管理-菜单管理】菜单图标
         */
        MENU_ICON("【系统管理-菜单管理】菜单图标", "MENU_ICON"),

        /**
         * 【人员登记】身份证
         */
        PERSON_REGISTER_CARD_IMGS("【人员登记】身份证", "PERSON_REGISTER_CARD_IMGS"),
        /**
         * 【人员登记】人员照片
         */
        PERSON_REGISTER_PERSON_IMGS("【人员登记】人员照片", "PERSON_REGISTER_PERSON_IMGS"),
        /**
         * 【人员登记】伤势情况材料上传
         */
        INJURY_REGISTER_IMGS("【人员登记】伤势情况材料", "INJURY_REGISTER_IMGS"),
        /**
         * 【人员登记】入办案区随身物品照片
         */
        GOODS_REGISTER_IMGS("【人员登记】入办案区随身物品照片", "GOODS_REGISTER_IMGS"),
        /**
         * 【出所取物】出办案区随身物品照片
         */
        GOODS_REGISTER_IMGS_OUT("【出所取物】出办案区随身物品照片", "GOODS_REGISTER_IMGS_OUT"),

        /**
         * 【电话使用】家属通知使用电话的录音文件
         */
        PHONE_RECORD("【电话使用】家属通知使用电话的录音文件", "PHONE_RECORD"),

        FACE_CATCH("云从抓拍图片文件", "FACE_CATCH"),

        FACE_BASE("云从抓拍匹配底库文件", "FACE_BASE"),

        /**
         * [尿检信息] 手写板签字附件
         */
        URINE_TEST_INFO_SIGN_IDENTIFIED("尿检报告-签字-被认定人", "URINE_TEST_INFO_SIGN_IDENTIFIED"),
        URINE_TEST_INFO_SIGN_COGNIZANT_1("尿检报告-签字-认定人1", "URINE_TEST_INFO_SIGN_COGNIZANT_1"),
        URINE_TEST_INFO_SIGN_COGNIZANT_2("尿检报告-签字-认定人2", "URINE_TEST_INFO_SIGN_COGNIZANT_2"),
        URINE_TEST_INFO_SIGN_ORG_MANAGER("尿检报告-签字-办案单位负责人", "URINE_TEST_INFO_SIGN_ORG_MANAGER"),
        URINE_TEST_INFO_SIGN_IMG("尿检报告-尿检照片", "URINE_TEST_INFO_SIGN_IMG"),

        /**
         * 【人员登记表台账】手写板签字附件
         */
        FORM_HOST_POLICE_SIGN("人员登记表台账-办案民警签名", "FORM_HOST_POLICE_SIGN"),
        FORM_ADMIN_SIGN("人员登记表台账-管 理 员1签名", "FORM_ADMIN_SIGN"),
        FORM_CHECK_POLICE_SIGN("人员登记表台账-检查民警签名", "FORM_CHECK_POLICE_SIGN"),
        FORM_WITNESS_SIGN("人员登记表台账-见证人签名", "FORM_WITNESS_SIGN"),
        FORM_SUSPECT_SIGN("人员登记表台账-被检查人签名", "FORM_SUSPECT_SIGN"),
        FORM_GOODS_HOST_POLICE_SIGN("人员登记表台账-随身物品-办案人员签名", "FORM_GOODS_HOST_POLICE_SIGN"),
        FORM_GOODS_ADMIN_SIGN("人员登记表台账-随身物品-管理员签名", "FORM_GOODS_ADMIN_SIGN"),
        FORM_GOODS_SUSPECT_SIGN("人员登记表台账-随身物品-涉案人员签名", "FORM_GOODS_SUSPECT_SIGN"),
        FORM_RECEIVE_SIGN("人员登记表台账-随身物品-领取人签名", "FORM_RECEIVE_SIGN"),
        FORM_GOODS_HOLD_ADMIN_SIGN("人员登记表台账-扣押物品-管理员签名签名", "FORM_GOODS_HOLD_ADMIN_SIGN");

        // 成员变量
        String name;
        String type;

        // 构造方法
        private ATTACH_TYPE(String name, String type) {
            this.name = name;
            this.type = type;
        }

        // get set 方法
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    enum ROOM_TYPE {
        WAIT_ROOM("等候室", "1"),
        INQUIRY_ROOM("询问室", "2");
        // 成员变量
        String name;
        String type;

        // 构造方法
        private ROOM_TYPE(String name, String type) {
            this.name = name;
            this.type = type;
        }

        // get set 方法
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }


	/**
	 * 启用；空闲状态--储物柜
	 *
	 * @author zhang
	 */
	enum BUSY_FREE_STATUS {
		BUSY("1"), FREE("0");
		String name;

		private BUSY_FREE_STATUS(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
