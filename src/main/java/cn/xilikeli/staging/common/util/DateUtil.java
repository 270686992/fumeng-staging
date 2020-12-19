package cn.xilikeli.staging.common.util;

import lombok.extern.slf4j.Slf4j;

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
     * 判断一个过期时间当前是否已经过期
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
     * 计算七牛云上传 token 有效期截止时间
     *
     * @param now              当前时间
     * @param upTokenTimeLimit 七牛云上传 token 有效期, 单位: 秒
     * @return 返回七牛云上传 token 有效期截止时间
     */
    public static Calendar calculateQiNiuUpTokenExpiryDate(Calendar now, Integer upTokenTimeLimit) {
        // 计算七牛云上传 token 有效期结束时间
        now.add(Calendar.SECOND, upTokenTimeLimit);
        return now;
    }

    /**
     * 将 Date 类型的日期转换为字符串形式的日期
     *
     * @param date Date 类型的日期
     * @return 返回转换之后的字符串形式的日期
     */
    public static String transformDateToStr(Date date) {
        // 定义日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 将时间转化为类似 2020-09-28 22:15:30 格式的字符串返回
        return sdf.format(date);
    }

    /**
     * 将字符串形式的日期转换为 Date 类型的日期
     *
     * @param date 字符串形式的日期
     * @return 返回转换之后的 Date 类型的日期
     */
    public static Date transformStrToDate(String date) {
        // 定义日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 转换为 Date 类型的日期返回
        Date result;

        try {
            sdf.setLenient(false);
            result = sdf.parse(date);
        } catch (Exception e) {
            log.error("DateUtil ===> 将字符串形式的日期时间转换为 Date 类型的日期时间时出现异常: ", e);
            return null;
        }

        return result;
    }
}