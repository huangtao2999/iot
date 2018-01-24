Sys_Cleanup_Cmd = 0x00000001
Sys_LocalTime_Set = 0x00000002
Sys_Restore_Cmd = 0x00000003
Sys_Reboot_Cmd = 0x00000004
Sys_AdjustLayout_Cmd = 0x00000005
Sys_FullScreen_Cmd = 0x00000006
Sys_UpgradeProgram_Cmd = 0x00000007
Sys_UpgradeWebPage_Cmd = 0x00000008
Sys_UpgradeProgress_Get = 0x00000009
Sys_SysInfo_Get = 0x0000000a
Sys_Style_Set = 0x0000000b
Sys_Backup_Cmd = 0x0000000c

Entry_Logon_Cmd = 0x00010000
Entry_Logoff_Cmd = 0x00010001
Entry_Prop_Get = 0x00010002
Entry_NVSList_Get = 0x00010003
Entry_NVSVer_Get = 0x00010004
Entry_ObsLst_Get = 0x00010005
Entry_Volume_Set = 0x00010006
Entry_Config_Get = 0x00010007

Obs_Prop_Get = 0x00020000
Obs_FullScreen_Cmd = 0x00020001
Obs_Add_Cmd = 0x00020002
Obs_Remove_Cmd = 0x00020003
Obs_StartRealTime_Cmd = 0x00020004
Obs_StartPlayBack_Cmd = 0x00020005
Obs_Pause_Cmd = 0x00020006
Obs_FastForward_Cmd = 0x00020007
Obs_SlowForward_Cmd = 0x00020008
Obs_Stop_Cmd = 0x00020009
Obs_ShowParam_Cmd = 0x0002000a
Obs_MicEnable_Set = 0x0002000b
Obs_SpeakerEnable_Set = 0x0002000c
Obs_Capture_Cmd = 0x0002000d
Obs_BeginRec_Cmd = 0x0002000e
Obs_EndRec_Cmd = 0x0002000f
Obs_Progress_Get = 0x00020010
Obs_ShowMsg_Cmd = 0x00020011
Obs_Volume_Set = 0x00020012
Obs_Volume_Get = 0x00020013
Obs_Monopolize_Cmd = 0x00020014
Obs_StartPlayBackOffLine_Cmd = 0x00020015
Obs_3dLocateEnable_Set = 0x00020016
Obs_3dLocateEnable_Get = 0x00020017
Obs_StopPause_Set = 0x00020018

Dev_Type_Set = 0x00030000
Dev_Type_Get = 0x00030001
Dev_AllType_Get = 0x00030002
Dev_Controllable_Get = 0x00030003
Dev_Control_Cmd = 0x00030004
Dev_Model_Get = 0x00030005
Dev_Device_Ctrl = 0x00030006
Dev_DeviceType_Set = 0x00030009
Dev_DeviceType_Get = 0x0003000a
Dev_SetComFormat_Set = 0x0003000b
Dev_SetComFormat_Get = 0x0003000c
Dev_CHNPTZCRUISE_Set = 0x0003000d
Dev_CHNPTZCRUISE_Get = 0x0003000e
Dev_CHNPTZCRUISEPLAY_Set = 0x0003000f

Alarm_MDEnable_Set = 0x00040000
Alarm_MDEnable_Get = 0x00040001
Alarm_MDThreshold_Set = 0x00040002
Alarm_MDThreshold_Get = 0x00040003
Alarm_MDArea_Get = 0x00040004
Alarm_MDClean_Cmd = 0x00040005
Alarm_VCEnable_Set = 0x00040006
Alarm_VCEnable_Get = 0x00040007
Alarm_VCThreshold_Set = 0x00040008
Alarm_VCThreshold_Get = 0x00040009
Alarm_VCArea_Set = 0x0004000a
Alarm_VCArea_Get = 0x0004000b
Alarm_VCClean_Cmd = 0x0004000c
Alarm_VLEnable_Set = 0x0004000d
Alarm_VLEnable_Get = 0x0004000e
Alarm_PortCnt_Get = 0x0004000f
Alarm_InputEnable_Set = 0x00040010
Alarm_InputEnable_Get = 0x00040011
Alarm_InputMode_Set = 0x00040012
Alarm_InputMode_Get = 0x00040013
Alarm_Link_Set = 0x00040014
Alarm_Link_Get = 0x00040015
Alarm_OutputMode_Set = 0x00040016
Alarm_OutputMode_Get = 0x00040017
Alarm_EmailEnable_Set = 0x00040018
Alarm_EmailEnable_Get = 0x00040019
Alarm_EmailInfo_Set = 0x0004001a
Alarm_EmailInfo_Get = 0x0004001b
Alarm_DrawEnable_Set = 0x0004001c
Alarm_DrawEnable_Get = 0x0004001d
Alarm_Server_Set = 0x0004001e
Alarm_Server_Get = 0x0004001f
Alarm_Schedule_Set = 0x00040020
Alarm_Schedule_Get = 0x00040021
Alarm_OutPortDelay_Set = 0x00040022
Alarm_OutPortDelay_Get = 0x00040023
Alarm_LinkRec_Set = 0x00040024
Alarm_LinkRec_Get = 0x00040025
Alarm_LinkSnap_Set = 0x00040026
Alarm_LinkSnap_Get = 0x00040027
Alarm_LinkOutPort_Set = 0x00040028
Alarm_LinkOutPort_Get = 0x00040029
Alarm_LinkPTZ_Set = 0x0004002a
Alarm_LinkPTZ_Get = 0x0004002b
Alarm_InPortEnable_Set = 0x0004002c
Alarm_InPortEnable_Get = 0x0004002d
Alarm_OutPortEnable_Set = 0x0004002e
Alarm_OutPortEnable_Get = 0x0004002f
Alarm_OutPortState_Set = 0x00040030

User_MaxConn_Set = 0x00050000
User_MaxConn_Get = 0x00050001
User_UserList_Get = 0x00050002
User_AddUser_Cmd = 0x00050003
User_DelUser_Cmd = 0x00050004
User_ModifyPwd_Cmd = 0x00050005

Osd_Type_Set = 0x00060000
Osd_Type_Get = 0x00060001
Osd_Text_Set = 0x00060002
Osd_Text_Get = 0x00060003
Osd_TextEx_Set = 0x00060004
Osd_TextEx_Get = 0x00060005

Video_StreamType_Set = 0x00070000
Video_StreamType_Get = 0x00070001
Video_MaxSize_Get = 0x00070002
Video_FrameRate_Set = 0x00070003
Video_FrameRate_Get = 0x00070004
Video_Display_Set = 0x00070005
Video_Display_Get = 0x00070006
Video_Quality_Set = 0x00070007
Video_Quality_Get = 0x00070008
Video_KbitRate_Set = 0x00070009
Video_KbitRate_Get = 0x0007000a
Video_Size_Set = 0x0007000b
Video_Size_Get = 0x0007000c
Video_Encode_Set = 0x0007000d
Video_Encode_Get = 0x0007000e
Video_NP_Set = 0x0007000f
Video_NP_Get = 0x00070010
Video_Scene_Set = 0x00070011
Video_Scene_Get = 0x00070012
Video_Reverse_Set = 0x00070013
Video_Reverse_Get = 0x00070014
Video_Mirror_Set = 0x00070015
Video_Mirror_Get = 0x00070016
Video_PreferMode_Set = 0x00070017
Video_PreferMode_Get = 0x00070018
Video_DisplaySchedule_Set = 0x00070019
Video_DisplaySchedule_Get = 0x0007001a
Video_IFrameRate_Set = 0x0007001b
Video_IFrameRate_Get = 0x0007001c
Video_MaxMinorSize_Get = 0x0007001d
Video_VencType_Set = 0x0007001e
Video_VencType_Get = 0x0007001f
Video_ShowFrameMode_Set = 0x00070020
Video_ShowFrameMode_Get = 0x00070021

Audio_Chann_Set = 0x00080000
Audio_Chann_Get = 0x00080001
Audio_Encoder_Set = 0x00080002
Audio_Encoder_Get = 0x00080003

Query_Query_Cmd = 0x00090000

Store_LocalPath_Set = 0x000a0001
Store_LocalPath_Get = 0x000a0002
Store_RmtRecExt_Set = 0x000a0003
Store_RmtRecExt_Get = 0x000a0004
Store_ManualRecOnRmt_Cmd = 0x000a0005
Store_RmtRecRule_Set = 0x000a0006
Store_RmtRecRule_Get = 0x000a0007
Store_RmtAlmRecPara_Set = 0x000a0008
Store_RmtAlmRecPara_Get = 0x000a0009
Store_RmtAlmRecEnable_Set = 0x000a000a
Store_RmtAlmRecEnable_Get = 0x000a000b
Store_RmtSchRecPara_Set = 0x000a000c
Store_RmtSchRecPara_Get = 0x000a000d
Store_RmtSchRecEnable_Set = 0x000a000e
Store_RmtSchRecEnable_Get = 0x000a000f
Store_RmtRecStat_Get = 0x000a0010
Store_DLStart_Cmd = 0x000a0011
Store_DLStop_Cmd = 0x000a0012
Store_DLProgress_Get = 0x000a0013
Store_RmtIsSupport_Get = 0x000a0014
Store_RmtDiskInfo_Get = 0x000a0015
Store_RmtDiskUsage_Set = 0x000a0016
Store_RmtFormat_Cmd = 0x000a0017
Store_RmtPart_Cmd = 0x000a0018
Store_RmtRebuildIndex_Cmd = 0x000a0019
Store_RmtDelFile_Cmd = 0x000a001a
Store_RmtUsbState_Get = 0x000a001b
Store_RmtMountUSB_Cmd = 0x000a001c
Store_RmtMapDev_Get = 0x000a001d
Store_RmtMapDev_Cmd = 0x000a001e
Store_OpenLocFile_Cmd = 0x000a001f
Store_Disk_Clear_Cmd = 0x000a0020

Net_NVSAddr_Set = 0x000b0000
Net_NVSAddr_Get = 0x000b0001
Net_DomainProp_Set = 0x000b0002
Net_DomainProp_Get = 0x000b0003
Net_PPPoEProp_Set = 0x000b0004
Net_PPPoEProp_Get = 0x000b0005
Net_HttpProp_Set = 0x000b0006
Net_HttpProp_Get = 0x000b0007
Net_IPFilter_Set = 0x000b0008
Net_IPFilter_Get = 0x000b0009
Net_WifiPara_Set = 0x000b000a
Net_WifiPara_Get = 0x000b000b
Net_WifiSrch_Cmd = 0x000b000c
Net_WifiSrchRst_Get = 0x000b000d
Net_UPNPEnable_Set = 0x000b000e
Net_UPNPEnable_Get = 0x000b000f
Net_WifiDhcpEnable_Set = 0x000b0010
Net_WifiDhcpEnable_Get = 0x000b0011
Net_DDNSPara_Set = 0x000b0012
Net_DDNSPara_Get = 0x000b0013

Log_DownLoad_Cmd = 0x000c0000
Log_Read_Cmd = 0x000c0001
Log_Clear_Cmd = 0x000c0002
Log_Query_Cmd = 0x000c0003

ZTE_ZTEInfo_Set = 0x000d0001
ZTE_ZTEInfo_Get = 0x000d0002
ZTE_OnlineState_Set = 0x000d0003
ZTE_OnlineState_Get = 0x000d0004

PU_ManagerServersInfo_Set = 0x000e0001
PU_ManagerServersInfo_Get = 0x000e0002
PU_DeviceID_Set = 0x000e0003
PU_DeviceID_Get = 0x000e0004
PU_NTP_Set = 0x000e0005
PU_NTP_Get = 0x000e0006
PU_CPUMEMAlarm_Set = 0x000e0007
PU_CPUMEMAlarm_Get = 0x000e0008
PU_OSDAlpha_Set = 0x000e0009
PU_OSDAlpha_Get = 0x000e000a
PU_PTZAUTO_Set = 0x000e000b
PU_PTZAUTO_Get = 0x000e000c
PU_MD5_Cmd = 0x000e000d

DZ_COMMON_Set = 0x000f0001
DZ_COMMON_Get = 0x000f0002
Com_Server_Set = 0x000f0003
Com_Server_Get = 0x000f0004
G_DeviceStatus_Get = 0x000f0005
G_Dialog_Set = 0x000f0006
G_Dialog_Get = 0x000f0007
G_Message_Set = 0x000f0008
G_Message_Get = 0x000f0009
G_TaskSchedule_Set = 0x000f000a
G_TaskSchedule_Get = 0x000f000b
G_Notify_Set = 0x000f000c
G_Notify_Get = 0x000f000d
G_VPDN_Set = 0x000f000e
G_VPDN_Get = 0x000f000f

HD_Camer_Set = 0x00100001
HD_Camer_Get = 0x00100002

//add by chufei for 20100819
NVR_ColorToGray_Set = 0x00120001
NVR_ColorToGray_Get = 0x00120002
//end add
NVR_FTPUsage_Set = 0x0012000e
NVR_FTPUsage_Get = 0x0012000f

NVR_FTPUploadConfig_Set = 0x00120010
NVR_FTPUploadConfig_Get = 0x00120011
//add by chufei for 20100824
ConnectInfor_Set = 0x00130001
ConnectInfor_Get = 0x00130002
LogonInfor_Set = 0x00130003
LogonInfor_Get = 0x00130004
//end add
HD_Camer_GetEx = 0x00130005
HD_Camer_SetEx = 0x00130006
HD_Camer_ClearEx = 0x00130007
HD_CamerSet_EX = 0x00130008
HD_CamerGet_EX = 0x00130009
SetSaveStatus = 0x0013000a
GetSaveStatus = 0x0013000b
//----------------OCX  msg----------------------
////////////////////////////////////////////////////////
var AXCOLOR = 1;     //界面颜色:0-绿色,1-蓝色
var NVSTYPE = 1;     //服务器类型:0-T,1-S
var STARTPICNUM = 4; //启动时显示画面数
var jsLanguage = 0;      //0-中文,1-English
var BACKCOLOR;       //按钮背景颜色:#A6DB9E-绿色  0 Tiandy
//1 ,#909CC6-蓝色-OEM
var splitCode = "\b";//
var BITRATEPOSX = 12;
var BITRATEPOSY = 40;

var iCurLogon = 0;
var iCurObs = 0;//当前obs编号－－视频窗口的标号
//------------------------------
var iCurObsCount = 0;//当前生成的视频窗口数量
var iCurMonitorCount = 0;//当前画面数
var ObsInfo = new Array(25);//存放25个视频窗口信息
var CurIndex = -1;//当前选择的点在树中的序号
//------------------------------
var iLogMode = 1;
var sServerHost;
var sProxyIP;
var iPort;
var iDevModel;//设备标识
var iStreamNO = 0;
var sDeviceID;
var xCoord;
var yCoord;
var xOld;
var yOld;
var trackbarobj;

var iMaxConUser;
var sCurUserName;
var sCurPassword;
var sChannelNumMax;
var iSelectedChannel;//在channel下拉框中选择的通道号
var iCurAuthority;
var iChannelNum;
var iLogonStatus;//当前登录状态

var sOsdText;
var iOsdTextColor;
var iCurUserNum;
var iIFrameRate;

var ChannelInfo = new Array(25);//存放通道信息的数组
for (var i = 0; i < 25; i++) {
    ChannelInfo[i] = new Array(5);
    ChannelInfo[i] = null;
    ChannelInfo[i] = new Object;
    ChannelInfo[i]['iCurObsID'] = 0;
    ChannelInfo[i]['bTalk'] = false;
    ChannelInfo[i]['bAudio'] = false;
    ChannelInfo[i]['bRec'] = false;
    ChannelInfo[i]['bShowBitRate'] = false;
    ChannelInfo[i]['bAuto'] = false;
}
var iCurObsIndex = 0;//当前iCurObs对应的obs编号(0-15)
var iBrightness;
var iSaturation;
var iContrast;
var iHue;
var iVideoQuality;
var iFrameRate;
var iMediaType;
var sChannelTitle;
var iOSDType;
var iOsdDTP;
var iOsdCTP;
var iMotionDetectThreshold;
var iVideoCoverThreshold;
var iMotionDetectAlarmCk;
var iVideoLostAlarmCk;
var iVideoCoverAlarmCk;
var iAlarmInPortNum;
var iAlarmOutPortNum;
var iAlarmInMode1;//
var iAlarmInMode2;//
var iAlarmInMode = new Array(4);
var iAlarmOutMode;
var iAudioCh;
var iDelayNum = 1;
var iInitializtion = 0;
//zc add
//for advanced options
var sIPChange;
var sMask;
var sGateway;
var sDNS;
var sSDKVer;
var sSvrVer;
var sAxVer;
var iBitRate;
var iVideoSize;
var iEncodeMode;
var iPPPEnabled;
var sPPPUser;
var sPPPPwd;
var sRegCenter;
//var sCoverArea;
var iDHCP;//DHCP使能
var arrayVideoSize = new Array("Quarter Cif", "Half Cif", "Full Cif", "Half D1",
                               "Full D1");
var iMaxVideoSize;
var iMaxMinorSize;
//for QueryFiles
iCurQFPage = 0;
iTotalQFPage = 0;
iQFPageSize = 5;

var arrSchedule = new Array(4);
for (i = 0; i < 4; i++) {
    arrSchedule[i] = new Array(4);
}
var arrSchedule3G = new Array(4);
for (i = 0; i < 4; i++) {
    arrSchedule3G[i] = new Array(4);
}
var BMPNUM = 0;
//var bDevAuto = false;
var bDevLight = false;
var bDevPower = false;
var iDomeSpeed = 50;
var iDevAddress = 0;

//define Main Menu
var TAB_LOGON = 1;
var TAB_DEVICE = 2;
var TAB_VIDEO = 3;
var TAB_ALARM = 4;
var TAB_NETWORK = 5;
var TAB_ADVANCED = 6;
var TAB_OSDParam = 7;
var TAB_MemorySet = 8;
var iCurrentTAB = TAB_LOGON;

//Action
var bUp = false;
var bDown = false;
var bLeft = false;
var bRight = false;
var bUpLeft = false;
var bUpRight = false;
var bDownLeft = false;
var bDownRight = false;

var IsFocusFar = false;
var IsFocusNear = false;
var IsZoomBig = false;
var IsZoomSmall = false;
var IsIrisOpen = false;
var IsIrisClose = false;
var IsRain = false;
//下载
var pbDownStatus = 0;//当前SD卡文件下载状态
var strLastDownFileName;//当前正在下载的文件名
var pbDownloadID = 0;
var pbDownInterval = 0;
var playBackStatus = 0;//当前播放状态
var chkbOutChannelLink = new Array(16);//控件数组checkbox

var blResumeVideo = false;//lijie add for
var iSaveStatus = 0;
var isWifiClose = false;
//---------------------------------------------------------------------------
//	Message(Chinese/English)
//---------------------------------------------------------------------------
var JS_cMsg0 = "请选择控制协议";
var JS_cMsg1 = "注册中心IP地址";
var JS_cMsg2 = "端口号";
var JS_cMsg3 = "不限码流";
var JS_cMsg4 = "请先打开区域设置再进行区域清除！";
var JS_cMsg5 = "没有登录！\n请选择已登录的视频再进行操作。";
var JS_cMsg6 = "没有登录，无法抓拍！";
var JS_cMsg7 = "输入含有非法字符！";
var JS_cMsg8 = "请输入一个整数值！";
var JS_cMsg9 = "请输入32~8000之间的值！";
var JS_cMsg10 = "无法删除当前活动用户！";
var JS_cMsg11 = "确定恢复默认设置？";
var JS_cMsg12 = "确定重新启动服务器？";
var JS_cMsg13 = "磁盘已满，录像和下载已停止！";
var JS_cMsg14 = "抓拍失败，请检查磁盘！";
var JS_cMsg15 = "系统中已经运行了一个客户端，请检查后重新下载本页面！";
var JS_cMsg16 = "未安装控件！请尝试重新下载。";
var JS_cMsg17 = "您的IE版本太低，不能正常浏览该网页！请将IE升级到6.0及以上版本！";
var JS_cMsg18 = "Admin为保留用户，不允许删除！";
var JS_cMsg19 = "两次输入密码不一致！";
var JS_cMsg20 = "有效地址范围为：0~255";
var JS_cMsg21 = "有效地址范围为：0~255";
var JS_cMsg22 = "有效预置点范围为：1~255";
var JS_cMsg23 = "无效IP地址";
var JS_cMsg24 = "无效网关或DNS";
var JS_cMsg25 = "无效的子网掩码";
var JS_cMsg26 = "两次输入密码不一致！";
var JS_cMsg27 = "起始时间必须小于结束时间";
var JS_cMsg28 = "不是有效的升级文件";
var JS_cMsg29 = "校验码不正确";
var JS_cMsg30 = "端口号无效";
var JS_cMsg31 = "登录失败，用户名密码错误";
var JS_cMsg32 = "您的权限不足，不能够进行此操作";
var JS_cMsg33 = "确认用当前系统时间校准设备时间？";
var JS_cMsg34 = "用户名不能为空";
var JS_cMsg35 = "确认退出？";
var JS_cMsg36 = "用户不存在";
var JS_cMsg37 = "用户已存在";
var JS_cMsg38 = "密码不能为空";
var JS_cMsg39 = " 只能由英文字母a～z(区分大小写)、数字0～9、特殊键字符组成";
var JS_cMsg40 = "您输入的字符超出了字长范围127，汉字按照2个字长计算。"
var JS_cMsg41 = " 请输入长度为:  ";
var JS_cMsg42 = " 个字符";
var JS_cMsg43 = " 请输入值为: ";
var JS_cMsg44 = " 的整数值";
var JS_cMsg45 = "无效的Email地址";
var JS_cMsg46 = "设置成功，20秒后系统会重新登录";
var JS_cMsg47 = "IP 和网关必须在同一个网段内";
var JS_cMsg48 = "您输入了一个无效的子网掩码，子网掩码必须是相邻的。";
var JS_cMsg49 = "设置失败";
var JS_cMsg50 = "设置成功";
var JS_cMsg51 = "没有登录，无法打开音频！";
var JS_cMsg52 = "没有登录，无法进行录像！";
var JS_cMsg53 = "没有登录，无法进行对讲！";
var JS_cMsg54 = "未选择设置移动报警区域";
var JS_cMsg55 = "开始时间必须小于结束时间!";
var JS_cMsg56 = "播放完毕!";
var JS_cMsg57 = "本地录像文件不需下载!";
var JS_cMsg58 = "请在右侧文件列表中选择您要下载的文件!";
var JS_cMsg59 = "同时只能下载一个文件!";
var JS_cMsg60 = "下载超过最大限制!";
var JS_cMsg61 = "下载完毕!文件保存到：";
var JS_cMsg62 = "两次输入密码不一致!";
var JS_cMsg63 = "正在升级中，请等待……";
var JS_cMsg64 = "此用户名不在用户名列表中！";
var JS_cMsg65 = "旧密码输入错误！";
var JS_cMsg66 = "有线IP不能与无线IP相同！";
var JS_cMsg67 = "请选择文件夹";
var JS_cMsg68 = "叠加字符长度不能超过32个字符(一个汉字占两个字符)";
var JS_cMsg69 = "设置成功，请重启设备！";
var JS_cMsg70 = "IP修改成功，请重新登录设备！";
var JS_cMsg71 = "超出最大可添加用户数量！";
var JS_cMsg72 = "添加用户失败！";
var JS_cMsg73 = "添加用户名重复！";
var JS_cMsg74 = "路径名输入错误或正在使用原路径";
var JS_cMsg75 = "输入值错误！";
var JS_cMsg76 = "用户名和密码不能设为空或含有空格";
var JS_cMsg77 = "对讲失败！";
var JS_cMsg78 = "密码长度不符合要求，请重新输入！";
var JS_cMsg79 = "用户名长度超出范围1-15个字符（一个汉字占两个字符）";
var JS_cMsg80 = "升级时将关闭录像、音频和对讲!";
var JS_cMsg81 = "设备端TCP连接数超过限制!";
var JS_cMsg82 = "此参数设置成功后，会自动重启设备";
var JS_cMsg83 = "是否确认断开无线连接？确认后将重启设备。";
var JS_cMsg84 = "是否确认断开3G连接？";
var JS_cMsg85 = "各时间段之间不能重复！";
//---------------------------------------------------------------------------
//	所有显示信息(Chinese/English)
//---------------------------------------------------------------------------
var JS_cShow1 = "服务器IP：";
var JS_cShow2 = "用户名：";
var JS_cShow3 = "密码：";
var JS_cShow4 = "[登录]";
var JS_cShow5 = "[断开]";
var JS_cShow6 = "代理IP：";
var JS_cShow7 = "端口：";
var JS_cShow8 = "通道：";
var JS_cShow9 = "码流：";
var JS_cShow10 = "主码流";
var JS_cShow11 = "副码流";
var JS_cShow12 = "协议：";
var JS_cShow13 = "[连接视频]";
var JS_cShow14 = "[断开视频]";
var JS_cShow15 = "注册中心：";
var JS_cShow16 = "服务器名：";
var JS_cShow17 = "子网掩码：";
var JS_cShow18 = "网关：";
var JS_cShow19 = "[无线设置]";
var JS_cShow20 = "[设置]";
var JS_cShow21 = "使用PPPoE拨号连接：";
var JS_cShow22 = "帐号：";
var JS_cShow23 = "Web端口：";
var JS_cShow24 = "请选择要升级的文件(*.bin/*.box)";
var JS_cShow25 = "[升级]";
var JS_cShow26 = "[清除]";
var JS_cShow27 = "[恢复默认]";
var JS_cShow28 = "[重启设备]";
var JS_cShow29 = "[设置时间]";
var JS_cShow30 = "用户管理";
var JS_cShow31 = "选择用户：";
var JS_cShow32 = "旧密码：";
var JS_cShow33 = "新密码：";
var JS_cShow34 = "权&nbsp;&nbsp; 限：";
var JS_cShow35 = "浏览";
var JS_cShow36 = "浏览+控制";
var JS_cShow37 = "浏览+控制+设置";
var JS_cShow38 = "管理员";

