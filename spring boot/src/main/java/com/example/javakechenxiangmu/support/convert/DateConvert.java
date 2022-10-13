package com.example.javakechenxiangmu.support.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component//生成一个bean
public class DateConvert implements Converter<String, Date> {//寻找一个converter接口。
    private String datePattern = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Date convert(String s) {//入参是string返回是date
        SimpleDateFormat sf = new SimpleDateFormat(datePattern);

        try {
            Date date = sf.parse(s);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("无效的格式，请用： " + datePattern);
        }
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
