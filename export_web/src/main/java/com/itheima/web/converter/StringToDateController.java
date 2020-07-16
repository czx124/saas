package com.itheima.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class StringToDateController implements Converter<String,Date> {
    @Override
    public Date convert(String source) {
        if(source.isEmpty()){
            return null;
        }

        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(source);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