var JS_cShow39 = "[添加]";
var JS_cShow40 = "[删除]";
var JS_cShow41 = "[修改密码]";
var JS_cShow42 = "系统信息";
var JS_cShow43 = "SDK版本：";
var JS_cShow44 = "服务器版本：";
var JS_cShow45 = "ActiveX版本：";
var JS_cShow46 = "[预置]";
var JS_cShow47 = "[调用]";
var JS_cShow48 = "启用3D定位";
var JS_cShow49 = "变倍大";
var JS_cShow50 = "变倍小";
var JS_cShow51 = "聚焦远";
var JS_cShow52 = "聚焦近";
var JS_cShow53 = "光圈开";
var JS_cShow54 = "光圈关";
var JS_cShow55 = "灯光";
var JS_cShow56 = "电源";
var JS_cShow57 = "雨刷";
var JS_cShow58 = "速度：";
var JS_cShow59 = "控制协议：";
var JS_cShow60 = "协议状态：";
var JS_cShow61 = "设备地址：";

var JS_cShow62 = "输入端口有效";
var JS_cShow63 = "输入端口：";
var JS_cShow64 = "输出端口：";
var JS_cShow65 = "输入端口：";
var JS_cShow66 = "输出端口：";
var JS_cShow67 = "联动设置";
var JS_cShow68 = "模式设置：";
var JS_cShow69 = "[清除区域]";
var JS_cShow70 = "设置移动报警区域";
var JS_cShow71 = "设置视频遮挡区域";
var JS_cShow72 = "视频移动报警灵敏度";
var JS_cShow73 = "视频移动报警";
var JS_cShow74 = "视频遮挡报警";
var JS_cShow75 = "视频丢失报警";
var JS_cShow76 = "闭路报警";
var JS_cShow77 = "开路报警";
var JS_cShow78 = "不叠加";
var JS_cShow79 = "日期时间";
var JS_cShow80 = "通道名称：";
var JS_cShow81 = "日期时间＋通道名称";
var JS_cShow82 = "字符叠加：";
var JS_cShow83 = "通道名称";
var JS_cShow84 = "通道名位置：";
var JS_cShow85 = "日期位置：";
var JS_cShow86 = "附加字符叠加：";
var JS_cShow87 = "X坐标：";
var JS_cShow88 = "Y坐标：";
var JS_cShow89 = "USB/SD设备";
var JS_cShow90 = "加载状态";
var JS_cShow91 = "未加载";
var JS_cShow92 = "已加载";
var JS_cShow93 = "磁盘空间(M)";
var JS_cShow94 = "剩余空间(M)";
var JS_cShow95 = "SATA设备";
var JS_cShow96 = "磁盘容量(M)";
var JS_cShow97 = "NFS 远程映射存储设备";
var JS_cShow98 = "映射状态";
var JS_cShow99 = "未映射";
var JS_cShow100 = "已映射";
var JS_cShow101 = "IP地址&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
var JS_cShow102 = "映射路径";
var JS_cShow103 = "[磁盘信息]";
var JS_cShow104 = "[应用到每一天]";
var JS_cShow105 = "最大录像时间 单位分钟(10-120)";
var JS_cShow106 = "剩余磁盘空间 单位MB(≥200)";
var JS_cShow107 = "保存策略：";
var JS_cShow108 = "停止录像";
var JS_cShow109 = "循环删除";
var JS_cShow110 = "循环删除(除报警文件)";
var JS_cShow111 = "定时录像";
var JS_cShow112 = "报警录像";
var JS_cShow113 = "停止";
var JS_cShow114 = "手动录像";
var JS_cShow115 = "录像状态：";
var JS_cShow116 = "周日";
var JS_cShow117 = "周一";
var JS_cShow118 = "周二";
var JS_cShow119 = "周三";
var JS_cShow120 = "周四";
var JS_cShow121 = "周五";
var JS_cShow122 = "周六";
var JS_cShow123 = "日期选择：";
var JS_cShow124 = "报警预录的时间，单位秒(5-60) ";
var JS_cShow125 = "报警录像延时，单位秒(10-60)";
var JS_cShow126 = "[前端抓拍]";
var JS_cShow127 = "报警预录：";
var JS_cShow128 = "本地文件存储路径:";
var JS_cShow129 = "服务器列表：";
var JS_cShow130 = "远程";
var JS_cShow131 = "文件类型：";
var JS_cShow132 = "录像";
var JS_cShow133 = "图片"
var JS_cShow134 = "开始时间"
var JS_cShow135 = "结束时间"
var JS_cShow136 = "录像类型："
var JS_cShow137 = "所有"
var JS_cShow138 = "手动"
var JS_cShow139 = "定时"
var JS_cShow140 = "报警"
var JS_cShow141 = "通道："
var JS_cShow142 = "[搜索]";
var JS_cShow143 = "[播放]"
var JS_cShow144 = "[暂停]";
var JS_cShow145 = "[停止]";
var JS_cShow146 = "[快进]";
var JS_cShow147 = "[慢进]";
var JS_cShow148 = "[下载]";
var JS_cShow149 = "[停止下载]";
var JS_cShow150 = "[首页]";
var JS_cShow151 = "[上一页]";
var JS_cShow152 = "[下一页]";

var JS_cShow153 = "[末页]";
var JS_cShow154 = "本地回放：";
var JS_cShow155 = "[返回]";
var JS_cShow156 = "亮度：";
var JS_cShow157 = "饱和度：";
var JS_cShow158 = "色调：";
var JS_cShow159 = "对比度：";
var JS_cShow160 = "最好";
var JS_cShow161 = "较好";
var JS_cShow162 = "好";
var JS_cShow163 = "一般";
var JS_cShow164 = "较差";
var JS_cShow165 = "视频质量：";
var JS_cShow166 = "纯视频";
var JS_cShow167 = "音视频";
var JS_cShow168 = "流类型：";
var JS_cShow169 = "质量优先";
var JS_cShow170 = "帧率优先";
var JS_cShow171 = "优先模式：";
var JS_cShow172 = "帧率：";
var JS_cShow173 = "码率：";
var JS_cShow174 = "视频大小：";
var JS_cShow175 = "编码模式：";
var JS_cShow176 = "制式：";
var JS_cShow177 = "显示帧率及码流信息";

var JS_cShow178 = "可用网络列表：";
var JS_cShow179 = "无线网卡";
var JS_cShow180 = "无";
var JS_cShow181 = "有";
var JS_cShow182 = "无线状态";
var JS_cShow183 = "不在线";
var JS_cShow184 = "在线";
var JS_cShow185 = "自动获取IP地址";
var JS_cShow186 = "无线IP：";
var JS_cShow187 = "无线子网掩码：";
var JS_cShow188 = "无线网关：";
var JS_cShow189 = "无线DNS：";
var JS_cShow190 = "安全类型：";
var JS_cShow191 = "密钥格式选择：";
var JS_cShow192 = "密钥类型：";
var JS_cShow193 = "禁用";
var JS_cShow194 = "64位";
var JS_cShow195 = "128位";
var JS_cShow196 = "密钥：";
var JS_cShow197 = "密码长度说明：选择64位密钥需输入16进制数字符10个，或者ASCII码字符5个。选择128位密钥需输入16进制数字符26个，或者ASCII码字符13个。";

var JS_cShow198 = "登录选项";
var JS_cShow199 = "设备控制";
var JS_cShow200 = "视频参数";
var JS_cShow201 = "字符叠加";
var JS_cShow202 = "报警设置";
var JS_cShow203 = "网络设置";
var JS_cShow204 = "前端存储";
var JS_cShow205 = "高级选项";
var JS_cShow206 = "开启";
var JS_cShow207 = "未开启";
var JS_cShow208 = "安全设置:";
var JS_cShow209 = "登录成功";
var JS_cShow210 = "登录成功,通道号错误，请重新选择通道显示视频";
var JS_cShow211 = "登录超时";
var JS_cShow212 = "升级成功，请等待设备自动重启(10s-60s)……";
var JS_cShow213 = "升级成功，请等待设备自动重启(10s-60s),重启后请重新打开IE登录。";
var JS_cShow214 = "升级失败！尚未登录网络视频服务器,或升级文件无效.";
var JS_cShow215 = "IP：";
var JS_cShow216 = "DNS：";
var JS_cShow217 = "密码";
var JS_cShow218 = "服务器名称";
var JS_cShow219 = "手动修改";
var JS_cShow220 = "设置";
var JS_cShow221 = "打开";

var JS_cShow222 = "单画面";
var JS_cShow223 = "开始对讲";
var JS_cShow224 = "录像回放";
var JS_cShow225 = "抓拍";
var JS_cShow226 = "停止对讲";
var JS_cShow227 = "四画面";
var JS_cShow228 = "全屏";
var JS_cShow229 = "打开音频";
var JS_cShow230 = "开始录像";
var JS_cShow231 = "十六画面";
var JS_cShow232 = "九画面";
var JS_cShow233 = "关闭音频";
var JS_cShow234 = "停止录像";

var JS_cShow235 = "动态域名解析使能";
var JS_cShow236 = "服务器域名：";
var JS_cShow237 = "域名解析服务器：";
var JS_cShow238 = "密码确认：";
var JS_cShow239 = "网络视频浏览器";
var JS_cShow240 = "播放帧数：";
var JS_cShow241 = "总帧数：";
var JS_cShow242 = "正在加载......";
var JS_cShow243 = "总条数：";
var JS_cShow244 = "重复登录！";
var JS_cShow245 = "此设备不支持副码流，请重新选择码流类型！";
var JS_cShow246 = "无人值守";
var JS_cShow247 = "密码确认：";
var JS_cShow248 = "重复连接";
var JS_cShow249 = "通道已经占用";
var JS_cShow250 = "同一个远程文件不能同时本地下载和预览";
var JS_cShow251 = "帐户";
var JS_cShow252 = "按大小打包 单位M(20-200)";
var JS_cShow253 = "不录像";
var JS_cShow254 = "定时录像";
var JS_cShow255 = "端口报警";
var JS_cShow256 = "移动报警";
var JS_cShow257 = "丢失报警";
var JS_cShow258 = "移动|端口";
var JS_cShow259 = "服务器列表";
var JS_cShow260 = "复合类型：";
var JS_cShow261 = "分辨率：";
var JS_cShow262 = "码流类型：";
var JS_cShow263 = "音频编码：";
var JS_cShow264 = "I帧帧率：";
var JS_cShow265 = "[报警高级]";
var JS_cShow266 = "手动消警";
var JS_cShow267 = "延时消警";
var JS_cShow268 = "消警模式：";
var JS_cShow269 = "延时时间：";
var JS_cShow270 = "报警类型：";
var JS_cShow271 = "使能";
var JS_cShow272 = "布防设置：";
var JS_cShow273 = "不联动";
var JS_cShow274 = "预置位";
var JS_cShow275 = "巡航轨迹";
var JS_cShow276 = "路径";
var JS_cShow277 = "联动类型：";
var JS_cShow278 = "联动抓拍";
var JS_cShow279 = "联动录像";
var JS_cShow280 = "联动输出";
var JS_cShow281 = "联动PTZ";
var JS_cShow282 = "联动通道";
var JS_cShow283 = "联动类型";
var JS_cShow284 = "相应编号";
var JS_cShow285 = "禁止访问IP：";
var JS_cShow286 = "磁盘编号：";
var JS_cShow287 = "录像";
var JS_cShow288 = "备份";
var JS_cShow289 = "用途：";
var JS_cShow290 = "分区数：";
var JS_cShow291 = "无磁盘";
var JS_cShow292 = "未格式化";
var JS_cShow293 = "已格式化";
var JS_cShow294 = "已挂载";
var JS_cShow295 = "读写中";
var JS_cShow296 = "[清除磁盘]";
var JS_cShow297 = "[USB分区格式化]";

var JS_cShow298 = "报警服务器：";
var JS_cShow299 = "串口服务器：";
var JS_cShow300 = "报警高级";

var JS_cShow301 = "开始格式化";
var JS_cShow302 = "格式化进度:";

var JS_cShow303 = "格式化成功";
var JS_cShow304 = "格式化失败，无效磁盘";
var JS_cShow305 = "格式化失败，其他用户正在格式化";
var JS_cShow306 = "格式化失败";
var JS_cShow307 = "格式化后所有磁盘内容都会丢失，确认要格式化磁盘？";
var JS_cShow308 = "正在修复磁盘";
var JS_cShow309 = "磁盘修复完成";

var JS_cShow310 = "[3G设置]";
var JS_cShow311 = "设备类型";
var JS_cShow312 = "在线状态";
var JS_cShow313 = "未上线";
var JS_cShow314 = "已上线";
var JS_cShow315 = "信号强度";
var JS_cShow316 = "3G IP地址";
var JS_cShow317 = "初始拨号时间";
var JS_cShow318 = "拨号方式";
var JS_cShow319 = "自动拨号";
var JS_cShow320 = "短信拨号";
var JS_cShow321 = "电话拨号";
var JS_cShow322 = "报警拨号";
var JS_cShow323 = "断线方式";
var JS_cShow324 = "主动断线";
var JS_cShow325 = "被动断线";
var JS_cShow326 = "拨号预计时长";
var JS_cShow327 = "电话激活支持号码(仅WCDMA支持)";
var JS_cShow328 = "通知字符串";
var JS_cShow329 = "手机号码";
var JS_cShow330 = "较弱";
var JS_cShow331 = "弱";
var JS_cShow332 = "中等";
var JS_cShow333 = "强";
var JS_cShow334 = "自动拨号和主动断线不能同时设置";
var JS_cShow335 = "[断开]";
var JS_cShow336 = "设置定时上线时间";
var JS_cShow337 = "短信通知类型";
var JS_cShow338 = "短信通知内容";
var JS_cShow339 = "3G高级设置";
var JS_cShow340 = "不通知";
var JS_cShow341 = "拨号成功后通知";
var JS_cShow342 = "拨号失败后通知";
var JS_cShow343 = "无效电话号码";

var JS_cShow350 = "电子云台";
var JS_cShow351 = "自动光圈";
var JS_cShow352 = "背光补偿";
var JS_cShow353 = "视频模式：";
var JS_cShow354 = "[翻转]";

var JS_cShow360 = "播放偏好：";
var JS_cShow361 = "低时延";
var JS_cShow362 = "高流畅性";
var JS_cShow400 = "ESSID：";
var JS_cShow410 = "3G专网(仅EVDO支持)";
var JS_cShow411 = "接入号码";
var JS_cShow412 = "帐号";
var JS_cShow413 = "密码";
var JS_cShow414 = "不能为空";
var JS_cShow415 = "视频遮挡报警灵敏度";
var JS_cShow416 = "类型：";
var JS_cShow417 = "遮挡报警";

var JS_cShow500 = "[PTZ巡航设置]";
var JS_cShow501 = "巡航轨迹号";
var JS_cShow502 = "轨迹使能";
var JS_cShow503 = "巡航点数";
var JS_cShow504 = "巡航点";
var JS_cShow505 = "速度";
var JS_cShow506 = "停留时间（秒）";
var JS_cShow507 = "[SATA分区格式化]";
var JS_cShow508 = "密码长度说明：选择ASCII码字符8-63个，选择16进制数字符64个";

var JS_cShow609 = "地址：";
var JS_cShow610 = "路径：";
var JS_cShow612 = "[高级]";
var JS_cShow613 = "FTP 设置";

var JS_cShow614 = "NTP 设置";
var JS_cShow615 = "地址：";
var JS_cShow616 = "端口：";
var JS_cShow617 = "间隔(小时)：";

var JS_cShow618 = "邮件报警";
var JS_cShow619 = "服务器：";
var JS_cShow620 = "端口号：";
var JS_cShow621 = "用户名：";
var JS_cShow622 = "密码：";
var JS_cShow623 = "登录模式：";
var JS_cShow624 = "邮件地址：";
var JS_cShow625 = "邮件主题：";

//add by chufei for 20100819
var JS_cShow626 = "[高清设置]";
var JS_cShow627 = "曝光时间：";
var JS_cShow628 = "快门速度：";
var JS_cShow629 = "彩转黑：";
var JS_cShow630 = "关闭";
var JS_cShow631 = "自动×2";
var JS_cShow632 = "自动×5";
var JS_cShow633 = "翻转：";
var JS_cShow634 = "开启";
var JS_cShow635 = "关闭";
var JS_cShow636 = "设置曝光区域";
var JS_cShow637 = "设置背光补偿区域";

var JS_cShow638 = "[FTP上传]";
var JS_cShow639 = "[抓拍联动设置]";
var JS_cShow640 = "抓拍联动使能";
var JS_cShow641 = "联动Email";
var JS_cShow642 = "联动FTP";
var JS_cShow643 = "定时抓拍使能";
var JS_cShow644 = "抓拍质量：";
var JS_cShow645 = "间隔(秒)：";
var JS_cShow646 = "定时抓拍设置";
var JS_cShow647 = "抓拍联动设置";
var JS_cShow648 = "定时上传设置";
var JS_cShow649 = "定时上传使能";
var JS_cShow650 = "文件类型：";
var JS_cShow651 = "全部";
var JS_cShow652 = "录像";
var JS_cShow653 = "图片";
var JS_cShow654 = "上传时间：";
var JS_cShow655 = "如果需要下载最新插件，请登录本公司网站(http://www.tiandy.com)!";
var JS_cShow656 = "自动";
var JS_cShow657 = "黑白";
var JS_cShow658 = "彩色";
var JS_cShow659 = "[全部断开]";
var JS_cShow660 = "自动增益：";
var JS_cShow661 = "高";
var JS_cShow662 = "中";
var JS_cShow663 = "低";
var JS_cShow664 = "关闭IE自动保存连接信息";
var JS_cShow665 = "FTP上传可能会中断其他FTP上传，是否继续？";
var JS_cShow666 = "本地文件不能上传！";
var JS_cShow667 = "[参数配置]";
var JS_cShow668 = "远程模式不支持";
//---------------------------------------------------------------------------
//	Load Data From Activex And Init Html
//---------------------------------------------------------------------------
function aXActivate() {
    //In/Out Port
}

