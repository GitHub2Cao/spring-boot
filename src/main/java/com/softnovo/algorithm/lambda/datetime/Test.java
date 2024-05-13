package com.softnovo.algorithm.lambda.datetime;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date);

        LocalTime now = LocalTime.now();
        System.out.println(now);

        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now1);

        LocalDate date1 = LocalDate.of(2020, 10, 10);
        LocalDate date2 = LocalDate.of(2021, 11, 20);
        Period between = Period.between(date2, date1);
        System.out.println(between);

        LocalTime time1 = LocalTime.of(10, 20, 30);
        LocalTime time2 = LocalTime.of(13, 41, 31);
        Duration duration = Duration.between(time1, time2);
        System.out.println(duration);

        Instant instant = Instant.now();
        System.out.println(instant);

//        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
//        System.out.println(zoneId);

        LocalDateTime dateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, zoneId);
        System.out.println(zonedDateTime);

        LocalDate localDate = LocalDate.now();
        LocalDate firstDayOfMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayOfMonth);

        LocalDate firstDayOfYear = localDate.with(TemporalAdjusters.firstDayOfYear());
        System.out.println(firstDayOfYear);

        LocalDate lastDayOfYear = localDate.with(TemporalAdjusters.lastDayOfYear());
        System.out.println(lastDayOfYear);

        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(availableZoneIds);

        ZoneId zoneIdShanghai = ZoneId.of("Asia/Shanghai");
        System.out.println("上海的时区规则：" + zoneIdShanghai.getRules());

        ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.now(), zoneIdShanghai);
        System.out.println("北京时间：" + zdt);
        System.out.println("年份：" + zdt.getYear());
        System.out.println("月份：" + zdt.getMonthValue());
        System.out.println("日期：" + zdt.getDayOfMonth());
        System.out.println("小时数：" + zdt.getHour());
        System.out.println("分钟数：" + zdt.getMinute());
        System.out.println("秒数：" + zdt.getSecond());

        ZonedDateTime zdt2 = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("调整时区后的UTC时间：" + zdt2);

        ZonedDateTime zdt3 = zdt.plus(Duration.ofHours(5));
        System.out.println("加上5小时后的时间：" + zdt3);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(zdt3.format(formatter1));
        System.out.println(zdt2.format(formatter1));

        System.out.println(ZoneId.systemDefault());

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        System.out.println("===sdf1=== " + sdf1.toPattern());
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
//        SimpleDateFormat sdf2 = new SimpleDateFormat(dtf1.toString());
//        System.out.println("DateTimeFormatter转换为SimpleDateFormat：" + sdf2.toString());
    }
}
