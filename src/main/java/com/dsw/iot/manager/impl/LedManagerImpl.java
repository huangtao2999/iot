package com.dsw.iot.manager.impl;

import com.dsw.iot.manager.LedManager;
import com.listenvision.led;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * LED管理服务类
 *
 * @author huangt
 * @create 2018-02-28 9:19
 **/
@Service
public class LedManagerImpl implements LedManager {
    private static final Logger logger = Logger.getLogger(LedManagerImpl.class);

    /**
     * <code>设置LED基本信息</code>
     *
     * @param IpStr     屏的IP
     * @param ColorType 屏的颜色 1.单色  2.双基色  3.七彩  4.全彩
     * @param LedWidth  屏的宽度点数
     * @param LedHeight 屏的高度点数
     * @return 0成功, 非0失败
     */
    @Override
    public int setBasicInfo(String IpStr, int ColorType, int LedWidth, int LedHeight) {
        return led.SetBasicInfo(IpStr, ColorType, LedWidth, LedHeight);
    }

    /**
     * <code>校时</code>
     *
     * @param IpStr 屏的IP
     * @return 0成功, 非0失败
     */
    @Override
    public int adjustTime(String IpStr) {
        return led.AdjustTime(IpStr);
    }

    /**
     * <code>设置亮度</code>
     *
     * @param IpStr           屏的IP
     * @param BrightnessValue 亮度值 0~15
     * @return 0成功, 非0失败
     */
    @Override
    public int setBrightness(String IpStr, int BrightnessValue) {
        return led.SetBrightness(IpStr, BrightnessValue);
    }

    /**
     * <code>创建节目对象</code>
     *
     * @param IpStr            屏的IP
     * @param LedWidth         屏的宽度
     * @param LedHeight        屏的高度
     * @param ColorType        屏的颜色 1.单色  2.双基色  3.七彩  4.全彩
     * @param ProgramNo        节目号
     * @param ProgramTime      节目播放时长 0.节目播放时长  非0.指定播放时长
     * @param LoopCount        循环播放次数
     * @param AreaNo           区域号
     * @param l                区域左上角横坐标
     * @param t                区域左上角纵坐标
     * @param w                区域宽度
     * @param h                区域高度
     * @param IsForegroundArea 是否为背景区域，0.前景区（默认） 1.背景区
     * @param AddType          添加的类型  0.为字符串  1.文件（只支持txt和rtf文件）
     * @param AddStr           AddType为0则为字符串数据,AddType为1则为文件路径
     * @param FontName         字体名
     * @param FontSize         字体大小
     * @param FontColor        字体颜色   0xff 红色  0xff00 绿色  0xffff黄色
     * @param FontBold         是否加粗 0不加粗 1加粗
     * @param FontItalic       是否是斜体  0 不斜 1斜
     * @param FontUnderline    是否下划线 0不加下划线 1加下划线
     * @param InStyle          入场特技
     * @param nSpeed           特技速度
     * @param DelayTime        停留时间
     * @param TextType         文本类型   0 单行文本,非0多行文本
     * @param nAlignment       左右居中对齐方式
     * @param IsVCenter        是否垂直居中
     * @return 0成功, 非0失败
     */
    @Override
    public int createProgram(String IpStr, int LedWidth, int LedHeight, int ColorType, int ProgramNo, int ProgramTime, int LoopCount, int AreaNo, int l, int t, int w, int h, int IsForegroundArea, int AddType, String AddStr, String FontName, int FontSize, int FontColor, int FontBold, int FontItalic, int FontUnderline, int InStyle, int nSpeed, int DelayTime, int TextType, int nAlignment, int IsVCenter) {
        int hProgram = led.CreateProgram(LedWidth, LedHeight, ColorType);
        led.AddProgram(hProgram, ProgramNo, ProgramTime, LoopCount);
        led.AddImageTextArea(hProgram, ProgramNo, AreaNo, l, t, w, h, IsForegroundArea);
        if (TextType == 0) {
            led.AddSinglelineTextToImageTextArea(hProgram, ProgramNo, AreaNo, AddType, AddStr, FontName, FontSize, FontColor, FontBold, FontItalic, FontUnderline, InStyle, nSpeed, DelayTime);
        } else {
            led.AddMultiLineTextToImageTextArea(hProgram, ProgramNo, AreaNo, AddType, AddStr, FontName, FontSize, FontColor, FontBold, FontItalic, FontUnderline, InStyle, nSpeed, DelayTime, nAlignment, IsVCenter);
        }
        int i = led.NetWorkSend(IpStr, hProgram);
        led.DeleteProgram(hProgram);
        return i;
    }

    /**
     * 在led显示内容
     *
     * @param ip
     * @param title
     * @param content
     */
    @Override
    public void showContent(String ip, String title, String content) {
        int hProgram = 0;
        int result = 0;
        try {
            hProgram = led.CreateProgram(128, 32, 2);
            led.AddProgram(hProgram, 1, 0, 1);
            led.AddImageTextArea(hProgram, 1, 1, 0, 0, 80, 16, 0);
            led.AddStaticTextToImageTextArea(hProgram, 1, 1, 0, title, "宋体", 9, 0xff, 0, 0, 0, 0, 2, 0);
            led.AddDigitalClockArea(hProgram, 1, 2, 80, 0, 48, 16, "宋体", 9, 0xff, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0xff, 0, 0xff, 2, 0xff);
            led.AddImageTextArea(hProgram, 1, 3, 0, 16, 128, 16, 0);
            led.AddMultiLineTextToImageTextArea(hProgram, 1, 3, 0, content, "宋体", 9, 0xff, 0, 0, 0, 6, 4, 2, 3, 0);//倒数第五个数字为2是左移（有停留）
            led.NetWorkSend(ip, hProgram);
        } catch (Exception e) {
            logger.info("LED显示异常!", e);
        } finally {
            //销毁对象 避免内存泄漏
            if (hProgram != 0) {
                led.DeleteProgram(hProgram);
            }
        }
        logger.info(String.format("%s,%s,%s ,LED展示结果:%s", ip, title, content, result));
    }

}