function loadInfoFromaX(iCh)//iCh:1~4
{
    if (iCurObs != TiandyVideo.CurObs) {
        return;
    }
    var arrResult = TiandyVideo.Commander(Obs_Prop_Get, iCurObs, 0, 0, 0, 0, 0,
                                          0, 0, 0).split('\n')[1].split(
        splitCode);
    var iTempID = parseInt(arrResult[0]);
    iChannelNum = parseInt(arrResult[2]);
    iLogMode = parseInt(arrResult[3]);
    iStreamNO = parseInt(arrResult[4]);

    arrResult =
        TiandyVideo.Commander(Entry_Prop_Get, iCurLogon, 0, 0, 0, 0, 0, 0, 0, 0)
            .split('\n')[1].split(splitCode);
    sProxyIP = arrResult[1];
    sServerHost = arrResult[2];
    sDeviceID = arrResult[3];
    iPort = parseInt(arrResult[4]);
    sChannelNumMax = arrResult[5];
    sCurUserName = arrResult[6];
    sCurPassword = arrResult[7];
    iCurAuthority = parseInt(arrResult[8]) - 1;

    if (iTempID != iCurLogon) {
        return;
    }
    if (iChannelNum + 1 > sChannelNumMax) {
        return;
    }
    iDevModel =
        TiandyVideo.Commander(Dev_Model_Get, iCurLogon, 0, 0, 0, 0, 0, 0, 0, 0)
            .split('\n')[1].split(splitCode)[1];

    iMaxConUser =
        parseInt(
            TiandyVideo.Commander(User_MaxConn_Get, iCurLogon, 0, 0, 0, 0, 0, 0,
                                  0, 0).split('\n')[1]);
    if (iChannelNum == -1) {
        return;
    }
    arrResult =
        TiandyVideo.Commander(Osd_Text_Get, iCurLogon, iChannelNum, 0, 0, 0, 0,
                              0, 0, 0).split('\n')[1].split(splitCode);
    sOsdText = arrResult[0];
    iOsdTextColor = parseInt(arrResult[1]);

    iOSDType = 0;
    arrResult =
        TiandyVideo.Commander(Osd_Type_Get, iCurLogon, iChannelNum, 0x01, 0, 0,
                              0, 0, 0, 0).split('\n')[1].split(splitCode);
    iOsdDTP = parseInt(arrResult[0]);
    iOSDType += parseInt(arrResult[2]);

    arrResult =
        TiandyVideo.Commander(Osd_Type_Get, iCurLogon, iChannelNum, 0x02, 0, 0,
                              0, 0, 0, 0).split('\n')[1].split(splitCode);
    iOsdCTP = parseInt(arrResult[0]);
    iOSDType += parseInt(arrResult[2]) << 1;

    arrResult =
        TiandyVideo.Commander(Osd_Type_Get, iCurLogon, iChannelNum, 0x04, 0, 0,
                              0, 0, 0, 0).split('\n')[1].split(splitCode);
    iOsdLTP = parseInt(arrResult[0]);
    iOSDType += parseInt(arrResult[2]) << 2;
    arrResult =
        TiandyVideo.Commander(Video_FrameRate_Get, iCurLogon, iChannelNum,
                              iStreamNO, 0, 0, 0, 0, 0, 0).split('\n')[1];
    iFrameRate = parseInt(arrResult);

    arrResult =
        TiandyVideo.Commander(Video_Display_Get, iCurLogon, iChannelNum, 0, 0,
                              0, 0, 0, 0, 0).split('\n')[1].split(splitCode);
    iBrightness = parseInt(arrResult[0]);
    if (iBrightness > 200) {
        iBrightness = 200;
    }
    if (iBrightness < 100) {
        iBrightness = 100;
    }

    iSaturation = parseInt(arrResult[1]);
    if (iSaturation > 200) {
        iSaturation = 200;
    }
    if (iSaturation < 100) {
        iSaturation = 100;
    }

    iContrast = parseInt(arrResult[2]);
    if (iContrast > 200) {
        iContrast = 200;
    }
    if (iContrast < 100) {
        iContrast = 100;
    }

    iHue = parseInt(arrResult[3]);
    if (iHue > 200) {
        iHue = 200;
    }
    if (iHue < 100) {
        iHue = 100;
    }

    iVideoQuality =
        parseInt(
            TiandyVideo.Commander(Video_Quality_Get, iCurLogon, iChannelNum,
                                  iStreamNO, 0, 0, 0, 0, 0, 0).split('\n')[1]);

    iMediaType =
        parseInt(
            TiandyVideo.Commander(Video_StreamType_Get, iCurLogon, iChannelNum,
                                  iStreamNO, 0, 0, 0, 0, 0, 0).split('\n')[1]);
    sChannelTitle = sOsdText;

    iMotionDetectThreshold =
        parseInt(
            TiandyVideo.Commander(Alarm_MDThreshold_Get, iCurLogon, iChannelNum,
                                  0, 0, 0, 0, 0, 0, 0).split('\n')[1]);
    iMotionDetectThreshold =
        ((iMotionDetectThreshold >= 0) && (iMotionDetectThreshold <= 255))
            ? iMotionDetectThreshold : 0;

    iVideoCoverThreshold =
        parseInt(
            TiandyVideo.Commander(Alarm_VCThreshold_Get, iCurLogon, iChannelNum,
                                  0, 0, 0, 0, 0, 0, 0).split('\n')[1]);
    iVideoCoverThreshold == ((iVideoCoverThreshold >= 0)
                             && (iVideoCoverThreshold <= 255))
        ? iVideoCoverThreshold : 0;
    iMotionDetectAlarmCk =
        parseInt(
            TiandyVideo.Commander(Alarm_MDEnable_Get, iCurLogon, iChannelNum, 0,
                                  0, 0, 0, 0, 0, 0).split('\n')[1]);
    iVideoLostAlarmCk =
        parseInt(
            TiandyVideo.Commander(Alarm_VLEnable_Get, iCurLogon, iChannelNum, 0,
                                  0, 0, 0, 0, 0, 0).split('\n')[1]);
    iVideoCoverAlarmCk =
        parseInt(
            TiandyVideo.Commander(Alarm_VCEnable_Get, iCurLogon, iChannelNum, 0,
                                  0, 0, 0, 0, 0, 0).split('\n')[1]);

    iAlarmInMode1 =
        parseInt(
            TiandyVideo.Commander(Alarm_InputMode_Get, iCurLogon, 0, 0, 0, 0, 0,
                                  0, 0, 0).split('\n')[1]);
    iAlarmInMode2 =
        parseInt(
            TiandyVideo.Commander(Alarm_InputMode_Get, iCurLogon, 1, 0, 0, 0, 0,
                                  0, 0, 0).split('\n')[1]);
    iAlarmOutMode =
        parseInt(
            TiandyVideo.Commander(Alarm_OutputMode_Get, iCurLogon, 0, 0, 0, 0,
                                  0, 0, 0, 0).split('\n')[1]);
    iAudioCh =
        parseInt(
            TiandyVideo.Commander(Audio_Chann_Get, iCurLogon, iChannelNum, 0, 0,
                                  0, 0, 0, 0, 0).split('\n')[1]);

    /*for advanced options*/
    arrResult =
        TiandyVideo.Commander(Net_NVSAddr_Get, iCurLogon, 0, 0, 0, 0, 0, 0, 0,
                              0).split('\n')[1].split(splitCode);
    iDHCP = parseInt(arrResult[0]);
    sMask = arrResult[3];
    sGateway = arrResult[4];
    sDNS = arrResult[5];
    sSDKVer = TiandyVideo.cSDKVer;

    var vTemp = TiandyVideo.Commander(Entry_NVSVer_Get, iCurLogon, 0, 0, 0, 0,
                                      0, 0, 0, 0).split('\n')[1];
    sSvrVer = vTemp.split(splitCode)[0];
    sAxVer = TiandyVideo.cAxVer;

    iBitRate =
        TiandyVideo.Commander(Video_KbitRate_Get, iCurLogon, iChannelNum,
                              iStreamNO, 0, 0, 0, 0, 0, 0).split('\n')[1];
    iVideoSize =
        TiandyVideo.Commander(Video_Size_Get, iCurLogon, iChannelNum, iStreamNO,
                              0, 0, 0, 0, 0, 0).split('\n')[1];

    iEncodeMode =
        TiandyVideo.Commander(Video_Encode_Get, iCurLogon, iChannelNum,
                              iStreamNO, 0, 0, 0, 0, 0, 0).split('\n')[1];
    arrResult =
        TiandyVideo.Commander(Net_PPPoEProp_Get, iCurLogon, 0, 0, 0, 0, 0, 0, 0,
                              0).split('\n')[1].split(splitCode);
    sPPPUser = arrResult[0];
    sPPPPwd = arrResult[1];
    iPPPEnabled = parseInt(arrResult[2]);
    sRegCenter =
        TiandyVideo.Commander(Net_DomainProp_Get, iCurLogon, 0, 0, 0, 0, 0, 0,
                              0, 0).split('\n')[1];
    arrResult =
        TiandyVideo.Commander(Sys_SysInfo_Get, iCurLogon, 0, 0, 0, 0, 0, 0, 0,
                              0);
    FilePahtGet();//获得文件路径名

    var opt;
    //DeviceType List
    dDeviceType.options.length = 0;
    opt = new Option(JS_cMsg0);
    dDeviceType.options.add(opt);
    var cResult = TiandyVideo.Commander(Dev_Type_Get, iCurLogon, iChannelNum, 2,
                                        0, 0, 0, 0, 0, 0).split('\n');
    if (cResult.length > 1) {
        var curDevTypeIfo = cResult[1].split(splitCode);
        var curDevType = curDevTypeIfo[1];
        txDevAddress.value = parseInt(curDevTypeIfo[3]);
    }

    var arrAllDevType = TiandyVideo.Commander(Dev_AllType_Get, 0, 0, 0, 0, 0, 0,
                                              0, 0, 0).split('\n');

    for (var i = 1; i < arrAllDevType.length; i++) {
        opt = new Option(arrAllDevType[i]);
        dDeviceType.options.add(opt);
        if (curDevType == arrAllDevType[i]) {
            dDeviceType.selectedIndex = i;
        }
    }
    Device_UpdateStatus();
    //add by chufei for 20100828
}

//显示错误
function OnError(iStatus, iID) {
    if (iStatus == 3)//登录超过连接
    {
        TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cMsg81, 3000,
                              0, 0, 0, 0, 0);
    }
    else if (iStatus == 6)//磁盘已达到阈值
    {
        for (i = 0; i < 16; i++) {
            lyRecorder.style.visibility = "visible";
            lyRecorderMD.style.visibility = "hidden";
            ChannelInfo[i]['bRec'] = false;
        }
        if (pbDownStatus) {
            PB_StopDown();
        }
        if (IsRemode) {
            PlayBack_Stop();
        }
        TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cMsg13, 3000,
                              0, 0, 0, 0, 0);
    }
}

//用户权限限制
function checkAuthority() {
    bSet2.disabled = false;
    if (iCurAuthority == 0) {
        bSet2.disabled = true;

        if (iCurrentTAB != TAB_LOGON) {
            DHT_Logon();
        }
    }
    else if (iCurAuthority == 1) {
        if ((iCurrentTAB != TAB_LOGON) && (iCurrentTAB != TAB_DEVICE)) {
            DHT_Logon();
        }
    }
    else if (iCurAuthority == 2) {
        //处理浏览加设置权限
    }
    else {
        //处理Admin权限
    }
}

function GetLogonStatus()//获取该设备的登录状态
{
    var sTemp = TiandyVideo.Commander(Obs_Prop_Get, iCurObs, 0, 0, 0, 0, 0, 0,
                                      0, 0);
    if (sTemp.length > 10) {
        var arrStatus = sTemp.split('\n')[1].split(splitCode);
    }
    else {
        return false;
    }
    iLogonStatus = parseInt(arrStatus[0]);
    if (iLogonStatus == -1) {
        return false;
    }
    else {
        return true;
    }
}

function initHtmlInfo(iCh)//iCh:1~4
{
    if (!GetLogonStatus()) {
        return;
    }
    if (iStreamNO == 0) {
        selStreamType.selectedIndex = 0;
    }
    else {
        selStreamType.selectedIndex = 1;
    }
    if (iLogMode == 1) {
        selNetMode.selectedIndex = 0;

    }
    else {
        selNetMode.selectedIndex = 1;
    }

    selChannel.selectedIndex = iChannelNum;
    txHostIp.value = sServerHost;
    if (sProxyIP == "0.0.0.0") {
        sProxyIP = "";
    }
    txAgentIp.value = sProxyIP;
    txUserName.value = sCurUserName;
    txPassword.value = sCurPassword;
    txPort.value = iPort;

    UpdateStatusMenu(); //更新录像、音频、对讲、显示码流状态
    var obj;
    if (ChannelInfo[iCurObsIndex]['bAuto']) {
        obj = document.getElementById("imgAuto");
        obj.src = "auto_s.gif";
    }
    else {
        obj = document.getElementById("imgAuto");
        obj.src = "auto.gif";
    }

    checkAuthority();

    PlayBack_ISRemote();

}

function UpdateStatusMenu()  //更新录像、音频、对讲状态
{
    if (ChannelInfo[iCurObsIndex] != null) {
        if (ChannelInfo[iCurObsIndex]['bRec']) {
            lyRecorder.style.visibility = "hidden";
            lyRecorderMD.style.visibility = "visible";
        }
        else {
            lyRecorder.style.visibility = "visible";
            lyRecorderMD.style.visibility = "hidden";
        }
        if (ChannelInfo[iCurObsIndex]['bAudio']) {
            lyAudio.style.visibility = "hidden";
            lyAudioMD.style.visibility = "visible";
        }
        else {
            lyAudio.style.visibility = "visible";
            lyAudioMD.style.visibility = "hidden";
        }
        if (ChannelInfo[iCurObsIndex]['bTalk']) {
            lyTalk.style.visibility = "hidden";
            lyTalkMD.style.visibility = "visible";
        }
        else {
            lyTalk.style.visibility = "visible";
            lyTalkMD.style.visibility = "hidden";
        }
        if (ChannelInfo[iCurObsIndex]['bShowBitRate']) {
            /*chkbShowBitRate.checked = true;*/
        }
        else {
            /*chkbShowBitRate.checked = false;*/
        }
    }
}

//---------------------------------------------------------------------------
//	Functions For Device Control
//---------------------------------------------------------------------------
var bErrorAddr = false;

function Device_SetAddress() {
    var cDevice = dDeviceType.options[dDeviceType.selectedIndex].innerText;
    if ((cDevice == "PTZ_PELCO_D") || (cDevice == "DOME_PELCO_D")) {
        if ((parseInt(txDevAddress.value) < 0) || (parseInt(txDevAddress.value)
                                                   > 255)) {
            alert(JS_cMsg20);
            bErrorAddr = true;
            return false;
        }
    }
    else {
        if ((parseInt(txDevAddress.value) < 0) || (parseInt(txDevAddress.value)
                                                   > 255)) {
            alert(JS_cMsg21);
            bErrorAddr = true;
            return false;
        }
    }
    if (txDevAddress.value == "") {
        iDevAddress = 0;
    }
    else {
        iDevAddress = parseInt(txDevAddress.value) - 1;
        if (iDevAddress < 0) {
            iDevAddress = 0;
        }
    }

    bErrorAddr = false;
    return true;
}

function Device_Up(bOpen) {
    if (iDomeSpeed == 0) {
        return;
    }
    if (Device_SetAddress() == false) {
        return;
    }
    window.clearInterval(this.intervalID);
    var obj = document.getElementById("imgUp");
    obj.src = bOpen ? "ctl_up_s.gif" : "ctl_up.gif";
    if (bOpen) {
        bUp = true;
        if (chkEPTZ.checked == false) {
            var a = TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon,
                                          iChannelNum, iDevAddress, 1,
                                          iDomeSpeed, 0, 0,
                                          dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                          0);
            this.intervalID =
                window.setInterval(
                    "TiandyVideo.Commander(Dev_Control_Cmd,iCurLogon,iChannelNum,iDevAddress,1,iDomeSpeed,0,0,dDeviceType.options[dDeviceType.selectedIndex].innerText,0)",
                    1000);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 1, 0,
                                  iDomeSpeed, 1, 0, 0, 0);
            this.intervalID =
                window.setInterval(
                    "TiandyVideo.Commander(Dev_Device_Ctrl,iCurLogon,iChannelNum,1,0,iDomeSpeed,1,0,0,0)",
                    1000);
        }
    }
    else {

        bUp = false;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 2, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 9, 0,
                                  iDomeSpeed, 1, 0, 0, 0);
        }
    }
    if (ChannelInfo[iCurObsIndex]['bAuto']) {
        obj = document.getElementById("imgAuto");
        obj.src = "auto.gif";
        ChannelInfo[iCurObsIndex]['bAuto'] = false;
    }
}

function Device_Down(bOpen) {
    if (iDomeSpeed == 0) {
        return;
    }
    if (Device_SetAddress() == false) {
        return;
    }
    window.clearInterval(this.intervalID);
    var obj = document.getElementById("imgDown");
    obj.src = bOpen ? "ctl_down_s.gif" : "ctl_down.gif";
    if (bOpen) {
        bDown = true;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 3, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);
            this.intervalID =
                window.setInterval(
                    "TiandyVideo.Commander(Dev_Control_Cmd,iCurLogon,iChannelNum,iDevAddress,3,iDomeSpeed,0,0,dDeviceType.options[dDeviceType.selectedIndex].innerText,0)",
                    1000);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 2, 0,
                                  iDomeSpeed, 1, 0, 0, 0);
            this.intervalID =
                window.setInterval(
                    "TiandyVideo.Commander(Dev_Device_Ctrl,iCurLogon,iChannelNum,2,0,iDomeSpeed,1,0,0,0)",
                    1000);
        }
    }
    else {
        bDown = false;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 4, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 9, 0,
                                  iDomeSpeed, 1, 0, 0, 0);
        }

    }
    if (ChannelInfo[iCurObsIndex]['bAuto']) {
        obj = document.getElementById("imgAuto");
        obj.src = "auto.gif";
        ChannelInfo[iCurObsIndex]['bAuto'] = false;
    }
}

function Device_Left(bOpen) {
    if (iDomeSpeed == 0) {
        return;
    }
    if (Device_SetAddress() == false) {
        return;
    }
    window.clearInterval(this.intervalID);
    var obj = document.getElementById("imgLeft");
    obj.src = bOpen ? "ctl_left_s.gif" : "ctl_left.gif";
    if (bOpen) {
        bLeft = true;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 5, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);
            this.intervalID =
                window.setInterval(
                    "TiandyVideo.Commander(Dev_Control_Cmd,iCurLogon,iChannelNum,iDevAddress,5,iDomeSpeed,0,0,dDeviceType.options[dDeviceType.selectedIndex].innerText,0)",
                    1000);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 3,
                                  iDomeSpeed, 0, 1, 0, 0, 0);
            this.intervalID =
                window.setInterval(
                    "TiandyVideo.Commander(Dev_Device_Ctrl,iCurLogon,iChannelNum,3,iDomeSpeed,0,1,0,0,0)",
                    1000);
        }
    }
    else {
        bLeft = false;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 6, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 9, 0,
                                  iDomeSpeed, 1, 0, 0, 0);
        }
    }
    if (ChannelInfo[iCurObsIndex]['bAuto']) {
        obj = document.getElementById("imgAuto");
        obj.src = "auto.gif";
        ChannelInfo[iCurObsIndex]['bAuto'] = false;
    }
}

function Device_Right(bOpen) {
    if (iDomeSpeed == 0) {
        return;
    }
    if (Device_SetAddress() == false) {
        return;
    }
    window.clearInterval(this.intervalID);
    var obj = document.getElementById("imgRight");
    obj.src = bOpen ? "ctl_right_s.gif" : "ctl_right.gif";
    if (bOpen) {
        bRight = true;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 7, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);
            this.intervalID =
                window.setInterval(
                    "TiandyVideo.Commander(Dev_Control_Cmd,iCurLogon,iChannelNum,iDevAddress,7,iDomeSpeed,0,0,dDeviceType.options[dDeviceType.selectedIndex].innerText,0)",
                    1000);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 4,
                                  iDomeSpeed, 0, 1, 0, 0, 0);
            this.intervalID =
                window.setInterval(
                    "TiandyVideo.Commander(Dev_Device_Ctrl,iCurLogon,iChannelNum,4,iDomeSpeed,0,1,0,0,0)",
                    1000);
        }
    }
    else {
        bRight = false;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 8, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 9, 0,
                                  iDomeSpeed, 1, 0, 0, 0);
        }
    }
    if (ChannelInfo[iCurObsIndex]['bAuto']) {
        obj = document.getElementById("imgAuto");
        obj.src = "auto.gif";
        ChannelInfo[iCurObsIndex]['bAuto'] = false;
    }
}

function Device_UpLeft(bOpen) {
    if (iDomeSpeed == 0) {
        return;
    }
    if (Device_SetAddress() == false) {
        return;
    }
    window.clearInterval(this.intervalID);
    var obj = document.getElementById("imgUpLeft");
    obj.src = bOpen ? "ctl_left_up_s.gif" : "ctl_left_up.gif";
    if (bOpen) {
        bUpLeft = true;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 9, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);
            this.intervalID =
                window.setInterval(
                    "TiandyVideo.Commander(Dev_Control_Cmd,iCurLogon,iChannelNum,iDevAddress,9,iDomeSpeed,0,0,dDeviceType.options[dDeviceType.selectedIndex].innerText,0)",
                    1000);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 6,
                                  iDomeSpeed, iDomeSpeed, 1, 0, 0, 0);
            this.intervalID =
                window.setInterval(
                    "TiandyVideo.Commander(Dev_Device_Ctrl,iCurLogon,iChannelNum,6,iDomeSpeed,iDomeSpeed,1,0,0,0)",
                    1000);
        }
    }
    else {
        bUpLeft = false;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 10, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 9, 0,
                                  iDomeSpeed, 1, 0, 0, 0);
        }
    }
    if (ChannelInfo[iCurObsIndex]['bAuto']) {
        obj = document.getElementById("imgAuto");
        obj.src = "auto.gif";
        ChannelInfo[iCurObsIndex]['bAuto'] = false;
    }
}

function Device_UpRight(bOpen) {
    if (iDomeSpeed == 0) {
        return;
    }
    if (Device_SetAddress() == false) {
        return;
    }
    window.clearInterval(this.intervalID);
    var obj = document.getElementById("imgUpRight");
    obj.src = bOpen ? "ctl_right_up_s.gif" : "ctl_right_up.gif";
    if (bOpen) {
        bUpRight = true;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 11, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);
            this.intervalID =
                window.setInterval(
                    "TiandyVideo.Commander(Dev_Control_Cmd,iCurLogon,iChannelNum,iDevAddress,11,iDomeSpeed,0,0,dDeviceType.options[dDeviceType.selectedIndex].innerText,0)",
                    1000);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 5,
                                  iDomeSpeed, iDomeSpeed, 1, 0, 0, 0);
            this.intervalID =
                window.setInterval(
                    "TiandyVideo.Commander(Dev_Device_Ctrl,iCurLogon,iChannelNum,5,iDomeSpeed,iDomeSpeed,1,0,0,0)",
                    1000);
        }
    }
    else {
        bUpRight = false;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 12, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 9, 0,
                                  iDomeSpeed, 1, 0, 0, 0);
        }
    }
    if (ChannelInfo[iCurObsIndex]['bAuto']) {
        obj = document.getElementById("imgAuto");
        obj.src = "auto.gif";
        ChannelInfo[iCurObsIndex]['bAuto'] = false;
    }
}

