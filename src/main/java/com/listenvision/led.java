package com.listenvision;

/**
 * 该类和lv_led.dll相关 已经绑定
 * 必须放在com.listenvision.led 全量名下
 *
 * @author huangt
 * @create 2018-02-28 9:30
 **/
public class led {

	/*
     *****************************************************************************************************************
	 *													  LED SDK 6.0
	 *
	 *													       胡伟(HuWei)
	 *	
	 *
	 *										(C) Copyright 2010 - 2015, LISTEN VISION
	 *												   All Rights Reserved
	 *														  
	 *****************************************************************************************************************
	 */


    /********************************************************************************************
     *	CreateProgram				创建节目对象，成功返回节目对象句柄
     *
     *	参数说明
     *				LedWidth		屏的宽度
     *				LedHeight		屏的高度
     *				ColorType		屏的颜色 1.单色  2.双基色  3.七彩  4.全彩
     *	返回值
     *				0				创建节目对象失败
     *				非0				创建节目对象成功
     ********************************************************************************************/
    public native static int CreateProgram(int LedWidth, int LedHeight, int ColorType);

    /*********************************************************************************************
     *	AddProgram					添加一个节目
     *
     *	参数说明
     *				hProgram		节目对象句柄
     *				ProgramNo		节目号
     *				ProgramTime		节目播放时长 0.节目播放时长  非0.指定播放时长
     *				LoopCount		循环播放次数
     *	返回值
     *				0				成功
     *				非0				失败
     ********************************************************************************************/
    public native static int AddProgram(int hProgram, int ProgramNo, int ProgramTime, int LoopCount);

    /*********************************************************************************************
     *	LV_AddImageTextArea				添加一个图文区域
     *
     *	参数说明
     *				hProgram			节目对象句柄
     *				ProgramNo			节目号
     *				AreaNo				区域号
     *				l					区域左上角横坐标
     *				t					区域左上角纵坐标
     *				w					区域宽度
     *				h					区域高度
     *				IsBackgroundArea	是否为背景区域，0.前景区（默认） 1.背景区
     *	返回值
     *				0					成功
     *				非0					失败
     ********************************************************************************************/
    public native static int AddImageTextArea(int hProgram, int ProgramNo, int AreaNo, int l, int t, int w, int h, int IsForegroundArea);

    /*********************************************************************************************
     * AddFileToImageTextArea				添加一个文件到图文区
     *
     *	参数说明
     *				hProgram				节目对象句柄
     *				ProgramNo				节目号
     *				AreaNo					区域号
     *				FilePath				文件路径，支持的文件类型有 txt  rtf  bmp  gif  png  jpg jpeg tiff
     *				InStyle					入场特技
     *				nSpeed					特技速度
     *				DelayTime				停留时间
     *	返回值
     *				0						成功
     *				非0						失败
     ********************************************************************************************/
    public native static int AddFileToImageTextArea(int hProgram, int ProgramNo, int AreaNo, String FilePath, int InStyle, int nSpeed, int DelayTime);

    /*********************************************************************************************
     * AddMultiLineTextToImageTextArea		添加一个多行文本到图文区
     *
     *	参数说明
     *				hProgram				节目对象句柄
     *				ProgramNo				节目号
     *				AreaNo					区域号
     *				AddType					添加的类型  0.为字符串  1.文件（只支持txt和rtf文件）
     *				AddStr					AddType为0则为字符串数据,AddType为1则为文件路径
     *				FontName				字体名
     *				FontSize				字体大小
     *				FontColor				字体颜色   0xff 红色  0xff00 绿色  0xffff黄色
     *				FontBold				是否加粗 0不加粗 1加粗
     *				FontItalic				是否是斜体  0 不斜 1斜
     *				FontUnderline			是否下划线 0不加下划线 1加下划线
     *				InStyle					入场特技
     *				nSpeed					特技速度
     *				DelayTime				停留时间
     *				nAlignment				左右居中对齐方式
     *				IsVCenter				是否垂直居中
     *	返回值
     *				0						成功
     *				非0						失败
     ********************************************************************************************/
    public native static int AddMultiLineTextToImageTextArea(int hProgram, int ProgramNo, int AreaNo, int AddType, String AddStr, String FontName, int FontSize, int FontColor, int FontBold, int FontItalic, int FontUnderline, int InStyle, int nSpeed, int DelayTime, int nAlignment, int IsVCenter);

