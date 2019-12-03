package com.alibaba.concurrent.util;

import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author shenmeng
 * @Date 2019/12/3
 **/

public class DateTimeUtil {
    public static final String WEEK_PREFIX="W";
    public static final int DAY_IN_WEEK=7;

    public DateTimeUtil() {
    }

    public static int getWeekOfYear(LocalDate localDate){
        Date date = localDate2Date(localDate);
        Calendar cal=getCustomCalendar(date,2);
        int week_of_year=cal.get(3);
        return week_of_year;
    }

    public static LocalDate getFirstDayOfWeek(LocalDate localDate){
        Date date=localDate2Date(localDate);
        Calendar cal=getCustomCalendar(date,2);
        int mondayPlus=cal.getFirstDayOfWeek()-cal.get(DAY_IN_WEEK);
        if(mondayPlus>0){
            mondayPlus-=7;
        }
        cal.add(5,mondayPlus);
        return date2LocalDate(cal.getTime());
    }

    public static LocalDate date2LocalDate(Date date) {
        return date==null?null: ZonedDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        return date==null?null: ZonedDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return localDateTime==null?null:Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date localDate2Date(LocalDate localDate) {
        return localDate==null?null:Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private static Calendar getCustomCalendar(Date date, int dayOfWeek) {
        Calendar cal =Calendar.getInstance();
        cal.setFirstDayOfWeek(dayOfWeek);
        cal.setMinimalDaysInFirstWeek(DAY_IN_WEEK);
        cal.setTime(date);
        return cal;
    }

    public static boolean isFirstDayOfWeek(LocalDate localDate){
        LocalDate monday = getFirstDayOfWeek(localDate);
        return localDate.compareTo(monday)==0;
    }

    public static LocalDateTime timestamp2LocalDateTime(Long timestamp){
        return timestamp==null?null:LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp/1000),ZoneId.systemDefault());
    }
}