function Device_DownLeft(bOpen) {
    if (iDomeSpeed == 0) {
        return;
    }
    if (Device_SetAddress() == false) {
        return;
    }
    window.clearInterval(this.intervalID);
    var obj = document.getElementById("imgDownLeft");
    obj.src = bOpen ? "ctl_left_down_s.gif" : "ctl_left_down.gif";
    if (bOpen) {
        bDownLeft = true;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 13, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);
            this.intervalID =
                window.setInterval(
                    "TiandyVideo.Commander(Dev_Control_Cmd,iCurLogon,iChannelNum,iDevAddress,13,iDomeSpeed,0,0,dDeviceType.options[dDeviceType.selectedIndex].innerText,0)",
                    1000);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 8,
                                  iDomeSpeed, iDomeSpeed, 1, 0, 0, 0);
            this.intervalID =
                window.setInterval(
                    "TiandyVideo.Commander(Dev_Device_Ctrl,iCurLogon,iChannelNum,8,iDomeSpeed,iDomeSpeed,1,0,0,0)",
                    1000);
        }
    }
    else {
        bDownLeft = false;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 14, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 9, 0,
                                  iDomeSpeed, 1, 0, 0, 0);
        }
    }
    if (ChannelInfo[iCurObsIndex]['bAuto']) {
        obj = document.getElementById("imgAuto");
        obj.src = "auto.gif";
        ChannelInfo[iCurObsIndex]['bAuto'] = false;
    }
}

function Device_DownRight(bOpen) {
    if (iDomeSpeed == 0) {
        return;
    }
    if (Device_SetAddress() == false) {
        return;
    }
    window.clearInterval(this.intervalID);
    var obj = document.getElementById("imgDownRight");
    obj.src = bOpen ? "ctl_right_down_s.gif" : "ctl_right_down.gif";
    if (bOpen) {
        bDownRight = true;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 15, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);
            this.intervalID =
                window.setInterval(
                    "TiandyVideo.Commander(Dev_Control_Cmd,iCurLogon,iChannelNum,iDevAddress,15,iDomeSpeed,0,0,dDeviceType.options[dDeviceType.selectedIndex].innerText,0)",
                    1000);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 7,
                                  iDomeSpeed, iDomeSpeed, 1, 0, 0, 0);
            this.intervalID =
                window.setInterval(
                    "TiandyVideo.Commander(Dev_Device_Ctrl,iCurLogon,iChannelNum,7,iDomeSpeed,iDomeSpeed,1,0,0,0)",
                    1000);
        }
    }
    else {
        bDownRight = false;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 16, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 9, 0,
                                  iDomeSpeed, 1, 0, 0, 0);
        }
    }

    if (ChannelInfo[iCurObsIndex]['bAuto']) {
        obj = document.getElementById("imgAuto");
        obj.src = "auto.gif";
        ChannelInfo[iCurObsIndex]['bAuto'] = false;
    }
}

function Device_Auto() {
    if (Device_SetAddress() == false) {
        return;
    }
    var obj = document.getElementById("imgAuto");
    if (ChannelInfo[iCurObsIndex]['bAuto']) {
        TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                              iDevAddress, 22, iDomeSpeed, 0, 0,
                              dDeviceType.options[dDeviceType.selectedIndex].innerText,
                              0);
        obj.src = "auto.gif";
        ChannelInfo[iCurObsIndex]['bAuto'] = false;
    }
    else {
        TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                              iDevAddress, 21, iDomeSpeed, 0, 0,
                              dDeviceType.options[dDeviceType.selectedIndex].innerText,
                              0);
        obj.src = "auto_s.gif";
        ChannelInfo[iCurObsIndex]['bAuto'] = true;
    }
}

function Device_ZoomBig(bOpen) {
    if (Device_SetAddress() == false) {
        return;
    }
    bZoomBig.style.backgroundColor = bOpen ? "#F5F9F5" : BACKCOLOR;
    if (bOpen) {
        IsZoomBig = true;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 31, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 10,
                                  iDomeSpeed, iDomeSpeed, 1, 0, 0, 0);
        }
    }
    else {
        IsZoomBig = false;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 32, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);

        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 9,
                                  iDomeSpeed, iDomeSpeed, 1, 0, 0, 0);
        }
    }
    if (ChannelInfo[iCurObsIndex]['bAuto']) {
        obj = document.getElementById("imgAuto");
        obj.src = "auto.gif";
        ChannelInfo[iCurObsIndex]['bAuto'] = false;
    }
}

function Device_ZoomSmall(bOpen) {
    if (Device_SetAddress() == false) {
        return;
    }
    bZoomSmall.style.backgroundColor = bOpen ? "#F5F9F5" : BACKCOLOR;
    if (bOpen) {
        IsZoomSmall = true;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 33, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 11,
                                  iDomeSpeed, iDomeSpeed, 1, 0, 0, 0);
        }
    }
    else {
        IsZoomSmall = false;
        if (chkEPTZ.checked == false) {
            TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                  iDevAddress, 34, iDomeSpeed, 0, 0,
                                  dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                  0);
        } else {
            TiandyVideo.Commander(Dev_Device_Ctrl, iCurLogon, iChannelNum, 9,
                                  iDomeSpeed, iDomeSpeed, 1, 0, 0, 0);
        }
    }
    if (ChannelInfo[iCurObsIndex]['bAuto']) {
        obj = document.getElementById("imgAuto");
        obj.src = "auto.gif";
        ChannelInfo[iCurObsIndex]['bAuto'] = false;
    }
}

function Device_FocusFar(bOpen) {
    if (Device_SetAddress() == false) {
        return;
    }
    bFocusFar.style.backgroundColor = bOpen ? "#F5F9F5" : BACKCOLOR;
    if (bOpen) {
        IsFocusFar = true;
        TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                              iDevAddress, 35, iDomeSpeed, 0, 0,
                              dDeviceType.options[dDeviceType.selectedIndex].innerText,
                              0);
    }
    else {
        IsFocusFar = false;
        TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                              iDevAddress, 36, iDomeSpeed, 0, 0,
                              dDeviceType.options[dDeviceType.selectedIndex].innerText,
                              0);
    }
    if (ChannelInfo[iCurObsIndex]['bAuto']) {
        obj = document.getElementById("imgAuto");
        obj.src = "auto.gif";
        ChannelInfo[iCurObsIndex]['bAuto'] = false;
    }
}

function Device_FocusNear(bOpen) {
    if (Device_SetAddress() == false) {
        return;
    }
    bFocusNear.style.backgroundColor = bOpen ? "#F5F9F5" : BACKCOLOR;
    if (bOpen) {
        IsFocusNear = true;
        TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                              iDevAddress, 37, iDomeSpeed, 0, 0,
                              dDeviceType.options[dDeviceType.selectedIndex].innerText,
                              0);
    }
    else {
        IsFocusNear = false;
        TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                              iDevAddress, 38, iDomeSpeed, 0, 0,
                              dDeviceType.options[dDeviceType.selectedIndex].innerText,
                              0);
    }
    if (ChannelInfo[iCurObsIndex]['bAuto']) {
        obj = document.getElementById("imgAuto");
        obj.src = "auto.gif";
        ChannelInfo[iCurObsIndex]['bAuto'] = false;
    }
}

function Device_IrisOpen(bOpen) {
    if (Device_SetAddress() == false) {
        return;
    }
    bIrisOpen.style.backgroundColor = bOpen ? "#F5F9F5" : BACKCOLOR;
    if (bOpen) {
        IsIrisOpen = true;
        TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                              iDevAddress, 39, iDomeSpeed, 0, 0,
                              dDeviceType.options[dDeviceType.selectedIndex].innerText,
                              0);
    }
    else {
        IsIrisOpen = false;
        TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                              iDevAddress, 40, iDomeSpeed, 0, 0,
                              dDeviceType.options[dDeviceType.selectedIndex].innerText,
                              0);
    }
    if (ChannelInfo[iCurObsIndex]['bAuto']) {
        obj = document.getElementById("imgAuto");
        obj.src = "auto.gif";
        ChannelInfo[iCurObsIndex]['bAuto'] = false;
    }
}

function Device_IrisClose(bOpen) {
    if (Device_SetAddress() == false) {
        return;
    }
    bIrisClose.style.backgroundColor = bOpen ? "#F5F9F5" : BACKCOLOR;
    if (bOpen) {
        IsIrisClose = true;
        TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                              iDevAddress, 41, iDomeSpeed, 0, 0,
                              dDeviceType.options[dDeviceType.selectedIndex].innerText,
                              0);
    }
    else {
        IsIrisClose = false;
        TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                              iDevAddress, 42, iDomeSpeed, 0, 0,
                              dDeviceType.options[dDeviceType.selectedIndex].innerText,
                              0);
    }
    if (ChannelInfo[iCurObsIndex]['bAuto']) {
        obj = document.getElementById("imgAuto");
        obj.src = "auto.gif";
        ChannelInfo[iCurObsIndex]['bAuto'] = false;
    }
}

function Device_Light() {
    if (Device_SetAddress() == false) {
        return;
    }
    if (bDevLight) {
        TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                              iDevAddress, 44, iDomeSpeed, 0, 0,
                              dDeviceType.options[dDeviceType.selectedIndex].innerText,
                              0);
        bLight.style.backgroundColor = BACKCOLOR;
        bDevLight = false;
    }
    else {
        TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                              iDevAddress, 43, iDomeSpeed, 0, 0,
                              dDeviceType.options[dDeviceType.selectedIndex].innerText,
                              0);
        bLight.style.backgroundColor = "#F5F9F5";
        bDevLight = true;
    }
}

function Device_Power() {
    if (Device_SetAddress() == false) {
        return;
    }
    if (bDevPower) {
        TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                              iDevAddress, 46, iDomeSpeed, 0, 0,
                              dDeviceType.options[dDeviceType.selectedIndex].innerText,
                              0);
        bPower.style.backgroundColor = BACKCOLOR;
        bDevPower = false;
    }
    else {
        TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                              iDevAddress, 45, iDomeSpeed, 0, 0,
                              dDeviceType.options[dDeviceType.selectedIndex].innerText,
                              0);
        bPower.style.backgroundColor = "#F5F9F5";
        bDevPower = true;
    }
}

function Device_Rain(bOpen) {
    if (Device_SetAddress() == false) {
        return;
    }
    bRain.style.backgroundColor = bOpen ? "#F5F9F5" : BACKCOLOR;

    if (bOpen) {
        IsRain = true;
        TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                              iDevAddress, 47, iDomeSpeed, 0, 0,
                              dDeviceType.options[dDeviceType.selectedIndex].innerText,
                              0);
    }
    else {
        IsRain = false;
        TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                              iDevAddress, 48, iDomeSpeed, 0, 0,
                              dDeviceType.options[dDeviceType.selectedIndex].innerText,
                              0);
    }
}

function Device_SetView() {
    if (Device_SetAddress() == false) {
        return;
    }
    var iPresent = parseInt(txPresent.value);
    if ((iPresent <= 255) && (iPresent >= 1)) {
        var a = TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                      iDevAddress, 63, 0, iPresent, 0,
                                      dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                      0);
    }
    else {
        alert(JS_cMsg22);
        txPresent.value = "";
        return;
    }
    if (ChannelInfo[iCurObsIndex]['bAuto']) {
        obj = document.getElementById("imgAuto");
        obj.src = "auto.gif";
        ChannelInfo[iCurObsIndex]['bAuto'] = false;
    }
}

function Device_CallView() {
    if (Device_SetAddress() == false) {
        return;
    }
    var iPresent = parseInt(txPresent.value);
    if (iPresent > 255 || iPresent < 1) {
        alert(JS_cMsg22);
        txPresent.value = "";
        return;
    }
    else {
        var a = TiandyVideo.Commander(Dev_Control_Cmd, iCurLogon, iChannelNum,
                                      iDevAddress, 62, 0, iPresent, 0,
                                      dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                      0);
    }
    if (ChannelInfo[iCurObsIndex]['bAuto']) {
        obj = document.getElementById("imgAuto");
        obj.src = "auto.gif";
        ChannelInfo[iCurObsIndex]['bAuto'] = false;
    }
}

//Set Device Type
function DeviceCtrl_DeviceType_Change() {
    if (dDeviceType.selectedIndex != 0) {
        var a = TiandyVideo.Commander(Dev_Type_Set, iCurLogon, iChannelNum, 2,
                                      dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                      0, 0, 0, 0, 0);
        setTimeout("Device_UpdateStatus()", 500);
    }
}

function DeviceCtrl_DeviceType_Change_3520() {
    if (dDeviceType.selectedIndex != 0) {
        var b = TiandyVideo.Commander(Dev_SetComFormat_Set, iCurLogon, 2,
                                      dDeviceType.options[dDeviceType.selectedIndex].innerText,
                                      selComFormat.options[selComFormat.selectedIndex].innerText,
                                      2, iChannelNum, 0, 0, 0);
        setTimeout("Device_UpdateStatus()", 500);
    }
}

function Device_UpdateStatus() {
    var vResult = TiandyVideo.Commander(Dev_SetComFormat_Get, iCurLogon, 2, 0,
                                        0, 0, 0, 0, 0, 0).split('\n');
    if (vResult.length > 1) {
        var arrCommFormat = vResult[1].split(splitCode);
        for (var i = 0; i < selComFormat.options.length; i++) {
            if (selComFormat.options[i].innerText == arrCommFormat[0]) {
                selComFormat.selectedIndex = i;
            }
        }
    }

    var arrDevType = TiandyVideo.Commander(Dev_Type_Get, iCurLogon, iChannelNum,
                                           2, 0, 0, 0, 0, 0, 0)
        .split('\n')[1].split(splitCode);
    if (dDeviceType.options[dDeviceType.selectedIndex].innerText
        == "DOME_TIANDY") {
        if (lyDeviceCtrl.style.visibility == "visible") {
            ly3D.style.visibility = "visible";
        }
    }
    else {
        ly3D.style.visibility = "hidden";
    }
}

function Device_MouseOut() {
    if (bErrorAddr) {
        return;
    }

    if (bUp) {
        Device_Up(false);
    }
    if (bDown) {
        Device_Down(false);
    }
    if (bLeft) {
        Device_Left(false);
    }
    if (bRight) {
        Device_Right(false);
    }
    if (bUpLeft) {
        Device_UpLeft(false);
    }
    if (bUpRight) {
        Device_UpRight(false);
    }
    if (bDownLeft) {
        Device_DownLeft(false);
    }
    if (bDownRight) {
        Device_DownRight(false);
    }

    if (IsZoomBig) {
        Device_ZoomBig(false);
    }
    if (IsZoomSmall) {
        Device_ZoomSmall(false);
    }
    if (IsFocusFar) {
        Device_FocusFar(false);
    }
    if (IsFocusNear) {
        Device_FocusNear(false);
    }
    if (IsIrisOpen) {
        Device_IrisOpen(false);
    }
    if (IsIrisClose) {
        Device_IrisClose(false);
    }
    if (IsRain) {
        Device_Rain(false);
    }
}

function Device_Set3DEnabled() {
    var arrParam = chkb3D.checked ? 1 : 0;
    if (Device_SetAddress() == false) {
        iDevAddress = 0;
    }
    TiandyVideo.Commander(Obs_3dLocateEnable_Set, iCurObs, parseInt(arrParam),
                          iDevAddress, parseInt(iDomeSpeed), 0, 0, 0, 0, 0);
}

function Device_Get3DEnabled() {
    var arrParam = TiandyVideo.Commander(Obs_3dLocateEnable_Get, iCurObs, 0, 0,
                                         0, 0, 0, 0, 0, 0).split('\n');
    if (arrParam[0] == '0') {
        var b = arrParam[1].split(splitCode);
        chkb3D.checked = (b[0] == 1) ? true : false;
        if (chkb3D.checked == true) {
            txDevAddress.value = parseInt(b[1]) + 1;
            Device_Set3DEnabled();
        }
    }
}

//---------------------------------------------------------------------------
//	Functions For VideoParam
//---------------------------------------------------------------------------

//---------------------------------------------------------------------------
//	Alarm
//---------------------------------------------------------------------------

//---------------------------------------------------------------------------
//	Functions For Main Menu
//---------------------------------------------------------------------------
function DHT_CloseAll() {
    lyLogon.style.visibility = "hidden";
    lyDeviceCtrl.style.visibility = "hidden";
    lyPlayBack1.style.visibility = "hidden";
    ly3D.style.visibility = "hidden";
    lyEPTZ.style.visibility = "hidden";
}

function DHT_DeviceCtrl() {
    var iDevType;
    var iRet;
    if (GetLogonStatus()) {
        iCurrentTAB = TAB_DEVICE;
        bSet1.style.backgroundColor = BACKCOLOR;
        bSet2.style.backgroundColor = "#F5F9F5";

        DHT_CloseAll();
        lyDeviceCtrl.style.visibility = "visible";
        if (dDeviceType.options[dDeviceType.selectedIndex].innerText
            == "DOME_TIANDY") {
            ly3D.style.visibility = "visible";
        }
        else {
            ly3D.style.visibility = "hidden";
        }
    }
    else {
        ShowWarningMsg();
    }
}

function DHT_Logon() {
    iCurrentTAB = TAB_LOGON;
    bSet1.style.backgroundColor = "#F5F9F5";
    bSet2.style.backgroundColor = BACKCOLOR;

    DHT_CloseAll();
    lyLogon.style.visibility = "visible";
}

function ShowWarningMsg() {
    alert(JS_cMsg5);
}

//---------------------------------------------------------------------------
//	Functions for Shortcut
//---------------------------------------------------------------------------
function DHT_1Pic_Click() {
    ly1pic.style.visibility = "hidden";
    ly1picMD.style.visibility = "visible";
    ly4pic.style.visibility = "visible";
    ly4picMD.style.visibility = "hidden";
}

function DHT_4Pic_Click() {
    ly1pic.style.visibility = "visible";
    ly1picMD.style.visibility = "hidden";
    ly4pic.style.visibility = "hidden";
    ly4picMD.style.visibility = "visible";
}

function DHT_FullSrc_Click() {
    ChangeMonitorNum(1);
    TiandyVideo.Commander(Obs_FullScreen_Cmd, iCurObs, true, 0, 0, 0, 0, 0, 0,
                          0);
}

function DHT_RecStart() {
    if (GetLogonStatus()) {
        var iResult = TiandyVideo.Commander(Obs_BeginRec_Cmd, iCurObs, 0, 30, 0,
                                            0, 0, 0, 0, 0).split('\n')[0];
        if (iResult == 0) {
            lyRecorder.style.visibility = "hidden";
            lyRecorderMD.style.visibility = "visible";
            ChannelInfo[iCurObsIndex]['bRec'] = true;
        }
        else {
            iError = parseInt(iResult) + 0x0000000100000000;
            if (iError == 0xE0000003) {
                TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cMsg13,
                                      3000, 0, 0, 0, 0, 0);
            }
        }
    }
}

function DHT_RecStop() {
    if (GetLogonStatus()) {
        lyRecorder.style.visibility = "visible";
        lyRecorderMD.style.visibility = "hidden";
        TiandyVideo.Commander(Obs_EndRec_Cmd, iCurObs, 0, 0, 0, 0, 0, 0, 0, 0);
        ChannelInfo[iCurObsIndex]['bRec'] = false;
    }
}

function DHT_AudioStart() {
    if (GetLogonStatus()) {
        lyAudio.style.visibility = "hidden";
        lyAudioMD.style.visibility = "visible";
        TiandyVideo.Commander(Obs_SpeakerEnable_Set, iCurObs, true, 60000, 0, 0,
                              0, 0, 0, 0);
        ChannelInfo[iCurObsIndex]['bAudio'] = true;
    }
}

function DHT_AudioStop() {
    if (GetLogonStatus()) {
        lyAudio.style.visibility = "visible";
        lyAudioMD.style.visibility = "hidden";
        TiandyVideo.Commander(Obs_SpeakerEnable_Set, iCurObs, 0, 0, 0, 0, 0, 0,
                              0, 0);
        ChannelInfo[iCurObsIndex]['bAudio'] = false;
    }
}

function Menu_TalkStart() {
    //if(IsUpState == 1){return;}//正在升级不能对讲

    if (iTalkStatus == true)  //停止其他设备的对讲
    {
        TiandyVideo.Commander(Obs_MicEnable_Set, iTalklogonID, 0, 0, 0, 0, 0, 0,
                              0, 0);
    }
    if (GetLogonStatus()) {
        TiandyVideo.Commander(Obs_MicEnable_Set, iCurLogon, true, 0, 0, 0, 0, 0,
                              0, 0);
    }
}

function Menu_TalkStop() {
    if (GetLogonStatus() && iCurLogon == iTalklogonID) {
        TiandyVideo.Commander(Obs_MicEnable_Set, iTalklogonID, 0, 0, 0, 0, 0, 0,
                              0, 0);
        for (var i = 0; i < 25; i++)  //清除所有对讲标识
        {
            ChannelInfo[i]['bTalk'] = false;
        }
        var ObsIDlist = TiandyVideo.Commander(Entry_ObsLst_Get, iTalklogonID, 0,
                                              0, 0, 0, 0, 0, 0, 0).split("\n");
        iTalkStatus = false;
        iTalklogonID = -1;
        lyTalk.style.visibility = "visible";
        lyTalkMD.style.visibility = "hidden";
        for (i = 1; i < ObsIDlist.length; i++)     //重置对应logonID的obsID的对讲状态
        {
            for (var j = 0; j < 25; j++) {
                if (ChannelInfo[j]['iCurObsID'] == ObsIDlist[i]) {
                    ChannelInfo[j]['bTalk'] = iTalkStatus;
                    break;
                }
            }
        }
    }
}

var iTalklogonID = -1;
var iTalkStatus = false;