    /*********************************************************************************************
     * AddSinglelineTextToImageTextArea		添加一个多行文本到图文区
     *
     *	参数说明
     *				hProgram				节目对象句柄
     *				ProgramNo				节目号
     *				AreaNo					区域号
     *				AddType					添加的类型  0.为字符串  1.文件（只支持txt和rtf文件）
     *				AddStr					AddType为0则为字符串数据,AddType为1则为文件路径
     *				FontName				字体名
     *				FontSize				字体大小
     *				FontColor				字体颜色   0xff 红色  0xff00 绿色  0xffff黄色
     *				FontBold				是否加粗 0不加粗 1加粗
     *				FontItalic				是否是斜体  0 不斜 1斜
     *				FontUnderline			是否下划线 0不加下划线 1加下划线
     *				InStyle					入场特技
     *				nSpeed					特技速度
     *				DelayTime				停留时间
     *	返回值
     *				0						成功
     *				非0						失败
     ********************************************************************************************/
    public native static int AddSinglelineTextToImageTextArea(int hProgram, int ProgramNo, int AreaNo, int AddType, String AddStr, String FontName, int FontSize, int FontColor, int FontBold, int FontItalic, int FontUnderline, int InStyle, int nSpeed, int DelayTime);

    /*********************************************************************************************
     * AddStaticTextToImageTextArea			添加一个静止文本到图文区
     *
     *	参数说明
     *				hProgram				节目对象句柄
     *				ProgramNo				节目号
     *				AreaNo					区域号
     *				AddType					添加的类型  0.为字符串  1.文件（只支持txt和rtf文件）
     *				AddStr					AddType为0则为字符串数据,AddType为1则为文件路径
     *				FontName				字体名
     *				FontSize				字体大小
     *				FontColor				字体颜色   0xff 红色  0xff00 绿色  0xffff黄色
     *				FontBold				是否加粗 0不加粗 1加粗
     *				FontItalic				是否是斜体  0 不斜 1斜
     *				FontUnderline			是否下划线 0不加下划线 1加下划线
     *				DelayTime				停留时间
     *				nAlignment				左右居中对齐方式
     *				IsVCenter				是否垂直居中
     *	返回值
     *				0						成功
     *				非0						失败
     ********************************************************************************************/
    public native static int AddStaticTextToImageTextArea(int hProgram, int ProgramNo, int AreaNo, int AddType, String AddStr, String FontName, int FontSize, int FontColor, int FontBold, int FontItalic, int FontUnderline, int DelayTime, int nAlignment, int IsVCenter);

    /*********************************************************************************************
     * AddSinglelineTextToImageTextArea		添加一个多行文本到图文区
     *
     *	参数说明
     *				hProgram				节目对象句柄
     *				ProgramNo				节目号
     *				AreaNo					区域号
     *				l						区域左上角横坐标
     *				t						区域左上角纵坐标
     *				w						区域宽度
     *				h						区域高度
     *				FontName				字体名
     *				FontSize				字体大小
     *				FontColor				字体颜色   0xff 红色  0xff00 绿色  0xffff黄色
     *				FontBold				是否加粗 0不加粗 1加粗
     *				FontItalic				是否是斜体  0 不斜 1斜
     *				FontUnderline			是否下划线 0不加下划线 1加下划线
     * 				IsYear					是否显示年 1为显示 0不显示 下同
     * 				IsWeek					是否显示星期
     *				IsMonth					是否显示月
     *				IsDay					是否显示日
     *				IsHour					是否显示时
     *				IsMinute				是否显示分
     *				IsSecond				是否显示秒
     *				DateFormat				日期格式 0.YYYY年MM月DD日  1.YY年MM月DD日  2.MM/DD/YYYY  3.YYYY/MM/DD  4.YYYY-MM-DD  5.YYYY.MM.DD  6.MM.DD.YYYY  7.DD.MM.YYYY
     *				DateColor				日期字体颜色0xff 红色  0xff00 绿色  0xffff黄色
     *				WeekFormat				星期格式 0.星期X  1.Monday  2.Mon.
     *				WeekColor				星期字体颜色0xff 红色  0xff00 绿色  0xffff黄色
     *				TimeFormat				时间格式 0.HH时mm分ss秒  1.HH時mm分ss秒  2.HH:mm:ss  3.上午 HH:mm:ss  4.AM HH:mm:ss  5.HH:mm:ss 上午  6.HH:mm:ss AM
     *				TimeColor				时间字体颜色0xff 红色  0xff00 绿色  0xffff黄色
     *	返回值
     *				0						成功
     *				非0						失败
     ********************************************************************************************/
    public native static int AddDigitalClockArea(int hProgram, int ProgramNo, int AreaNo, int l, int t, int w, int h, String FontName, int FontSize, int FontColor, int FontBold, int FontItalic, int FontUnderline, int IsYear, int IsWeek, int IsMonth, int IsDay, int IsHour, int IsMinute, int IsSecond, int DateFormat, int DateColor, int WeekFormat, int WeekColor, int TimeFormat, int TimeColor);

