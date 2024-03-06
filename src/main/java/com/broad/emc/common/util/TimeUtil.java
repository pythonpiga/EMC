package com.broad.emc.common.util;

import java.time.LocalDate;
import java.util.Date;

/**
 * 自定义 日期计算类工具
 */
public class TimeUtil {

    /**
     * 计算两个日期间相差月数
     * @param time1
     * @param time2
     * @return
     */
    public static int calculateMonthDifference(LocalDate time1, LocalDate time2) {
        // 获取时间1的年份和月份
        int year1 = time1.getYear();
        int month1 = time1.getMonthValue();

        // 获取时间2的年份和月份
        int year2 = time2.getYear();
        int month2 = time2.getMonthValue();

        // 计算时间1的总月数
        int totalMonths1 = year1 * 12 + month1;

        // 计算时间2的总月数
        int totalMonths2 = year2 * 12 + month2;

        // 计算总月数差
        int monthsDiff = totalMonths2 - totalMonths1;

        // 获取时间1的日
        int day1 = time1.getDayOfMonth();

        // 获取时间2的日
        int day2 = time2.getDayOfMonth();

        // 计算天数差
        int daysDiff = day2 - day1;

        // 如果天数差为负数，表示时间2在时间1之前，需要从月数差中减去1
        if (daysDiff < 0) {
            monthsDiff--;
        }

        return monthsDiff;
    }

    
}