function OnTalk(iLogonID, iStatus) {
    if (iStatus != 0 && iStatus != 3) {
        alert(JS_cMsg77);
        iTalkStatus = false;
        iTalklogonID = -1;
        lyTalk.style.visibility = "visible";
        lyTalkMD.style.visibility = "hidden";
    }
    for (var i = 0; i < 25; i++)  //清除所有对讲标识
    {
        ChannelInfo[i]['bTalk'] = false;
    }
    var ObsIDlist = TiandyVideo.Commander(Entry_ObsLst_Get, iLogonID, 0, 0, 0,
                                          0, 0, 0, 0, 0).split("\n");
    //var iTalkStatus = false;
    if (iStatus == 0) {
        iTalkStatus = true;
        iTalklogonID = iLogonID;
        lyTalk.style.visibility = "hidden";
        lyTalkMD.style.visibility = "visible";
    }
    for (i = 1; i < ObsIDlist.length; i++)     //重置对应logonID的obsID的对讲状态
    {
        for (var j = 0; j < 25; j++) {
            if (ChannelInfo[j]['iCurObsID'] == ObsIDlist[i]) {
                ChannelInfo[j]['bTalk'] = iTalkStatus;
                break;
            }
        }
    }
}

// 抓拍
function Advanced_CapBMP() {
    if (GetLogonStatus()) {
        var now = new Date();
        var YY = now.getFullYear();
        var MM = now.getMonth() + 1;
        if (MM < 10) {
            MM = "0" + MM;
        }
        var DD = now.getDate();
        if (DD < 10) {
            DD = "0" + DD;
        }
        var hh = now.getHours();
        if (hh < 10) {
            hh = "0" + hh;
        }
        var ff = now.getMinutes();
        if (ff < 10) {
            ff = "0" + ff;
        }
        var ss = now.getSeconds();
        if (ss < 10) {
            ss = "0" + ss;
        }
        var RecDate = YY.toString() + MM + DD + hh + ff + ss;
        var chn = iChannelNum + 1;
        var sFilePath = sServerHost + "-" + chn + "-" + RecDate + "-" + BMPNUM
                        + ".bmp";
        var a = TiandyVideo.Commander(Obs_Capture_Cmd, iCurObs, sFilePath, 50,
                                      0, 0, 0, 0, 0, 0);
        BMPNUM++;
    }
    else {
        alert(JS_cMsg6);
    }
}

var IsCap = 1;

function Menu_Snap(bOpen) {
    if (bOpen) {
        try {
            if (GetLogonStatus()) {
                if (IsCap == 1) {
                    Advanced_CapBMP();

                    IsCap = 0;
                    setTimeout("Menu_SnapOK()", 800);
                }
                lySnap.style.visibility = "hidden";
                lySnapMD.style.visibility = "visible";
            }
        }
        catch (e) {
            lySnap.style.visibility = "hidden";
            lySnapMD.style.visibility = "visible";
        }
    }
    else {
        lySnap.style.visibility = "visible";
        lySnapMD.style.visibility = "hidden";
    }
}

function Menu_SnapOK() {
    IsCap = 1;
    lySnap.style.visibility = "visible";
    lySnapMD.style.visibility = "hidden";
}

function Menu_PlayBack() {
    DHT_CloseAll();
    Menu_Close_All(true);
    lyMenuParent.style.left = -500;
    lyMenuParent.style.top = -500;
    lyPlayBack1.style.visibility = "visible";
    Playback_QFtime_init();
    ChangeMonitorNum(17);
}

function Menu_Back() {
    lyMenuParent.style.left = 20;
    lyMenuParent.style.top = 549;
    setTimeout("Menu_BackMain()", 10);
}

function Menu_BackMain() {
    PlayBack_Stop();
    PB_StopDown();
    Menu_Close_All(false);
    ChangeMonitorNum(iCurMonitorCount);
    DHT_Logon();
    checkAuthority();
}

function Menu_Close_All(open) {
    ly1pic.disabled = open;
    ly4pic.disabled = open;
    ly9pic.disabled = open;
    ly16pic.disabled = open;
    lyFullSrc.disabled = open;
    lyAudio.disabled = open;
    lyAudioMD.disabled = open;
    lyRecorder.disabled = open;
    lyRecorderMD.disabled = open;
    lyTalk.disabled = open;
    lyTalkMD.disabled = open;
    lySnap.disabled = open;
    lySnapMD.disabled = open;
    bSet1.disabled = open;
    bSet2.disabled = open;
}

//---------------------------------------------------------------------------
//	Functions for Network
//---------------------------------------------------------------------------

//---------------------------------------------------------------------------
//	Functions for advanced options
//---------------------------------------------------------------------------
//---------------------------------------------------------------------------
//	Activex Event
//---------------------------------------------------------------------------
/*单击Panel事件：获取通道参数*/
function DHT_Ch_Change(_ulObsID) {
    iCurObs = _ulObsID;
    var i;
    if (ChannelInfo[16]['iCurObsID'] != iCurObs) {
        for (i = 0; i < 25; i++) {
            if (ChannelInfo[i]['iCurObsID'] == iCurObs) {
                iCurObsIndex = i;
                break;
            }
        }
    }
    for (i = 0; i < 16; i++) {
        if (ChannelInfo[i]['iCurObsID'] != iCurObs) {
            TiandyVideo.Commander(Obs_SpeakerEnable_Set,
                                  ChannelInfo[i]['iCurObsID'], 0, 0, 0, 0, 0, 0,
                                  0, 0);
        }
    }

    if (ChannelInfo[iCurObsIndex]['bAudio']) {
        DHT_AudioStart();
    }
    else {
        TiandyVideo.Commander(Obs_SpeakerEnable_Set, iCurObs, 0, 0, 0, 0, 0, 0,
                              0, 0);
    }

    if (lyPlayBack1.style.visibility == "visible") {
        Menu_Close_All(true);
    }
    if (!GetLogonStatus() || ChannelInfo[16]['iCurObsID'] == iCurObs) {
        txAgentIp.value = "";
        return;
    }

    var arrResult = TiandyVideo.Commander(Obs_Prop_Get, iCurObs, 0, 0, 0, 0, 0,
                                          0, 0, 0).split('\n')[1].split(
        splitCode);
    iRet = parseInt(arrResult[0]);
    if (iRet == -1) {
        return;
    }
    iCurLogon = iRet;
    for (i = 0; i < selNVS.length; i++) {
        var strUnion = selNVS.options[i].text;
        var arrUnion = strUnion.split(splitCode);
        if (arrUnion[5] == iCurLogon) {
            selNVS.selectedIndex = i;
            selNVSIP.selectedIndex = i;
            break;
        }
    }
    aXActivate();
    loadInfoFromaX(_ulObsID);
    initHtmlInfo();
}

/*双击Panel事件：获取屏幕大小*/
function DHT_Panel_DblClick() {
    var iFullSrc = TiandyVideo.iFullSize;
    //四画面
    if (iFullSrc == 0) {
        ly1pic.style.visibility = "visible";
        ly1picMD.style.visibility = "hidden";
        ly4pic.style.visibility = "hidden";
        ly4picMD.style.visibility = "visible";
    }
    //一画面
    if (iFullSrc == 1) {
        ly1pic.style.visibility = "hidden";
        ly1picMD.style.visibility = "visible";
        ly4pic.style.visibility = "visible";
        ly4picMD.style.visibility = "hidden";
    }
    //全屏
    if (iFullSrc == 2) {
        ly1pic.style.visibility = "visible";
        ly1picMD.style.visibility = "hidden";
        ly4pic.style.visibility = "hidden";
        ly4picMD.style.visibility = "visible";
    }
}

function DHT_RecError(iObsID) {
    lyRecorder.style.visibility = "visible";
    lyRecorderMD.style.visibility = "hidden";
    for (var i = 0; i < 16; i++) {
        ChannelInfo[i]['bRec'] = false;
        TiandyVideo.Commander(Obs_EndRec_Cmd, ChannelInfo[i]['iCurObsID'], 0, 0,
                              0, 0, 0, 0, 0, 0);
    }
    TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cMsg13, 3000, 0, 0,
                          0, 0, 0);
}

//---------------------------------------------------------------------------
//	Mouse Event
//---------------------------------------------------------------------------
function DHT_Mouseover(Obj) {
    if ((Obj == bLogOn) || (Obj == bLogOff)) {
        Obj.style.backgroundColor = "#FFFFFF";
    } else {
        Obj.style.backgroundColor = "#FFFFFF";//"#B0D5AC";
    }
}

function DHT_Mouseout(Obj) {
    if ((Obj == bLogOn) || (Obj == bLogOff)) {
        Obj.style.backgroundColor = BACKCOLOR;
    }
    else {
        Obj.style.backgroundColor = BACKCOLOR;
    }
}

//---------------------------------------------------------------------------
var Ismousedown = false;

function trackbar_mousedown(obj) {
    Ismousedown = true;
    xOld = xCoord;
    yOld = yCoord;
    trackbarobj = obj
}

function trackbar_mouseup(obj) {
    Ismousedown = false;
}

function trackbar_mousemove(obj) {
}

function checkwhere(e) {
    if (document.layers) {
        xCoord = e.x;
        yCoord = e.y;
    }
    else if (document.all) {
        xCoord = event.clientX;
        yCoord = event.clientY;
    }
    else if (document.getElementById) {
        xCoord = e.clientX;
        yCoord = e.clientY;
    }
    var xMove = xCoord - xOld;
    var yMove = yCoord - yOld;
    if ((Ismousedown == true) && (xMove != 0)) {
        var xStart = Math.floor((trackbarobj.style.left).substring(
            0, (trackbarobj.style.left).length - 2));
        var xPos = xStart + xMove;
        if (xPos < 0) {
            xPos = 0;
        }
        if (xPos > 100) {
            xPos = 100;
        }

        trackbarobj.style.left = xPos + "px";
        if (trackbarobj == lySpeedBut) {
            iDomeSpeed = xPos;
            labSpeedValue.innerText = xPos;
        }

        xOld = xCoord;
        yOld = yCoord;
    }
}

function doc_mouseup() {
    Ismousedown = false;
}

document.onmousemove = checkwhere;
document.onmouseup = doc_mouseup;
if (document.captureEvents) {
    document.captureEvents(Event.MOUSEMOVE);
}

//---------------------------------------------------------------------------
//	Common Functions
//---------------------------------------------------------------------------
function readCookie(name) {
    var cookieValue = "";
    var search = name + "=";
    if (document.cookie.length > 0) {
        offset = document.cookie.indexOf(search);
        if (offset != -1) {
            offset += search.length;
            end = document.cookie.indexOf(";", offset);
            if (end == -1) {
                end = document.cookie.length;
            }
            cookieValue = unescape(document.cookie.substring(offset, end))
        }
    }
    return cookieValue;
}

function writeCookie(name, value, hours) {
    var expire = "";
    if (hours != null) {
        expire = new Date((new Date()).getTime() + hours * 3600000);
        expire = "; expires=" + expire.toGMTString();
    }
    document.cookie = name + "=" + escape(value) + expire;
}

function CheckNumber(args) {
    var iLength = args.length;
    var i;
    var iASC;
    for (i = 0; i < iLength; i++) {
        iASC = args.charAt(i).charCodeAt();
        if ((iASC > 57 || iASC < 48) && iASC != 46) {
            return false;
            break;
        }
    }
    return true;
}

function GetArgsFromHref(sHref, sArgName) {
    var args = sHref.split("?");
    var retval = "";

    //null param
    if (args[0] == sHref) {
        return retval;
    }
    var str = args[1];
    args = str.split("&");
    for (var i = 0; i < args.length; i++) {
        str = args[i];
        var arg = str.split("=");
        if (arg.length <= 1) {
            continue;
        }
        if (arg[0] == sArgName) {
            retval = arg[1];
        }
    }
    return retval;
}

function CheckValueWith(Num, NumStar, NumEnd, sMsg) {

    if (Num >= NumStar && Num <= NumEnd) {
        return true;
    }
    else {
        alert(sMsg + JS_cMsg43 + NumStar + " - " + NumEnd + JS_cMsg44);
        return false;
    }
}

//时间比较
function IsTimeJudge(vTimer1, vTimer2) {
    if (vTimer1 < vTimer2) {
        return true;
    } else {
        return false;
    }
}

//求字符串的字符长度
function LenTextEx(strmyform) {
    var sum = 0;
    var str = strmyform;
    if (str.length > 300) {
        return 300;
    }
    for (i = 0; i < str.length; i++) {
        if ((str.charCodeAt(i) >= 0) && (str.charCodeAt(i) <= 127)) {
            sum = sum + 1;
        } else {
            sum = sum + 2;
        }
    }
    return sum;
}

//验证后缀是否有效
function GetHouZhui(sTr, sHz) {
    if (sTr == "") {
        return false;
    }
    var Temp;

    var Temp1 = sTr.match(/([^\?\\]+)$/);
    if (Temp1[1] != undefined) {
        Temp = Temp1[1];
    }
    else {
        return false;
    }
    var args = Temp.split(".");
    if (args[args.length - 1] == sHz) {
        return true;
    }
    else {
        return false;
    }
}

//比较时间 格式 yyyy-mm-dd hh:mi:ss
function comptime(beginTime, endTime) {
    var a = Math.floor(endTime) - Math.floor(beginTime);

    if (a < 0) {
        return -1;
    } else if (a > 0) {
        return 1;
    } else if (a == 0) {
        return 0;
    } else {
        return 'exception';
    }
}

