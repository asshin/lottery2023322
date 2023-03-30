package com.cqupt.wang.domain.support.ids.policy;

import com.cqupt.wang.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Random;

/**
 * @author zsw
 * @create 2023-03-30 11:15
 */
@Component
public class ShortCode implements IIdGenerator {
    @Override
    public synchronized Long nextId() {
        Calendar calendar=Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(year-2022);
        stringBuilder.append(String.format("%02d",week));
        stringBuilder.append(String.format("%03d",day));
        stringBuilder.append(hour);
        stringBuilder.append(String.format("%03d",new Random().nextInt(1000)));
        return Long.parseLong(stringBuilder.toString());
    }
}
