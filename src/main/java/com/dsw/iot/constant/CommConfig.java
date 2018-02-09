package com.dsw.iot.constant;

public interface CommConfig {
    //系统目录分隔符
    public static final String SEPARATOR = System.getProperty("file.separator");
    //cookie存在客户端的key
    public static final String GUANKONG_USER = "GUANKONG_USER";
    //cookie 生命周期
    public static final int COOKIE_AGE = 8 * 60 * 60 * 1000;
    //guankong系统加密key
    public static final String GUANGKONG_KEY = "3c56602233254dd4be6db5d9b7f9a23a";
    //is_deleted N 常量
    public static final String NOT_DELETED = "N";

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

	/**
	 * log日志类型常量枚举
	 *
	 * @author zhang
	 *
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
	 * @author zhang
	 *
	 */
	enum LOG_MODULE {
		/**
		 * 角色模块，角色表
		 */
		ROLE("角色管理", "1"),
		/**
		 * 操作了角色菜单表role_menu
		 */
		USER("用户管理", "2");
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

}