function ChangeLanguage() {
    var IsCH = GetArgsFromHref(document.location.href, "Language");
    if (IsCH == 1) {
        jsLanguage = 1;
    }

    if (jsLanguage == 1) {
        JS_cMsg0 = "Select Protocol";
        JS_cMsg1 = "Register Center IP";
        JS_cMsg2 = "Port";
        JS_cMsg3 = "unlimited";
        JS_cMsg4 = "Please check \"SetArea\" before cleaning!";
        JS_cMsg5 = "Not Logon!\nPlease select a video window that is logon.";
        JS_cMsg6 = "Not Logon! Snapshot Unavailable!";
        JS_cMsg7 = "Invalid Input!";
        JS_cMsg8 = "Require An Integer!";
        JS_cMsg9 = "The number must be between 32 and 8000!";
        JS_cMsg10 = "Fail to delete current user!";
        JS_cMsg11 = "Confirm to restore default settings?";
        JS_cMsg12 = "Confirm to reboot the server?";
        JS_cMsg13 =
            "The disk is full, recording or downloading has been stopped!";
        JS_cMsg14 = "Snapshot failed,please check the disk!";
        JS_cMsg15 =
            "A client is running,please check and open this page again!";
        JS_cMsg16 =
            "The activex control is not installed! Please download again.";
        JS_cMsg17 =
            "The version of Internet Explorer is too low!Please update to 6.0 or higher.";
        JS_cMsg18 = "Admin cann't be deleted as a special user!";
        JS_cMsg19 = "New password is not the same with the password confirm!";
        JS_cMsg20 = "Valid device address:0~255";
        JS_cMsg21 = "Valid device address:0~255";
        JS_cMsg22 = "Valid preset:1~255";
        JS_cMsg23 = "Invalid IP Address";
        JS_cMsg24 = "Invalid Gateway or DNS";
        JS_cMsg25 = "Invalid Subnet Mask";
        JS_cMsg26 = "New password is not the same with the old password!";
        JS_cMsg27 = "Start time must be earlier than End time!";
        JS_cMsg28 = "Invalid Update file";
        JS_cMsg29 = "Incorrect validate code";
        JS_cMsg30 = "Invalid Port ";
        JS_cMsg31 = "Fail to log on,user name or password error!";
        JS_cMsg32 = "Lack of authority,you can not operate!"
        JS_cMsg33 =
            "Confirm to calibrate device time with the current system time?";
        JS_cMsg34 = "user name can not be empty!"
        JS_cMsg35 = "Exit System?";
        JS_cMsg36 = "user does not exist.";
        JS_cMsg37 = "Users has been in existence.";
        JS_cMsg38 = "Password can not be empty.";
        JS_cMsg39 =
            " can only be English letters a - z(Distinguish between upper and lower case), numbers 0 to 9,special bond characters.";
        JS_cMsg40 =
            "You enter characters beyond the length of the 127,in accordance with the two Chinese characters length calculation.";
        JS_cMsg41 = " Please enter length: ";
        JS_cMsg42 = " characters ";
        JS_cMsg43 = " Please import value was: ";
        JS_cMsg44 = " values ";
        JS_cMsg45 = "Email Addresses Invalid";
        JS_cMsg46 = "Successfully set,20 seconds,the system will re-login";
        JS_cMsg47 = "IP and GateWay must be at one network segment.";
        JS_cMsg48 =
            "You enter an invalid subnet mask,subnet mask must be adjacent.";
        JS_cMsg49 = "Setup Failure";
        JS_cMsg50 = "Setup Success";
        JS_cMsg51 = "Don't log on, unable to open the audio!";
        JS_cMsg52 = "Don't log on, unable to record!";
        JS_cMsg53 = "Don't log on, unable to Talk!";
        JS_cMsg54 = "Failed,Please choose to Set DetectArea";
        JS_cMsg55 = "Start time must be earlier than End time!";
        JS_cMsg56 = "Player finished!";
        JS_cMsg57 = "Local video does not need to download ";
        JS_cMsg58 =
            "Please choose the right side of the list you want to download the file.";
        JS_cMsg59 = "only one file can be downloaded at the same time.";
        JS_cMsg60 = "Download more than the maximum limit.";
        JS_cMsg61 = "Download finished!Save the file to:";
        JS_cMsg62 = "New password is not the same with the old password!";
        JS_cMsg63 = "Updating Program,waitting……";
        JS_cMsg64 = "The user name is not in the list!";
        JS_cMsg65 = "the old password error!";
        JS_cMsg66 = "Wifi IP and wired IP cannot be the same!";
        JS_cMsg67 = "please select the folder!";
        JS_cMsg68 =
            "Character can not overlay the length of more than 32 characters(a Chinese account for two characters)";
        JS_cMsg69 = "Set success equipment to restart, please!";
        JS_cMsg70 = "IP modified successfully, please log equipment!";
        JS_cMsg71 = "In excess of the maximum number of users";
        JS_cMsg72 = "Add user failed!";
        JS_cMsg73 = "Add user name repeated!";
        JS_cMsg74 = "Pathname input error,or old pathname using!";
        JS_cMsg75 = "Enter the value of the error!";
        JS_cMsg76 = "UserName and Password can not be set to NULL or Space!";
        JS_cMsg77 = "Talking Failed!";
        JS_cMsg78 = "The length of password is not correct,Please input again!"
        JS_cMsg79 =
            "user name character  overlay the length 1-15 characters(a Chinese account for two characters)";
        JS_cMsg80 = "Upgrading,Talking,Recording and Audio will be closed! ";
        JS_cMsg81 = "Device TCP Connect Overlay!";
        JS_cMsg82 = "After Setting Success,NVS will reboot!";
        JS_cMsg83 =
            "Confirm to disconnect wireless connection? After being confirmed,NVS will reboot.";
        JS_cMsg84 = "Confirm to disconnect 3G wireless connection? ";
        JS_cMsg85 =
            "The deffirent Time-Segments can not be overlap with eachother!";
        JS_cShow1 = "Server IP:";
        JS_cShow2 = "user Name:";
        JS_cShow3 = "Password:";
        JS_cShow4 = "[Log On]";
        JS_cShow5 = "[Log Off]";
        JS_cShow6 = "Proxy IP:";
        JS_cShow7 = "Port:";
        JS_cShow8 = "Channel:";
        JS_cShow9 = "Stream:";
        JS_cShow10 = "major";
        JS_cShow11 = "minor";
        JS_cShow12 = "Network:";
        JS_cShow13 = "[Connect]";
        JS_cShow14 = "[Disconnect]";
        JS_cShow15 = "Registration Center";
        JS_cShow16 = "ServerName";
        JS_cShow17 = "SubMask:";
        JS_cShow18 = "Gateway:";
        JS_cShow19 = "[WIFI Set]";
        JS_cShow20 = "[Set]";
        JS_cShow21 = "PPPoE dial-up connection";
        JS_cShow22 = "Account:";
        JS_cShow23 = "Web Port:";
        JS_cShow24 = "Please select the document to be upgraded(*.bin)/(*.box)";
        JS_cShow25 = "[Upgrade]";
        JS_cShow26 = "[Clear]";
        JS_cShow27 = "[Restore Default]";
        JS_cShow28 = "[Reboot]";
        JS_cShow29 = "[SetTime]";
        JS_cShow30 = "user Management";
        JS_cShow31 = "user List:";
        JS_cShow32 = "Old Pwd:";
        JS_cShow33 = "New Pwd:";
        JS_cShow34 = "Authority:";
        JS_cShow35 = "Browse";
        JS_cShow36 = "Browse+Control";
        JS_cShow37 = "Browse+Control+Set";
        JS_cShow38 = "Admin";

        JS_cShow39 = "[Add]";
        JS_cShow40 = "[Delete]";
        JS_cShow41 = "[Modify Pwd]";
        JS_cShow42 = "System Information";
        JS_cShow43 = "SDK Ver:";
        JS_cShow44 = "Server Ver:";
        JS_cShow45 = "ActiveX Ver:";
        JS_cShow46 = "[SetView]";
        JS_cShow47 = "[CallView]";
        JS_cShow48 = "3D Enable";
        JS_cShow49 = "Zoom +";
        JS_cShow50 = "Zoom -";
        JS_cShow51 = "Focus +";
        JS_cShow52 = "Focus -";
        JS_cShow53 = "Iris +";
        JS_cShow54 = "Iris -";
        JS_cShow55 = "Light";
        JS_cShow56 = "Power";
        JS_cShow57 = "Rain";
        JS_cShow58 = "Speed:";
        JS_cShow59 = "Protocol:";
        JS_cShow60 = "Status:";
        JS_cShow61 = "Address:";

        JS_cShow62 = "InPort Enable";
        JS_cShow63 = "InPort:";
        JS_cShow64 = "OutPort:";
        JS_cShow65 = "Alarm InPort:";
        JS_cShow66 = "Alarm OutPort:";
        JS_cShow67 = "LinkSet";
        JS_cShow68 = "Type:";
        JS_cShow69 = "[CleanArea]";
        JS_cShow70 = "Set DetectArea";
        JS_cShow71 = "Set Video Cover Area";
        JS_cShow72 = "Motion Detect Threshold";
        JS_cShow73 = "AlarmMotionDetect";
        JS_cShow74 = "AlarmVideoCover";
        JS_cShow75 = "AlarmVideoLost";
        JS_cShow76 = "ON";
        JS_cShow77 = "OFF";
        JS_cShow78 = "NO OSD";
        JS_cShow79 = "Date and Time";
        JS_cShow80 = "Channel Name:";
        JS_cShow81 = "Date + Channel";
        JS_cShow82 = "OSD Type:";
        JS_cShow83 = "Channel Name";
        JS_cShow84 = "ChannelNamePos:";
        JS_cShow85 = "Date Pos:";
        JS_cShow86 = "Draw Text On Video:";
        JS_cShow87 = "X:";
        JS_cShow88 = "Y:";
        JS_cShow89 = "USB/SD Device";
        JS_cShow90 = "Status:";
        JS_cShow91 = "unMounted";
        JS_cShow92 = "Mounted";
        JS_cShow93 = "DiskSpace:";
        JS_cShow94 = "Remained:";
        JS_cShow95 = "SATA Device";
        JS_cShow96 = "DiskSpace:";
        JS_cShow97 = "NFS Device";
        JS_cShow98 = "MapStatus:";
        JS_cShow99 = "UnMapped";
        JS_cShow100 = "Mapped";
        JS_cShow101 = "IP Address:";
        JS_cShow102 = "Mapping Path:";
        JS_cShow103 = "[Disk Information]";
        JS_cShow104 = "[Set All Day]";
        JS_cShow105 = "Maximum record time  minute(10-120)";
        JS_cShow106 = "Disk remaining space MB(>=200)";
        JS_cShow107 = "Storage Policy";
        JS_cShow108 = "StopRec";
        JS_cShow109 = "CycleDel";
        JS_cShow110 = "CycleDel(Except Alarm)";
        JS_cShow111 = "TimeRec";
        JS_cShow112 = "AlarmRec";
        JS_cShow113 = "Stoped";
        JS_cShow114 = "ManualRec";
        JS_cShow115 = "Record Status";
        JS_cShow116 = "Sunday";
        JS_cShow117 = "Monday";
        JS_cShow118 = "Tuesday";
        JS_cShow119 = "Wednesday";
        JS_cShow120 = "Thursday";
        JS_cShow121 = "Friday";
        JS_cShow122 = "Saturday";
        JS_cShow123 = "Date Selection";
        JS_cShow124 = "Alarm pre-record time,second(5-60)";
        JS_cShow125 = "Alarm record delay,second(10-60)";
        JS_cShow126 = "[Device Snapshot]";
        JS_cShow127 = "Pre-recorded Alarm";
        JS_cShow128 = "Local File Path:";
        JS_cShow129 = "NVS list:";
        JS_cShow130 = "Remote:";

        JS_cShow131 = "FileType:";
        JS_cShow132 = "Record";
        JS_cShow133 = "Picture";
        JS_cShow134 = "Start Time:";
        JS_cShow135 = "End Time:&nbsp;";
        JS_cShow136 = "RecType";
        JS_cShow137 = "All";
        JS_cShow138 = "Manual";
        JS_cShow139 = "timed";
        JS_cShow140 = "Alarm";
        JS_cShow141 = "Channel";
        JS_cShow142 = "[Search]";
        JS_cShow143 = "[Play]"
        JS_cShow144 = "[Pause]";
        JS_cShow145 = "[Stop]";
        JS_cShow146 = "[Fast]";
        JS_cShow147 = "[Slow]";
        JS_cShow148 = "[DownLoad]";
        JS_cShow149 = "[Stop DownLoad]";
        JS_cShow150 = "[FirstPage]";
        JS_cShow151 = "[PrePage]";
        JS_cShow152 = "[NextPage]";

        JS_cShow153 = "[LastPage]";
        JS_cShow154 = "Local Play Back:";
        JS_cShow155 = "[Back]";
        JS_cShow156 = "Brightness";
        JS_cShow157 = "Saturation";
        JS_cShow158 = "Hue";
        JS_cShow159 = "Contrast";
        JS_cShow160 = "Best";
        JS_cShow161 = "Better";
        JS_cShow162 = "Good";
        JS_cShow163 = "Fair";
        JS_cShow164 = "Poor";
        JS_cShow165 = "Video Quality:";
        JS_cShow166 = "Pure Video";
        JS_cShow167 = "Audio Video";
        JS_cShow168 = "Stream Type:";
        JS_cShow169 = "Quality";
        JS_cShow170 = "Frame Rate";
        JS_cShow171 = "Prefer Mode:";
        JS_cShow172 = "Frame Rate:";
        JS_cShow173 = "Rate:";
        JS_cShow174 = "Video Size:";
        JS_cShow175 = "Coding Mode:";
        JS_cShow176 = "N/P Mode:";
        JS_cShow177 = "Showing frame-rate and bit-stream information";

        JS_cShow178 = "NetWork List:";
        JS_cShow179 = "WifiCard";
        JS_cShow180 = "no";
        JS_cShow181 = "yes";
        JS_cShow182 = "WifiStatus";
        JS_cShow183 = "unOnline";
        JS_cShow184 = "Online";
        JS_cShow185 = "Auto Get IP Address";
        JS_cShow186 = "Wifi IP:";
        JS_cShow187 = "Wifi SubMask:";
        JS_cShow188 = "Wifi GateWay:";
        JS_cShow189 = "Wifi DNS:";
        JS_cShow190 = "SecurityType:";
        JS_cShow191 = "Key Format:";
        JS_cShow192 = "Key Type:";
        JS_cShow193 = "Forbid";
        JS_cShow194 = "64 Bit";
        JS_cShow195 = "128 Bit";
        JS_cShow196 = "Key:";
        JS_cShow197 =
            "Password length: 64-bit key choices need to enter the 16-character hexadecimal number 10, or ASCII character code 5. Select 128-bit keys need to enter the 16-character hexadecimal number 26 or 13 characters in ASCII code.";

        JS_cShow198 = "LogOn";
        JS_cShow199 = "Device";
        JS_cShow200 = "Video";
        JS_cShow201 = "OSD";
        JS_cShow202 = "Alarm";
        JS_cShow203 = "NetWork";
        JS_cShow204 = "Memory";
        JS_cShow205 = "Advance";
        JS_cShow206 = "ON";
        JS_cShow207 = "OFF";
        JS_cShow208 = "encryption";
        JS_cShow209 = "logon Successfully";
        JS_cShow210 = "logon Successfully, but channel error.";
        JS_cShow211 = "Timeout";
        JS_cShow212 = "Successful upgrade and wait for reboot(10s-60s).";
        JS_cShow213 =
            "Success, it will be valid after restart(10s-60s).Please re-open IE logon.";
        JS_cShow214 = "Fail,Not logon nvs or Invalid document upgrade!";
        JS_cShow215 = "IP:";
        JS_cShow216 = "DNS:";
        JS_cShow217 = "Password";
        JS_cShow218 = "ServerName";
        JS_cShow219 = "Modify";
        JS_cShow220 = "Set";
        JS_cShow221 = "Open";

        JS_cShow222 = "1Pic";
        JS_cShow223 = "Start Talking";
        JS_cShow224 = "PlayBack";
        JS_cShow225 = "Capture";
        JS_cShow226 = "Stop Talking";
        JS_cShow227 = "4Pic";
        JS_cShow228 = "Full Screen";
        JS_cShow229 = "Open Audio";
        JS_cShow230 = "Start Video";
        JS_cShow231 = "16Pic";
        JS_cShow232 = "9Pic";
        JS_cShow233 = "Close Audio";
        JS_cShow234 = "Stop Record";

        JS_cShow235 = "DDNS Enable";
        JS_cShow236 = "Server Domain:";
        JS_cShow237 = "DDNS Domain:";
        JS_cShow238 = "Password Confirm:";
        JS_cShow239 = "Net Video Browser";
        JS_cShow240 = "Current Frame:";
        JS_cShow241 = "Total Frames:";
        JS_cShow242 = "Loading......";
        JS_cShow243 = "Total:";
        JS_cShow244 = "Relogon!";
        JS_cShow245 =
            "Device without minor stream，Please choose stream type again！";
        JS_cShow246 = "Unattended";
        JS_cShow247 = "Pwd Confirm:";
        JS_cShow248 = "ReConnect";
        JS_cShow249 = "Channel has been used!";
        JS_cShow250 =
            "Can not the same remote file download and preview at the same time local.";
        JS_cShow251 = "Account";
        JS_cShow252 = "Pack by Size, MB(20-200)";
        JS_cShow253 = "NoRec";
        JS_cShow254 = "TaskRec";
        JS_cShow255 = "PortAlarm";
        JS_cShow256 = "MotionAlarm";
        JS_cShow257 = "LostAlarm";
        JS_cShow258 = "Port|Motion";
        JS_cShow259 = "NVS List";
        JS_cShow260 = "MediaType:";
        JS_cShow261 = "Resolution:";
        JS_cShow262 = "StreamType:";
        JS_cShow263 = "AudioEncoder:";
        JS_cShow264 = "IFrameRate:";
        JS_cShow265 = "[AdvanceLink]";
        JS_cShow266 = "Manaul";
        JS_cShow267 = "TimeDelay";
        JS_cShow268 = "Delay:";
        JS_cShow269 = "DelayTime:";
        JS_cShow270 = "AlarmType:";
        JS_cShow271 = "Enable";
        JS_cShow272 = "Schedule:";
        JS_cShow273 = "NoLink";
        JS_cShow274 = "Preset";
        JS_cShow275 = "Track";
        JS_cShow276 = "CruisePath";
        JS_cShow277 = "LinkType:";
        JS_cShow278 = "LinkSnap";
        JS_cShow279 = "LinkRec";
        JS_cShow280 = "LinkOut";
        JS_cShow281 = "LinkPTZ";
        JS_cShow282 = "LinkChannel";
        JS_cShow283 = "LinkType";
        JS_cShow284 = "Number";
        JS_cShow285 = "Filter IP:";
        JS_cShow286 = "DeviceNo:";
        JS_cShow287 = "Record";
        JS_cShow288 = "Backup";
        JS_cShow289 = "Usage:";
        JS_cShow290 = "Partnum:";
        JS_cShow291 = "UnMounted";
        JS_cShow292 = "unFormated";
        JS_cShow293 = "Formated";
        JS_cShow294 = "Mounted";
        JS_cShow295 = "Using";
        JS_cShow296 = "[ClearDisk]";
        JS_cShow297 = "[USBPartFormat]";

        JS_cShow298 = "Alarm Server:";
        JS_cShow299 = "COM Server:";
        JS_cShow300 = "Alarm Advance";
        JS_cShow301 = "Start Formatting";
        JS_cShow302 = "Formatting...";
        JS_cShow303 = "Format Success";
        JS_cShow304 = "Format Failed,Disk INVALID";
        JS_cShow305 = "Format Failed,Other Formating";
        JS_cShow306 = "Format Failed";
        JS_cShow307 =
            "After formatting the disk the contents of all will be lost,confirmed that the disk format?";
        JS_cShow308 = "Disk Repair......";
        JS_cShow309 = "Disk Repair Finish";

        JS_cShow310 = "[3G Set]";
        JS_cShow311 = "DeviceType";
        JS_cShow312 = "OnlineState";
        JS_cShow313 = "Offline";
        JS_cShow314 = "Online";
        JS_cShow315 = "Intensity";
        JS_cShow316 = "3G IP Address";
        JS_cShow317 = "Begin dial-up time";
        JS_cShow318 = "Dial type";
        JS_cShow319 = "Auto Dial";
        JS_cShow320 = "Note Dial";
        JS_cShow321 = "Phone Dial";
        JS_cShow322 = "Alarm Dial";
        JS_cShow323 = "DisconnectType";
        JS_cShow324 = "Initiative";
        JS_cShow325 = "Passiveness";
        JS_cShow326 = "DialIntendTime";
        JS_cShow327 = "Phone Number List(only WCDMA)";
        JS_cShow328 = "NotifyMessage";
        JS_cShow329 = "Phone";
        JS_cShow330 = "Weaker";
        JS_cShow331 = "Weak";
        JS_cShow332 = "Middling";
        JS_cShow333 = "Strong";
        JS_cShow334 =
            "Auto Dial and InitiativeDisconnect cannot set at the same time!";
        JS_cShow335 = "[Logoff]";
        JS_cShow336 = "SetOnlineTaskSchedule";
        JS_cShow337 = "SMS Notify Type";
        JS_cShow338 = "SMS Content";
        JS_cShow339 = "3G Advance";
        JS_cShow340 = "No Notify";
        JS_cShow341 = "Success after dial-up";
        JS_cShow342 = "Fail after dial-up";
        JS_cShow343 = "Invalid Phone";

        JS_cShow350 = "ePTZ";
        JS_cShow351 = "AutoAperture";
        JS_cShow352 = "BackLightCompensate";
        JS_cShow353 = "VencType:";
        JS_cShow354 = "[Flip]";

        JS_cShow360 = "PlayImpression:";
        JS_cShow361 = "Low Delay";
        JS_cShow362 = "High Fluency";
        JS_cShow400 = "ESSID:";
        JS_cShow410 = "3GVPN(only EVDO)";
        JS_cShow411 = "DialNumber";
        JS_cShow412 = "Account";
        JS_cShow413 = "Password";
        JS_cShow414 = " can not be empty!";
        JS_cShow415 = "Cover Detect Threshold";
        JS_cShow416 = "Type:";
        JS_cShow417 = "CoverAlarm";

        JS_cShow500 = "[PTZ Cruise Set]";
        JS_cShow501 = "Cruise";
        JS_cShow502 = "Enable";
        JS_cShow503 = "CruiseNum";
        JS_cShow504 = "CruiseNo";
        JS_cShow505 = "Speed";
        JS_cShow506 = "StayTime(S)";
        JS_cShow507 = "[STATPartFormat]";
        JS_cShow508 =
            "Password length:choices ASCII character code need to enter the ASCII character number 8-63, the 16-character hexadecimal character code  need to enter he 16-character hexadecimal number 64.";
        JS_cShow609 = "Address:";
        JS_cShow610 = "path:";
        JS_cShow611 = "Encoder Ver:";
        JS_cShow612 = "[Advance]";
        JS_cShow613 = "FTP Setting";

        JS_cShow614 = "NTP Setting";
        JS_cShow615 = "Server IP:";
        JS_cShow616 = "Port:";
        JS_cShow617 = "Interval(h):";

        JS_cShow618 = "Email Alarm";
        JS_cShow619 = "Server IP:";
        JS_cShow620 = "Port:";
        JS_cShow621 = "UserName:";
        JS_cShow622 = "Password:";
        JS_cShow623 = "Logon Model:";
        JS_cShow624 = "Address:";
        JS_cShow625 = "Email Title:";

        JS_cShow626 = "[HD Setting]";
        JS_cShow627 = "Exposal Time:";
        JS_cShow628 = "Shutter:";
        JS_cShow629 = "Color To Gray:";
        JS_cShow630 = "Close";
        JS_cShow631 = "AUTO×2";
        JS_cShow632 = "AUTO×5";

        JS_cShow633 = "Flip:";
        JS_cShow634 = "disable";
        JS_cShow635 = "enable";
        JS_cShow636 = "Set Exposal Area";
        JS_cShow637 = "Set Apheliotropic Area";

        JS_cShow638 = "[FTP Upload]";
        JS_cShow639 = "[Capture Link]";
        JS_cShow640 = "Capture Link Enable";
        JS_cShow641 = "Link Email";
        JS_cShow642 = "Link FTP";
        JS_cShow643 = "Timer Capture Enable";
        JS_cShow644 = "Cap-Quality:";
        JS_cShow645 = "Interval(s):";
        JS_cShow646 = "Timer Capture Setting";
        JS_cShow647 = "Capture Link Setting";
        JS_cShow648 = "Timer Upload Setting";
        JS_cShow649 = "Timer Upload Enable";
        JS_cShow650 = "File Style:";
        JS_cShow651 = "All";
        JS_cShow652 = "Record";
        JS_cShow653 = "Picture";
        JS_cShow654 = "Upload Time:";
        JS_cShow655 =
            "Download the new OCX, Please login WebSite: www.tiandy.com !";

        JS_cShow656 = "Auto";
        JS_cShow657 = "Black";
        JS_cShow658 = "Color";
        JS_cShow659 = "[All Log Off]";

        JS_cShow660 = "Atuo Increase:";
        JS_cShow661 = "High";
        JS_cShow662 = "Mid";
        JS_cShow663 = "Low";
        JS_cShow664 = "Close IE auto save connect info";
        JS_cShow665 =
            "Current FTP-Upload maybe intermit other FTP-Upload,is continue?";
        JS_cShow666 = "Local file can not be uploaded!";
        JS_cShow667 = "[Config]";
        JS_cShow668 = "not allowed in remote mode";
    }
    document.title = JS_cShow239;
}

function OutPrint(vText) {
    document.write(vText);
}

function DHT_LogOn() {
    try {
        blResumeVideo = false;
        sServerHost = txHostIp.value;
        sProxyIP = txAgentIp.value;

        var lstNVS = TiandyVideo.Commander(Entry_NVSList_Get, 0, 0, 0, 0, 0, 0,
                                           0, 0, 0);//获得已登录的NVS
        var arrNVS = lstNVS.split('\n');
        selNVS.options.length = 0;
        selNVSIP.options.length = 0;
        var varItem;
        var varItemIP;
        for (var i = arrNVS.length - 1; i > 0; i--) {
            varItem = new Option(arrNVS[i]);
            selNVS.options.add(varItem);
            var arrIP = arrNVS[i].split(splitCode);
            varItemIP = new Option(arrIP[2]);
            selNVSIP.options.add(varItemIP);
        }

        for (i = 0; i < selNVS.length; i++) {
            var strUnion = selNVS.options[i].text;
            var arrUnion = strUnion.split(splitCode);
            if (arrUnion[1] == "0.0.0.0") {
                arrUnion[1] = "";
            }
            if ((arrUnion[2] == sServerHost) && (sProxyIP == arrUnion[1])) {
                selNVS.selectedIndex = i;
                selNVSIP.selectedIndex = i;
                iCurLogon = parseInt(arrUnion[5]);
                TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true,
                                      JS_cShow244, 1000, 0, 0, 0, 0, 0);
                return;
            }
        }

        iMode = selNetMode.options[selNetMode.selectedIndex].value;
        iStreamNO =
            parseInt(selStreamType.options[selStreamType.selectedIndex].value);
        if (TiandyVideo.iInitializtion == 1 || TiandyVideo.iInitializtion
                                               == 2) {
            iCurObs = TiandyVideo.CurObs;//获取当前播放索引
            writeCookie('UserName', txUserName.value, 12);
            var a = TiandyVideo.commander(Entry_Logon_Cmd, sProxyIP,
                                          sServerHost, txUserName.value,
                                          txPassword.value, "",
                                          parseInt(txPort.value), 0, 0, 0);
        }
        else if (TiandyVideo.iInitializtion == -1) {
            alert(JS_cMsg15);
            window.close();
        }
        else {
            alert(JS_cMsg16);
            window.close();
        }
    } catch (e) {
    }
}

//lijie add for 恢复上次连接视频
function ResumePlayAV(iLogonID) {
    var vRet = TiandyVideo.Commander(ConnectInfor_Get, 0, 0, 0, 0, 0, 0, 0, 0,
                                     0).split('\n');
    var sConnectInfo = vRet[1];
    var arryConnect = sConnectInfo.split("@@");

    //恢复上次关闭IE时连接的视频
    var sProxyIP = "";
    var sHostIP = "";
    var sUserNmae = "";
    var sPassword = "";
    var iPort = 0;
    var sLogon = "";
    var iChannelNum = 0;
    var iStreamNoTemp = 0;
    var iDelayNum = 0;
    var iMode = 0;
    for (var i = 0; i < 16; i++) {//debugger;
        //格式：监视器号##登陆ID##主机IP##代理IP##用户名##密码##端口号##通道号##码流类型##协议类型
        var temp = arryConnect[i].split("##");
        if (parseInt(temp[1]) == iLogonID) {
            sProxyIP = temp[3];
            sHostIP = temp[2];
            sUserNmae = temp[4];
            sPassword = temp[5];
            iPort = parseInt(temp[6]);
            iChannelNum = parseInt(temp[7]);
            iStreamNoTemp = parseInt(temp[8]);
            iMode = parseInt(temp[9]);

            if (iStreamNoTemp == 1 && !(iDevModel & 0x0008)) {
                TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true,
                                      JS_cShow245, 3000, 0, 0, 0, 0, 0);
                continue;
            }
            var a = TiandyVideo.Commander(Obs_StartRealTime_Cmd,
                                          ChannelInfo[i]['iCurObsID'], iLogonID,
                                          iChannelNum, iStreamNoTemp, 20
                                                                      * iDelayNum,
                                          iDelayNum, iMode, 0, 0).split('\n');
            iCurObs = TiandyVideo.CurObs;
            Sleep(20);
        }
    }
}

//end add

function PlayAV() {
    if (GetLogonStatus())//重复连接提示
    {
        return;
    }
    if (iStreamNO == 1 && !(iDevModel & 0x0008)) {
        TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cShow245, 3000,
                              0, 0, 0, 0, 0);
        return;
    }
    var iMode = parseInt(selNetMode.options[selNetMode.selectedIndex].value);
    iChannelNum = selChannel.selectedIndex;
    var a = TiandyVideo.Commander(Obs_StartRealTime_Cmd, iCurObs, iCurLogon,
                                  iChannelNum, iStreamNO, 20 * iDelayNum,
                                  iDelayNum, iMode, 0, 0).split('\n');
    iCurObs = TiandyVideo.CurObs;
}

