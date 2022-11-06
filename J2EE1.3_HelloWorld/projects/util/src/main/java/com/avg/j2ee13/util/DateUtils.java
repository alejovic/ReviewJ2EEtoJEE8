package com.avg.j2ee13.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final String[] formats = {"yyyy-MM-dd HH:mm:ss.S"};

    private DateUtils() {
    }

    public static Date toDate(String s, Locale locale) throws ParseException {
        return toDate(s, new SimpleDateFormat(formats[0], locale));
    }

    public static Date toDate(String s, SimpleDateFormat sdf) throws ParseException {
        Date date;
        sdf.setLenient(false);
        sdf.applyPattern(formats[0]);
        date = sdf.parse(s);
        return date;
    }

    public static String getDateTime(Date date, Locale locale) {
        String result = "";

        if ((date != null) && (locale != null)) {
            DateFormat formatter;
            formatter = new SimpleDateFormat(formats[0], locale);
            result = formatter.format(date);
        }

        return result;
    }
}
