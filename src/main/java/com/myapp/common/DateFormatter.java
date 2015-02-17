package com.myapp.common;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateFormatter {

    public static Date getValidDate(){

        TimeZone tzz = TimeZone.getTimeZone("Europe/Moscow");
        Calendar cal = Calendar.getInstance(tzz);
        cal.setTimeZone(tzz);
        GregorianCalendar gc = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
        Date mow = gc.getTime();

        return mow;
    }

    public static Calendar getValidCalendar(){

        TimeZone tzz = TimeZone.getTimeZone("Europe/Moscow");
        Calendar cal = Calendar.getInstance(tzz);
        cal.setTimeZone(tzz);
        GregorianCalendar gc = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
//        Date mow = gc.getTime();
        gc.set(GregorianCalendar.MILLISECOND, 0);
        return gc;
    }

}