    /*********************************************************************************************
     *	DeleteProgram						销毁节目对象(注意：如果此节目对象不再使用，请调用此函数销毁，否则会造成内存泄露)
     *
     *	参数说明
     *				hProgram				节目对象句柄
     ********************************************************************************************/
    public native static void DeleteProgram(int hProgram);

    /*********************************************************************************************
     *	NetWorkSend							发送节目，此发送为一对一发送
     *
     *	参数说明
     *				IpStr					LED屏IP
     *				hProgram				节目对象句柄
     *	返回值
     *				0						成功
     *				非0						失败
     ********************************************************************************************/
    public native static int NetWorkSend(String IpStr, int hProgram);

    /*********************************************************************************************
     *	SetBasicInfo						设置基本屏参
     *
     *	参数说明
     *				IpStr					LED屏的IP
     *				ColorType				屏的颜色 1.单色  2.双基色  3.七彩  4.全彩
     *				LedWidth				屏的宽度点数
     *				LedHeight				屏的高度点数
     *	返回值
     *				0						成功
     *				非0						失败
     ********************************************************************************************/
    public native static int SetBasicInfo(String IpStr, int ColorType, int LedWidth, int LedHeight);

    /*********************************************************************************************
     *	SetOEDA								设置OE DA
     *
     *	参数说明
     *				IpStr					LED屏的IP
     *				Oe						OE  0.低有效  1.高有效
     *				Da						DA  0.负极性  1.正极性
     *	返回值
     *				0						成功
     *				非0						失败
     ********************************************************************************************/
    public native static int SetOEDA(String IpStr, int Oe, int Da);

    /*********************************************************************************************
     *	AdjustTime							校时
     *
     *	参数说明
     *				IpStr					LED屏的IP
     *	返回值
     *				0						成功
     *				非0						失败
     ********************************************************************************************/
    public native static int AdjustTime(String IpStr);

    /*********************************************************************************************
     *	PowerOnOff							开关屏
     *
     *	参数说明
     *				IpStr					LED屏的IP
     *				OnOff					开关值  0.关屏  1.开屏
     *	返回值
     *				0						成功
     *				非0						失败
     ********************************************************************************************/
    public native static int PowerOnOff(String IpStr, int OnOff);

    /*********************************************************************************************
     *	TimePowerOnOff						定时开关屏
     *
     *	参数说明
     *				IpStr					LED屏的IP
     *				StartHour				起始小时
     *				StartMinute				起始分钟
     *				EndHour					结束小时
     *				EndMinute				结束分钟
     *	返回值
     *				0						成功
     *				非0						失败
     ********************************************************************************************/
    public native static int TimePowerOnOff(String IpStr, int StartHour, int StartMinute, int EndHour, int EndMinute);

    /*********************************************************************************************
     *	SetBrightness						设置亮度
     *
     *	参数说明
     *				IpStr					LED屏的IP
     *				BrightnessValue			亮度值 0~15
     *	返回值
     *				0						成功
     *				非0						失败
     ********************************************************************************************/
    public native static int SetBrightness(String IpStr, int BrightnessValue);

    /*********************************************************************************************
     *	LedTest								LED测试
     *
     *	参数说明
     *				IpStr					LED屏的IP
     *				TestValue				测试值
     *	返回值
     *				0						成功
     *				非0						失败
     ********************************************************************************************/
    public native static int LedTest(String IpStr, int TestValue);

    /*********************************************************************************************
     *	SetLedCommunicationParameter		设置LED屏的网络信息
     *
     *	参数说明
     *				IpStr					LED屏的IP
     *				NewIp					LED屏的新IP
     *				NewNetMask				LED屏的新子网掩码
     *				NewGateway				LED屏的新网关
     *				NewMac					LED屏的新mac地址
     *	返回值
     *				0						成功
     *				非0						失败
     ********************************************************************************************/
    public native static int SetLedCommunicationParameter(String IpStr, String NewIp, String NewNetMask, String NewGateway, String NewMac);


    //dll动态链接库无法打进jar包，将其放入jdk/jre/bin目录下也是一样的
    static {
        System.loadLibrary("lv_led");
    }
}
