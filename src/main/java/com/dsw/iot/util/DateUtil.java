package com.dsw.iot.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    private static final String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_1 = "yyyyMMddHHmmss";
    private static final String FORMAT_2 = "yyyy-MM-dd";

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

    public static String formatFull(String dateStr) {
        Date date = new Date();
        date.setTime(formatTime(dateStr));
        SimpleDateFormat sf = new SimpleDateFormat(FORMAT_FULL);
        return sf.format(date);
    }

    public static String getToday() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMAT_2);
        LocalDateTime localDateTime = LocalDateTime.now();
        String today = dateTimeFormatter.format(localDateTime);
        return today;
    }
}
