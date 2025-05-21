package net.lesscoding.unified.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Stone.Cai
 * 2025年5月21日17:07:36
 * 添加
 * 时间处理工具类
 */
public class TimeUtils {


    // 获取当前时间
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    // 格式化时间
    public static String format(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    // 加时间（支持 days, hours, minutes, seconds）
    public static LocalDateTime add(LocalDateTime dateTime, long amount, ChronoUnit unit) {
        return dateTime.plus(amount, unit);
    }

    // 时间差（返回 long 值）
    public static long diff(LocalDateTime d1, LocalDateTime d2, ChronoUnit unit) {
        return unit.between(d2, d1);
    }

    // 获取本月最后一天
    public static LocalDate getLastDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(date.lengthOfMonth());
    }

    // 时间戳转 LocalDateTime
    public static LocalDateTime fromTimestamp(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    // LocalDateTime 转时间戳
    public static long toTimestamp(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    // 相对时间（如：3天前）
    public static String formatRelativeTime(LocalDateTime dateTime) {
        long seconds = ChronoUnit.SECONDS.between(dateTime, LocalDateTime.now());
        if (seconds < 60) return "刚刚";
        if (seconds < 3600) return seconds / 60 + " 分钟前";
        if (seconds < 86400) return seconds / 3600 + " 小时前";
        if (seconds < 2592000) return seconds / 86400 + " 天前";
        return format(dateTime, "yyyy-MM-dd");
    }


}
