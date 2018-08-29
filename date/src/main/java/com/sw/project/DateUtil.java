package com.sw.project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期操作类
 */
public class DateUtil {

    public static DateTimeFormatter formatter(String pattern){
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter;
    }

    /**
     * 获取当前时间
     * @param pattern 时间格式
     * @return
     */
    public static String currentDateString(String pattern){

        return LocalDateTime.now().format(formatter(pattern));
    }

    public static void main(String[] args) {
        String dateNow=DateUtil.currentDateString("YYYY-MM-dd HH:mm:ss");
        System.out.println(dateNow);
    }


}
