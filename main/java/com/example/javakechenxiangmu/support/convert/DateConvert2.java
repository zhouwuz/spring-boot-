package com.example.javakechenxiangmu.support.convert;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

public class DateConvert2 implements Converter<String, Date> {
    private String datePattern = "yyyy-MM-dd HH:mm:ss";
    @Override
    public Date convert(String s) {
        Long time = Long.parseLong(s);
        Date date = new Date();
        date.setTime(time);
        return date;

    }

    //    @Override
//    public Date convert(String s) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
//        try {
//            return simpleDateFormat.parse(s);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            throw new IllegalArgumentException("无效的格式，请用： " + datePattern);
//        }
//
//    }
}
