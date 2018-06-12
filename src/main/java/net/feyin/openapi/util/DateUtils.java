package net.feyin.openapi.util;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public abstract class DateUtils {
    public static long SEC_MILLIS = 1000L;
    public static long MIN_MILLIS = 60000L;
    public static long HOUR_MILLIS = 3600000L;
    public static long DAY_MILLIS = 86400000L;
    public static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static String DATE_PATTERN = "yyyy-MM-dd";
    public static String DATE_PATTERN_CN = "yyyy年MM月dd日";
    public static String DATETIME_PATTERN_CN = "yyyy年MM月dd日 HH:mm:ss";
    public static TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone("Asia/Shanghai");
    private static String YYYY = "yyyy";
    private static String MM = "MM";
    private static String DD = "dd";
    private static String YYYY_MM_DD = "yyyy-MM-dd";
    private static String YYYYMMDD = "yyyyMMdd";
    private static String YYYY_MM = "yyyy-MM";
    private static String HH_MM_SS = "HH:mm:ss";
    private static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public DateUtils() {
    }

    public static synchronized Date getCurrDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static String getCurrDateStr() {
        return format(getCurrDate(), "yyyy-MM-dd");
    }

    public static String getCurrMonthStr() {
        return format(getCurrDate(), "yyyy-MM");
    }

    public static String getCurrMonthFirstStr() {
        return format(getCurrDate(), "yyyy-MM") + "-01";
    }

    /**
     * 当前时间1号0点
     * @return
     */
    public static Date getCurrMonthFirst() {
        return parseDate(format(getCurrDate(), "yyyy-MM") + "-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrDateStr2() {
        return format(getCurrDate(), "yyyyMMdd");
    }

    public static String getCurrTimeStr() {
        return format(getCurrDate(), "HH:mm:ss");
    }

    public static String getCurrDateTimeStr() {
        return format(getCurrDate(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getYear() {
        return format(getCurrDate(), "yyyy");
    }

    public static String getMonth() {
        return format(getCurrDate(), "MM");
    }

    public static String getDay() {
        return format(getCurrDate(), "dd");
    }

    public static boolean isDate(String strDate, String pattern) {
        return parseDate(strDate, pattern) != null;
    }

    public static boolean isYYYY(String strDate) {
        return parseDate(strDate, "yyyy") != null;
    }

    public static boolean isYYYY_MM(String strDate) {
        return parseDate(strDate, "yyyy-MM") != null;
    }

    public static boolean isYYYY_MM_DD(String strDate) {
        return parseDate(strDate, "yyyy-MM-dd") != null;
    }

    public static boolean isYYYY_MM_DD_HH_MM_SS(String strDate) {
        return parseDate(strDate, "yyyy-MM-dd HH:mm:ss") != null;
    }

    public static boolean isHH_MM_SS(String strDate) {
        return parseDate(strDate, "HH:mm:ss") != null;
    }

    public static String getNextDate(String refenceDate, int intevalDays) {
        try {
            return getNextDate(parseDate(refenceDate, "yyyy-MM-dd"), intevalDays);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getNextDate(Date refenceDate, int intevalDays) {
        try {
            Calendar ee = Calendar.getInstance();
            ee.setTime(refenceDate);
            ee.set(Calendar.DATE, ee.get(Calendar.DATE) + intevalDays);
            return format(ee.getTime(), "yyyy-MM-dd");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 多少天之后
     * @param refenceDate
     * @param intevalDays
     * @return
     */
    public static Date getNextDateFromData(Date refenceDate, int intevalDays) {
        try {
            Calendar ee = Calendar.getInstance();
            ee.setTime(refenceDate);
            ee.set(Calendar.DATE, ee.get(Calendar.DATE) + intevalDays);
            return ee.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    public static long getIntevalDays(String startDate, String endDate) {
        try {
            return getIntevalDays(parseDate(startDate, "yyyy-MM-dd"), parseDate(endDate, "yyyy-MM-dd"));
        } catch (Exception e) {
            return 0L;
        }
    }

    public static long getIntevalDays(Date startDate, Date endDate) {
        try {
            Calendar ee = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            ee.setTime(startDate);
            endCalendar.setTime(endDate);
            long diff = endCalendar.getTimeInMillis() - ee.getTimeInMillis();
            return diff / 86400000L;
        } catch (Exception e) {
            return 0L;
        }
    }

    public static long getTodayIntevalDays(String startDate) {
        try {
            Date ee = new Date();
            Date theDate = parseDate(startDate);
            return (ee.getTime() - theDate.getTime()) / 86400000L;
        } catch (Exception e) {
            return 0L;
        }
    }

    public static String getLastDayOfMonth(String year, String month) throws ParseException {
        String LastDay = "";
        Calendar cal = Calendar.getInstance();
        Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse(year + "-" + month + "-14");
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DATE, value);
        Date date_ = cal.getTime();
        LastDay = (new SimpleDateFormat("yyyy-MM-dd")).format(date_);
        return LastDay;
    }

    public static boolean isValid(int year, int month, int day) {
        if (month > 0 && month < 13 && day > 0 && day < 32) {
            int mon = month - 1;
            GregorianCalendar calendar = new GregorianCalendar(year, mon, day);
            return calendar.get(Calendar.YEAR) == year && calendar.get(Calendar.MONTH) == mon && calendar.get(Calendar.DATE) == day;
        }

        return false;
    }

    private static Calendar convert(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    //n年后
    public static Date yearOffset(Date date, int offset) {
        return offsetDate(date, 1, offset);
    }

    //n月后
    public static Date monthOffset(Date date, int offset) {
        return offsetDate(date, 2, offset);
    }

    //n天后
    public static Date dayOffset(Date date, int offset) {
        return offsetDate(date, 5, offset);
    }

    public static Date offsetDate(Date date, int field, int offset) {
        Calendar calendar = convert(date);
        calendar.add(field, offset);
        return calendar.getTime();
    }

    public static Date secondOffset(Date date, int offset) {
        return offsetDate(date, Calendar.SECOND, offset);
    }

    public static Date minuteOffset(Date date, int offset) {
        return offsetDate(date, Calendar.MINUTE, offset);
    }

    public static Date hourOffset(Date date, int offset) {
        return offsetDate(date, Calendar.HOUR_OF_DAY, offset);
    }

    /**
     * 该月1号的日期
     * @param date
     * @return
     */
    public static Date firstDay(Date date) {
        Calendar calendar = convert(date);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 该月最后一天的日期
     * @param date
     * @return
     */
    public static Date lastDay(Date date) {
        Calendar calendar = convert(date);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    public static boolean isSameDay(Calendar startDate, Calendar endDate) {
        if (startDate == null && endDate == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            return (startDate != null && endDate != null) && (startDate.get(Calendar.ERA) == endDate.get(Calendar.ERA)
                    && startDate.get(Calendar.YEAR) == endDate.get(Calendar.YEAR)
                    && startDate.get(Calendar.DAY_OF_YEAR) == endDate.get(Calendar.DAY_OF_YEAR));
        }
    }

    public static boolean isInMonth(Date startDate, Date endDate, int months) {
        if (startDate == null && endDate == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else if (startDate != null && endDate != null) {
            Date tmpDate = org.apache.commons.lang.time.DateUtils.addMonths(startDate, months);
            return getIntevalDays(tmpDate, endDate) >= 0L;
        } else {
            return false;
        }
    }

    public static boolean isSameDay(Date date1, Date date2) {
        return ((date1 == null || date2 != null) && (date1 != null || date2 == null))
                && org.apache.commons.lang.time.DateUtils.isSameDay(date1, date2);
    }

    public static Date parseDate(String date) {
        return parseDate(date, "yyyy-MM-dd");
    }

    public static Date parseDatetime(String datetime) {
        return parseDate(datetime, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date parseDate(String date, String pattern) {
        if (StringUtils.isBlank(date)) {
            return null;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

            try {
                return dateFormat.parse(date);
            } catch (ParseException var4) {
                var4.printStackTrace();
                return null;
            }
        }
    }

    public static long getHours(Date date) {
        return getHours(date, DEFAULT_TIME_ZONE);
    }

    public static long getHours(Date date, TimeZone timeZone) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
            calendar.setTime(date);
            return (long) calendar.get(Calendar.HOUR_OF_DAY);
        }
    }

    public static long getMinutes(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return (long) calendar.get(Calendar.MINUTE);
        }
    }

    public static String formatDate(Date date) {
        return date == null ? null : format(date, "yyyy-MM-dd");
    }

    public static String formatDatetime(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date getDayBegin(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 当天开始时间
     * @param date
     * @return
     */
    public static Date getStartTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 当天结束时间
     * @param date
     * @return
     */
    public static Date getEndTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 今天0点的时间
     * @return
     */
    public static Date today() {
        return getStartTime(new Date());
    }

    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.format(date);
        }
    }

    public static String format(Calendar calendar) {
        return format(calendar, "yyyy-MM-dd HH:mm:ss");
    }

    public static String format(Calendar calendar, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * n个月后的最后一天一秒
     */
    public static Date monthOffsetEndTime(Date date, int month) {
        return getEndTime(lastDay(monthOffset(date, month)));
    }
}