function DHT_Install(iLogStatus, iLogonID) {
    if (iLogStatus == 1) {
        var lstNVS = TiandyVideo.Commander(Entry_NVSList_Get, 0, 0, 0, 0, 0, 0,
                                           0, 0, 0);
        var arrNVS = lstNVS.split('\n');
        selNVS.options.length = 0;
        selNVSIP.options.length = 0;
        var varItem;
        var varItemIP
        for (var i = arrNVS.length - 1; i > 0; i--) {
            varItem = new Option(arrNVS[i]);
            selNVS.options.add(varItem);
            var arrIP = arrNVS[i].split(splitCode);
            varItemIP = new Option(arrIP[2]);
            selNVSIP.options.add(varItemIP);
        }
        iCurLogon = iLogonID;
        iDevModel =
            TiandyVideo.Commander(Dev_Model_Get, iCurLogon, 0, 0, 0, 0, 0, 0, 0,
                                  0).split('\n')[1].split(splitCode)[1];
        //lijie add for 恢复上次连接视频
        if (blResumeVideo == true) {
            ResumePlayAV(iLogonID);
            return;
        }
        //end add

        var iCurObsList = new Object;
        var j = 0;

        if (ChannelInfo[16]['iCurObsID'] == iCurObs)//若正处于回放状态则返回
        {
            return;
        }

        TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cShow209, 3000,
                              0, 0, 0, 0, 0);
        var arrResult = TiandyVideo.Commander(Entry_Prop_Get, iCurLogon, 0, 0,
                                              0, 0, 0, 0, 0, 0)
            .split('\n')[1].split(splitCode);
        sChannelNumMax = arrResult[5];
        iSelectedChannel = selChannel.selectedIndex;

        if (iSelectedChannel + 1 > sChannelNumMax) {
            TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cShow210,
                                  3000, 0, 0, 0, 0, 0);
            return;
        }
        TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cShow209, 3000,
                              0, 0, 0, 0, 0);
        PlayAV();
        aXActivate();
        loadInfoFromaX(1);
        initHtmlInfo();
    }
    if (iLogStatus == 2) {
        TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cShow211, 3000,
                              0, 0, 0, 0, 0);
    }
    if (iLogStatus == 3) {
        TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cMsg31, 3000,
                              0, 0, 0, 0, 0);
    }
    if (iLogStatus == 5) {
        PlayAV();
    }
}

var lstObs = new Array;

function DHT_AddObs() {
    iCurObs =
        parseInt(TiandyVideo.Commander(Obs_Add_Cmd, 0, 0, 0, 0, 0, 0, 0, 0, 0)
                     .split('\n')[1]);
    lstObs.push(iCurObs);
}

function DHT_RemoveObs() {
    TiandyVideo.Commander(Obs_Remove_Cmd, iCurObs, 0, 0, 0, 0, 0, 0, 0, 0);
}

function AddObs() {
    var ret = -1;
    cmdResult =
        TiandyVideo.Commander(Obs_Add_Cmd, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            .split('\n');
    if (cmdResult[0] == 0) {
        ret = parseInt(cmdResult[1]);
    }
    return ret;
}

function DeleteObs(obsid) {
    var ret = -1;
    var index = GetIndexByObsID(obsid);
    DHT_LogOff(ObsInfo[index]['iLogonID'], index);
    cmdResult =
        TiandyVideo.Commander(Obs_Remove_Cmd, parseInt(obsid), 0, 0, 0, 0, 0, 0,
                              0, 0).split('\n');
    return cmdResult[0];
}

function DHT_StartObs() {
    blResumeVideo = false;
    if (GetLogonStatus())//重复连接提示
    {
        TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cShow249, 3000,
                              0, 0, 0, 0, 0);
        return;
    }

    iSelectedChannel = selChannel.selectedIndex;
    iChannelNum = selChannel.selectedIndex;
    if (iSelectedChannel + 1 > sChannelNumMax) {
        TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cShow210, 3000,
                              0, 0, 0, 0, 0);
        return;
    }
    iStreamNO =
        parseInt(selStreamType.options[selStreamType.selectedIndex].value);
    var iMode = parseInt(selNetMode.options[selNetMode.selectedIndex].value);
    if (iStreamNO == 1 && !(iDevModel & 0x0008)) //此设备不支持副码流
    {
        TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cShow245, 3000,
                              0, 0, 0, 0, 0);
        return;
    }

    var a = TiandyVideo.Commander(Obs_StartRealTime_Cmd, iCurObs, iCurLogon,
                                  iChannelNum, iStreamNO, 20 * iDelayNum,
                                  iDelayNum, iMode, 0, 0).split('\n');
    if (a == "-536739835") {
        TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cShow248, 3000,
                              0, 0, 0, 0, 0);
    }
    iCurObs = TiandyVideo.CurObs;
    if (iCurLogon == iTalklogonID) {
        ChannelInfo[iCurObsIndex]['bTalk'] = true;
    }
    UpdateStatusMenu();//更新录像、音频、对讲、显示码流状态
}

function DHT_PauseObs() {
    TiandyVideo.Commander(Obs_Pause_Cmd, iCurObs, 0, 0, 0, 0, 0, 0, 0, 0);
}

function DHT_StopObs() {
    TiandyVideo.Commander(Obs_ShowParam_Cmd, iCurObs, BITRATEPOSX, BITRATEPOSY,
                          0, 0, 0, 0, 0, 0);
    ChannelInfo[iCurObsIndex]['bShowBitRate'] = false;
    ChannelInfo[iCurObsIndex]['bAuto'] = false;
    DHT_RecStop();
    DHT_AudioStop();
    Menu_TalkStop();
    TiandyVideo.Commander(Obs_Stop_Cmd, iCurObs, 0, 0, 0, 0, 0, 0, 0, 0);
//	RecordDisConnect( iCurObsIndex );//lijie add for 恢复上次连接视频
}

function DHT_LogOff() {
    try {
        var IsOff = 0;//add by chufei for 20100913
        var IsCur = 0;
        var IsCurObs = 0;
        for (var i = 0; i < selNVS.length; i++) {
            var strUnion = selNVS.options[i].text;
            var arrUnion = strUnion.split(splitCode);
            if (arrUnion[1] == "0.0.0.0") {
                arrUnion[1] = "";
            }
            if ((arrUnion[2] == txHostIp.value) && (txAgentIp.value
                                                    == arrUnion[1])) {
                selNVS.selectedIndex = i;
                selNVSIP.selectedIndex = i;
                if (iCurLogon == parseInt(arrUnion[5])) {
                    IsCurObs = 1;
                }
                else {
                    iCurLogon = parseInt(arrUnion[5]);
                }
                IsOff = 1;
                break;
            }
        }

        if (IsOff == 0) {
            return;
        }
        if (IsCurObs == 1) {
            ChannelInfo[iCurObsIndex]['bShowBitRate'] = false;
            ChannelInfo[iCurObsIndex]['bAuto'] = false;

            Menu_TalkStop();
            LogOffAll(iCurLogon);//删除logOn对应的obs
        }
        TiandyVideo.Commander(Entry_Logoff_Cmd, iCurLogon, 0, 0, 0, 0, 0, 0, 0,
                              0);
        //RecordLogoff( iCurLogon );
        var lstNVS = TiandyVideo.Commander(Entry_NVSList_Get, 0, 0, 0, 0, 0, 0,
                                           0, 0, 0).split('\n');
        selNVS.options.length = 0;
        selNVSIP.options.length = 0;
        var varItem;
        var varItemIP;
        for (i = 1; i < lstNVS.length; i++) {
            varItem = new Option(lstNVS[i]);
            selNVS.options.add(varItem);
            var arrIP = lstNVS[i].split(splitCode);
            varItemIP = new Option(arrIP[2]);
            selNVSIP.options.add(varItemIP);
        }
    }
    catch (e) {
    }
}

function DHT_AllLogOff()   //add by chufei for 20100907
{
    for (var i = 0; i < 16; i++) {
        if (ChannelInfo[i]['iCurObsID'] == 0) {
            continue;
        }
        var arrResult = TiandyVideo.Commander(Obs_Prop_Get,
                                              ChannelInfo[i]['iCurObsID'], 0, 0,
                                              0, 0, 0, 0, 0, 0)
            .split('\n')[1].split(splitCode);
        iRet = parseInt(arrResult[0]);
        if (iRet == -1) {
            continue;
        }
        ChannelInfo[i]['bShowBitRate'] = false;
        ChannelInfo[i]['bAuto'] = false;
        ChannelInfo[i]['bTalk'] = false;
        TiandyVideo.Commander(Obs_MicEnable_Set, iRet, 0, 0, 0, 0, 0, 0, 0, 0);
        LogOffAll(iRet);//删除logOn对应的obs
        TiandyVideo.Commander(Entry_Logoff_Cmd, iRet, 0, 0, 0, 0, 0, 0, 0, 0);
        //RecordLogoff(iRet);
    }
    selNVS.options.length = 0;
    selNVSIP.options.length = 0;
}

//-----------------------------------------------------------------------------------------------
// playback
function StartPlay(ihr, iObsID) {
    if (ihr != 0 || iObsID == null) {
        return;
    }
    var i;
    for (i = 0; i < 16; i++) {
        if (ChannelInfo[i]['iCurObsID'] == iObsID) {
            break;
        }
    }
    if (iObsID == iCurObs && ChannelInfo[i]['bAudio'] == true) {
        TiandyVideo.Commander(Obs_SpeakerEnable_Set, iCurObs, true, 60000, 0, 0,
                              0, 0, 0, 0);
    }
    if (ChannelInfo[i]['bRec'] == true) {
        TiandyVideo.Commander(Obs_BeginRec_Cmd, iObsID, 0, 30, 0, 0, 0, 0, 0,
                              0);
    }
    setTimeout("loadInfoFromaX(1)", 1000);
}

function DHT_SelNVS() {
    var strUnion = selNVS.options[selNVSIP.selectedIndex].text;
    selNVS.selectedIndex = selNVSIP.selectedIndex;
    var arrUnion = strUnion.split(splitCode);
    iCurLogon = parseInt(arrUnion[5]);
}

var vStartTime;
var vEndTime;
var IsRemode;

function QF_Query() {
    divRecordNum.innerText = "";
    divPageNum.innerText = "";
    selQFResult.options.length = 0;
    iCurQFPage = 0;
    iTotalQFPage = 0;
    vStartTime = selBeginTimeYYYY.options[selBeginTimeYYYY.selectedIndex].text
                 + selBeginTimeSM.options[selBeginTimeSM.selectedIndex].text
                 + selBeginTimeDD.options[selBeginTimeDD.selectedIndex].text
                 + selBeginTimeHH.options[selBeginTimeHH.selectedIndex].text
                 + selBeginTimeMM.options[selBeginTimeMM.selectedIndex].text
                 + "00";
    vEndTime = selEndTimeYYYY.options[selEndTimeYYYY.selectedIndex].text
               + selEndTimeSM.options[selEndTimeSM.selectedIndex].text
               + selEndTimeDD.options[selEndTimeDD.selectedIndex].text
               + selEndTimeHH.options[selEndTimeHH.selectedIndex].text
               + selEndTimeMM.options[selEndTimeMM.selectedIndex].text
               + "59";
    if (comptime(vStartTime, vEndTime) <= 0) {
        alert(JS_cMsg55);
        return;
    }
    if (selNVSIP.selectedIndex == -1) {
        return;
    }
    var strUnion = selNVS.options[selNVSIP.selectedIndex].text;
    var arrUnion = strUnion.split(splitCode);
    var lstNVS = TiandyVideo.Commander(Query_Query_Cmd, parseInt(arrUnion[5]),
                                       selChann.selectedIndex,
                                       chkbRemote.checked, selFileType.selectedIndex
                                                           + 1,
                                       parseInt(
                                           selRecType.options[selRecType.selectedIndex].value),
                                       vStartTime, vEndTime, iQFPageSize,
                                       iCurQFPage).split('\n');
    IsRemode = chkbRemote.checked;
}

function QF_PrePage() {
    if (iCurQFPage <= 0) {
        iCurQFPage = 0;
        return;
    }
    iCurQFPage--;
    var strUnion = selNVS.options[selNVSIP.selectedIndex].text;
    var arrUnion = strUnion.split(splitCode);
    TiandyVideo.Commander(Query_Query_Cmd, parseInt(arrUnion[5]),
                          selChann.selectedIndex,
                          IsRemode, selFileType.selectedIndex + 1,
                          parseInt(
                              selRecType.options[selRecType.selectedIndex].value),
                          vStartTime, vEndTime, iQFPageSize, iCurQFPage)
        .split('\n');
}

function QF_NextPage() {
    if (iCurQFPage + 1 >= iTotalQFPage) {
        iCurQFPage = iTotalQFPage - 1;
        return;
    }
    iCurQFPage++;
    var strUnion = selNVS.options[selNVSIP.selectedIndex].text;
    var arrUnion = strUnion.split(splitCode);
    TiandyVideo.Commander(Query_Query_Cmd, parseInt(arrUnion[5]),
                          selChann.selectedIndex,
                          IsRemode, selFileType.selectedIndex + 1,
                          parseInt(
                              selRecType.options[selRecType.selectedIndex].value),
                          vStartTime, vEndTime, iQFPageSize, iCurQFPage)
        .split('\n');
}

function QF_FirstPage() {
    iCurQFPage = 0;
    var strUnion = selNVS.options[selNVSIP.selectedIndex].text;
    var arrUnion = strUnion.split(splitCode);
    TiandyVideo.Commander(Query_Query_Cmd, parseInt(arrUnion[5]),
                          selChann.selectedIndex,
                          IsRemode, selFileType.selectedIndex + 1,
                          parseInt(
                              selRecType.options[selRecType.selectedIndex].value),
                          vStartTime, vEndTime, iQFPageSize, iCurQFPage)
        .split('\n');
}

function QF_LastPage() {
    if (iTotalQFPage == 0) {
        return;
    }
    iCurQFPage = iTotalQFPage - 1;
    var strUnion = selNVS.options[selNVSIP.selectedIndex].text;
    var arrUnion = strUnion.split(splitCode);
    TiandyVideo.Commander(Query_Query_Cmd, parseInt(arrUnion[5]),
                          selChann.selectedIndex,
                          IsRemode, selFileType.selectedIndex + 1,
                          parseInt(
                              selRecType.options[selRecType.selectedIndex].value),
                          vStartTime, vEndTime, iQFPageSize, iCurQFPage)
        .split('\n');
}

function UpdateQueryResult(_lLogonID, _strResult) {
    var arrFile = _strResult.split('\n');
    var arrPage = arrFile[1].split(splitCode);
    iTotalQFPage =
        parseInt(parseInt(arrPage[1]) / iQFPageSize + (parseInt(arrPage[1])
                                                       % iQFPageSize ? 1 : 0));
    selQFResult.options.length = 0;
    var varItem;
    for (var i = 2; i < arrFile.length; i++) {
        varItem = new Option(arrFile[i]);
        selQFResult.options.add(varItem);
    }
    divPageNum.innerText = (iCurQFPage + 1) + "/" + (iTotalQFPage);
    divRecordNum.innerText = JS_cShow243 + arrPage[1];
    if (iTotalQFPage == 0) {
        divRecordNum.innerText = "";
        divPageNum.innerText = "";
    }
}

var pbDownPlayStatus = 0;
var arrSaveFileName;
var PlaybackInterval = null;
var pbPicState = 0;

function PlayBack_Start() {
    if (PlaybackInterval != null) {
        clearInterval(PlaybackInterval);
        PlaybackInterval = null;
    }
    if (selQFResult.selectedIndex < 0)//调filepath_open函数去掉清除搜索列表语句
    {
        if ((localfile.value == "") || (typeof(localfile.value)
                                        == "undefined")) {
            return;
        }
        if (PlaybackInterval != null) {
            clearInterval(PlaybackInterval);
            PlaybackInterval = null;
        }
        if (GetHouZhui(localfile.value, "jpg") || GetHouZhui(localfile.value,
                                                             "bmp")) {
            lookpic(localfile.value);

        }
        else {
            var arrResult = TiandyVideo.Commander(Obs_StartPlayBackOffLine_Cmd,
                                                  iCurObs, localfile.value, 0,
                                                  0, 0, 0, 0, 0, 0);
            if (arrResult == 0) {
                PlaybackInterval = setInterval("PlayBack_GetProgress()", 1000);
                playBackStatus = 1;
                iCurPlay = "";
            }
            else {
                playBackStatus = 0;
            }
        }
        return;
    }
    var Filename = selQFResult.options[selQFResult.selectedIndex].text;
    var FileIPname = Filename.split('-')[0];
    FilePahtGet();
    var OpenPathPic = txpath.value + "Picture\\" + FileIPname + "\\" + Filename;
    if (IsRemode) {
        if (pbPicState != 0) {
            return;
        }
    }
    arrSaveFileName = txpath.value + Filename;
    if (GetHouZhui(Filename, "jpg") || GetHouZhui(Filename, "bmp")) {
        PlayBack_Stop();
        if (IsRemode) {
            var downRet = TiandyVideo.Commander(Store_DLStart_Cmd, iCurLogon,
                                                Filename, arrSaveFileName, 0, 0,
                                                0, 0, 0, 0).split('\n');
            if (downRet[0] == 0) {
                pbDownPlayStatus = 1;
                TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true,
                                      JS_cShow242, 8000, 0, 0, 0, 0, 0);
                pbPicState = 1;
                setTimeout("pbPicState=0", 2000);
            }
            else {
                iError = parseInt(downRet[0]) + 0x0000000100000000;
                if (iError == 0xE0000003) {
                    TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true,
                                          JS_cMsg13, 3000, 0, 0, 0, 0, 0);
                    return;
                }
            }
        }
        else {
            lookpic(OpenPathPic);
            pbDownPlayStatus = 0;
        }
    }
    else {
        play_speed_slow = 0;
        play_speed_fast = 0;
        playBackStatus = 0;

        pbDownPlayStatus = 0;
        var arriRed = TiandyVideo.Commander(Obs_StartPlayBack_Cmd, ObsTemp[16],
                                            iCurLogon,
                                            (IsRemode == false) ? 0 : 1,
                                            selQFResult.options[selQFResult.selectedIndex].text, iCurQFPage
                                                                                                 * iQFPageSize
                                                                                                 + selQFResult.selectedIndex,
                                            0, 0, 0, 0);
        if (arriRed == 0) {
            ObsTemp[16] = TiandyVideo.CurObs;
            iCurObs = TiandyVideo.CurObs;
            PlaybackInterval = setInterval("PlayBack_GetProgress()", 1000);
            playBackStatus = 1;
            iCurPlay = "";
        }
        else {
            iError = parseInt(arriRed) + 0x0000000100000000;
            if (iError == 0xE0010006) {
                if (iError == 0xE0010006) {
                    playBackStatus = 0;
                    alert(JS_cShow250);
                }
                else if (iError == 0xE0000003) {
                    playBackStatus = 0;
                    TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true,
                                          JS_cMsg13, 3000, 0, 0, 0, 0, 0);
                }
                else {
                    playBackStatus = 0;
                }
            }
            else if (iError == 0xE0000003) {
                playBackStatus = 0;
                TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cMsg13,
                                      3000, 0, 0, 0, 0, 0);
            }
            else {
                playBackStatus = 0;
            }
        }
    }
}

var iCurPlay = "";

function PlayBack_GetProgress()//获取播放进度
{
    var iPlay = TiandyVideo.Commander(Obs_Progress_Get, iCurObs, 0, 0, 0, 0, 0,
                                      0, 0, 0).split('\n');
    if (iPlay[0] == 0) {
        iCurPlay = iPlay[1].split('/');
        if (iCurPlay[0] == -1) {
            iCurPlay[0] = 1;
        }
        var arriPlayMeg = JS_cShow240 + iCurPlay[0] + JS_cShow241 + iCurPlay[1];
        TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, arriPlayMeg, 3000,
                              0, 0, 0, 0, 0);
    }
    else {
        TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cShow242, 1000,
                              0, 0, 0, 0, 0);
    }
}

function PlayBack_Pause() {
    TiandyVideo.Commander(Obs_StopPause_Set, iCurObs, 0, 0, 0, 0, 0, 0, 0, 0);
}

function PlayBack_Stop() {
    TiandyVideo.Commander(Obs_Stop_Cmd, iCurObs, 0, 0, 0, 0, 0, 0, 0, 0);
    if (playBackStatus == 1 && PlaybackInterval != null) {
        clearInterval(PlaybackInterval);
        PlaybackInterval = null;
    }
    play_speed_slow = 0;
    play_speed_fast = 0;
    playBackStatus = 0;
}

function PlayBack_StartEx() {
    PlayBack_Stop();
    PlayBack_Start();
}

var play_speed_fast = 0;

function PlayBack_Fast() {
    if (playBackStatus != 1) {
        return;
    }  //是否正在播放
    if (IsRemode) {
        var arriPlayMeg = JS_cShow668;
        TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, arriPlayMeg, 3000,
                              0, 0, 0, 0, 0);
        return;
    }
    play_speed_fast++;
    TiandyVideo.Commander(Obs_FastForward_Cmd, iCurObs, play_speed_fast, 0, 0,
                          0, 0, 0, 0, 0);
    TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cShow146.replace(
        "[", "").replace("]", "") + " X " + play_speed_fast, 3000, 0, 0, 0, 0,
                          0);
    if (play_speed_fast == 4) {
        play_speed_fast = 0;
    }

}

var play_speed_slow = 0;

function PlayBack_Slow() {
    if (playBackStatus != 1) {
        return;
    }  //是否正在播放
    play_speed_slow++;
    TiandyVideo.Commander(Obs_SlowForward_Cmd, iCurObs, play_speed_slow, 0, 0,
                          0, 0, 0, 0, 0);
    TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cShow147.replace(
        "[", "").replace("]", "") + " X " + play_speed_slow, 3000, 0, 0, 0, 0,
                          0);
    if (play_speed_slow == 4) {
        play_speed_slow = 0;
    }
}

//回放抓拍
function PlayBack_Snap() {
    var now = new Date();
    var YY = now.getFullYear();
    var MM = now.getMonth() + 1;
    if (MM < 10) {
        MM = "0" + MM;
    }
    var DD = now.getDate();
    if (DD < 10) {
        DD = "0" + DD;
    }
    var hh = now.getHours();
    if (hh < 10) {
        hh = "0" + hh;
    }
    var ff = now.getMinutes();
    if (ff < 10) {
        ff = "0" + ff;
    }
    var ss = now.getSeconds();
    if (ss < 10) {
        ss = "0" + ss;
    }
    var RecDate = YY.toString() + MM + DD + hh + ff + ss;
    var sFilePath = RecDate + ".bmp";
    var a = TiandyVideo.Commander(Obs_Capture_Cmd, iCurObs, sFilePath, 50, 0, 0,
                                  0, 0, 0, 0);
}

function PlayBack_ISRemote() {
    if (chkbRemote.checked) {
        selRecType.disabled = false;
    }
    else {
        selRecType.options.selectedIndex = 0;
        selRecType.disabled = true;
    }
}

