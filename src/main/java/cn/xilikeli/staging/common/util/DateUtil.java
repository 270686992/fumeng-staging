package cn.xilikeli.staging.common.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * 日期工具类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/28 - 22:09
 * @since JDK1.8
 */
@Slf4j
public class DateUtil {

    /**
     * 判断指定的过期时间 expiredTime 当前是否已经过期
     *
     * @param expiredTime 过期时间
     * @return 过期返回 true, 没有过期返回 false
     */
    public static boolean isOutOfDate(Date expiredTime) {
        if (expiredTime == null) {
            return true;
        }

        long now = Calendar.getInstance().getTimeInMillis();
        long expiredTimeStamp = expiredTime.getTime();
        return now > expiredTimeStamp;
    }

    /**
     * 根据起始时间和时间间隔计算结束时间, 可指定时间间隔的单位
     *
     * @param startTime    起始时间
     * @param timeInterval 时间间隔,
     *                     如传 3 然后单位假如传天的话, 就是计算出起始时间 3 天后的时间点,
     *                     传 -3 就是计算出起始时间 3 天前的时间点
     * @param unit         超时时限的单位, 如: Calendar.SECOND, Calendar.HOUR
     * @return 返回计算出的结束时间
     */
    public static Date calculateEndDate(Date startTime, int timeInterval, int unit) {
        if (startTime == null) {
            startTime = new Date();
        }

        Date endTime;
        Calendar start = Calendar.getInstance();
        start.setTime(startTime);
        start.add(unit, timeInterval);
        endTime = start.getTime();
        return endTime;
    }

    /**
     * 将 Date 类型的日期转换为字符串形式的日期
     *
     * @param date Date 类型的日期
     * @return 返回转换之后的字符串形式的日期
     */
    public static String transformDateToStr(Date date) {
        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 将字符串形式的日期转换为 Date 类型的日期
     *
     * @param dateStr 字符串形式的日期, 传入格式: yyyy-MM-dd HH:mm:ss
     * @return 转换通过返回转换之后的 Date 类型的日期, 转换不通过返回 null
     */
    public static Date transformStrToDate(String dateStr) {
        if (dateStr == null) {
            dateStr = "";
        }

        Date date;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            // 设置 lenient 为 false, 否则 SimpleDateFormat 会比较宽松地验证日期
            // 比如 2007/02/29 会被接受, 并转换成 2007/03/01
            sdf.setLenient(false);
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            log.error("DateUtil ===> 将字符串形式的日期时间转换为 Date 类型的日期时间时出现异常: ", e);
            return null;
        }

        return date;
    }

}