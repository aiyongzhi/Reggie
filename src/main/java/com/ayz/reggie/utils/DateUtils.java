package com.ayz.reggie.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final SimpleDateFormat dateTimeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
    private DateUtils(){
    }
    public static String formatDateTime(Date date){
        return dateTimeFormat.format(date);
    }
    public static String formatDate(Date date){
        return dateFormat.format(date);
    }
    public static String formatTime(Date date){
        return timeFormat.format(date);
    }
}