function OnPlayBack(iObsID, iStatus) {
    if (iStatus == 0) {
        if (iCurPlay != "") {
            var arriPlayMeg = JS_cShow240 + iCurPlay[1] + JS_cShow241
                              + iCurPlay[1];
            TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, arriPlayMeg,
                                  3000, 0, 0, 0, 0, 0);
        }
        alert(JS_cMsg56);
        PlayBack_Stop();
    }
}

function FilePath() {
    var result = TiandyVideo.Commander(Store_LocalPath_Set, txpath.value, 0, 0,
                                       0, 0, 0, 0, 0, 0);
    if (result != 0) {
        alert(JS_cMsg74);
    }
    FilePahtGet();
}

function FilePahtGet() {
    var arrResult = TiandyVideo.Commander(Store_LocalPath_Get, 0, 0, 0, 0, 0, 0,
                                          0, 0, 0).split('\n')[1];
    txpath.value = arrResult;
}

function FilePath_Open() {
    if ((localfile.value == "") || (typeof(localfile.value) == "undefined")) {
        return;
    }
    selQFResult.length = 0;
    divRecordNum.innerText = "";
    divPageNum.innerText = "";
    IsRemode = false;
    if (PlaybackInterval != null) {
        clearInterval(PlaybackInterval);
        PlaybackInterval = null;
    }
    if (GetHouZhui(localfile.value, "jpg") || GetHouZhui(localfile.value,
                                                         "bmp")) {
        lookpic(localfile.value);
    }
    else {
        var arrResult = TiandyVideo.Commander(Obs_StartPlayBackOffLine_Cmd,
                                              iCurObs, localfile.value, 0, 0, 0,
                                              0, 0, 0, 0);
        if (arrResult == 0) {
            PlaybackInterval = setInterval("PlayBack_GetProgress()", 1000);
            playBackStatus = 1;
            iCurPlay = "";
        }
        else {
            playBackStatus = 0;
        }
    }
}

function FilePath_Open_Clear() {
    if (localfile.value != "") {
        localfile.select();
        document.execCommand("delete");
    }
}

function Playback_QFtime_init() {
    var now = new Date();
    var yA = now.getYear();
    var mA = now.getMonth() + 1;
    if (mA < 10) {
        mA = "0" + mA;
    }
    var dA = now.getDate();
    if (dA < 10) {
        dA = "0" + dA;
    }
    var hh = now.getHours();
    if (hh < 10) {
        hh = "0" + hh;
    }
    var mm = now.getMinutes();
    if (mm < 10) {
        mm = "0" + mm;
    }
    var ss = now.getSeconds();
    if (ss < 10) {
        ss = "0" + ss;
    }

    selBeginTimeYYYY.value = yA - 1;
    selBeginTimeSM.value = mA;
    selBeginTimeDD.value = dA;
    selBeginTimeHH.value = hh;
    selBeginTimeMM.value = mm;
    selEndTimeYYYY.value = yA;
    selEndTimeSM.value = mA;
    selEndTimeDD.value = dA;
    selEndTimeHH.value = hh;
    selEndTimeMM.value = mm;
}

function lookpic(arrPicNameValue) {
    TiandyVideo.Commander(Store_OpenLocFile_Cmd, arrPicNameValue, 0, 0, 0, 0, 0,
                          0, 0, 0);
}

//----------------------------------------------------------------------------------
// 下载相关函数
function PB_Down() {
    if (IsRemode == false) {
        alert(JS_cMsg57);
        return;
    }
    if (pbDownStatus == 0)//未下载
    {
        if (selQFResult.options.selectedIndex < 0) {
            alert(JS_cMsg58);
            return;
        }
        FilePahtGet();
        fd = txpath.value;
        strLastDownFileName =
            selQFResult.options[selQFResult.selectedIndex].text;//获得当前播放的录像文件名
        var strSaveFileName = fd + strLastDownFileName;
        if (pbPicState != 0) {
            return;
        }
        var downRet = TiandyVideo.Commander(Store_DLStart_Cmd, iCurLogon,
                                            strLastDownFileName,
                                            strSaveFileName, 0, 0, 0, 0, 0, 0)
            .split('\n');
        if (downRet[0] == 0) {
            pbDownloadID = parseInt(downRet[1]);
            divDownProgress.style.visibility = "visible";
            pbDownInterva = setInterval("PB_GetDownProgress()", 1000);
            pbDownStatus = 1;
        }
        else {
            iError = parseInt(downRet[0]) + 0x0000000100000000;
            if (iError == 0xE0000003) {
                TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, true, JS_cMsg13,
                                      3000, 0, 0, 0, 0, 0);
                return;
            }
            else if (iError == 0xE0010006) {
                alert(JS_cShow250);
                return;
            }
        }
    }
    else if (pbDownStatus == 1)//正在下载
    {
        alert(JS_cMsg59);
    }
    else {
        return;
    }
}

function PB_StopDown() {
    var downRet = TiandyVideo.Commander(Store_DLStop_Cmd, pbDownloadID, 0, 0, 0,
                                        0, 0, 0, 0, 0).split('\n');
    clearInterval(pbDownInterval);
    pbDownStatus = 0;
    divDownProgress.innerText = "";
    //setTimeout("divDownProgress.style.visibility = 'hidden'",1000);
    divDownProgress.style.visibility = 'hidden';
}

function PB_DownloadMsg(downloadID, msg) {
    if (true/*downloadID == pbDownloadID*/) //是当前下载的消息
    {
        if (msg == 10) {
            alert(JS_cMsg60);
        }
        else {
            if (pbDownPlayStatus == 1) {
                TiandyVideo.Commander(Obs_ShowMsg_Cmd, iCurObs, false,
                                      JS_cShow242, 1000, 0, 0, 0, 0, 0);
                setTimeout("lookpic(arrSaveFileName)", 1000);
                pbDownPlayStatus = 0;
            }
            else {
                if (msg == "4294967295") {
                    divDownProgress.innerText = "";
                    alert(JS_cMsg60);
                } else {
                    divDownProgress.innerText = "100%";
                    alert(JS_cMsg61 + txpath.value);
                }
                PB_StopDown();
            }
        }
    }
}

function PB_GetDownProgress()//获取下载进度
{
    var downRet = TiandyVideo.Commander(Store_DLProgress_Get, pbDownloadID, 0,
                                        0, 0, 0, 0, 0, 0, 0).split('\n');
    if (downRet[0] == 0) {
        var curPro = parseInt(downRet[1].split(splitCode)[0]);
        divDownProgress.innerText = curPro + "%";
        if (curPro >= 100) {
            PB_StopDown();
        }
    }
}

//消息响应函数
function DHT_OnParaChange(_lLogonID, _usChann, _ulType) {
    if (_lLogonID != iCurLogon || _usChann != iChannelNum)//
    {
        switch (_ulType) {
            case 67:
                NetWifiSearchGet();
                break;
            default:
                break;
        }
        return;
    }
    switch (_ulType) {
        case 56:
            //NetFile_GetRecordState();
            break;
        case 51:
            //Mem_GetMapDevInfo(1);
            break;
        case 45:
            //NetFile_GetTimerREC();
            break;
        case 46:
            //NetFile_GetAlertREC();
            break;
        case 67:
            //NetWifiSearchGet();
            break;
        case 113:
            //G3_DeviceInfoGet();
            break;
        case 114:
            //G3_DialogGet();
            break;
        case 115:
            // G3_MessageGet();
            break;
        case 118:
            //G3_TaskScheduleGet();
            break;
        case 119:
            //G3_NotifyGet();
            break;
        case 127:
            //G3_VPDNGet();
            break;
        default:
            break;
    }
}

//lijie add for 恢复上次连接视频
function Sleep(n) {
    var start = new Date().getTime();
    while (true) {
        if ((new Date().getTime() - start ) > n) {
            break;
        }
    }
}

function ResumeVideo() {
    var vStatus;
    vStatus =
        TiandyVideo.Commander(GetSaveStatus, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            .split('\n');
    if (vStatus[1] == '0') {
        IniMonitorNum(17);
        ChangeMonitorNum(1);
        blResumeVideo = false;
        chkSaveConnectInfor.checked = false;
        TiandyVideo.Commander(ConnectInfor_Set, "", 0, 0, 0, 0, 0, 0, 0, 0);
        return;
    }
    else {
        chkSaveConnectInfor.checked = true;
    }
    blResumeVideo = true;
    var vRet = TiandyVideo.Commander(ConnectInfor_Get, 0, 0, 0, 0, 0, 0, 0, 0,
                                     0).split('\n');
    var sConnectInfo = vRet[1];
    var arryConnect = sConnectInfo.split("@@");

    //恢复上次关闭IE时的窗口数量
    IniMonitorNum(17);
    if (arryConnect[16].split("##")[0] == "") {
        ChangeMonitorNum(1);
    }
    else {
        ChangeMonitorNum(parseInt(arryConnect[16].split("##")[0]));
    }
    var sProxyIP = "";
    var sHostIP = "";
    var sUserNmae = "";
    var sPassword = "";
    var iPort = 0;
    var sLogon = "";
    var iChannelNum = 0;
    var iStreamNo = 0;
    var iDelayNum = 0;
    var iMode = 0;
    for (var i = 0; i < 16; i++) {
        //格式：监视器号##登陆ID##主机IP##代理IP##用户名##密码##端口号##通道号##码流类型##协议类型
        var temp = arryConnect[i].split("##");
        if (temp[1] != "") {
            sProxyIP = temp[3];
            sHostIP = temp[2];
            sUserNmae = temp[4];
            sPassword = temp[5];
            iPort = parseInt(temp[6]);
            iChannelNum = parseInt(temp[7]);
            iStreamNo = parseInt(temp[8]);
            iMode = parseInt(temp[9]);

            var lstNVS = TiandyVideo.Commander(Entry_NVSList_Get, 0, 0, 0, 0, 0,
                                               0, 0, 0, 0);//获得已登录的NVS
            var arrNVS = lstNVS.split('\n');
            selNVS.options.length = 0;
            selNVSIP.options.length = 0;
            var varItem;
            var varItemIP;
            for (var j = arrNVS.length - 1; j > 0; j--) {
                varItem = new Option(arrNVS[j]);
                selNVS.options.add(varItem);
                var arrIP = arrNVS[j].split(splitCode);
                varItemIP = new Option(arrIP[2]);
                selNVSIP.options.add(varItemIP);
            }

            var blIsLogon = false;
            for (j = 0; j < selNVS.length; j++) {
                var strUnion = selNVS.options[j].text;
                var arrUnion = strUnion.split(splitCode);
                if (arrUnion[1] == "0.0.0.0") {
                    arrUnion[1] = "";
                }
                if ((arrUnion[2] == sHostIP) && (sProxyIP == arrUnion[1])) {
                    selNVS.selectedIndex = j;
                    selNVSIP.selectedIndex = j;
                    iCurLogon = parseInt(arrUnion[5]);
                    blIsLogon = true;
                    break;
                }
            }
            if (blIsLogon == true) {
                continue;
            }
            sLogon =
                TiandyVideo.commander(Entry_Logon_Cmd, sProxyIP, sHostIP,
                                      sUserNmae, sPassword, "", iPort, 0, 0, 0);
            Sleep(200);
        }
    }
}

//---------------------------------------------------------------------------
/*
	Page OnLoad/UnLoad
*/
function SaveConnectInfo() {
    var vStatus;
    vStatus =
        TiandyVideo.Commander(GetSaveStatus, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            .split('\n');
    if (vStatus[1] == '0') {
        return;
    }
    var connectInfo = new Array(17);
    for (var i = 0; i < 16; i++) {
        var obsID = ChannelInfo[i]['iCurObsID'];
        if (obsID == 0) {
            connectInfo[i] = i + "##################";
            continue;
        }
        //监视器号##登陆ID##主机IP##代理IP##用户名##密码##端口号##通道号##码流类型##协议类型
        var vRet;
        var arrResult;

        vRet =
            TiandyVideo.Commander(Obs_Prop_Get, obsID, 0, 0, 0, 0, 0, 0, 0, 0)
                .split('\n');
        if (vRet[0] != '0') {
            connectInfo[i] = i + "##################";
            continue;
        }
        else {
            arrResult = vRet[1].split(splitCode);
        }
        var iID = parseInt(arrResult[0]);
        if (iID < 0) {
            connectInfo[i] = i + "##################";
            continue;
        }
        var iChannelNum = parseInt(arrResult[2]);
        var iMode = parseInt(arrResult[3]);
        var iStreamNO = parseInt(arrResult[4]);

        arrResult =
            TiandyVideo.Commander(Entry_Prop_Get, iID, 0, 0, 0, 0, 0, 0, 0, 0)
                .split('\n')[1].split(splitCode);
        if (arrResult[1] == "0.0.0.0") {
            sProxyIP = "";
        }
        else {
            sProxyIP = arrResult[1];
        }
        sServerHost = arrResult[2];
        iPort = parseInt(arrResult[4]);
        sCurUserName = arrResult[6];
        sCurPassword = arrResult[7];

        connectInfo[i] =
            i + "##" + iID + "##" + sServerHost + "##" + sProxyIP + "##"
            + sCurUserName + "##" + sCurPassword + "##" + iPort + "##"
            + iChannelNum + "##" + iStreamNO + "##" + iMode;
    }
    connectInfo[16] = iCurMonitorCount + "##################";
    var astrConncetInfo = "";
    for (var j = 0; j < 17; j++) {
        astrConncetInfo = astrConncetInfo + connectInfo[j] + "@@";
    }
    TiandyVideo.Commander(ConnectInfor_Set, astrConncetInfo, 0, 0, 0, 0, 0, 0,
                          0, 0);
}

function LoadHtml() {
    if (jsLanguage == 1) {
        TiandyVideo.iLanguage = 1;
    }
    if (AXCOLOR == 0) {
        BACKCOLOR = "#A6DB9E";
    }
    else {
        BACKCOLOR = "#909CC6";

        lyLogon.style.backgroundColor = "#909CC6";
        lyLogon.style.layerbackgroundcolor = "#909CC6";
    }
    bSet1.value = JS_cShow198;
    bSet2.value = JS_cShow199;

    bZoomBig.value = JS_cShow49;
    bZoomSmall.value = JS_cShow50;
    bFocusFar.value = JS_cShow51;
    bFocusNear.value = JS_cShow52;
    bIrisOpen.value = JS_cShow53;
    bIrisClose.value = JS_cShow54;
    bLight.value = JS_cShow55;
    bPower.value = JS_cShow56;
    bRain.value = JS_cShow57;
    btnbrowse.value = JS_cShow220;
    btnPlayLocal.value = JS_cShow221;
    imgPic1.alt = JS_cShow222;
    imgTalkStart.alt = JS_cShow223;
    imgPlayBack.alt = JS_cShow224;
    imgSnap.alt = JS_cShow225;
    imgTalkStop.alt = JS_cShow226;
    imgPic4.alt = JS_cShow227;
    imgPicFull.alt = JS_cShow228;
    imgAudioStart.alt = JS_cShow229;
    imgRecStart.alt = JS_cShow230;
    imgPic16.alt = JS_cShow231;
    imgPic9.alt = JS_cShow232;
    imgAudioStop.alt = JS_cShow233;
    imgRecStop.alt = JS_cShow234;

    var xVersion = navigator.appVersion.indexOf("MSIE") + 5;
    xVersion = parseInt(navigator.appVersion.substring(xVersion, xVersion + 3));
    if (xVersion < 6) {
        alert(JS_cMsg17);
    }

    var HostInfo = document.location.host.split(":");
    sProxyIP = HostInfo[0];
    sServerHost = GetArgsFromHref(document.location.href, "encoder");
    var WPort = GetArgsFromHref(document.location.href, "wport");
    if (sServerHost == "") {
        sServerHost = sProxyIP;
        sProxyIP = ""
    }
    else {
        selNetMode.selectedIndex = 0;
        selStreamType.selectedIndex = 0;
    }
    txAgentIp.value = (sProxyIP == "localhost") ? "" : sProxyIP;
    txHostIp.value = sServerHost;
    if (WPort != "") {
        txPort.value = WPort;
    }
    txUserName.value = readCookie('UserName');
    if (STARTPICNUM == 1) {
        DHT_1Pic_Click();
    }

    if (TiandyVideo.iInitializtion == -1) {
        alert(JS_cMsg15);
        window.close();
    }
    if (AXCOLOR == 1) {
        arrResult =
            TiandyVideo.Commander(Sys_Style_Set, 0x00ffffff, 0x00ab7a69, 0, 0,
                                  0, 0, 0, 0, 0);
    }

    if (jsLanguage == 1) {
        divPageNum.style.left = 208;
        divPageNum.style.top = 400;
        divDownProgress.style.left = 230;
        divRecordNum.style.left = 190;
    }
    else {
        divPageNum.style.left = 170;
        divPageNum.style.top = 382;
        divDownProgress.style.left = 140;
        divRecordNum.style.left = 169;
    }
    var now = new Date();
    writeYears(selBeginTimeYYYY, now);
    writeYears(selEndTimeYYYY, now);
    writeMonths(selBeginTimeSM);
    writeMonths(selEndTimeSM);
    writeDate(selBeginTimeDD);
    writeDate(selEndTimeDD);
    writeHours(selBeginTimeHH);
    writeHours(selEndTimeHH);
    writeMinutes(selBeginTimeMM);
    writeMinutes(selEndTimeMM);

    ResumeVideo();

}

function UnLoadHtml() {
    try {
        SaveConnectInfo();
        TiandyVideo.Commander(Sys_Cleanup_Cmd, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }
    catch (e) {
    }
}

var ObsTemp = new Object;

function ChangeMonitorNum(num) {
    var ObsTempNum = new Object;
    if (num == 17) {
        ObsTempNum[0] = ObsTemp[16];
    }
    else {
        for (var i = 0; i < num; i++) {
            if (num == 1) {
                if (iCurObs == 0) {
                    ObsTempNum[0] =
                        parseInt(ChannelInfo[iCurObsIndex]['iCurObsID']);
                } else {
                    if (iCurObs == ObsTemp[16]) {
                        ObsTempNum[0] =
                            parseInt(ChannelInfo[iCurObsIndex]['iCurObsID']);
                    } else {
                        ObsTempNum[0] = iCurObs;
                    }
                }
            }
            else {
                ObsTempNum[i] = ObsTemp[i];
            }
        }
    }
    if (SetObsShow(ObsTempNum) == true) {
        if (num == 17) {
        }
        else {
            iCurMonitorCount = num;
        }
    }
//	RecordMonitorNum( iCurMonitorCount );
}

function IniMonitorNum(num) {
    if (num > iCurObsCount)//增多画面
    {
        ChangeObsNum(num);//当视频窗口生成数不足时补齐
    }
    for (var i = 0; i < num; i++) {

        ObsTemp[i] = parseInt(ChannelInfo[i]['iCurObsID']);
    }
}

function ChangeObsNum(num) {
    if (num > iCurObsCount)//增多画面
    {
        for (var i = iCurObsCount; i < num; i++) {
            ChannelInfo[i]['iCurObsID'] = AddObs();
            iCurObsCount++;
        }
        iCurObsCount = iCurObsCount - 1;
    }
}

/*
  *	设置视频窗口显示哪些，参数为Obs数组
  */
function SetObsShow(_ObsArr) {
    try {
        if (_ObsArr.length <= 0) {
            return false;
        }
        var ret = TiandyVideo.Commander(Obs_Monopolize_Cmd, _ObsArr, 0, 0, 0, 0,
                                        0, 0, 0, 0);
        TiandyVideo.CurObs = _ObsArr[0];
        iCurObs = TiandyVideo.CurObs;

        if (ChannelInfo[16]['iCurObsID'] != iCurObs) {
            for (var i = 0; i < 25; i++) {
                if (ChannelInfo[i]['iCurObsID'] == iCurObs) {
                    iCurObsIndex = i;
                    break;
                }
            }
        }
        if (ret == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    catch (e) {
    }
}

/*端口所有链接*/
function LogOffAll(logonID) {
    var b = TiandyVideo.Commander(Entry_ObsLst_Get, logonID, 0, 0, 0, 0, 0, 0,
                                  0, 0).split("\n");
    if (b[0] == "0") {
        for (var i = 1; i <= b.length - 1; i++) {
            var arrResult = TiandyVideo.Commander(Obs_Prop_Get, parseInt(b[i]),
                                                  0, 0, 0, 0, 0, 0, 0, 0)
                .split('\n')[1].split(splitCode);

            for (var j = 0; j < 25; j++) {
                if (ChannelInfo[j]['iCurObsID'] == b[i]) {
                    ChannelInfo[j]['bShowBitRate'] = false;
                    ChannelInfo[j]['bAuto'] = false;
                    if (iCurObs == b[i]) {
                        lyRecorder.style.visibility = "visible";
                        lyRecorderMD.style.visibility = "hidden";
                        lyAudio.style.visibility = "visible";
                        lyAudioMD.style.visibility = "hidden";
                    }
                    TiandyVideo.Commander(Obs_EndRec_Cmd, parseInt(b[i]), 0, 0,
                                          0, 0, 0, 0, 0, 0);
                    ChannelInfo[j]['bRec'] = false;
                    TiandyVideo.Commander(Obs_SpeakerEnable_Set, parseInt(b[i]),
                                          0, 0, 0, 0, 0, 0, 0, 0);
                    ChannelInfo[j]['bAudio'] = false;
                    TiandyVideo.Commander(Obs_Stop_Cmd, parseInt(b[i]), 0, 0, 0,
                                          0, 0, 0, 0, 0);
                }
            }
        }
    }
}

function ChannelToObsID(iChannelNO) {
    var ObsIDlist = TiandyVideo.Commander(Entry_ObsLst_Get, iCurLogon, 0, 0, 0,
                                          0, 0, 0, 0, 0).split("\n");//对应logonID的obsID
    for (i = 1; i < ObsIDlist.length; i++) {
        listObsId = parseInt(ObsIDlist[i]);
        arrResult =
            TiandyVideo.Commander(Obs_Prop_Get, listObsId, 0, 0, 0, 0, 0, 0, 0,
                                  0).split('\n')[1].split(splitCode);
        iChannelno = parseInt(arrResult[2]);
        if (iChannelno == iChannelNO) {
            return listObsId;
        }
    }
    if (i >= ObsIDlist.length) {
        return false;
    }
}

//显示参数配置页面
function ShowConfig() {
    TiandyVideo.Commander(Entry_Config_Get, iCurObs, 0, 0, 0, 0, 0, 0, 0, 0);

}

//end add


