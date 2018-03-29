package com.dsw.iot.util;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    protected static final Logger logger = Logger.getLogger(DateUtil.class);

    public static final String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_1 = "yyyyMMddHHmmss";
    public static final String FORMAT_2 = "yyyy-MM-dd";
    public static final String FORMAT_3 = "yyyy/MM/dd";

    public static Long formatTime(String dateStr) {
        SimpleDateFormat sf = new SimpleDateFormat(FORMAT_1);
        Date date = null;
        try {
            date = sf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static Long formatTime(String dateStr, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static Date formatDate(String dateStr) {
        SimpleDateFormat sf = new SimpleDateFormat(FORMAT_FULL);
        Date date = null;
        try {
            date = sf.parse(dateStr);
        } catch (ParseException e) {
            logger.error("时间转换失败!", e);
        }
        return date;
    }

    public static String formatFull(String dateStr) {
        Date date = new Date();
        date.setTime(formatTime(dateStr));
        SimpleDateFormat sf = new SimpleDateFormat(FORMAT_FULL);
        return sf.format(date);
    }

    public static String getToday() {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMAT_2);
//        LocalDateTime localDateTime = LocalDateTime.now();
//        String today = dateTimeFormatter.format(localDateTime);
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat(FORMAT_2);
        String today = sf.format(date);
        return today;
    }

    public static String getDate() {
//		DateTimeFormatter dateTimeFormatter = DateTimeFormatter
//				.ofPattern(FORMAT_3);
//		LocalDateTime localDateTime = LocalDateTime.now();
//		String date = dateTimeFormatter.format(localDateTime);
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat(FORMAT_3);
        return sf.format(date);
    }

    /**
     * 获取当前是今天的第几周（周一为每周第一天）
     *
     * @return yyyyuu 201803
     */
    public static String getWeekOfYear() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置周日为一周的第一天
        cal.setTime(date);
        int num = cal.get(Calendar.WEEK_OF_YEAR);// 获取日期所属周数
        int year = cal.get(cal.YEAR);// 获取年
        return (year + "" + String.format("%02d", num));
    }

    /**
     * 获取当前月
     *
     * @return yyyymm 201803
     */
    public static String getYearMonth() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return (cal.get(cal.YEAR) + "" + String.format("%02d", cal.get(cal.MONTH) + 1));
    }

    /**
     * 获取当前年
     *
     * @return yyyy 2018
     */
    public static String getYear() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return (cal.get(cal.YEAR) + "");
    }
}
