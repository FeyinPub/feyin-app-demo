package net.feyin.app.demo.util;

import java.util.Date;

public class DateTimeCheckUtil {

    /**
     * 传入日期与当前系统的时间差
     * @param time
     * @return 当前时间-传入时间，单位毫秒
     */
    public static long dateTimeDifferenceToNow(long time) {
        return new Date().getTime() - time;
    }

    /**
     * 传入时间是否已经超时一分钟
     * @param time
     * @return
     */
    public static boolean isExpireOneMinute(long time) {
        return dateTimeDifferenceToNow(time) > 60*1000;
    }

    /**
     * 传入日期与当前系统的时间差
     * @param date
     * @return 当前时间-传入时间，单位毫秒
     */
    public static long dateTimeDifferenceToNow(Date date) {
        return new Date().getTime() - date.getTime();
    }

    /**
     * 传入时间是否已经超时一分钟
     * @param date
     * @return
     */
    public static boolean isExpireOneMinute(Date date) {
        return dateTimeDifferenceToNow(date) > 60*1000;
    }
}
