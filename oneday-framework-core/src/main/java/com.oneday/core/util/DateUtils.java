/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.oneday.core.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    public static String getYear(Date date) {
        return formatDate(date, "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    public static String getMonth(Date date) {
        return formatDate(date, "MM");
    }


    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    public static String getDay(Date date) {
        return formatDate(date, "dd");
    }


    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }


    /**
     * 转换Date类型为字符串类型
     *
     * @param value
     * @return
     */
    public static String getSimpleDate(Date value) {
        return getSimpleDate(value, "yyyy-MM-dd HH:mm:ss");
    }
    public static String getSimpleDate2(Date value) {
        return getSimpleDate(value, "yyyy-MM-dd");
    }

    /**
     * 转换Date类型为字符串类型
     *
     * @param value
     * @return
     */
    public static String getSimpleDate(Date value, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(value);
    }

    /**
     * 获取几月后的时间
     *
     * @param d
     * @param m
     * @return
     */
    public static Date nextMonth(Date d, int m) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.MONTH, now.get(Calendar.MONTH) + m);
        return now.getTime();
    }

    /**
     * 获取几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date nextDate(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 获取几小时后的时间
     *
     * @param d
     * @param hour
     * @return
     */
    public static Date nextHour(Date d, int hour) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY) + hour);
        return now.getTime();
    }

    /**
     * 获取几分钟后的时间
     *
     * @param d
     * @param minute
     * @return
     */
    public static Date nextMinute(Date d, int minute) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + minute);
        return now.getTime();
    }

    /**
     * 获取两个日期的间隔天数
     *
     * @param startDay
     * @param endDay
     * @return
     */
    public static int dayInterval(Date startDay, Date endDay) {
        return (int) ((endDay.getTime() - startDay.getTime()) / (24 * 60 * 60 * 1000));
    }

    public static String timeInterval(Date startDay, Date endDay) {
        long intervalSecond = (endDay.getTime() - startDay.getTime()) / 1000;
        long hour = intervalSecond / 3600;
        long minute = (intervalSecond - hour * 3600) / 60;
        long second = intervalSecond - hour * 3600 - minute * 60;
        String str = "";
        if (hour != 0) {
            str += hour + "小时";
        }
        if (minute != 0) {
            str += minute + "分";
        }
        str += second + "秒";
        return str;
    }

    /**
     * 转换String类型为Date类型
     *
     * @param value
     * @return
     * @throws ParseException
     */
    public static Date getSimpleDate(String value) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.parse(value);
    }

    /**
     * 转换String类型为Date类型
     *
     * @param value
     * @return
     * @throws ParseException
     */
    public static Date getSimpleDateBy(String value, String pattern)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.parse(value);
    }

    /**
     * 转换String类型为Date类型
     *
     * @param value
     * @return
     * @throws ParseException
     */
    public static Date getSimpleDate2(String value) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(value);
    }

    /**
     * 把时分秒置为0
     *
     * @param date
     * @return
     */
    public static Date getDateOnly(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }

    /**
     * 首字母大写
     *
     * @param name
     * @return
     */
    public static String getUpperName(String name) {
        byte[] items = name.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    /**
     * 判断字符串是否合法(不含有非法字符或中文) 若数组中其中一字符串含有非法字符，返回false，反之，返回true
     *
     * @param sarray
     * @return
     */
    public static boolean judgeIllegalChar(String[] sarray) {
        boolean result = true;
        if (sarray != null) {
            Pattern pattern = Pattern.compile("^\\w+$");
            for (int i = 0; i < sarray.length; i++) {
                if (!pattern.matcher(sarray[i]).matches()) {
                    result = false;
                    break;
                }
            }
        } else {
            result = false;
        }
        return result;
    }

    /**
     * 从指定的时间截取年月日。将时分秒毫秒都设置为0
     *
     * @param source 原始时间
     * @return 将时分秒毫秒都设置为0的日期
     */
    public static Date trimDate(Date source) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(source);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当月第一天
     *
     * @return
     */
    public static Date monthFirstDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当月最后一天
     *
     * @return
     */
    public static Date monthLastDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取上一个月的第一题哪
     * @return
     */
    public static Date lastMonthFirstDate() {
        return preMonthFirstDate(1);
    }

    /**
     * 获取之前某一个月的第一天
     *
     * @param pre
     * @return
     */
    public static Date preMonthFirstDate(int pre) {
        Validate.isTrue(pre >= 0);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -pre);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取之前上一个月的最后一天
     *
     * @return
     */
    public static Date lastMonthLastDate() {
        return preMonthLastDate(1);
    }

    /**
     * 获取之前某一个月的最后一天
     *
     * @param pre
     * @return
     */
    public static Date preMonthLastDate(int pre) {
        Validate.isTrue(pre >= 0);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -pre);
        calendar.set(Calendar.DAY_OF_MONTH,  calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天的时间点
     *
     * @param time
     * @return
     */
    public static Date ExactTime(int time) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, time);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    public static Date getSimpleDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    /**
     * 获取最近七天，包括今天,从前往后
     * @return
     */
    public static List<Date> list7Day() {
        Date date = getSimpleDate();
        List<Date> list = Lists.newArrayListWithCapacity(7);
        for (int i = 6; i >=0; i--) {
            list.add(addDays(date, -i));
        }
        return list;
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        System.out.println(ExactTime(6));
        System.out.println(trimDate(new Date()));
        System.out.println(monthFirstDate());
        System.out.println(monthLastDate());
        System.out.println(getSimpleDate2("2018-12-15"));

    }
}
