package ru.geekbrains.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {
    public static long getDurationInMinutes(Date from, Date to){
        long dif = to.getTime() - from.getTime();
        return TimeUnit.MILLISECONDS.toMinutes(dif);
    }
}
